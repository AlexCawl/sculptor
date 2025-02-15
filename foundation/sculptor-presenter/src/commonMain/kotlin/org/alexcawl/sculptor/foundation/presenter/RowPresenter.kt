package org.alexcawl.sculptor.foundation.presenter

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.layout.LayoutContract
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.presenter.LayoutPresenter
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.foundation.contract.layout.RowLayoutContract
import org.alexcawl.sculptor.foundation.contract.layout.RowStateContract
import org.alexcawl.sculptor.foundation.layout.RowLayout
import kotlin.reflect.KClass

class RowPresenter : LayoutPresenter<RowLayoutContract, RowStateContract>() {
    override val input: KClass<RowLayoutContract> = RowLayoutContract::class

    @OptIn(InternalSculptorApi::class)
    override fun PresenterScope.transform(
        id: String,
        modifier: Modifier,
        state: RowStateContract
    ): RowLayout {
        return with(state) {
            RowLayout(
                id = id,
                modifier = modifier,
                horizontalArrangement = map(horizontalArrangement),
                verticalAlignment = map(verticalAlignment),
                content = content.map { layoutId: Identifier ->
                    getLayout<LayoutContract>(layoutId)
                }.map { layoutContract ->
                    internalMap(layoutContract::class, Layout::class, layoutContract) as Layout
                },
            )
        }
    }
}
