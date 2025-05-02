package org.alexcawl.sculptor.runtime.engine.domain.useCases

import org.alexcawl.sculptor.core.contract.Identifier
import org.alexcawl.sculptor.core.contract.SculptorScreenScaffold
import org.alexcawl.sculptor.core.layout.Layout
import org.alexcawl.sculptor.core.presenter.PresenterScope
import org.alexcawl.sculptor.runtime.engine.domain.SculptorCommand.TransformToLayoutCommand
import org.alexcawl.sculptor.runtime.engine.domain.SculptorEvent
import org.alexcawl.sculptor.runtime.presenter.ComponentProvider
import org.alexcawl.sculptor.runtime.presenter.PresenterProvider
import org.alexcawl.sculptor.runtime.presenter.StateValidator
import org.alexcawl.sculptor.runtime.presenter.impl.PresenterScopeImpl
import kotlin.reflect.KClass

internal class TransformToLayoutUseCase(
    private val presenterProvider: PresenterProvider,
    private val stateValidator: StateValidator,
) : SculptorUseCase<TransformToLayoutCommand>() {
    override val type: KClass<TransformToLayoutCommand> = TransformToLayoutCommand::class

    override suspend fun TaskBuilder.handle(command: TransformToLayoutCommand) {
        val scaffold: SculptorScreenScaffold = command.scaffold
        val componentProvider = ComponentProvider { identifier: Identifier ->
            scaffold.blocks
                .find { it.id == identifier }
                ?: error("Cannot find block with identifier $identifier")
        }
        val presenterScope: PresenterScope = PresenterScopeImpl(
            presenterProvider = presenterProvider,
            componentProvider = componentProvider,
            stateValidator = stateValidator,
        )
        runCatching {
            presenterScope.layout(identifier = scaffold.root)
        }.onSuccess { layout: Layout ->
            dispatch {
                SculptorEvent.HandleUiContentEvent(layout)
            }
        }.onFailure { exception: Throwable ->
            dispatch {
                SculptorEvent.HandleFailureEvent(cause = exception)
            }
        }
    }
}
