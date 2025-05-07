package org.alexcawl.sculptor.internal.mvi.core

/**
 * Represents a task that encapsulates a list of events.
 *
 * @param Events The type of events contained within the task.
 * @property events The list of events associated with this task.
 */
public data class Task<out Events>(public val events: List<Events>) : List<Events> by events
