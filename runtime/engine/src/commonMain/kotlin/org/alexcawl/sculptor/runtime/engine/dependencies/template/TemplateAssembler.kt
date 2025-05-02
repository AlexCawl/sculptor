package org.alexcawl.sculptor.runtime.engine.dependencies.template

import org.alexcawl.sculptor.core.contract.ScreenScaffold
import org.alexcawl.sculptor.core.contract.ScreenSchema

public interface TemplateAssembler {
    public suspend fun assemble(screenSchema: ScreenSchema): ScreenScaffold
}
