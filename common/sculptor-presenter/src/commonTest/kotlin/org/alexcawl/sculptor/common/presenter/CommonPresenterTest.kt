package org.alexcawl.sculptor.common.presenter

abstract class CommonPresenterTest<I : Any, O : Any> : PresenterTest<I, O>() {
    abstract override val presenter: CommonPresenter<I, O>
    abstract override val input: I
    abstract override val expected: O
}
