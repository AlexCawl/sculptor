package org.alexcawl.skulptor.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.fastFilter
import androidx.compose.ui.util.fastFold

interface Skulptor : SkulptorModifier.ModifierScope, ComponentLayout.ComponentLayoutScope, ContainerLayout.ContainerLayoutScope {
    @Composable
    operator fun invoke()

    companion object {
        fun root(schema: SkulptorSchema, rootId: String, scope: Any = Any()): Skulptor =
            SkulptorImpl(schema = schema, id = rootId, scope = scope)
    }
}

@Suppress("UNCHECKED_CAST")
private class SkulptorImpl(
    private val schema: SkulptorSchema,
    override val id: String,
    override val scope: Any
) : Skulptor {
    override fun carve(modifiers: List<SkulptorModifier>): Modifier {
        val initial: Modifier = Modifier
        return modifiers.fastFold(initial) { modifier, skulptorModifier ->
            skulptorModifier.internalChain(initial = modifier, scope = this)
        }
    }

    override fun <T : BaseState> getState(id: String): T =
        schema.states.fastFilter { it.id == id }.first() as T

    override fun <T : BaseState> getStateOrNull(id: String): T? =
        schema.states.fastFilter { it.id == id }.firstOrNull() as? T

    override fun <T : BaseLayout<*>> getLayout(id: String): T =
        schema.layouts.fastFilter { it.id == id }.first() as T

    override fun <T : BaseLayout<*>> getLayoutOrNull(id: String): T? =
        schema.layouts.fastFilter { it.id == id }.firstOrNull() as? T


    @Composable
    override fun Any.place(layout: BaseLayout<*>) {
        val skulptor = SkulptorImpl(schema = schema, id = layout.id, scope = this)
        return when (layout) {
            is ComponentLayout -> layout.internalBuild(skulptor).invoke()
            is ContainerLayout -> layout.internalBuild(skulptor).invoke()
        }
    }

    @Composable
    override fun invoke() = with(scope) {
        val layout = getLayout<BaseLayout<*>>(id)
        place(layout)
    }

    override fun dispatch(action: SkulptorAction) {
        println("$id: $action")
    }
}
