package com.jb4j.wheel.collections.java;

import java.util.Iterator;

public interface JavaCollection<E> extends Iterable<E> {

  // Sizing
  int size();
  default boolean isEmpty() {
    return size() == 0;
  }

  // Peeking
  boolean contains(Object e);
  default boolean containsAll(JavaCollection<?> c) {
    for (Object e: c) {
      if (!contains(e)) {
        return false;
      }
    }
    return true;
  }
  Iterator<E> iterator(); // Technically shrinking kind of, Iterator can remove

  // Growing
  boolean add(E e);
  boolean addAll(JavaCollection<? extends E> c);

  // Shrinking
  boolean remove(Object e);
  default boolean removeAll(JavaCollection<?> c) {
    boolean modified = false;
    for (Object e: c) {
      if (remove(e)) {
        modified = true;
      }
    }
    return modified;
  }
  boolean retainAll(JavaCollection<?> c);
  void clear();

  // Transforming
  Object[] toArray();
  <T> T[] toArray(T[] a);




}
