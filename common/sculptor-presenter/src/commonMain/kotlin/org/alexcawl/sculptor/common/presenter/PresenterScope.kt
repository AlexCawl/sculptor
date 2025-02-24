package org.alexcawl.sculptor.common.presenter

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.contract.Section
import org.alexcawl.sculptor.common.contract.StateContract
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import org.alexcawl.sculptor.common.core.Logger
import org.alexcawl.sculptor.common.core.Tag
import org.alexcawl.sculptor.common.layout.Layout
import kotlin.reflect.KClass

/**
 * TODO: docs
 */
public typealias PresenterProvider = (inputClass: KClass<out Any>, outputClass: KClass<out Any>) -> Presenter<*, *>

/**
 * TODO: docs
 */
public typealias SectionProvider = (id: Identifier) -> Section

/**
 * TODO: docs
 */
@OptIn(InternalSculptorApi::class)
public class PresenterScope private constructor(
    private val presenterProvider: PresenterProvider,
    private val sectionProvider: SectionProvider,
) {
    @InternalSculptorApi
    public constructor(
        presenters: List<Presenter<*, *>>,
        sections: List<Section>,
    ) : this(
        presenterProvider = { inputClass: KClass<out Any>, outputClass: KClass<out Any> ->
            presenters
                .find { it.input == inputClass && it.output == outputClass }
                ?: Logger.e(
                    tag = Tag.PRESENTER_SCOPE,
                    message = "Cannot resolve presenter for input $inputClass and output $outputClass",
                )
        },
        sectionProvider = { identifier ->
            sections
                .find { it.id == identifier }
                ?: Logger.e(
                    tag = Tag.PRESENTER_SCOPE,
                    message = "Cannot resolve section for id $identifier",
                )
        },
    )

    /**
     * TODO: docs
     */
    public inline fun <reified In : Any, reified Out : Any> map(
        input: In,
    ): Out = this.internalMap(
        inputClass = In::class,
        outputClass = Out::class,
        value = input,
    ) as Out

    /**
     * TODO: docs
     */
    public inline fun <reified In : Any, reified Out : Any> mapEach(
        input: Iterable<In>,
    ): List<Out> = input.map(transform = ::map)

    /**
     * TODO: docs
     */
    public fun buildModifier(
        modifiers: List<ModifierContract>
    ): Modifier = mapEach<ModifierContract, Modifier>(input = modifiers).fold(
        initial = Modifier,
        operation = Modifier::then,
    )

    /**
     * TODO: docs
     */
    public fun getLayout(
        identifier: Identifier,
    ): Layout {
        val section: Section = sectionProvider(identifier)
        val modifiers: List<ModifierContract> = section.modifiers
        val state: StateContract = section.state
        val statePresenter: Presenter<*, *> = presenterProvider(state::class, Layout::class)
        if (statePresenter !is StatePresenter<*>) {
            Logger.e(
                tag = Tag.PRESENTER_SCOPE,
                message = "Cannot resolve state presenter for state ${state::class}. Expected StatePresenter but it was ${statePresenter::class} instead"
            )
        }
        return statePresenter.internalTransform(
            scope = this,
            input = StatePresenter.Bundle(
                id = section.id + identifier,
                modifiers = buildModifier(
                    modifiers = modifiers,
                ),
                state = state,
            ),
        )
    }

    /**
     * TODO: docs
     */
    public fun safeGetLayout(
        identifier: Identifier,
    ): Result<Layout> = runCatching { getLayout(identifier) }

    @InternalSculptorApi
    public fun internalMap(
        inputClass: KClass<out Any>,
        outputClass: KClass<out Any>,
        value: Any
    ): Any = presenterProvider(inputClass, outputClass).internalTransform(
        scope = this,
        input = value,
    )
}
