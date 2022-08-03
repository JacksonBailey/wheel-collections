package com.jb4j.wheel.collections;

import org.jetbrains.annotations.NotNull;

public interface Iterable2<T> extends Iterable<T> {

  @Override @NotNull Iterator2<@NotNull T> iterator();

}
