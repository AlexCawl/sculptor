package org.alexcawl.sculptor.internal.di

public interface Module {
    public fun DiComponent.install()
}

public fun module(factory: Dependencies.() -> Unit): Module = DiModuleImpl(factory)

private class DiModuleImpl(private val factory: Dependencies.() -> Unit) : Module {
    override fun DiComponent.install() {
        return factory(this)
    }
}
