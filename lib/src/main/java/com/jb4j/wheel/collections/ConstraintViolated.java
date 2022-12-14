package com.jb4j.wheel.collections;

public enum ConstraintViolated {
  /**
   * When adding to a full bag
   */
  CAPACITY,
  /**
   * When adding a duplicate to a bag that does not accept duplicates
   */
  UNIQUENESS,
  /**
   * When adding somewhere logically "outside" of the bag
   * <p>
   * TODO This might be dumb
   */
  BOUNDARY
}
