package org.alexcawl.sculptor.runtime.engine.dependencies.template

import org.alexcawl.sculptor.core.contract.SculptorScreenScaffold
import org.alexcawl.sculptor.core.contract.SculptorScreenSchema

internal interface TemplateAssembler {
    suspend fun assemble(schema: SculptorScreenSchema): SculptorScreenScaffold
}
