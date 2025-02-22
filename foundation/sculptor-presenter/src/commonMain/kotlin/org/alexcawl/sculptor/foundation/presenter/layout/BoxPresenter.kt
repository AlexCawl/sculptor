package org.alexcawl.sculptor.foundation.presenter.layout

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.common.presenter.StatePresenter
import org.alexcawl.sculptor.foundation.contract.layout.BoxState
import org.alexcawl.sculptor.foundation.layout.BoxLayout
import kotlin.reflect.KClass

public class BoxPresenter : StatePresenter<BoxState>() {
    override val input: KClass<BoxState> = BoxState::class

    override fun PresenterScope.transform(
        blockId: Identifier,
        blockModifiers: List<ModifierContract>,
        state: BoxState
    ): Layout {
        return with(state) {
            BoxLayout(
                id = blockId + id,
                modifier = modifierMap(input = blockModifiers + modifiers),
                contentAlignment = map(contentAlignment),
                propagateMinConstraints = propagateMinConstraints,
                content = content.map(::getLayout),
            )
        }
    }
}
