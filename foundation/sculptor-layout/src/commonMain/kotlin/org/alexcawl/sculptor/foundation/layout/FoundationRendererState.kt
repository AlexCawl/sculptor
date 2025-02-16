@file:Suppress("UNCHECKED_CAST")

package org.alexcawl.sculptor.foundation.layout

import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.layout.Renderer
import org.alexcawl.sculptor.common.layout.SculptorRenderer
import org.alexcawl.sculptor.foundation.layout.renderer.BasicTextRenderer
import org.alexcawl.sculptor.foundation.layout.renderer.BoxRenderer
import org.alexcawl.sculptor.foundation.layout.renderer.ColumnRenderer
import org.alexcawl.sculptor.foundation.layout.renderer.RowRenderer

object FoundationRendererState : SculptorRenderer.State {
    override val renderers: List<Renderer<Layout>> = buildList {
        add(BasicTextRenderer() as Renderer<Layout>)
        add(BoxRenderer() as Renderer<Layout>)
        add(ColumnRenderer() as Renderer<Layout>)
        add(RowRenderer() as Renderer<Layout>)
    }
}
