package org.alexcawl.sculptor.foundation.contractor

import org.alexcawl.sculptor.core.contractor.RendererBundle
import org.alexcawl.sculptor.core.contractor.renderer
import org.alexcawl.sculptor.foundation.contractor.renderer.BasicTextRenderer
import org.alexcawl.sculptor.foundation.contractor.renderer.BoxRenderer
import org.alexcawl.sculptor.foundation.contractor.renderer.ColumnRenderer
import org.alexcawl.sculptor.foundation.contractor.renderer.RowRenderer

internal object FoundationRendererBundle : RendererBundle {
    override val renderers: RendererBundle.Consumer.() -> Unit = {
        renderer(::BasicTextRenderer)
        renderer(::BoxRenderer)
        renderer(::ColumnRenderer)
        renderer(::RowRenderer)
    }
}
