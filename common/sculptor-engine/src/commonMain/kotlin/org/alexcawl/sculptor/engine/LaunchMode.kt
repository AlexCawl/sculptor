package org.alexcawl.sculptor.engine

import androidx.compose.runtime.Immutable
import org.alexcawl.sculptor.common.contract.Scaffold
import org.alexcawl.sculptor.common.layout.Layout

/**
 * TODO: docs
 */
public sealed interface LaunchMode {
    /**
     * TODO: docs
     */
    @Immutable
    public data class FromRaw(val string: String) : LaunchMode

    /**
     * TODO: docs
     */
    @Immutable
    public data class FromScaffold(val scaffold: Scaffold) : LaunchMode

    /**
     * TODO: docs
     */
    @Immutable
    public data class FromLayout(val layout: Layout) : LaunchMode
}

/**
 * TODO: docs
 */
public fun String.asLaunchMode(): LaunchMode = LaunchMode.FromRaw(string = this)

/**
 * TODO: docs
 */
public fun Scaffold.asLaunchMode(): LaunchMode = LaunchMode.FromScaffold(scaffold = this)

/**
 * TODO: docs
 */
public fun Layout.asLaunchMode(): LaunchMode = LaunchMode.FromLayout(layout = this)
