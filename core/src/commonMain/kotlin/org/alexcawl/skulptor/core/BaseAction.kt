package org.alexcawl.skulptor.core

typealias Dispatch = (BaseAction) -> Unit

interface BaseAction {
    val id: Long
}
