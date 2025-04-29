package org.alexcawl.sculptor.internal.di

public interface DiTreeBuilder {
    public fun override(override: DiComponent.() -> Unit)

    public fun build(): DiTree
}
