package org.alexcawl.sculptor.common.di

import kotlin.reflect.KClass

public interface DiComponent : Dependencies, AutoCloseable {
    public val declarations: List<Declaration<*>>

    public fun <T : Any> get(key: KClass<T>): T

    public fun <T : Any> getAll(key: KClass<T>): List<T>

    public fun addDeclarations(declarations: List<Declaration<*>>, autoCloseable: Boolean)

    public fun addModule(module: Module)

    public fun addModules(vararg modules: Module)

    public fun child(): DiComponent

    public fun materializeAll()

    public companion object {
        public operator fun invoke(): DiComponent = DiComponentImpl()
    }
}

public inline fun <reified T : Any> DiComponent.get(): T = get(T::class)

public inline fun <reified T : Any> DiComponent.getAll(): List<T> = getAll(T::class)

private class DiComponentImpl : DiComponent {
    private val _declarations: MutableList<Declaration<*>> = mutableListOf()

    override val declarations: List<Declaration<*>>
        get() = _declarations.toList()

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> get(key: KClass<T>): T {
        val declaration: Declaration<T> = _declarations.find { it.key == key } as? Declaration<T>
            ?: error("$key is not registered in DI tree.")
        return with(declaration) { materialize() }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> getAll(key: KClass<T>): List<T> {
        val declarations: List<Declaration<T>> = _declarations
            .filter { it.key == key }
            .mapNotNull { it as? Declaration<T> }
        return declarations.map { declaration: Declaration<T> ->
            with(declaration) { materialize() }
        }
    }

    override fun addDeclarations(declarations: List<Declaration<*>>, autoCloseable: Boolean) {
        declarations.forEach { declaration: Declaration<*> ->
            _declarations.add(
                element = delegateDeclaration(
                    source = declaration,
                    autoCloseable = autoCloseable
                )
            )
        }
    }

    override fun addModule(module: Module) {
        with(module) {
            materialize()
        }
    }

    override fun addModules(vararg modules: Module) {
        modules.forEach { module: Module ->
            addModule(module)
        }
    }

    override fun child(): DiComponent {
        return DiComponent().apply {
            this@DiComponentImpl._declarations.forEach { declaration: Declaration<*> ->
                _declarations.add(element = declaration.clone())
            }
        }
    }

    override fun materializeAll() {
        synchronized(lock = _declarations) {
            _declarations.forEach { declaration: Declaration<*> ->
                with(declaration) { materialize() }
            }
        }
    }

    override fun <V : Any> singleton(key: KClass<V>, factory: DiComponent.() -> V) {
        synchronized(lock = _declarations) {
            val declaration: Declaration<V> = singletonDeclaration(key, factory)
            _declarations.add(declaration)
        }
    }

    override fun <V : Any> scoped(key: KClass<V>, factory: DiComponent.() -> V) {
        synchronized(lock = _declarations) {
            val declaration: Declaration<V> = scopedDeclaration(key, factory)
            _declarations.add(declaration)
        }
    }

    override fun <V : Any> factory(key: KClass<V>, factory: DiComponent.() -> V) {
        synchronized(lock = _declarations) {
            val declaration: Declaration<V> = factoryDeclaration(key, factory)
            _declarations.add(declaration)
        }
    }

    override fun close() {
        synchronized(lock = _declarations) {
            _declarations.forEach { declaration: Declaration<*> ->
                declaration.close()
            }
        }
    }
}
