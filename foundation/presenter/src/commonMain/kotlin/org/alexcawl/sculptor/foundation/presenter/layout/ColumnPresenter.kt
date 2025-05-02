package org.alexcawl.sculptor.foundation.presenter.layout

import org.alexcawl.sculptor.core.layout.UiState
import org.alexcawl.sculptor.core.presenter.PresenterScope
import org.alexcawl.sculptor.core.presenter.StatePresenter
import org.alexcawl.sculptor.core.presenter.map
import org.alexcawl.sculptor.foundation.contract.layout.ColumnState
import org.alexcawl.sculptor.foundation.layout.ColumnUiState
import kotlin.reflect.KClass

public class ColumnPresenter : StatePresenter<ColumnState>() {
    override val input: KClass<ColumnState> = ColumnState::class

    public override suspend fun PresenterScope.dslTransform(input: ColumnState): UiState {
        return with(input) {
            ColumnUiState(
                verticalArrangement = map(verticalArrangement),
                horizontalAlignment = map(horizontalAlignment),
                content = layouts(content),
            )
        }
    }
}
