package io.substrait.plan;

import io.substrait.expression.FunctionLookup;
import io.substrait.expression.proto.ImmutableFunctionLookup;
import io.substrait.function.SimpleExtension;
import io.substrait.proto.PlanRel;
import io.substrait.relation.ProtoRelConverter;
import io.substrait.relation.Rel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProtoPlanConverter {
  static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(io.substrait.plan.ProtoPlanConverter.class);

  private final SimpleExtension.ExtensionCollection extensionCollection;

  public ProtoPlanConverter() throws IOException {
    this(SimpleExtension.loadDefaults());
  }

  public ProtoPlanConverter(SimpleExtension.ExtensionCollection extensionCollection) {
    this.extensionCollection = extensionCollection;
  }

  public Plan from(io.substrait.proto.Plan plan) {
    FunctionLookup functionLookup = ImmutableFunctionLookup.builder().from(plan).build();
    ProtoRelConverter relConverter = new ProtoRelConverter(functionLookup, extensionCollection);
    List<Plan.Root> roots = new ArrayList<>();
    for (PlanRel planRel : plan.getRelationsList()) {
      io.substrait.proto.RelRoot root = planRel.getRoot();
      Rel rel = relConverter.from(root.getInput());
      roots.add(ImmutableRoot.builder().input(rel).names(root.getNamesList()).build());
    }

    io.substrait.proto.Version v = plan.getVersion();
    Version version =
        ImmutableVersion.builder()
            .majorNumber(v.getMajorNumber())
            .minorNumber(v.getMinorNumber())
            .patchNumber(v.getPatchNumber())
            .gitHash(v.getGitHash())
            .producer(v.getProducer())
            .build();

    return ImmutablePlan.builder()
        .version(version)
        .roots(roots)
        .expectedTypeUrls(plan.getExpectedTypeUrlsList())
        .advancedExtension(
            Optional.ofNullable(plan.hasAdvancedExtensions() ? plan.getAdvancedExtensions() : null))
        .build();
  }
}
