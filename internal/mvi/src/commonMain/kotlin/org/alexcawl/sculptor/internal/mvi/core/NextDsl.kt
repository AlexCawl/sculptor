package org.alexcawl.sculptor.internal.mvi.core

public interface NextDsl<State : Any, Command : Any, News : Any> {
    public fun state(block: State.() -> State)

    public fun commands(vararg commands: Command?)

    public fun news(vararg news: News?)

    public fun build(): Next<State, Command, News>

    public companion object {
        public fun <State : Any, Command : Any, News : Any> create(
            initialState: State,
        ): NextDsl<State, Command, News> {
            return NextDslImpl(initialState = initialState)
        }
    }
}

private class NextDslImpl<State : Any, Command : Any, News : Any>(
    initialState: State,
) : NextDsl<State, Command, News> {
    private val lock: Any = Any()
    private var state: State = initialState
    private val commands = mutableListOf<Command>()
    private val news = mutableListOf<News>()

    override fun state(block: State.() -> State) {
        synchronized(lock = lock) {
            state = state.block()
        }
    }

    override fun commands(vararg commands: Command?) {
        synchronized(lock = lock) {
            for (item in commands) {
                if (item != null) {
                    this.commands.add(item)
                }
            }
        }
    }

    override fun news(vararg news: News?) {
        synchronized(lock = lock) {
            for (item in news) {
                if (item != null) {
                    this.news.add(item)
                }
            }
        }
    }

    override fun build(): Next<State, Command, News> {
        return synchronized(lock = lock) {
            Next(
                state = state,
                commands = commands,
                news = news
            )
        }
    }
}
