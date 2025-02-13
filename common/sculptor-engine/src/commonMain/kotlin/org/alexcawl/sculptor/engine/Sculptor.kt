package org.alexcawl.sculptor.engine

import androidx.compose.runtime.Immutable
import org.alexcawl.sculptor.common.contract.SculptorScreen
import org.alexcawl.sculptor.common.contract.layout.LayoutContract
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import org.alexcawl.sculptor.common.layout.DelegateRender
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.layout.Renderer
import org.alexcawl.sculptor.common.layout.RendererScope
import org.alexcawl.sculptor.common.presenter.DelegateTransform
import org.alexcawl.sculptor.common.presenter.CommonPresenter
import org.alexcawl.sculptor.common.presenter.PresenterScope

@Immutable
data class Sculptor private constructor(private val context: SculptorContext) {
    @OptIn(InternalSculptorApi::class)
    private val delegateTransform: DelegateTransform
        get() = { inputClass, outputClass, value ->
            context.findPresenter(inputClass, outputClass).internalTransform(presenterScope, value)
        }

    @OptIn(InternalSculptorApi::class)
    private val presenterScope = PresenterScope(delegateTransform = delegateTransform)

    @OptIn(InternalSculptorApi::class)
    private val delegateRender: DelegateRender
        get() = { layout ->
            context.findRenderer(layout::class).render(rendererScope, layout)
        }

    @OptIn(InternalSculptorApi::class)
    private val rendererScope = RendererScope(delegateRender = delegateRender)

    fun transform(input: SculptorScreen): Layout {
        val rootLayoutContract: LayoutContract<*>? = input.layout.find { it.id == input.rootLayoutId }
    }

    companion object Factory {
        fun create(
            presenters: List<CommonPresenter<*, *>>,
            renderers: List<Renderer<Layout>>,
        ): Sculptor {
            val context = SculptorContext(
                presenters = presenters,
                renderers = renderers,
            )
            return Sculptor(context = context)
        }
    }
}

