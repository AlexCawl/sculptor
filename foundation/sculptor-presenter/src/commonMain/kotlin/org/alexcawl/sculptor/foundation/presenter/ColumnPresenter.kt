package org.alexcawl.sculptor.foundation.presenter

import org.alexcawl.sculptor.common.presenter.Presenter
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.foundation.contract.ColumnContract
import org.alexcawl.sculptor.foundation.layout.ColumnLayout
import kotlin.reflect.KClass

class ColumnPresenter : Presenter<ColumnContract, ColumnLayout>() {
    override val input: KClass<ColumnContract> = ColumnContract::class
    override val output: KClass<ColumnLayout> = ColumnLayout::class

    override fun PresenterScope.transform(input: ColumnContract): ColumnLayout {
        return ColumnLayout(
            id = input.id,
            modifier = map(input.modifiers),
            verticalArrangement = map(input.verticalArrangement),
            horizontalAlignment = map(input.horizontalAlignment),
            content = map(input.content),
        )
    }
}
