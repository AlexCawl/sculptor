package org.alexcawl.sculptor.foundation.presenter

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.presenter.CommonPresenter
import org.alexcawl.sculptor.common.presenter.LayoutPresenter
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.foundation.contract.BasicTextLayoutContract
import org.alexcawl.sculptor.foundation.contract.BasicTextStateContract
import org.alexcawl.sculptor.foundation.contract.BoxLayoutContract
import org.alexcawl.sculptor.foundation.contract.BoxStateContract
import org.alexcawl.sculptor.foundation.layout.BasicTextLayout
import org.alexcawl.sculptor.foundation.layout.BoxLayout
import kotlin.reflect.KClass

class BoxPresenter : LayoutPresenter<BoxLayoutContract, BoxStateContract, BoxLayout>() {
    override val input: KClass<BoxLayoutContract> = BoxLayoutContract::class
    override val output: KClass<BoxLayout> = BoxLayout::class

    override fun PresenterScope.transform(
        id: String,
        modifier: Modifier,
        state: BoxStateContract
    ): BoxLayout {
        return with(state) {
            BoxLayout(
                id = id,
                modifier = modifier,
                contentAlignment = map(contentAlignment),
                propagateMinConstraints = propagateMinConstraints,
                content = listMap(content)
            )
        }
    }
}
