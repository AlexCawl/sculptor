package org.alexcawl.sculptor.foundation.presenter.layout

import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.common.presenter.StatePresenter
import org.alexcawl.sculptor.foundation.contract.layout.RowState
import org.alexcawl.sculptor.foundation.layout.RowLayout
import kotlin.reflect.KClass

public class RowPresenter : StatePresenter<RowState>() {
    override val input: KClass<RowState> = RowState::class

    override fun PresenterScope.transform(
        blockId: Identifier,
        blockModifiers: List<ModifierContract>,
        state: RowState
    ): Layout {
        return with(state) {
            RowLayout(
                id = blockId + id,
                modifier = modifierMap(input = blockModifiers + modifiers),
                horizontalArrangement = map(horizontalArrangement),
                verticalAlignment = map(verticalAlignment),
                content = content.map(::getLayout),
            )
        }
    }
}
