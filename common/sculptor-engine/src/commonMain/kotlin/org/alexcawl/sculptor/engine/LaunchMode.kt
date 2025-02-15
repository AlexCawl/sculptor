package org.alexcawl.sculptor.engine

import org.alexcawl.sculptor.common.contract.Scaffold
import org.alexcawl.sculptor.common.layout.Layout

sealed interface LaunchMode {
    data class FromRaw(val string: String) : LaunchMode
    data class FromScaffold(val scaffold: Scaffold) : LaunchMode
    data class FromLayout(val layout: Layout) : LaunchMode
}

fun String.asLaunchMode(): LaunchMode = LaunchMode.FromRaw(string = this)

fun Scaffold.asLaunchMode(): LaunchMode = LaunchMode.FromScaffold(scaffold = this)

fun Layout.asLaunchMode(): LaunchMode = LaunchMode.FromLayout(layout = this)
