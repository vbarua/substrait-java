package io.substrait.relation;

import io.substrait.expression.FunctionLookup;
import io.substrait.function.SimpleExtension;
import io.substrait.proto.ExtensionSingleRel;
import io.substrait.proto.ReadRel;
import java.io.IOException;

public class ProtoRelConverter extends ProtoRelConverterBase<Void, Void, Void> {
  static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ProtoRelConverter.class);

  public ProtoRelConverter(FunctionLookup lookup) throws IOException {
    this(lookup, SimpleExtension.loadDefaults());
  }

  public ProtoRelConverter(FunctionLookup lookup, SimpleExtension.ExtensionCollection extensions) {
    super(lookup, extensions);
  }

  @Override
  protected Void optimization(io.substrait.proto.AdvancedExtension ae) {
    // Optimization are safe to ignore
    return null;
  }

  @Override
  protected Void enhancement(io.substrait.proto.AdvancedExtension ae) {
    // Enhancements CANNOT be ignored by consumers.
    // TODO: Custom exception type
    throw new RuntimeException("Advanced Extension contains enhancement which must be handled");
  }

  @Override
  protected Void extensibleScanDetail(ReadRel.ExtensionTable et) {
    return null;
  }

  @Override
  protected ExtensionSingle newExtensionSingle(ExtensionSingleRel esr) {
    // TODO: Use a custom exception type
    throw new RuntimeException(
        "Cannot convert ExtensionSingleRel. Extend and override ProtoRelConverter w/ custom logic");
  }
}
