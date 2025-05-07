package org.alexcawl.sculptor.internal.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.any
import kotlinx.coroutines.test.runTest
import org.alexcawl.sculptor.internal.mvi.core.Store
import org.alexcawl.sculptor.internal.mvi.logging.INITIAL_COMMAND_TAG
import org.alexcawl.sculptor.internal.mvi.logging.INITIAL_STATE_TAG
import org.alexcawl.sculptor.internal.mvi.logging.impl.LoggingUseCaseImpl.Companion.COMMAND_RECEIVED_TAG
import org.alexcawl.sculptor.internal.mvi.logging.impl.LoggingUseCaseImpl.Companion.EVENT_SENT_TAG
import org.alexcawl.sculptor.internal.mvi.logging.impl.LoggingReducerImpl.Companion.COMMAND_SENT_TAG
import org.alexcawl.sculptor.internal.mvi.logging.impl.LoggingReducerImpl.Companion.EVENT_RECEIVED_TAG
import org.alexcawl.sculptor.internal.mvi.logging.impl.LoggingReducerImpl.Companion.STATE_TAG
import org.alexcawl.sculptor.internal.mvi.logging.StoreLogger
import org.alexcawl.sculptor.internal.mvi.mocks.MockUseCase
import org.alexcawl.sculptor.internal.mvi.mocks.MockLogger
import org.alexcawl.sculptor.internal.mvi.mocks.MockLoggingStore
import org.alexcawl.sculptor.internal.mvi.mocks.MockReducer
import org.alexcawl.sculptor.internal.mvi.mocks.entity.Command
import org.alexcawl.sculptor.internal.mvi.mocks.entity.Event
import org.alexcawl.sculptor.internal.mvi.mocks.entity.State
import kotlin.test.Test
import kotlin.test.assertContentEquals

class LoggingStoreTest {
    @Test
    fun `Check WHEN store is empty THEN logging only initial state`() = runTest {
        // Init
        val messages: MutableList<Pair<String, String>> = mutableListOf()
        val logger: StoreLogger = MockLogger { tag: String, message: String ->
            messages.add(tag to message)
        }

        // Work
        MockLoggingStore(
            initialState = State.Initial,
            logger = logger,
        )

        // Assert
        assertContentEquals(
            expected = listOf(INITIAL_STATE_TAG to State.Initial.toString()),
            actual = messages,
            message = "Logging is not correct",
        )
    }

    @Test
    fun `Check WHEN store has initial commands THEN state is reduced`() = runTest {
        // Init
        val messages: MutableList<Pair<String, String>> = mutableListOf()
        val logger: StoreLogger = MockLogger { tag: String, message: String ->
            messages.add(tag to message)
        }

        // Work
        MockLoggingStore(
            initialState = State.Initial,
            initialCommands = listOf(
                Command(name = "1"),
                Command(name = "2"),
                Command(name = "3"),
            ),
            logger = logger,
        )

        // Assert
        assertContentEquals(
            expected = listOf(
                INITIAL_STATE_TAG to State.Initial.toString(),
                INITIAL_COMMAND_TAG to Command(name = "1").toString(),
                INITIAL_COMMAND_TAG to Command(name = "2").toString(),
                INITIAL_COMMAND_TAG to Command(name = "3").toString(),
            ),
            actual = messages,
            message = "Logging is not correct",
        )
    }

    @Test
    fun `Check WHEN store handles events THEN state is reduced`() = runTest {
        // Init
        val messages: MutableList<Pair<String, String>> = mutableListOf()
        val logger: StoreLogger = MockLogger { tag: String, message: String ->
            messages.add(tag to message)
        }

        // Work
        val scope = CoroutineScope(context = Dispatchers.Default)
        val store: Store<State, Event> = MockLoggingStore(
            initialState = State.Initial,
            useCases = listOf(MockUseCase()),
            reducers = listOf(MockReducer()),
            logger = logger,
        )
        store.launchIn(coroutineScope = scope)
        store.dispatch(event = Event.ChangeState(name = "1"))
        store.dispatch(event = Event.ChangeState(name = "2"))

        // Wait until all events are handled
        store.state.any { it.name == "2" }

        // Assert
        assertContentEquals(
            expected = listOf(
                INITIAL_STATE_TAG to State.Initial.toString(),
                EVENT_RECEIVED_TAG to Event.ChangeState(name = "1").toString(),
                STATE_TAG to State.Content(name = "1").toString(),
                EVENT_RECEIVED_TAG to Event.ChangeState(name = "2").toString(),
                STATE_TAG to State.Content(name = "2").toString(),
            ),
            actual = messages,
            message = "Logging is not correct",
        )

        // Dispose
        scope.cancel()
    }

    @Test
    fun `Check WHEN store handles commands and events THEN state is reduced`() = runTest {
        // Init
        val messages: MutableList<Pair<String, String>> = mutableListOf()
        val logger: StoreLogger = MockLogger { tag: String, message: String ->
            messages.add(tag to message)
        }

        // Work
        val scope = CoroutineScope(context = Dispatchers.Default)
        val store: Store<State, Event> = MockLoggingStore(
            initialState = State.Initial,
            useCases = listOf(MockUseCase()),
            reducers = listOf(MockReducer()),
            logger = logger,
        )
        store.launchIn(coroutineScope = scope)
        store.dispatch(event = Event.ChangeState(name = "1"))
        store.dispatch(event = Event.SendCommand(name = "2"))

        // Wait until all events are handled
        store.state.any { it.name == "2" }

        println(messages)
        // Assert
        assertContentEquals(
            expected = listOf(
                INITIAL_STATE_TAG to State.Initial.toString(),
                EVENT_RECEIVED_TAG to Event.ChangeState(name = "1").toString(),
                STATE_TAG to State.Content(name = "1").toString(),
                EVENT_RECEIVED_TAG to Event.SendCommand(name = "2").toString(),
                COMMAND_SENT_TAG to Command(name = "2").toString(),
                COMMAND_RECEIVED_TAG to Command(name = "2").toString(),
                EVENT_SENT_TAG to Event.ChangeState(name = "2").toString(),
                EVENT_RECEIVED_TAG to Event.ChangeState(name = "2").toString(),
                STATE_TAG to State.Content(name = "2").toString(),
            ),
            actual = messages,
            message = "Logging is not correct",
        )

        // Dispose
        scope.cancel()
    }
}
