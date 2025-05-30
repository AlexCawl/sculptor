package org.alexcawl.sculptor.runtime.engine

import org.alexcawl.sculptor.runtime.engine.dependencies.IntentResolver
import org.alexcawl.sculptor.runtime.engine.dependencies.SculptorLogger

public interface SculptorBuilder {
    public fun sculptorLogger(sculptorLogger: () -> SculptorLogger)

    public fun intentResolver(intentResolver: () -> IntentResolver)
}
