package org.alexcawl.skulptor.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.fastFilter
import org.alexcawl.skulptor.core.factory.BaseLayout
import kotlin.properties.Delegates

private val defaultScope: Skulptor.State by Delegates.notNull()
private val scopeProvider: ProvidableCompositionLocal<Skulptor.State> =
    compositionLocalOf { defaultScope }

object Skulptor {
    val scope: Any
        @Composable
        get() = scopeProvider.current.scope

    val dispatch: Dispatch
        @Composable
        get() = scopeProvider.current.dispatch

    @Composable
    operator fun invoke(
        rootLayoutId: String,
        state: State,
        modifier: Modifier = Modifier,
    ) = CompositionLocalProvider(value = scopeProvider provides state) {
        val layout = getLayout(rootLayoutId)
        layout.factory.build(
            layoutId = layout.id,
            stateProvider = { getState(rootLayoutId) },
            modifiers = layout.modifiers,
            initial = modifier
        )
    }

    @Composable
    fun getLayout(id: String): BaseLayout<out BaseState> {
        val schema = scopeProvider.current.schema
        return schema.layouts.fastFilter { it.id == id }.first()
    }

    @Composable
    fun getState(id: String): BaseState {
        val schema = scopeProvider.current.schema
        return schema.states.fastFilter { it.id == id }.first()
    }

    @Composable
    fun withScope(scope: Any, content: @Composable () -> Unit) =
        CompositionLocalProvider(
            value = scopeProvider provides scopeProvider.current.copy(scope = scope),
            content = content
        )

    data class State(
        val schema: SkulptorSchema,
        val dispatch: Dispatch,
        val scope: Any = Any(),
    )
}
