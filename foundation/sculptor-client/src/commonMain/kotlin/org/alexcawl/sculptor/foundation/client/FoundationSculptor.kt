package org.alexcawl.sculptor.foundation.client

import org.alexcawl.sculptor.engine.Sculptor

/**
 * TODO: docs
 */
public object FoundationSculptor {
    /**
     * TODO: docs
     */
    public operator fun invoke(): Sculptor = Sculptor.create(
        contractorState = FoundationContractorState,
        presenterState = FoundationPresenterState,
        rendererState = FoundationRendererState
    )
}
