package org.alexcawl.sculptor.common.presenter

import org.alexcawl.sculptor.common.contract.StateContract
import org.alexcawl.sculptor.common.layout.UiState
import kotlin.reflect.KClass

public abstract class StatePresenter<Input : StateContract> : Presenter<Input, UiState>() {
    public final override val output: KClass<UiState> = UiState::class
}
