@file:Suppress("UNCHECKED_CAST")

package org.alexcawl.sculptor.foundation.client

import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.renderer.Renderer
import org.alexcawl.sculptor.foundation.renderer.BasicTextRenderer
import org.alexcawl.sculptor.foundation.renderer.BoxRenderer
import org.alexcawl.sculptor.foundation.renderer.ColumnRenderer
import org.alexcawl.sculptor.foundation.renderer.RowRenderer

/**
 * TODO: docs
 */
public object FoundationRendererState : SculptorRenderer.State {
    override val renderers: List<Renderer<Layout>> = buildList {
        add(BasicTextRenderer() as Renderer<Layout>)
        add(BoxRenderer() as Renderer<Layout>)
        add(ColumnRenderer() as Renderer<Layout>)
        add(RowRenderer() as Renderer<Layout>)
    }
}
