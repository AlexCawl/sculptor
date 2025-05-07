package org.alexcawl.sculptor.internal.mvi.core.impl

import org.alexcawl.sculptor.internal.mvi.core.Task
import org.alexcawl.sculptor.internal.mvi.core.TaskDsl

internal class TaskDslImpl<Event : Any> : TaskDsl<Event> {
    private val events: MutableList<Event> = mutableListOf()

    override fun dispatch(intent: () -> Event) {
        events.add(intent())
    }

    override fun events(vararg events: Event?) {
        for (event in events) {
            if (event != null) {
                this.events.add(event)
            }
        }
    }

    override fun build(): Task<Event> {
        return Task(events = events)
    }
}
