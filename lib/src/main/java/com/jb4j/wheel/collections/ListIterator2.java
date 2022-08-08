package com.jb4j.wheel.collections;

import java.util.ListIterator;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public interface ListIterator2<E> extends ListIterator<E>, Iterator2<E> {

  default @NotNull Optional<@NotNull E> maybePrevious() {
    if (hasPrevious()) {
      return Optional.of(previous());
    }
    return Optional.empty();
  }

}
