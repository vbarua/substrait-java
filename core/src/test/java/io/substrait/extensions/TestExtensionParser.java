package io.substrait.extensions;

import io.substrait.function.SimpleExtension;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class TestExtensionParser {

  @Test
  void defaultExtensionsAreParsable() throws IOException {
    SimpleExtension.loadDefaults();
  }
}
