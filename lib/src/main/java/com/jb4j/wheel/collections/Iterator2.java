package com.jb4j.wheel.collections;

import java.util.Iterator;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public interface Iterator2<E> extends Iterator<@NotNull E> {

  default @NotNull Optional<@NotNull E> maybeNext() {
    if (hasNext()) {
      return Optional.of(next());
    }
    return Optional.empty();
  }

  default void clear() {
    while (hasNext()) {
      next();
      remove();
    }
  }

}
