package org.alexcawl.sculptor.common.di

import kotlin.reflect.KClass

public interface Declaration<T> : AutoCloseable {
    public val key: KClass<*>

    public fun clone(): Declaration<T>

    public fun DiComponent.materialize(): T
}

internal fun <T : Any> scopedDeclaration(
    key: KClass<T>,
    factory: DiComponent.() -> T,
): Declaration<T> {
    return object : Declaration<T> {
        private var value: T? = null
        override val key: KClass<*> = key

        override fun DiComponent.materialize(): T {
            return value ?: factory().also { value = it }
        }

        override fun close() {
            (value as? AutoCloseable)?.close()
        }

        override fun clone(): Declaration<T> {
            return scopedDeclaration(key, factory)
        }
    }
}

internal fun <T : Any> singletonDeclaration(
    key: KClass<T>,
    factory: DiComponent.() -> T,
): Declaration<T> = object : Declaration<T> {
    private var instance: T? = null
    override val key: KClass<*> = key

    override fun close() {
        (instance as? AutoCloseable)?.close()
    }

    override fun clone(): Declaration<T> = this

    override fun DiComponent.materialize(): T {
        return instance ?: factory().also { instance = it }
    }
}

internal fun <T : Any> factoryDeclaration(
    key: KClass<T>,
    factory: DiComponent.() -> T,
): Declaration<T> = object : Declaration<T> {
    private var value: T? = null
    override val key: KClass<*> = key

    override fun close() {
        (value as? AutoCloseable)?.close()
    }

    override fun clone(): Declaration<T> = factoryDeclaration(key, factory)

    override fun DiComponent.materialize(): T {
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
