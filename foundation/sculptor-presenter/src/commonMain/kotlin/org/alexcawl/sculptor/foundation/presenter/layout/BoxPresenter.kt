package org.alexcawl.sculptor.foundation.presenter.layout

import org.alexcawl.sculptor.common.layout.UiState
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.common.presenter.StatePresenter
import org.alexcawl.sculptor.common.presenter.map
import org.alexcawl.sculptor.foundation.contract.layout.BoxState
import org.alexcawl.sculptor.foundation.layout.BoxUiState
import kotlin.reflect.KClass

public class BoxPresenter : StatePresenter<BoxState>() {
    override val input: KClass<BoxState> = BoxState::class

    public override suspend fun PresenterScope.transform(input: BoxState): UiState {
        return with(input) {
            BoxUiState(
                contentAlignment = map(contentAlignment),
                propagateMinConstraints = propagateMinConstraints,
                content = layout(content),
            )
        }
    }
}
