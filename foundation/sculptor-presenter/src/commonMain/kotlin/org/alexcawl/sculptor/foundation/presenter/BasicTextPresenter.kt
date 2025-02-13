package org.alexcawl.sculptor.foundation.presenter

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.presenter.LayoutPresenter
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.foundation.contract.BasicTextLayoutContract
import org.alexcawl.sculptor.foundation.contract.BasicTextStateContract
import org.alexcawl.sculptor.foundation.layout.BasicTextLayout
import kotlin.reflect.KClass

class BasicTextPresenter : LayoutPresenter<BasicTextLayoutContract, BasicTextStateContract, BasicTextLayout>() {
    override val input: KClass<BasicTextLayoutContract> = BasicTextLayoutContract::class
    override val output: KClass<BasicTextLayout> = BasicTextLayout::class

    override fun PresenterScope.transform(
        id: String,
        modifier: Modifier,
        state: BasicTextStateContract
    ): BasicTextLayout {
        return with(state) {
            BasicTextLayout(
                id = id,
                modifier = modifier,
                text = text.value,
                softWrap = softWrap,
                maxLines = maxLines,
                minLines = minLines,
            )
        }
    }
}
