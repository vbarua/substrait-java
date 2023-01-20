package io.substrait.plan;

import io.substrait.proto.AdvancedExtension;
import io.substrait.relation.Rel;
import java.util.List;
import java.util.Optional;
import org.immutables.value.Value;

@Value.Immutable
public abstract class Plan {
  static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Plan.class);

  public abstract Version getVersion();

  public abstract List<Root> getRoots();

  public abstract List<String> getExpectedTypeUrls();

  public abstract Optional<AdvancedExtension> getAdvancedExtension();

  @Value.Immutable
  public abstract static class Root {
    public abstract Rel getInput();

    public abstract List<String> getNames();
  }
}
