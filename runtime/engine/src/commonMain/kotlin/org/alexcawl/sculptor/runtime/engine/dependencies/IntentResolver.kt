package org.alexcawl.sculptor.runtime.engine.dependencies

import org.alexcawl.sculptor.runtime.engine.domain.SculptorRequest
import org.alexcawl.sculptor.runtime.engine.SculptorIntent

public interface IntentResolver {
    public suspend fun resolve(intent: SculptorIntent): SculptorRequest?
}
