package org.alexcawl.sculptor.foundation.client

import org.alexcawl.sculptor.runtime.engine.SculptorGlobalBuilder
import org.alexcawl.sculptor.runtime.engine.contractor

public object FoundationBundle {
    public fun SculptorGlobalBuilder.install() {
        contractor { FoundationContract }
        with(FoundationPresenter) { install() }
        with(FoundationRenderer) { install() }
    }
}
