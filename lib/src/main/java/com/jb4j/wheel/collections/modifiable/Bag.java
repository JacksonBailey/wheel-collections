package com.jb4j.wheel.collections.modifiable;

import com.jb4j.wheel.collections.Iterable2;
import java.util.Objects;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Root of the modifiable Wheel Collections API.
 *
 * @param <E> The type of objects in the bag
 */
public interface Bag<E> extends Iterable2<E> {

  int size();

  default boolean isEmpty() {
    return size() == 0;
  }

  default boolean contains(@Nullable Object o) {
    for (E e: this) {
      if (Objects.equals(e, o)) {
        return true;
      }
    }
    return false;
  }

  default boolean containsAll(@NotNull Bag<?> c) {
    for (Object e: c) {
      if (!contains(e)) {
        return false;
      }
    }
    return true;
  }

  boolean add(@NotNull E e);

  default boolean addAll(@NotNull Bag<? extends E> c) {
    boolean modified = false;
    for (E e: c) {
      if (add(e)) {
        modified = true;
      }
    }
    return modified;
  }

  @Contract("null -> false")
  boolean remove(@Nullable Object e);

  default boolean removeAll(@NotNull Bag<?> c) {
    boolean modified = false;
    for (Object e: c) {
      if (remove(e)) {
        modified = true;
      }
    }
    return modified;
  }

  default boolean retainAll(@NotNull Bag<?> c) {
    boolean modified = false;
    var it = iterator();
    while (it.hasNext()) {
      var curr = it.next();
      if (!c.contains(curr)) {
        it.remove(); // Must remove through iterator specifically
        modified = true;
      }
    }
    return modified;
  }

  default void clear() {
    iterator().clear();
  }

  default Object[] toArray() {
    throw new UnsupportedOperationException();
  }

  default <T> T[] toArray(@NotNull T[] a) {
    throw new UnsupportedOperationException();
  }

}
