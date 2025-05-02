package org.alexcawl.sculptor.internal.di.impl

import org.alexcawl.sculptor.internal.di.Dependencies
import org.alexcawl.sculptor.internal.di.DiComponent
import org.alexcawl.sculptor.internal.di.Module

internal class DiModuleImpl(private val factory: Dependencies.() -> Unit) : Module {
    override fun DiComponent.install() {
        return factory(this)
    }
}
