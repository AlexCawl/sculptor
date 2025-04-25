package org.alexcawl.sculptor.common.presenter.impl

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.contract.Section
import org.alexcawl.sculptor.common.contract.StateContract
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.layout.UiState
import org.alexcawl.sculptor.common.presenter.ComponentProvider
import org.alexcawl.sculptor.common.presenter.StateValidator
import org.alexcawl.sculptor.common.presenter.PresenterProvider
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.common.presenter.mapModifier
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

    override suspend fun layout(input: List<Identifier>): List<Layout> = coroutineScope {
        input.map { identifier: Identifier ->
            async(start = CoroutineStart.LAZY) {
                val block: Section.Block = componentProvider.findBlock(identifier)
                val modifiers: List<ModifierContract> = block.modifiers
                val stateContract: StateContract = block.state
                val uiState = presenterProvider.findPresenter(
                    inputClass = stateContract::class,
                    outputClass = UiState::class
                ).transform(
                    scope = this@PresenterScopeImpl,
                    any = stateContract
                ) as UiState
                validateState(uiState = uiState)
                Layout(
                    id = (block.id + stateContract.id).value,
                    modifier = mapModifier(modifiers),
                    uiState = uiState,
                )
            }
        }.awaitAll()
    }

    private fun CoroutineScope.validateState(uiState: UiState) {
        launch {
            if (!stateValidator.canBeDrawn(uiState)) {
                error("No presenter found for $uiState. State cannot be drawn.")
            }
        }
    }
}
