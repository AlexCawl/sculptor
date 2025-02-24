@file:Suppress("UNCHECKED_CAST")

package org.alexcawl.sculptor.common.presenter

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.StateContract
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import org.alexcawl.sculptor.common.core.Logger
import org.alexcawl.sculptor.common.core.Tag
import org.alexcawl.sculptor.common.layout.Layout
import kotlin.reflect.KClass

/**
 * TODO: docs
 */
public abstract class StatePresenter<Input : StateContract> : Presenter<Input, Layout>() {
    /**
     * TODO: docs
     */
    public final override val output: KClass<Layout> = Layout::class

    @InternalSculptorApi
    final override fun internalTransform(scope: PresenterScope, input: Any): Layout {
        val bundle = input as? Bundle
            ?: Logger.e(
                tag = Tag.STATE_PRESENTER,
                message = "Input is not a Bundle.class: $input"
            )
        val state = bundle.state as? Input
            ?: Logger.e(
                tag = Tag.STATE_PRESENTER,
                message = "Bundle has wrong state inside. It is ${bundle.state::class}, but ${this.input} expected"
            )
        return scope.transform(
            id = bundle.id,
            modifier = bundle.modifiers,
            state = state
        )
    }

    /**
     * TODO: docs
     */
    public abstract fun PresenterScope.transform(
        id: String,
        modifier: Modifier,
        state: Input,
    ): Layout

    /**
     * TODO: docs
     */
    internal data class Bundle(
        val id: String,
        val modifiers: Modifier,
        val state: StateContract,
    )
}
