package org.alexcawl.sculptor.foundation.presenter

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.presenter.CommonPresenter
import org.alexcawl.sculptor.common.presenter.LayoutPresenter
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.foundation.contract.BoxLayoutContract
import org.alexcawl.sculptor.foundation.contract.BoxStateContract
import org.alexcawl.sculptor.foundation.contract.ColumnLayoutContract
import org.alexcawl.sculptor.foundation.contract.ColumnStateContract
import org.alexcawl.sculptor.foundation.layout.BoxLayout
import org.alexcawl.sculptor.foundation.layout.ColumnLayout
import kotlin.reflect.KClass

class ColumnPresenter : LayoutPresenter<ColumnLayoutContract, ColumnStateContract, ColumnLayout>() {
    override val input: KClass<ColumnLayoutContract> = ColumnLayoutContract::class
    override val output: KClass<ColumnLayout> = ColumnLayout::class

    override fun PresenterScope.transform(
        id: String,
        modifier: Modifier,
        state: ColumnStateContract
    ): ColumnLayout {
        return with(state) {
            ColumnLayout(
                id = id,
                modifier = modifier,
                verticalArrangement = map(verticalArrangement),
                horizontalAlignment = map(horizontalAlignment),
                content = listMap(content),
            )
        }
    }
}
