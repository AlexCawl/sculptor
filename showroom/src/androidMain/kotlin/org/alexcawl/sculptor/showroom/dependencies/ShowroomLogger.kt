package org.alexcawl.sculptor.showroom.dependencies

import android.util.Log
import org.alexcawl.sculptor.runtime.engine.dependencies.logger.SculptorLogger

internal object ShowroomLogger : SculptorLogger {
    override fun debug(tag: String, message: String) {
        Log.d(tag, message)
    }

    override fun info(tag: String, message: String) {
        Log.i(tag, message)
    }

    override fun warning(tag: String, message: String) {
        Log.w(tag, message)
    }

    override fun error(tag: String, message: String) {
        Log.e(tag, message)
    }
}
