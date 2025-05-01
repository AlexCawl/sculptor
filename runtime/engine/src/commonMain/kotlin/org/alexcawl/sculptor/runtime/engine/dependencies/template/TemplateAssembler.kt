package org.alexcawl.sculptor.runtime.engine.dependencies.template

import org.alexcawl.sculptor.core.contract.SculptorScreen.Scaffold
import org.alexcawl.sculptor.core.contract.SculptorScreen.Schema

public interface TemplateAssembler {
    public suspend fun assemble(schema: Schema): Scaffold
}
