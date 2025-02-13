package org.alexcawl.sculptor.engine

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.layout.LayoutContract
import org.alexcawl.sculptor.common.contract.layout.StateContract
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.layout.Renderer
import org.alexcawl.sculptor.common.layout.RendererScope
import org.alexcawl.sculptor.common.layout.renderer
import org.alexcawl.sculptor.common.presenter.LayoutPresenter
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.common.presenter.layoutPresenter
import kotlin.reflect.KClass

interface Block<C : LayoutContract, S : StateContract, L : Layout> {
    val presenter: LayoutPresenter<C, S, L>
    val renderer: Renderer<L>
}

inline fun <reified C : LayoutContract, reified S : StateContract, reified L : Layout> block(
    presenter: LayoutPresenter<C, S, L>,
    renderer: Renderer<L>,
): Block<C, S, L> = object : Block<C, S, L> {
    override val presenter: LayoutPresenter<C, S, L> = presenter
    override val renderer: Renderer<L> = renderer
}

inline fun <reified C : LayoutContract, reified S : StateContract, reified L : Layout> block(
    contractType: KClass<C>,
    layoutType: KClass<L>,
    crossinline transformBlock: PresenterScope.(id: String, modifier: Modifier, state: S) -> L,
    crossinline rendererBlock: @Composable RendererScope.(layout: L) -> Unit,
): Block<C, S, L> = object : Block<C, S, L> {
    override val presenter: LayoutPresenter<C, S, L> = layoutPresenter(
        contract = contractType,
        layout = layoutType,
        transformer = transformBlock
    )

    override val renderer: Renderer<L> = renderer(
        layoutType = layoutType,
        renderer = rendererBlock
    )
}
