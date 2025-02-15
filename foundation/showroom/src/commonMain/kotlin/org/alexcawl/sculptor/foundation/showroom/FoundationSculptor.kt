package org.alexcawl.sculptor.foundation.showroom

import org.alexcawl.sculptor.engine.Sculptor
import org.alexcawl.sculptor.foundation.contract.FoundationContractorState
import org.alexcawl.sculptor.foundation.layout.FoundationRendererState
import org.alexcawl.sculptor.foundation.presenter.FoundationPresenterState

val foundationSculptor: Sculptor
    get() = Sculptor.create(
        contractorState = FoundationContractorState,
        presenterState = FoundationPresenterState,
        rendererState = FoundationRendererState
    )
