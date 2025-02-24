package org.alexcawl.sculptor.foundation.presenter.layout

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.common.presenter.StatePresenter
import org.alexcawl.sculptor.foundation.contract.layout.BoxState
import org.alexcawl.sculptor.foundation.layout.BoxLayout
import kotlin.reflect.KClass

public class BoxPresenter : StatePresenter<BoxState>() {
    override val input: KClass<BoxState> = BoxState::class

    override fun PresenterScope.transform(id: String, modifier: Modifier, state: BoxState): Layout {
        return with(state) {
            BoxLayout(
                id = id,
                modifier = modifier,
                contentAlignment = map(contentAlignment),
                propagateMinConstraints = propagateMinConstraints,
                content = content.map(::getLayout),
            )
        }
    }
}
