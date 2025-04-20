package org.alexcawl.sculptor.common.di

import kotlin.reflect.KClass

public interface DiTree : AutoCloseable {
    public val diComponent: DiComponent

    public fun child(): DiTree

    public fun <T : Any> get(key: KClass<T>): T

    public fun <T : Any> getAll(key: KClass<T>): List<T>

    public companion object {
        public operator fun invoke(diComponent: DiComponent): DiTree = DiTreeImpl(diComponent)
    }
}

public inline fun <reified T : Any> DiTree.get(): T = get(T::class)

public inline fun <reified T : Any> DiTree.getAll(): List<T> = getAll(T::class)


private class DiTreeImpl(override val diComponent: DiComponent) : DiTree {
    override fun <T : Any> get(key: KClass<T>): T = diComponent.get(key)

    override fun <T : Any> getAll(key: KClass<T>): List<T> = diComponent.getAll(key)

    override fun child(): DiTree = DiTreeImpl(diComponent.child())

    override fun close(): Unit = diComponent.close()
}
