package com.jb4j.wheel.collections.modifiable;

import com.jb4j.wheel.collections.ConstraintViolated;
import com.jb4j.wheel.collections.Iterator2;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public class SimpleBag<E> implements Bag<E> {

  @Override
  public @Range(from = 0, to = Integer.MAX_VALUE) int size() {
    return 0;
  }

  @Override
  public @NotNull Iterator2<E> iterator() {
    return null;
  }

  @Override
  public @NotNull Optional<ConstraintViolated> add(@NotNull E e) {
    return Optional.empty();
  }
}
