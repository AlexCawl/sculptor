package org.alexcawl.sculptor.common.presenter

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.combineIds
import org.alexcawl.sculptor.common.contract.layout.LayoutContract
import org.alexcawl.sculptor.common.contract.layout.ModifierContract
import org.alexcawl.sculptor.common.contract.layout.StateContract
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import org.alexcawl.sculptor.common.layout.Layout

@Suppress(names = ["UNCHECKED_CAST", "EXTENSION_SHADOWED_BY_MEMBER"])
abstract class LayoutPresenter<Input : LayoutContract, S : StateContract, Output : Layout> : Presenter<Input, Output> {
    @InternalSculptorApi
    override fun internalTransform(scope: PresenterScope, input: Any): Output {
        val castedInput: Input = input as Input
        val selectedState: S = castedInput.states.first { it.id == castedInput.state } as S
        val modifiers: List<Modifier> = scope.listMap<ModifierContract, Modifier>(
            input = castedInput.modifiers + selectedState.modifiers
        )
        return scope.transform(
            id = combineIds(castedInput.id, selectedState.id),
            modifier = modifiers.fold(Modifier, Modifier::then),
            state = selectedState,
        )
    }

    abstract fun PresenterScope.transform(
        id: String,
        modifier: Modifier,
        state: S
    ): Output
}
