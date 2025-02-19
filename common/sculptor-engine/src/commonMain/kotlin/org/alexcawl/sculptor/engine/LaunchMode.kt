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
    public data class Data(val string: String) : LaunchMode

    /**
     * TODO: docs
     */
    @Immutable
    public data class Domain(val scaffold: Scaffold) : LaunchMode

    /**
     * TODO: docs
     */
    @Immutable
    public data class Ui(val layout: Layout) : LaunchMode
}

/**
 * TODO: docs
 */
public fun String.asLaunchMode(): LaunchMode = LaunchMode.Data(string = this)

/**
 * TODO: docs
 */
public fun Scaffold.asLaunchMode(): LaunchMode = LaunchMode.Domain(scaffold = this)

/**
 * TODO: docs
 */
public fun Layout.asLaunchMode(): LaunchMode = LaunchMode.Ui(layout = this)
