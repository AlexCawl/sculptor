package org.alexcawl.sculptor.common.builder

import org.alexcawl.sculptor.common.contract.Scaffold

public fun scaffold(
    forcedRoot: String? = null,
    builderBlock: ScaffoldBuilder.() -> Unit
): Scaffold = ScaffoldBuilder.invoke(forcedRoot = forcedRoot).also(block = builderBlock).build()
