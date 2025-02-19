package org.alexcawl.sculptor.foundation.client

import org.alexcawl.sculptor.engine.SculptorState

/**
 * TODO: docs
 */
public object FoundationSculptor {
    /**
     * TODO: docs
     */
    public operator fun invoke(): SculptorState = SculptorState.create(
        contractorState = FoundationContractorState,
        presenterState = FoundationPresenterState,
        rendererState = FoundationRendererState
    )
}
