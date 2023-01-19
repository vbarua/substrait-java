package io.substrait.type;

import io.substrait.function.SimpleExtension;
import java.io.IOException;

public class YamlRead {
  static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(YamlRead.class);

  public static void main(String[] args) throws Exception {
    try {
      // TODO: Read files from args to allow users to spot check extensions
      inspectDefaults();
    } catch (Exception ex) {
      throw ex;
    }
  }

  public static void inspectDefaults() throws IOException {
    for (String resourcePath : SimpleExtension.DEFAULT_EXTENSIONS) {
      SimpleExtension.ExtensionCollection extension = SimpleExtension.load(resourcePath);
      System.out.println(
          String.format(
              "Parsed %s functions in file %s",
              extension.scalarFunctions().size() + extension.aggregateFunctions().size(),
              resourcePath));
      if (!extension.scalarFunctions().isEmpty()) {
        System.out.println("  Scalar:");
        extension.scalarFunctions().stream().forEach(f -> System.out.println("    " + f.key()));
      }
      if (!extension.aggregateFunctions().isEmpty()) {
        System.out.println("  Aggregate:");
        extension.aggregateFunctions().stream().forEach(f -> System.out.println("    " + f.key()));
      }
      if (!extension.windowFunctions().isEmpty()) {
        System.out.println("  Window:");
        extension.windowFunctions().stream().forEach(f -> System.out.println("    " + f.key()));
      }
    }
  }
}
