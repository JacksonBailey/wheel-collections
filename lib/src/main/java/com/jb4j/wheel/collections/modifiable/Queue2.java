package com.jb4j.wheel.collections.modifiable;

import com.jb4j.wheel.collections.ConstraintViolated;
import com.jb4j.wheel.collections.Iterator2;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Introduces the concept of head and tail. Elements are inserted at the tail and removed from the
 * head.
 */
public interface Queue2<E> extends Bag<E> {

  /**
   * Iterates from head to tail.
   * <p>
   * TODO Consider not specifically iterating from head to tail
   */
  @Override
  @Contract(pure = true)
  @NotNull Iterator2<E> iterator();

  /**
   * Returns an optional of the element at the head or none if empty. Does not remove the element.
   */
  @Contract(pure = true)
  default @NotNull Optional<E> get() {
    return iterator().maybeNext();
  }

  @Contract(pure = true)
  default @NotNull Optional<E> getHead() {
    return get();
  }

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
   * Removes the first element (when going from head to tail) that equals the given object.
   * <p>
   * @implNote Requires {@link Iterator#remove()} to be implemented.
   */
  @Override
  @Contract(value = "null -> false", mutates = "this")
  default boolean remove(@Nullable Object e) {
    var it = iterator();
    while (it.hasNext()) {
      var curr = it.next();
      if (Objects.equals(curr, e)) {
        it.remove();
        return true;
      }
    }
    return false;
  }

  /**
   * Returns an optional of the element at the head or none if empty. Removes the element.
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
