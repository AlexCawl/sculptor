package org.alexcawl.sculptor.runtime.engine.mvi

import org.alexcawl.sculptor.internal.mvi.core.UseCaseDsl

internal abstract class SculptorUseCase<Command : SculptorCommand> : UseCaseDsl<Command, SculptorEvent>()
