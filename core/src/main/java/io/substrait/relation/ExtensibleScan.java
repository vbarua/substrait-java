package io.substrait.relation;

import org.immutables.value.Value;

@Value.Immutable
public abstract class ExtensibleScan<Detail> extends AbstractReadRel {

  public abstract Detail getDetail();

  @Override
  public <O, E extends Exception> O accept(RelVisitor<O, E> visitor) throws E {
    return null;
  }
}
