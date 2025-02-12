package org.alexcawl.sculptor.foundation.presenter

import org.alexcawl.sculptor.common.presenter.Presenter
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.foundation.contract.RowContract
import org.alexcawl.sculptor.foundation.layout.RowLayout
import kotlin.reflect.KClass

class RowPresenter : Presenter<RowContract, RowLayout>() {
    override val input: KClass<RowContract> = RowContract::class
    override val output: KClass<RowLayout> = RowLayout::class

    override fun PresenterScope.transform(input: RowContract): RowLayout {
        return RowLayout(
            id = input.id,
            modifier = map(input.modifiers),
            horizontalArrangement = map(input.horizontalArrangement),
            verticalAlignment = map(input.verticalAlignment),
            content = map(input.content),
        )
    }
}
