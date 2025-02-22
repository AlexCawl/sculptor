package org.alexcawl.sculptor.foundation.presenter.layout

import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.common.presenter.StatePresenter
import org.alexcawl.sculptor.foundation.contract.layout.BasicTextState
import org.alexcawl.sculptor.foundation.contract.layout.BasicTextState.TextType.Dynamic
import org.alexcawl.sculptor.foundation.contract.layout.BasicTextState.TextType.Static
import org.alexcawl.sculptor.foundation.contract.value.StringValueContract
import org.alexcawl.sculptor.foundation.layout.BasicTextLayout
import kotlin.reflect.KClass

public class BasicTextPresenter : StatePresenter<BasicTextState>() {
    override val input: KClass<BasicTextState> = BasicTextState::class

    override fun PresenterScope.transform(
        blockId: Identifier,
        blockModifiers: List<ModifierContract>,
        state: BasicTextState
    ): Layout {
        return with(state) {
            BasicTextLayout(
                id = blockId + id,
                modifier = modifierMap(input = blockModifiers + modifiers),
                text = when (val textType = textType) {
                    is Dynamic -> getValue<StringValueContract>(textType.textIdentifier).value
                    is Static -> textType.text
                },
                softWrap = softWrap,
                maxLines = maxLines,
                minLines = minLines,
            )
        }
    }
}
