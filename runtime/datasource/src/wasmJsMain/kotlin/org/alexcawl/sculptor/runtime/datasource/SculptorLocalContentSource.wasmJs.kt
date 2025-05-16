package org.alexcawl.sculptor.runtime.datasource

import kotlinx.browser.localStorage
import org.alexcawl.sculptor.runtime.engine.dependencies.LocalContentSource
import org.w3c.dom.get
import org.w3c.dom.set

public actual class SculptorLocalContentSource : LocalContentSource {
    override suspend fun find(key: String): String? {
        return localStorage[key]
    }

    override suspend fun save(key: String, content: String) {

        localStorage[key] = content
    }

    public actual class Factory {
        public actual fun create(): SculptorLocalContentSource {
            return SculptorLocalContentSource()
        }
    }
}
