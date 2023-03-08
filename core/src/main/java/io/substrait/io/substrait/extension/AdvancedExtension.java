package io.substrait.io.substrait.extension;

import java.util.Optional;
import org.immutables.value.Value;

@Value.Immutable
public interface AdvancedExtension<OPTIMIZATION, ENHANCEMENT> {

  Optional<OPTIMIZATION> getOptimization();

  Optional<ENHANCEMENT> getEnhancment();

  io.substrait.proto.AdvancedExtension toProto();
}
