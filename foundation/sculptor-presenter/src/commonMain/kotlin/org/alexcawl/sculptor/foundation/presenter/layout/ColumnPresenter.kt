package org.alexcawl.sculptor.foundation.presenter.layout

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.common.presenter.StatePresenter
import org.alexcawl.sculptor.foundation.contract.layout.ColumnState
import org.alexcawl.sculptor.foundation.layout.ColumnLayout
import kotlin.reflect.KClass

public class ColumnPresenter : StatePresenter<ColumnState>() {
    override val input: KClass<ColumnState> = ColumnState::class

    override fun PresenterScope.transform(
        blockId: Identifier,
        blockModifiers: List<ModifierContract>,
        state: ColumnState
    ): Layout {
        return with(state) {
            ColumnLayout(
                id = blockId + id,
                modifier = modifierMap(input = blockModifiers + modifiers),
                verticalArrangement = map(verticalArrangement),
                horizontalAlignment = map(horizontalAlignment),
                content = content.map(::getLayout),
            )
        }
    }
}
