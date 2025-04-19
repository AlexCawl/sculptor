package org.alexcawl.sculptor.common.presenter

import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.alexcawl.sculptor.common.contract.Block
import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.contract.StateContract
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.layout.UiState
import kotlin.reflect.KClass

public sealed interface PresenterScope {
    public suspend fun map(
        inputClass: KClass<out Any>,
        outputClass: KClass<out Any>,
        value: Any
    ): Any

    public suspend fun layout(input: List<Identifier>): List<Layout>

    public companion object {
        public operator fun invoke(
            presenterProvider: PresenterProvider,
            componentProvider: ComponentProvider,
            stateCreateCallback: StateCreateCallback,
        ): PresenterScope = PresenterScopeImpl(
            presenterProvider = presenterProvider,
            componentProvider = componentProvider,
            stateCreateCallback = stateCreateCallback,
        )
    }
}

public suspend inline fun <reified In : Any, reified Out : Any> PresenterScope.map(input: In): Out {
    return this.map(
        inputClass = In::class,
        outputClass = Out::class,
        value = input
    ) as Out
}

public suspend inline fun <reified In : Any, reified Out : Any> PresenterScope.mapEach(input: List<In>): List<Out> {
    return coroutineScope {
        input.map { inputItem: In ->
            async(start = CoroutineStart.LAZY) {
                this@mapEach.map(
                    inputClass = inputItem::class,
                    outputClass = Out::class,
                    value = inputItem
                ) as Out
            }
        }.awaitAll()
    }
}

public suspend fun PresenterScope.mapModifier(input: List<ModifierContract>): Modifier {
    return coroutineScope {
        input.map { inputModifier: ModifierContract ->
            async(start = CoroutineStart.LAZY) {
                this@mapModifier.map(
                    inputClass = inputModifier::class,
                    outputClass = Modifier::class,
                    value = inputModifier
                ) as Modifier
            }
        }.awaitAll()
    }.fold(
        initial = Modifier,
        operation = Modifier::then,
    )
}

private class PresenterScopeImpl(
    private val presenterProvider: PresenterProvider,
    private val componentProvider: ComponentProvider,
    private val stateCreateCallback: StateCreateCallback,
) : PresenterScope {
    @OptIn(InternalSculptorApi::class)
    override suspend fun map(
        inputClass: KClass<out Any>,
        outputClass: KClass<out Any>,
        value: Any
    ): Any = presenterProvider(
        inputClass = inputClass,
        outputClass = outputClass
    ).transform(
        scope = this,
        any = value,
    )

    @OptIn(InternalSculptorApi::class)
    override suspend fun layout(input: List<Identifier>): List<Layout> = coroutineScope {
        input.map { identifier: Identifier ->
            async(start = CoroutineStart.LAZY) {
                val block: Block = componentProvider(id = identifier)
                val modifiers: List<ModifierContract> = block.modifiers
                val stateContract: StateContract = block.state
                val uiState = presenterProvider(
                    inputClass = stateContract::class,
                    outputClass = UiState::class
                ).transform(
                    scope = this@PresenterScopeImpl,
                    any = stateContract
                ) as UiState
                stateCreateCallback(uiState)
                Layout(
                    id = (block.id + stateContract.id).value,
                    modifier = mapModifier(modifiers),
                    uiState = uiState,
                )
            }
        }.awaitAll()
    }
}
