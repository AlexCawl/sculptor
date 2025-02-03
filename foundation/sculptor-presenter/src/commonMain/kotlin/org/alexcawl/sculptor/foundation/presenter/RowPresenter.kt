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
            modifier = transform(input.modifier),
            horizontalArrangement = transform(input.horizontalArrangement),
            verticalAlignment = transform(input.verticalAlignment),
            content = transform(input.content),
        )
    }
}
