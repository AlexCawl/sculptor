package org.alexcawl.sculptor.engine

import androidx.compose.runtime.Immutable
import org.alexcawl.sculptor.common.contract.Schema
import org.alexcawl.sculptor.common.layout.Layout

public sealed interface LaunchMode {
    @Immutable
    public data class Data(val string: String) : LaunchMode

    @Immutable
    public data class Domain(val schema: Schema) : LaunchMode

    @Immutable
    public data class Ui(val layout: Layout) : LaunchMode
}

public fun String.asLaunchMode(): LaunchMode = LaunchMode.Data(string = this)

public fun Schema.asLaunchMode(): LaunchMode = LaunchMode.Domain(schema = this)

public fun Layout.asLaunchMode(): LaunchMode = LaunchMode.Ui(layout = this)
