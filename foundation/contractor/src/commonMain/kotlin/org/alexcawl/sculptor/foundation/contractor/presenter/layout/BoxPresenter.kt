package org.alexcawl.sculptor.foundation.contractor.presenter.layout

import org.alexcawl.sculptor.core.contractor.layout.UiState
import org.alexcawl.sculptor.core.contractor.presenter.PresenterScope
import org.alexcawl.sculptor.core.contractor.presenter.StatePresenter
import org.alexcawl.sculptor.core.contractor.presenter.map
import org.alexcawl.sculptor.foundation.contract.state.BoxState
import org.alexcawl.sculptor.foundation.contractor.state.BoxUiState
import kotlin.reflect.KClass

internal class BoxPresenter : StatePresenter<BoxState>() {
    override val input: KClass<BoxState> = BoxState::class

    override suspend fun PresenterScope.dslTransform(input: BoxState): UiState {
        return with(input) {
            BoxUiState(
                contentAlignment = map(contentAlignment),
                propagateMinConstraints = propagateMinConstraints,
                content = layouts(content),
            )
        }
    }
}
