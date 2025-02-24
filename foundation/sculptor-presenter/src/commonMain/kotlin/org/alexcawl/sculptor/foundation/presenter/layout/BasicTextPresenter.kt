package org.alexcawl.sculptor.foundation.presenter.layout

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.common.presenter.StatePresenter
import org.alexcawl.sculptor.foundation.contract.layout.BasicTextState
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
                text = text,
                softWrap = softWrap,
                maxLines = maxLines,
                minLines = minLines,
            )
        }
    }
}
