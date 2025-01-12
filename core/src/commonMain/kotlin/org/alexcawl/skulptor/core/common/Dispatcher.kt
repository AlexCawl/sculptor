package org.alexcawl.skulptor.core.common

import org.alexcawl.skulptor.core.SkulptorAction

interface Dispatcher {
    fun dispatch(action: SkulptorAction)
}
