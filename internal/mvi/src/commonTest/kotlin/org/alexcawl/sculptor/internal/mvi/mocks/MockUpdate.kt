package org.alexcawl.sculptor.internal.mvi.mocks

import org.alexcawl.sculptor.internal.mvi.core.UpdateDsl
import org.alexcawl.sculptor.internal.mvi.mocks.entity.Command
import org.alexcawl.sculptor.internal.mvi.mocks.entity.Event
import org.alexcawl.sculptor.internal.mvi.mocks.entity.News
import org.alexcawl.sculptor.internal.mvi.mocks.entity.State

class MockUpdate : UpdateDsl<State, Event, Command, News>() {
    override suspend fun NextBuilder.update(event: Event) {
        state {
            copy(name = event.name)
        }
    }
}
