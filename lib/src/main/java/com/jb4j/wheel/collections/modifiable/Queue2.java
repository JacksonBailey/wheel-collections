package com.jb4j.wheel.collections.modifiable;

import java.util.Optional;
import org.jetbrains.annotations.NotNull;

/**
 * Adds concept of head. Sometimes capacity.
 */
public interface Queue2<E> extends Bag<E> {

  /**
   * Adds an element, but not specifically in any location. Throws IllegalStateException if no there
   * is no space available.
   * <p>
   * A return value of false implies there was no modification but that there was space available.
   * An example would be if this did not accept duplicate values but was not full.
   */
  @Override
  boolean add(@NotNull E e) throws IllegalStateException;

  /**
   * Adds an element, but not specifically in any location. Returns false when the {@code Queue} is
   * not modified or full.
   */
  boolean offer(@NotNull E e);

  /**
   * Retrieves, but does not remove, the head of this queue
   */
  Optional<@NotNull E> peek();

}
