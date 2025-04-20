package org.alexcawl.sculptor.common.di

public interface OverridableBuilder {
    public fun override(override: DiComponent.() -> Unit)
}
