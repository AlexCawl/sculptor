package org.alexcawl.sculptor.internal.di

import kotlin.reflect.KClass

public interface Dependencies {
    public fun <K : T, T : Any> singleton(key: KClass<K>, type: KClass<T>, factory: DiComponent.() -> K)
    public fun <K : T, T : Any> factory(key: KClass<K>, type: KClass<T>, factory: DiComponent.() -> K)
    public fun module(module: Module)
}

public inline fun <reified K : Any> Dependencies.singleton(noinline factory: DiComponent.() -> K) {
    singleton(key = K::class, type = K::class, factory = factory)
}

public inline fun <reified K : T, reified T : Any> Dependencies.singleton(type: KClass<T>, noinline factory: DiComponent.() -> K) {
    singleton(key = K::class, type = type, factory = factory)
}

public inline fun <reified K : Any> Dependencies.factory(noinline factory: DiComponent.() -> K) {
    factory(key = K::class, type = K::class, factory = factory)
}

public inline fun <reified K : T, reified T : Any> Dependencies.factory(type: KClass<T>, noinline factory: DiComponent.() -> K) {
    factory(key = K::class, type = type, factory = factory)
}
