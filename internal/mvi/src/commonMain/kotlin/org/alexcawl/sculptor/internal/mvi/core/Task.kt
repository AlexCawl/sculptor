package org.alexcawl.sculptor.internal.mvi.core

public data class Task<out Events>(public val events: List<Events>) : List<Events> by events
