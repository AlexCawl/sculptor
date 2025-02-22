package org.alexcawl.sculptor.common.builder

import org.alexcawl.sculptor.common.contract.Scaffold

public fun scaffold(
    builder: ScaffoldBuilder.() -> Unit
): Scaffold = ScaffoldBuilder.create(builder = builder)
