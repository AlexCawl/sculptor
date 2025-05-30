package org.alexcawl.sculptor.foundation.contractor

import org.alexcawl.sculptor.core.contractor.Bundle
import org.alexcawl.sculptor.core.contractor.ContractBundle
import org.alexcawl.sculptor.core.contractor.PresenterBundle
import org.alexcawl.sculptor.core.contractor.RendererBundle

public object FoundationBundle : Bundle {
    override val contractBundle: ContractBundle = FoundationContractBundle
    override val presenterBundle: PresenterBundle = FoundationPresenterBundle
    override val rendererBundle: RendererBundle = FoundationRendererBundle
}
