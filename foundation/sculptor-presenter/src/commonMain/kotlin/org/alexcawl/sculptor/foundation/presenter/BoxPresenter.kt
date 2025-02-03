package org.alexcawl.sculptor.foundation.presenter

import org.alexcawl.sculptor.common.presenter.Presenter
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.foundation.contract.BoxContract
import org.alexcawl.sculptor.foundation.layout.BoxLayout
import kotlin.reflect.KClass

class BoxPresenter : Presenter<BoxContract, BoxLayout>() {
    override val input: KClass<BoxContract> = BoxContract::class
    override val output: KClass<BoxLayout> = BoxLayout::class

    override fun PresenterScope.transform(input: BoxContract): BoxLayout {
        return BoxLayout(
            id = input.id,
            modifier = transform(input.modifier),
            contentAlignment = transform(input.contentAlignment),
            propagateMinConstraints = input.propagateMinConstraints,
            content = transform(input.content)
        )
    }
}
