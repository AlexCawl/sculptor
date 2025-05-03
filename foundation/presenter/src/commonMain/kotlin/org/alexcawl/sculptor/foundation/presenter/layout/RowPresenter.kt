package org.alexcawl.sculptor.foundation.presenter.layout

import org.alexcawl.sculptor.core.layout.UiState
import org.alexcawl.sculptor.core.presenter.PresenterScope
import org.alexcawl.sculptor.core.presenter.StatePresenter
import org.alexcawl.sculptor.core.presenter.map
import org.alexcawl.sculptor.foundation.contract.layout.RowState
import org.alexcawl.sculptor.foundation.layout.RowUiState
import kotlin.reflect.KClass

public class RowPresenter : StatePresenter<RowState>() {
    override val input: KClass<RowState> = RowState::class

    public override suspend fun PresenterScope.dslTransform(input: RowState): UiState {
        return with(input) {
            RowUiState(
                horizontalArrangement = map(horizontalArrangement),
                verticalAlignment = map(verticalAlignment),
                content = layouts(content),
            )
        }
    }
}
