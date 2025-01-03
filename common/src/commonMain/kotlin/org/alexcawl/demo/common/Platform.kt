package org.alexcawl.demo.common

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
