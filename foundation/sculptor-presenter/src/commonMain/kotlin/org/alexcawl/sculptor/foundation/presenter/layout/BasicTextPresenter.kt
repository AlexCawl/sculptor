package org.alexcawl.sculptor.foundation.presenter.layout

import androidx.compose.ui.Modifier
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
        id: String,
        modifier: Modifier,
        state: BasicTextState
    ): Layout {
        return with(state) {
            BasicTextLayout(
                id = id,
                modifier = modifier,
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
