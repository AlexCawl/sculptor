package org.alexcawl.sculptor.internal.di.impl

import org.alexcawl.sculptor.internal.di.Declaration
import org.alexcawl.sculptor.internal.di.DiComponent
import org.alexcawl.sculptor.internal.di.Module
import org.alexcawl.sculptor.internal.di.delegateDeclaration
import org.alexcawl.sculptor.internal.di.factoryDeclaration
import org.alexcawl.sculptor.internal.di.singletonDeclaration
import kotlin.reflect.KClass

internal class DiComponentImpl : DiComponent {
    private val lock: Any = Any()
    private val holder: MutableMap<KClass<*>, Declaration<*>> = mutableMapOf()

    override val declarations: List<Declaration<*>>
        get() = holder.values.toList()

    override fun <K : Any> get(key: KClass<K>): K =
        getOrNull(key) ?: error("$key is not registered in DI tree.")

    @Suppress("UNCHECKED_CAST")
    override fun <K : Any> getOrNull(key: KClass<K>): K? = synchronized(lock = lock) {
        when (val declaration: Declaration<K>? = holder[key] as? Declaration<K>) {
            null -> null
            else -> with(declaration) { materialize() }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> getAll(type: KClass<out T>): List<T> = synchronized(lock = lock) {
        val values = holder.filterValues { it.type == type }
            .map { (_: KClass<*>, declaration: Declaration<*>) ->
                with(declaration) {
                    materialize()
                }
            }
        values.mapNotNull { it as? T }
    }

    override fun addDeclaration(declaration: Declaration<*>, autoCloseable: Boolean): Unit =
        synchronized(lock = lock) {
            holder[declaration.key] = delegateDeclaration(
                source = declaration,
                autoCloseable = autoCloseable,
            )
        }

    override fun addDeclarations(declarations: List<Declaration<*>>, autoCloseable: Boolean) {
        declarations.forEach { declaration: Declaration<*> ->
            addDeclaration(declaration, autoCloseable)
        }
    }

    override fun addModule(module: Module): Unit = synchronized(lock = lock) {
        with(module) {
            install()
        }
    }

    override fun addModules(vararg modules: Module) {
        modules.forEach { module: Module ->
            addModule(module)
        }
    }

    override fun clone(): DiComponent = synchronized(lock = lock) {
        DiComponent().apply {
            this@DiComponentImpl.holder.entries.forEach { (key, declaration) ->
                holder[key] = declaration.clone()
            }
        }
    }

    override fun <K : T, T : Any> singleton(
        key: KClass<K>,
        type: KClass<T>,
        factory: DiComponent.() -> K
    ): Unit = synchronized(lock = lock) {
        val declaration: Declaration<T> = singletonDeclaration(
            key = key,
            type = type,
            factory = factory,
        )
        holder[key] = declaration
    }

    override fun <K : T, T : Any> factory(
        key: KClass<K>,
        type: KClass<T>,
        factory: DiComponent.() -> K
    ): Unit = synchronized(lock = lock) {
        val declaration: Declaration<T> = factoryDeclaration(
            key = key,
            type = type,
            factory = factory,
        )
        holder[key] = declaration
    }

    override fun module(module: Module) {
        addModule(module)
    }

    override fun close(): Unit = synchronized(lock) {
        holder.forEach { (_, declaration) ->
            declaration.close()
        }
    }
}
