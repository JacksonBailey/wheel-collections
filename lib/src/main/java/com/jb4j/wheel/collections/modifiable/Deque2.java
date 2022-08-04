package com.jb4j.wheel.collections.modifiable;

import com.jb4j.wheel.collections.ConstraintViolated;
import java.util.Iterator;
import java.util.Optional;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Elements can also be inserted at the head and removed from the tail.
 */
public interface Deque2<E> extends Queue2<E> {

  /**
   * Adds an element to the head.
   */
  @Contract(mutates = "this")
  @NotNull Optional<ConstraintViolated> addHead(@NotNull E e);

  /**
   * Returns an optional of the element at the tail or none if empty.
   *
   * @implNote Requires {@link Iterator#remove()} to be implemented.
   */
  @Contract(mutates = "this")
  default @NotNull Optional<E> removeTail() {
    if (isEmpty()) {
      return Optional.empty();
    }
    var it = iterator();
    E curr;
    do {
      curr = it.next();
    } while (it.hasNext());
    it.remove();
    return Optional.of(curr);
  }

}
