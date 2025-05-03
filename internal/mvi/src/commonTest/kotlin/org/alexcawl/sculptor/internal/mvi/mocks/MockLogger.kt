package org.alexcawl.sculptor.internal.mvi.mocks

import org.alexcawl.sculptor.internal.mvi.logging.StoreLogger

class MockLogger(private var delegateLog: (tag: String, message: String) -> Unit) : StoreLogger {
    override fun log(tag: String, message: String) = delegateLog(tag, message)
}
