package org.alexcawl.sculptor.foundation.presenter

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.StringValueContract
import org.alexcawl.sculptor.common.presenter.LayoutPresenter
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.foundation.contract.layout.BasicTextLayoutContract
import org.alexcawl.sculptor.foundation.contract.layout.BasicTextStateContract
import org.alexcawl.sculptor.foundation.layout.BasicTextLayout
import kotlin.reflect.KClass

class BasicTextPresenter : LayoutPresenter<BasicTextLayoutContract, BasicTextStateContract>() {
    override val input: KClass<BasicTextLayoutContract> = BasicTextLayoutContract::class

    override fun PresenterScope.transform(
        id: String,
        modifier: Modifier,
        state: BasicTextStateContract
    ): BasicTextLayout {
        return with(state) {
            when (this) {
                is BasicTextStateContract.Dynamic -> {
                    BasicTextLayout(
                        id = id,
                        modifier = modifier,
                        text = getValue<StringValueContract>(textIdentifier).value,
                        softWrap = softWrap,
                        maxLines = maxLines,
                        minLines = minLines,
                    )
                }
                is BasicTextStateContract.Static -> {
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
    }
}
