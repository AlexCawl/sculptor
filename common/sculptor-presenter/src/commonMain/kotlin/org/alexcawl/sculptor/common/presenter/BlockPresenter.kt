package org.alexcawl.sculptor.common.presenter

import org.alexcawl.sculptor.common.contract.Block
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import org.alexcawl.sculptor.common.core.Logger
import org.alexcawl.sculptor.common.core.Tag
import org.alexcawl.sculptor.common.layout.Layout
import kotlin.reflect.KClass

public data object BlockPresenter : Presenter<Block<*>, Layout>() {
    override val input: KClass<Block<*>> = Block::class
    override val output: KClass<Layout> = Layout::class

    @OptIn(InternalSculptorApi::class)
    override fun internalTransform(scope: PresenterScope, input: Any): Layout {
        val castedInput = input as? Block<*>
            ?: Logger.e(
                tag = Tag.BLOCK_PRESENTER,
                message = "Input is not a Block<*>. Expected ${this.input} but it was ${input::class} instead"
            )
        return scope.getLayout(identifier = castedInput.id)
    }
}
