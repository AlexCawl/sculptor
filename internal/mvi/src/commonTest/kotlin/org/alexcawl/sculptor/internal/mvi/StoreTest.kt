package org.alexcawl.sculptor.internal.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.test.runTest
import org.alexcawl.sculptor.internal.mvi.core.Store
import org.alexcawl.sculptor.internal.mvi.mocks.MockReducer
import org.alexcawl.sculptor.internal.mvi.mocks.MockStore
import org.alexcawl.sculptor.internal.mvi.mocks.MockUseCase
import org.alexcawl.sculptor.internal.mvi.mocks.entity.Command
import org.alexcawl.sculptor.internal.mvi.mocks.entity.Event
import org.alexcawl.sculptor.internal.mvi.mocks.entity.State
import kotlin.test.Test
import kotlin.test.assertEquals

class StoreTest {
    @Test
    fun `Check WHEN store is empty THEN state is initial`() = runTest {
        // Init
        val store: Store<State, Event> = MockStore(
            initialState = State.Initial,
        )

        // Work
        val resultState: State = store.state.value
        assertEquals(
            expected = resultState,
            actual = State.Initial,
        )
    }

    @Test
    fun `Check WHEN store has initial commands THEN state is reduced`() = runTest {
        // Init
        val scope = CoroutineScope(context = Dispatchers.Default)
        val store: Store<State, Event> = MockStore(
            initialState = State.Initial,
            initialCommands = listOf(
                Command(name = "1"),
                Command(name = "2"),
                Command(name = "3"),
            ),
            useCases = listOf(MockUseCase()),
            reducers = listOf(MockReducer()),
        )
        store.launchIn(coroutineScope = scope)

        // Work
        val resultStates: List<State> = store.state
            .take(count = 4)
            .toCollection(destination = mutableListOf())
        assertEquals(
            expected = resultStates[0],
            actual = State.Initial,
        )
        assertEquals(
            expected = resultStates[1],
            actual = State.Content(name = "1"),
        )
        assertEquals(
            expected = resultStates[2],
            actual = State.Content(name = "2"),
        )
        assertEquals(
            expected = resultStates[3],
            actual = State.Content(name = "3"),
        )

        // Dispose
        scope.cancel()
    }

    @Test
    fun `Check WHEN store handles events THEN state is reduced`() = runTest {
        // Init
        val scope = CoroutineScope(context = Dispatchers.Default)
        val store: Store<State, Event> = MockStore(
            initialState = State.Initial,
            useCases = listOf(MockUseCase()),
            reducers = listOf(MockReducer()),
        )
        store.launchIn(coroutineScope = scope)

        // Main
        val state1: State = store.state.value
        assertEquals(
            expected = State.Initial,
            actual = state1,
            message = "State is not initial",
        )

        store.dispatch(event = Event.ChangeState(name = "1"))
        val state2: State = store.state.first { it != state1 }
        assertEquals(
            expected = State.Content(name = "1"),
            actual = state2,
            message = "State is not changed after Event.ChangeState",
        )

        store.dispatch(event = Event.SendCommand(name = "2"))
        val state3: State = store.state.first { it != state2 }
        assertEquals(
            expected = State.Content(name = "2"),
            actual = state3,
            message = "State is not changed after Event.SendCommand",
        )

        store.dispatch(event = Event.ChangeState(name = "3"))
        val state4: State = store.state.first { it != state3 }
        assertEquals(
            expected = State.Content(name = "3"),
            actual = state4,
            message = "State is not changed after Event.ChangeState",
        )

        store.dispatch(event = Event.SendCommand(name = "4"))
        val state5: State = store.state.first { it != state4 }
        assertEquals(
            expected = State.Content(name = "4"),
            actual = state5,
            message = "State is not changed after Event.SendCommand",
        )

        // Dispose
        scope.cancel()
    }
}
