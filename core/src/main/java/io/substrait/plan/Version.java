package io.substrait.plan;

import javax.annotation.Nullable;
import org.immutables.value.Value;

@Value.Immutable
public abstract class Version {
  static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Version.class);

  public abstract Integer getMajorNumber();

  public abstract Integer getMinorNumber();

  public abstract Integer getPatchNumber();

  @Nullable
  public abstract String getGitHash();

  public abstract String getProducer();
}
