@file:Suppress("UNCHECKED_CAST")

package org.alexcawl.sculptor.common.presenter

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.layout.LayoutContract
import org.alexcawl.sculptor.common.contract.layout.StateContract
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import org.alexcawl.sculptor.common.layout.Layout
import kotlin.reflect.KClass

abstract class LayoutPresenter<Input : LayoutContract, State : StateContract> : Presenter<Input, Layout> {
    final override val output: KClass<Layout> = Layout::class

    @InternalSculptorApi
    final override fun internalTransform(scope: PresenterScope, input: Any): Layout {
        val castedInput: Input = input as Input
        val layoutIdentifier: String = castedInput.id.identifier
        val modifier: Modifier = scope.modifierMap(castedInput.modifiers)
        val selectedState: State = castedInput.states.find { it.id == castedInput.state } as? State
            ?: error("No state found for ${castedInput.state}")
        return scope.transform(id = layoutIdentifier, modifier = modifier, state = selectedState)
    }

    abstract fun PresenterScope.transform(
        id: String,
        modifier: Modifier,
        state: State,
    ): Layout
}
