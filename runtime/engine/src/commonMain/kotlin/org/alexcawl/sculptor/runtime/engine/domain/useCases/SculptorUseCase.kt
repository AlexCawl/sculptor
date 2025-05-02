package org.alexcawl.sculptor.runtime.engine.domain.useCases

import org.alexcawl.sculptor.internal.mvi.core.UseCaseDsl
import org.alexcawl.sculptor.runtime.engine.domain.SculptorCommand
import org.alexcawl.sculptor.runtime.engine.domain.SculptorEvent

internal abstract class SculptorUseCase<Command : SculptorCommand> : UseCaseDsl<Command, SculptorEvent>()
