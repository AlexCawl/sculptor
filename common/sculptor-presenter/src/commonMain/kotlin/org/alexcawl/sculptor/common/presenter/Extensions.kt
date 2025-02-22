package org.alexcawl.sculptor.common.presenter

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.contract.StateContract
import org.alexcawl.sculptor.common.layout.Layout
import kotlin.reflect.KClass

/**
 * TODO: docs
 */
public inline fun <reified I : Any, reified O : Any> commonPresenter(
    input: KClass<I>,
    output: KClass<O>,
    crossinline transformer: PresenterScope.(input: I) -> O,
) : CommonPresenter<I, O> {
    return object : CommonPresenter<I, O>() {
        override val input: KClass<I> = input
        override val output: KClass<O> = output

        override fun PresenterScope.transform(input: I): O = transformer(input)
    }
}

/**
 * TODO: docs
 */
public inline fun <reified SC : StateContract, reified L : Layout> statePresenter(
    stateContract: KClass<SC>,
    crossinline transformer: PresenterScope.(blockId: Identifier, blockModifiers: List<ModifierContract>, state: SC) -> L,
) : StatePresenter<SC> {
    return object : StatePresenter<SC>() {
        override val input: KClass<SC> = stateContract

        override fun PresenterScope.transform(
            blockId: Identifier,
            blockModifiers: List<ModifierContract>,
            state: SC
        ): Layout = transformer(blockId, blockModifiers, state)
    }
}

/**
 * TODO: docs
 */
public inline fun <reified MC : ModifierContract> modifierPresenter(
    modifierContract: KClass<MC>,
    crossinline transformer: PresenterScope.(contract: MC) -> Modifier,
) : ModifierPresenter<MC> {
    return object : ModifierPresenter<MC>() {
        override val input: KClass<MC> = modifierContract

        override fun PresenterScope.transform(input: MC): Modifier = transformer(input)
    }
}
