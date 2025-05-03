package org.alexcawl.sculptor.core.contract

public val String.id: Identifier
    get() = Identifier(value = this)
