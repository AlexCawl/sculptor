package org.alexcawl.skulptor.builder

import org.alexcawl.skulptor.core.SkulptorSchema

inline fun layout(block: Builder.() -> Unit): SkulptorSchema {
    val builder = BuilderImpl()
    builder.block()
    return SkulptorSchema(builder.layouts, builder.states)
}
