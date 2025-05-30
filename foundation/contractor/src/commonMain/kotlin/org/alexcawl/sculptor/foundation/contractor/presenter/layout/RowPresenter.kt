package org.alexcawl.sculptor.foundation.contractor.presenter.layout

import org.alexcawl.sculptor.core.contractor.layout.UiState
import org.alexcawl.sculptor.core.contractor.presenter.PresenterScope
import org.alexcawl.sculptor.core.contractor.presenter.StatePresenter
import org.alexcawl.sculptor.core.contractor.presenter.map
import org.alexcawl.sculptor.foundation.contract.state.RowState
import org.alexcawl.sculptor.foundation.contractor.state.RowUiState
import kotlin.reflect.KClass

internal class RowPresenter : StatePresenter<RowState>() {
    override val input: KClass<RowState> = RowState::class

    override suspend fun PresenterScope.dslTransform(input: RowState): UiState {
        return with(input) {
            RowUiState(
                horizontalArrangement = map(horizontalArrangement),
                verticalAlignment = map(verticalAlignment),
                content = layouts(content),
            )
        }
    }
}
