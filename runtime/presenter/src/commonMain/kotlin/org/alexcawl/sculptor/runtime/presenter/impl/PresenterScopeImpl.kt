package org.alexcawl.sculptor.runtime.presenter.impl

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.alexcawl.sculptor.core.contract.Identifier
import org.alexcawl.sculptor.core.contract.ModifierContract
import org.alexcawl.sculptor.core.contract.Section.Block
import org.alexcawl.sculptor.core.contract.StateContract
import org.alexcawl.sculptor.core.layout.Layout
import org.alexcawl.sculptor.core.layout.UiState
import org.alexcawl.sculptor.core.presenter.PresenterScope
import org.alexcawl.sculptor.core.presenter.mapModifier
import org.alexcawl.sculptor.runtime.presenter.ComponentProvider
import org.alexcawl.sculptor.runtime.presenter.PresenterProvider
import org.alexcawl.sculptor.runtime.presenter.StateValidator
import kotlin.reflect.KClass

public class PresenterScopeImpl(
    private val presenterProvider: PresenterProvider,
    private val componentProvider: ComponentProvider,
    private val stateValidator: StateValidator,
) : PresenterScope {
    override suspend fun map(
        inputClass: KClass<out Any>,
        outputClass: KClass<out Any>,
        value: Any
    ): Any = presenterProvider.findPresenter(
        inputClass = inputClass,
        outputClass = outputClass
    ).transform(
        scope = this,
        any = value,
    )

    override suspend fun layout(identifier: Identifier): Layout {
        val block: Block = componentProvider.findBlock(identifier)
        val modifiers: List<ModifierContract> = block.modifiers
        val stateContract: StateContract = block.state
        val uiState = presenterProvider.findPresenter(
            inputClass = stateContract::class,
            outputClass = UiState::class
        ).transform(
            scope = this@PresenterScopeImpl,
            any = stateContract
        ) as UiState

        coroutineScope {
            launch {
                validateState(uiState = uiState)
            }
        }

        return Layout(
            id = block.id.value,
            modifier = mapModifier(modifiers),
            uiState = uiState,
        )
    }

    override suspend fun layouts(input: List<Identifier>): List<Layout> = coroutineScope {
        input.map { identifier: Identifier ->
            async(start = CoroutineStart.LAZY) {
                val block: Block = componentProvider.findBlock(identifier)
                val modifiers: List<ModifierContract> = block.modifiers
                val stateContract: StateContract = block.state
                val uiState = presenterProvider.findPresenter(
                    inputClass = stateContract::class,
                    outputClass = UiState::class
                ).transform(
                    scope = this@PresenterScopeImpl,
                    any = stateContract
                ) as UiState

                launch {
                    validateState(uiState = uiState)
                }

                Layout(
                    id = block.id.value,
                    modifier = mapModifier(modifiers),
                    uiState = uiState,
                )
            }
        }.awaitAll()
    }

    private suspend fun validateState(uiState: UiState) {
        if (!stateValidator.canBeDrawn(uiState)) {
            error("No presenter found for $uiState. State cannot be drawn.")
        }
    }
}
