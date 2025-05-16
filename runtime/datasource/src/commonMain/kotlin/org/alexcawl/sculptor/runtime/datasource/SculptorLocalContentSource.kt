package org.alexcawl.sculptor.runtime.datasource

import org.alexcawl.sculptor.runtime.engine.dependencies.LocalContentSource

public expect class SculptorLocalContentSource : LocalContentSource {
    public class Factory {
        public fun create(): SculptorLocalContentSource
    }
}
