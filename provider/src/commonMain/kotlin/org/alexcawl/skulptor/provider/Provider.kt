package org.alexcawl.skulptor.provider

interface Provider<Value> {
    operator fun invoke(): Value
}
