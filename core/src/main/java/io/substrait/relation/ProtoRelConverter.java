package io.substrait.relation;

import io.substrait.expression.FunctionLookup;
import io.substrait.function.SimpleExtension;
import java.io.IOException;

public class ProtoRelConverter extends ProtoRelConverterBase {
  static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ProtoRelConverter.class);

  public ProtoRelConverter(FunctionLookup lookup) throws IOException {
    this(lookup, SimpleExtension.loadDefaults());
  }

  public ProtoRelConverter(FunctionLookup lookup, SimpleExtension.ExtensionCollection extensions) {
    super(lookup, extensions);
  }
}
