package org.alexcawl.sculptor.common.presenter

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.layout.LayoutContract
import org.alexcawl.sculptor.common.contract.layout.ModifierContract
import org.alexcawl.sculptor.common.contract.layout.StateContract
import org.alexcawl.sculptor.common.layout.Layout
import kotlin.reflect.KClass

inline fun <reified I : Any, reified O : Any> commonPresenter(
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

inline fun <reified LC : LayoutContract, reified SC : StateContract, reified L : Layout> layoutPresenter(
    contract: KClass<LC>,
    crossinline transformer: PresenterScope.(id: String, modifier: Modifier, state: SC) -> L,
) : LayoutPresenter<LC, SC> {
    return object : LayoutPresenter<LC, SC>() {
        override val input: KClass<LC> = contract

        override fun PresenterScope.transform(id: String, modifier: Modifier, state: SC): L = transformer(id, modifier, state)
    }
}

inline fun <reified MC : ModifierContract> modifierPresenter(
    input: KClass<MC>,
    crossinline transformer: PresenterScope.(input: MC) -> Modifier,
) : ModifierPresenter<MC> {
    return object : ModifierPresenter<MC>() {
        override val input: KClass<MC> = input

        override fun PresenterScope.transform(input: MC): Modifier = transformer(input)
    }
}
