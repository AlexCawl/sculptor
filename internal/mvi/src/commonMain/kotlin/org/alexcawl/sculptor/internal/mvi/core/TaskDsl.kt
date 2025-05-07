package org.alexcawl.sculptor.internal.mvi.core

import org.alexcawl.sculptor.internal.mvi.core.impl.TaskDslImpl

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

