package org.alexcawl.sculptor.internal.di

import org.alexcawl.sculptor.internal.di.impl.DiComponentImpl
import kotlin.reflect.KClass

public interface DiComponent : Dependencies, AutoCloseable {
    public val declarations: List<Declaration<*>>

    public fun <K : Any> get(key: KClass<K>): K

    public fun <K : Any> getOrNull(key: KClass<K>): K?

    public fun <T : Any> getAll(type: KClass<out T>): List<T>

    public fun addDeclaration(declaration: Declaration<*>, autoCloseable: Boolean)

    public fun addDeclarations(declarations: List<Declaration<*>>, autoCloseable: Boolean)

    public fun addModule(module: Module)

    public fun addModules(vararg modules: Module)

    public fun clone(): DiComponent

    public companion object {
        public operator fun invoke(): DiComponent = DiComponentImpl()
    }
}

public inline fun <reified T : Any> DiComponent.get(): T = get(T::class)

public inline fun <reified T : Any> DiComponent.getAll(): List<T> = getAll(T::class)
