package io.substrait.relation;

import io.substrait.expression.FunctionLookup;
import io.substrait.function.SimpleExtension;
import io.substrait.io.substrait.extension.AdvancedExtension;
import java.io.IOException;

public class ProtoRelConverter
    extends ProtoRelConverterBase<ProtoRelConverter.EmptyAdvancedExtension> {
  static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ProtoRelConverter.class);

  public ProtoRelConverter(FunctionLookup lookup) throws IOException {
    this(lookup, SimpleExtension.loadDefaults());
  }

  public ProtoRelConverter(FunctionLookup lookup, SimpleExtension.ExtensionCollection extensions) {
    super(lookup, extensions);
  }

  @Override
  protected EmptyAdvancedExtension advancedExtension(io.substrait.proto.AdvancedExtension ae) {
    return new EmptyAdvancedExtension();
  }

  public static class EmptyAdvancedExtension implements AdvancedExtension {
    @Override
    public io.substrait.proto.AdvancedExtension toProto() {
      return io.substrait.proto.AdvancedExtension.newBuilder().build();
    }
  }
}
