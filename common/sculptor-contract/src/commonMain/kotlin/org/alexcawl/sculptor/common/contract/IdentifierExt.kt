package org.alexcawl.sculptor.common.contract

public val String.id: Identifier
    get() = Identifier(value = this)
