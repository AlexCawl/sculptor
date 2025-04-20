package org.alexcawl.sculptor.engine.di.impl

import org.alexcawl.sculptor.common.di.DiComponent
import org.alexcawl.sculptor.common.di.OverridableBuilder
import org.alexcawl.sculptor.engine.di.api.SculptorBuilder

public class SculptorBuilderImpl : SculptorBuilder, OverridableBuilder {
    private val diComponent: DiComponent = DiComponent()

    override fun override(override: DiComponent.() -> Unit) {
        override(diComponent)
    }

    public fun build(): Unit = TODO()
}
