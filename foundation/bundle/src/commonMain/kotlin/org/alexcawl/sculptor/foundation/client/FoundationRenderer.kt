package org.alexcawl.sculptor.foundation.client

import org.alexcawl.sculptor.core.renderer.Renderer
import org.alexcawl.sculptor.foundation.renderer.BasicTextRenderer
import org.alexcawl.sculptor.foundation.renderer.BoxRenderer
import org.alexcawl.sculptor.foundation.renderer.ColumnRenderer
import org.alexcawl.sculptor.foundation.renderer.RowRenderer
import org.alexcawl.sculptor.runtime.engine.SculptorGlobalBuilder
import org.alexcawl.sculptor.runtime.engine.renderer

internal object FoundationRenderer {
    internal fun SculptorGlobalBuilder.install() {
        renderer { BasicTextRenderer() }
        renderer { BoxRenderer() }
        renderer { ColumnRenderer() }
        renderer { RowRenderer() }
    }
}
