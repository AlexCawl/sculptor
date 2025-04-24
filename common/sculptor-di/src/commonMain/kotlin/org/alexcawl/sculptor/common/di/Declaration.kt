package org.alexcawl.sculptor.common.di

import kotlin.reflect.KClass

public interface Declaration<out T> : AutoCloseable {
    public val key: KClass<*>

    public val type: KClass<*>

    public fun clone(): Declaration<T>

    public fun DiComponent.materialize(): T
}

internal fun <K : T, T : Any> singletonDeclaration(
    key: KClass<K>,
    type: KClass<T>,
    factory: DiComponent.() -> K,
): Declaration<K> = object : Declaration<K> {
    private var instance: K? = null

    override val key: KClass<*> = key

    override val type: KClass<*> = type

    override fun close() {
        (instance as? AutoCloseable)?.close()
    }

    override fun clone(): Declaration<K> = this

    override fun DiComponent.materialize(): K {
        return instance ?: factory().also { instance = it }
    }
}

internal fun <K : T, T : Any> factoryDeclaration(
    key: KClass<K>,
    type: KClass<T>,
    factory: DiComponent.() -> K,
): Declaration<K> = object : Declaration<K> {
    private var value: K? = null

    override val key: KClass<*> = key

    override val type: KClass<*> = type

    override fun close() {
        (value as? AutoCloseable)?.close()
    }

    override fun clone(): Declaration<K> = factoryDeclaration(key, type, factory)

    override fun DiComponent.materialize(): K {
        return factory().also { value = it }
    }
}

internal fun <T> delegateDeclaration(
    source: Declaration<T>,
    autoCloseable: Boolean,
): Declaration<T> = object : Declaration<T> by source {
    override fun close() {
        if (autoCloseable) {
            source.close()
        }
    }

    override fun clone(): Declaration<T> {
        return delegateDeclaration(source.clone(), autoCloseable)
    }
}
