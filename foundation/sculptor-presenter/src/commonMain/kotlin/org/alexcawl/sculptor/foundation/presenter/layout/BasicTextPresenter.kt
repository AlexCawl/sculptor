package org.alexcawl.sculptor.foundation.presenter.layout

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.StringValueContract
import org.alexcawl.sculptor.common.presenter.LayoutPresenter
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.foundation.contract.layout.BasicTextLayoutContract
import org.alexcawl.sculptor.foundation.contract.layout.BasicTextStateContract
import org.alexcawl.sculptor.foundation.layout.BasicTextLayout
import kotlin.reflect.KClass

public class BasicTextPresenter : LayoutPresenter<BasicTextLayoutContract, BasicTextStateContract>() {
    override val input: KClass<BasicTextLayoutContract> = BasicTextLayoutContract::class

    override fun PresenterScope.transform(
        id: String,
        modifier: Modifier,
        state: BasicTextStateContract
    ): BasicTextLayout {
        return with(state) {
            BasicTextLayout(
                id = id,
                modifier = modifier,
                text = when (val textType = textType) {
                    is BasicTextStateContract.TextType.Dynamic -> getValue<StringValueContract>(
                        identifier = textType.textIdentifier
                    ).value

                    is BasicTextStateContract.TextType.Static -> textType.text
                },
                softWrap = softWrap,
                maxLines = maxLines,
                minLines = minLines,
            )
        }
    }
}
