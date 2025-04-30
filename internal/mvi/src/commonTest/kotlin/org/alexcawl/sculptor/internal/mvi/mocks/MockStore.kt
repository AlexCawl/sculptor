package org.alexcawl.sculptor.internal.mvi.mocks

import org.alexcawl.sculptor.internal.mvi.core.Store
import org.alexcawl.sculptor.internal.mvi.mocks.entity.Command
import org.alexcawl.sculptor.internal.mvi.mocks.entity.Event
import org.alexcawl.sculptor.internal.mvi.mocks.entity.News
import org.alexcawl.sculptor.internal.mvi.mocks.entity.State

class MockStore : Store<State, Event, News> by Store.create<State, Event, Command, News>(
    initialState = State.initial,
)
