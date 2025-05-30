package org.alexcawl.sculptor.foundation.contractor.presenter.layout

import org.alexcawl.sculptor.core.contractor.layout.UiState
import org.alexcawl.sculptor.core.contractor.presenter.PresenterScope
import org.alexcawl.sculptor.core.contractor.presenter.StatePresenter
import org.alexcawl.sculptor.core.contractor.presenter.map
import org.alexcawl.sculptor.foundation.contract.state.ColumnState
import org.alexcawl.sculptor.foundation.contractor.state.ColumnUiState
import kotlin.reflect.KClass

internal class ColumnPresenter : StatePresenter<ColumnState>() {
    override val input: KClass<ColumnState> = ColumnState::class

    override suspend fun PresenterScope.dslTransform(input: ColumnState): UiState {
        return with(input) {
            ColumnUiState(
                verticalArrangement = map(verticalArrangement),
                horizontalAlignment = map(horizontalAlignment),
                content = layouts(content),
            )
        }
    }
}
