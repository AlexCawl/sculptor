package org.alexcawl.sculptor.common.di

import kotlin.reflect.KClass

public interface Dependencies {
    public fun <V : Any> singleton(key: KClass<V>, factory: DiComponent.() -> V)
    public fun <V : Any> scoped(key: KClass<V>, factory: DiComponent.() -> V)
    public fun <V : Any> factory(key: KClass<V>, factory: DiComponent.() -> V)
}

public inline fun <reified V : Any> Dependencies.singleton(noinline factory: DiComponent.() -> V) {
    singleton(key = V::class, factory = factory)
}

public inline fun <reified V : Any> Dependencies.scoped(noinline factory: DiComponent.() -> V) {
    scoped(key = V::class, factory = factory)
}

public inline fun <reified V : Any> Dependencies.factory(noinline factory: DiComponent.() -> V) {
    factory(key = V::class, factory = factory)
}
