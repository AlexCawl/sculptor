package org.alexcawl.sculptor.internal.mvi.mocks

import org.alexcawl.sculptor.internal.mvi.core.ReducerDsl
import org.alexcawl.sculptor.internal.mvi.mocks.entity.Command
import org.alexcawl.sculptor.internal.mvi.mocks.entity.Event
import org.alexcawl.sculptor.internal.mvi.mocks.entity.State
import kotlin.reflect.KClass

class MockReducer : ReducerDsl<State, Event, Command>() {
    override val key: KClass<Event> = Event::class

    override suspend fun NextBuilder.reduce(event: Event) {
        println("Init event: $event")
        when (event) {
            is Event.ChangeState ->{
                state {
                    State.Content(name = event.name)
                }
            }
            is Event.SendCommand -> {
                commands(Command(name = event.name))
            }
        }
        println("Finish event: $event")
    }
}
