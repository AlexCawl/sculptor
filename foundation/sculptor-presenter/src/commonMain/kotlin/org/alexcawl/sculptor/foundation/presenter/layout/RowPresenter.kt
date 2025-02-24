package org.alexcawl.sculptor.foundation.presenter.layout

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.common.presenter.StatePresenter
import org.alexcawl.sculptor.foundation.contract.layout.RowState
import org.alexcawl.sculptor.foundation.layout.RowLayout
import kotlin.reflect.KClass

public class RowPresenter : StatePresenter<RowState>() {
    override val input: KClass<RowState> = RowState::class

    override fun PresenterScope.transform(id: String, modifier: Modifier, state: RowState): Layout {
        return with(state) {
            RowLayout(
                id = id,
                modifier = modifier,
                horizontalArrangement = map(horizontalArrangement),
                verticalAlignment = map(verticalAlignment),
                content = content.map(::getLayout),
            )
        }
    }
}
