package com.jb4j.wheel.collections.modifiable;

import com.jb4j.wheel.collections.ConstraintViolated;
import com.jb4j.wheel.collections.Iterator2;
import java.util.Iterator;
import java.util.Optional;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Introduces the concept of head and tail. Elements are inserted at the tail and removed from the
 * head.
 */
public interface Queue2<E> extends Bag<E> {

  /**
   * Iterates from head to tail.
   */
  @Override
  @Contract(pure = true)
  @NotNull Iterator2<E> iterator();

  /**
   * Adds an element to the tail.
   */
  @Override
  @Contract(mutates = "this")
  @NotNull Optional<ConstraintViolated> add(@NotNull E e);

  @Contract(mutates = "this")
  default @NotNull Optional<ConstraintViolated> addTail(@NotNull E e) {
    return add(e);
  }

  /**
   * Returns an optional of the element at the head or none if empty.
   *
   * @implNote Requires {@link Iterator#remove()} to be implemented.
   */
  @Contract(mutates = "this")
  default @NotNull Optional<E> remove() {
    var it = iterator();
    var head = it.maybeNext();
    if (head.isPresent()) {
      it.remove();
    }
    return head;
  }

  @Contract(mutates = "this")
  default @NotNull Optional<E> removeHead() {
    return remove();
  }
}
