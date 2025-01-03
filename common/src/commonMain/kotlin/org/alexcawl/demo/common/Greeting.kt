package org.alexcawl.demo.common

val greeting: String
    get() {
        val platform = getPlatform()
        return "Hello, ${platform.name}!"
    }
