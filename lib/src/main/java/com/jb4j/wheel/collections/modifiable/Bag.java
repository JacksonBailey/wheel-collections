package com.jb4j.wheel.collections.modifiable;

import com.jb4j.wheel.collections.ConstraintViolated;
import com.jb4j.wheel.collections.Iterable2;
import com.jb4j.wheel.collections.Iterator2;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

/**
 * Root of the modifiable Wheel Collections API.
 * <p>
 * The following are the bare minimum methods to implement for functionality.
 * <ul>
 *   <li>{@link Bag#size()}</li>
 *   <li>{@link Bag#isFull()} (if you intend to limit the capacity)</li>
 *   <li>{@link Bag#iterator()} </li>
 *   <li>{@link Bag#contains(E)}</li>
 *   <li>{@link Bag#add(E)}</li>
 *   <li>{@link Bag#remove(E)}</li>
 *   <li>Strongly encouraged: {@link Iterator#remove}</li>
 * </ul>
 * Conditions:
 * <ul>
 *   <li>Cannot contain nulls</li>
 *   <li>Possibly capacity-restricted</li>
 * </ul>
 *
 * @param <E> The type of elements in the bag
 */
public interface Bag<E> extends Iterable2<E> {

  /**
   * Returns number of elements in the bag.
   */
  @Range(from = 0, to = Integer.MAX_VALUE)
  @Contract(pure = true)
  int size();

  @Contract(pure = true)
  default boolean isEmpty() {
    return size() == 0;
  }

  /**
   * @implNote Defaults to return false always since most things are likely not capacity-restricted
   */
  @Contract(pure = true)
  default boolean isFull() {
    return false;
  }

  /**
   * Returns an iterator that iterates over all elements but in no particular order.
   */
  @Override
  @Contract(pure = true)
  @NotNull Iterator2<E> iterator();

  /**
   * Returns true if any element in this bag equals {@code o} if {@code Objects.equals(e, o)} is
   * true.
   */
  @Contract(value = "null -> false", pure = true)
  default boolean contains(@Nullable Object o) {
    for (E e : this) {
      if (Objects.equals(e, o)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Adds an element to no specific location.
   * <p>
   * Returns empty if there is no error. Otherwise, returns the constraint that was violated.
   */
  @Contract(mutates = "this")
  @NotNull Optional<ConstraintViolated> add(@NotNull E e);

  /**
   * Removes an element that equals the given object. Not necessarily from any specific location.
   * <p>
   * @implNote Requires {@link Iterator#remove()} to be implemented.
   */
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

  /*
  default Object[] toArray() {
    throw new UnsupportedOperationException();
  }

  default <T> T[] toArray(@NotNull T[] a) {
    throw new UnsupportedOperationException();
  }

  default boolean containsAll(@NotNull Bag<?> c) {
    for (Object e : c) {
      if (!contains(e)) {
        return false;
      }
    }
    return true;
  }

  default boolean addAll(@NotNull Bag<? extends E> c) {
    boolean modified = false;
    for (E e : c) {
      if (add(e)) {
        modified = true;
      }
    }
    return modified;
  }

  default boolean removeAll(@NotNull Bag<?> c) {
    boolean modified = false;
    for (Object e : c) {
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
  }*/

}
