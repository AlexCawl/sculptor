package org.alexcawl.sculptor.foundation.presenter.layout

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.presenter.LayoutPresenter
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.foundation.contract.layout.ColumnLayoutContract
import org.alexcawl.sculptor.foundation.contract.layout.ColumnStateContract
import org.alexcawl.sculptor.foundation.layout.ColumnLayout
import kotlin.reflect.KClass

public class ColumnPresenter : LayoutPresenter<ColumnLayoutContract, ColumnStateContract>() {
    override val input: KClass<ColumnLayoutContract> = ColumnLayoutContract::class

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
                content = content.map(::getLayout),
            )
        }
    }
}
