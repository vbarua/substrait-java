package io.substrait.relation;

import org.immutables.value.Value;

@Value.Immutable
public abstract class ExtensionSingle extends SingleInputRel {
  @Override
  public <O, E extends Exception> O accept(RelVisitor<O, E> visitor) throws E {
    return visitor.visit(this);
  }
}
