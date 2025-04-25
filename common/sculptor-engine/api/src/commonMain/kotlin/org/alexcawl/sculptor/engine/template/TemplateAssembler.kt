package org.alexcawl.sculptor.engine.template

import org.alexcawl.sculptor.common.contract.SculptorScreen.Scaffold
import org.alexcawl.sculptor.common.contract.SculptorScreen.Schema

public interface TemplateAssembler {
    public suspend fun assemble(schema: Schema): Scaffold
}
