package org.alexcawl.skulptor.core.common

interface Provider<Value> {
    operator fun invoke(): Value
}
