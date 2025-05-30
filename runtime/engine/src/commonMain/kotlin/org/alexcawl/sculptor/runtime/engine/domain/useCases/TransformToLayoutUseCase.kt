package org.alexcawl.sculptor.runtime.engine.domain.useCases

import org.alexcawl.sculptor.core.contract.Identifier
import org.alexcawl.sculptor.core.contract.ScreenScaffold
import org.alexcawl.sculptor.core.contractor.layout.Layout
import org.alexcawl.sculptor.core.contractor.presenter.PresenterScope
import org.alexcawl.sculptor.runtime.engine.domain.SculptorCommand.TransformToLayoutCommand
import org.alexcawl.sculptor.runtime.engine.domain.SculptorEvent
import org.alexcawl.sculptor.runtime.contractor.presenter.ComponentProvider
import org.alexcawl.sculptor.runtime.contractor.presenter.PresenterProvider
import org.alexcawl.sculptor.runtime.contractor.presenter.StateValidator
import org.alexcawl.sculptor.runtime.contractor.presenter.impl.PresenterScopeImpl
import kotlin.reflect.KClass

internal class TransformToLayoutUseCase(
    private val presenterProvider: PresenterProvider,
    private val stateValidator: StateValidator,
) : SculptorUseCase<TransformToLayoutCommand>() {
    override val type: KClass<TransformToLayoutCommand> = TransformToLayoutCommand::class

    override suspend fun TaskBuilder.handle(command: TransformToLayoutCommand) {
        val scaffold: ScreenScaffold = command.scaffold
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
