package org.alexcawl.sculptor.foundation.presenter.layout

import org.alexcawl.sculptor.common.layout.UiState
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.common.presenter.StatePresenter
import org.alexcawl.sculptor.common.presenter.map
import org.alexcawl.sculptor.foundation.contract.layout.RowState
import org.alexcawl.sculptor.foundation.layout.RowUiState
import kotlin.reflect.KClass

public class RowPresenter : StatePresenter<RowState>() {
    override val input: KClass<RowState> = RowState::class

    public override suspend fun PresenterScope.transform(input: RowState): UiState {
        return with(input) {
            RowUiState(
                horizontalArrangement = map(horizontalArrangement),
                verticalAlignment = map(verticalAlignment),
                content = layout(content),
            )
        }
    }
}
