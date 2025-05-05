package org.alexcawl.sculptor.runtime.engine.di

import org.alexcawl.sculptor.internal.di.DiTree

public data class SculptorConnector internal constructor(
    internal val localDiTree: DiTree,
)
