package org.alexcawl.sculptor.internal.mvi.core

public interface NextDsl<State : Any, Command : Any> {
    public fun state(block: State.() -> State)

    public fun commands(vararg commands: Command?)

    public fun build(): Next<State, Command>

    public companion object {
        public fun <State : Any, Command : Any> create(initialState: State): NextDsl<State, Command> {
            return NextDslImpl(initialState = initialState)
        }
    }
}

private class NextDslImpl<State : Any, Command : Any>(initialState: State) : NextDsl<State, Command> {
    private var state: State = initialState
    private val commands: MutableList<Command> = mutableListOf()

    override fun state(block: State.() -> State) {
        state = state.block()
    }

    override fun commands(vararg commands: Command?) {
        for (item in commands) {
            if (item != null) {
                this.commands.add(item)
            }
        }
    }

    override fun build(): Next<State, Command> {
        return Next(
            state = state,
            commands = commands,
        )
    }
}
