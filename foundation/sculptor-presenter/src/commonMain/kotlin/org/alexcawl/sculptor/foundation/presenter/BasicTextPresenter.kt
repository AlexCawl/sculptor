package org.alexcawl.sculptor.foundation.presenter

import org.alexcawl.sculptor.common.presenter.Presenter
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.foundation.contract.BasicTextContract
import org.alexcawl.sculptor.foundation.layout.BasicTextLayout
import kotlin.reflect.KClass

class BasicTextPresenter : Presenter<BasicTextContract, BasicTextLayout>() {
    override val input: KClass<BasicTextContract> = BasicTextContract::class
    override val output: KClass<BasicTextLayout> = BasicTextLayout::class

    override fun PresenterScope.transform(input: BasicTextContract): BasicTextLayout {
        return BasicTextLayout(
            id = input.id,
            modifier = map(input.modifiers),
            text = input.text.value,
            softWrap = input.softWrap,
            maxLines = input.maxLines,
            minLines = input.minLines,
        )
    }
}
