package org.alexcawl.sculptor.common.di

public interface DiTreeBuilder {
    public fun override(override: DiComponent.() -> Unit)

    public fun build(): DiTree
}
