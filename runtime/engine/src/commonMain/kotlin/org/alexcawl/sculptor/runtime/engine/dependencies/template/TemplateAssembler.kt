package org.alexcawl.sculptor.runtime.engine.dependencies.template

import org.alexcawl.sculptor.core.contract.ScreenScaffold
import org.alexcawl.sculptor.core.contract.ScreenSchema

internal interface TemplateAssembler {
    suspend fun assemble(schema: ScreenSchema): ScreenScaffold
}
