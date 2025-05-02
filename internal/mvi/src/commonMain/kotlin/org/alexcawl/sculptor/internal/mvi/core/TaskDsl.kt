package org.alexcawl.sculptor.internal.mvi.core

public interface TaskDsl<Event : Any> {
    public fun dispatch(intent: () -> Event)

    public fun events(vararg events: Event?)

    public fun build(): Task<Event>

    public companion object {
        public fun <Event : Any> create(): TaskDsl<Event> {
            return TaskDslImpl()
        }
    }
}

private class TaskDslImpl<Event : Any> : TaskDsl<Event> {
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
