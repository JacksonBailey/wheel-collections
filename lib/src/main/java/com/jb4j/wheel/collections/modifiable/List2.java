package com.jb4j.wheel.collections.modifiable;

import com.jb4j.wheel.collections.ConstraintViolated;
import com.jb4j.wheel.collections.Iterator2;
import com.jb4j.wheel.collections.ListIterator2;
import java.util.Optional;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Elements can be inserted or removed from any point between head and tail. Also introduces an
 * index.
 */
public interface List2<E> extends Deque2<E> {

  /**
   * Iterates from head to tail.
   */
  @Override
  @Contract(pure = true)
  default @NotNull Iterator2<E> iterator() {
    return listIterator();
  }

  @Contract(pure = true)
  @NotNull ListIterator2<E> listIterator();

  int indexOf(@Nullable Object o);

  /**
   * Returns an optional of the index'th element or none if empty. Does not remove the element.
   * <p>
   * Only throws when index is negative. Otherwise, returns none.
   */
  @Contract(pure = true)
  default @NotNull Optional<E> get(int index) throws IndexOutOfBoundsException {
    if (index < 0) {
      throw new IndexOutOfBoundsException();
    }
    var it = iterator();
    var curr = it.maybeNext();
    for (int i = 1; i <= index && curr.isPresent(); i++) {
      curr = it.maybeNext();
    }
    return curr;
  }
  /**
   * Sets the element at index to e. Returns the old value there.
   */
  @Contract(mutates = "this")
  default @NotNull E set(int index, E e) throws IndexOutOfBoundsException {
    if (index < 0 || index >= size()) {
      throw new IndexOutOfBoundsException();
    }
    var it = listIterator();
    assert it.hasNext();
    var curr = it.next();
    for (int i = 0; i <= index; i++) {
      assert it.hasNext();
      curr = it.next();
    }
    it.set(e);
    return curr;
  }

  Optional<ConstraintViolated> add(int index, E element);

  Optional<E> remove(int index);

}
