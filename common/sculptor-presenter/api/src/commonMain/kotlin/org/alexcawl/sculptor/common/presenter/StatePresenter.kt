package org.alexcawl.sculptor.common.presenter

import org.alexcawl.sculptor.common.contract.StateContract
import org.alexcawl.sculptor.common.layout.UiState
import kotlin.reflect.KClass

/**
 * Abstract presenter class that handles state management based on the [StateContract].
 * This class extends the [Presenter] class and is designed to work with input states
 * that implement or extend the [StateContract] and output states represented by [UiState].
 *
 * @param Input The type of input state that extends or implements [StateContract].
 */
public abstract class StatePresenter<Input : StateContract> : Presenter<Input, UiState>() {
    /**
     * The output state class type, which is fixed to [UiState].
     * This property ensures that all subclasses of [StatePresenter] will output states of type [UiState].
     */
    public final override val output: KClass<UiState> = UiState::class
}
