package org.alexcawl.skulptor.core

interface Provider<Value> {
    operator fun invoke(): Value
}
