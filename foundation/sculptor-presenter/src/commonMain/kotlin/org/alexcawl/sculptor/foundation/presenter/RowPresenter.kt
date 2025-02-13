package org.alexcawl.sculptor.foundation.presenter

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.presenter.CommonPresenter
import org.alexcawl.sculptor.common.presenter.LayoutPresenter
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.foundation.contract.ColumnLayoutContract
import org.alexcawl.sculptor.foundation.contract.ColumnStateContract
import org.alexcawl.sculptor.foundation.contract.RowLayoutContract
import org.alexcawl.sculptor.foundation.contract.RowStateContract
import org.alexcawl.sculptor.foundation.layout.ColumnLayout
import org.alexcawl.sculptor.foundation.layout.RowLayout
import kotlin.reflect.KClass

class RowPresenter : LayoutPresenter<RowLayoutContract, RowStateContract, RowLayout>() {
    override val input: KClass<RowLayoutContract> = RowLayoutContract::class
    override val output: KClass<RowLayout> = RowLayout::class

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
                content = listMap(content),
            )
        }
    }
}
