package org.alexcawl.sculptor.common.builder

@SculptorBuilder
public interface Builder<T> {
    public fun build(): T
}
