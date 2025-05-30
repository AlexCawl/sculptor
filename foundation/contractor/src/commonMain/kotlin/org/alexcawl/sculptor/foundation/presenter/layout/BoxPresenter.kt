package org.alexcawl.sculptor.foundation.presenter.layout

import org.alexcawl.sculptor.core.layout.UiState
import org.alexcawl.sculptor.core.presenter.PresenterScope
import org.alexcawl.sculptor.core.presenter.StatePresenter
import org.alexcawl.sculptor.core.presenter.map
import org.alexcawl.sculptor.foundation.contract.layout.BoxState
import org.alexcawl.sculptor.foundation.layout.BoxUiState
import kotlin.reflect.KClass

public class BoxPresenter : StatePresenter<BoxState>() {
    override val input: KClass<BoxState> = BoxState::class

    public override suspend fun PresenterScope.dslTransform(input: BoxState): UiState {
        return with(input) {
            BoxUiState(
                contentAlignment = map(contentAlignment),
                propagateMinConstraints = propagateMinConstraints,
                content = layouts(content),
            )
        }
    }
}
