package org.alexcawl.sculptor.foundation.presenter.layout

import org.alexcawl.sculptor.common.layout.UiState
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.common.presenter.StatePresenter
import org.alexcawl.sculptor.common.presenter.map
import org.alexcawl.sculptor.foundation.contract.layout.ColumnState
import org.alexcawl.sculptor.foundation.layout.ColumnUiState
import kotlin.reflect.KClass

public class ColumnPresenter : StatePresenter<ColumnState>() {
    override val input: KClass<ColumnState> = ColumnState::class

    public override suspend fun PresenterScope.transform(input: ColumnState): UiState {
        return with(input) {
            ColumnUiState(
                verticalArrangement = map(verticalArrangement),
                horizontalAlignment = map(horizontalAlignment),
                content = layout(content),
            )
        }
    }
}
