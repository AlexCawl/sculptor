package org.alexcawl.sculptor.common.presenter

interface PresenterState {
    val layoutPresenters: List<LayoutPresenter<*, *>>
    val modifierPresenters: List<ModifierPresenter<*>>
    val commonPresenters: List<CommonPresenter<*, *>>

    val presenters: List<Presenter<*, *>>
        get() = layoutPresenters + modifierPresenters + commonPresenters
}
