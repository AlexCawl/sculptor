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

inline fun <reified C : LayoutContract, reified S : StateContract, reified L : Layout> layoutPresenter(
    contract: KClass<C>,
    layout: KClass<L>,
    crossinline transformer: PresenterScope.(id: String, modifier: Modifier, state: S) -> L,
) : LayoutPresenter<C, S, L> {
    return object : LayoutPresenter<C, S, L>() {
        override val input: KClass<C> = contract
        override val output: KClass<L> = layout

        override fun PresenterScope.transform(id: String, modifier: Modifier, state: S): L = transformer(id, modifier, state)
    }
}

inline fun <reified C : ModifierContract, reified M : Modifier> modifierPresenter(
    input: KClass<C>,
    output: KClass<M>,
    crossinline transformer: PresenterScope.(input: C) -> M,
) : ModifierPresenter<C, M> {
    return object : ModifierPresenter<C, M>() {
        override val input: KClass<C> = input
        override val output: KClass<M> = output

        override fun PresenterScope.transform(input: C): M = transformer(input)
    }
}
