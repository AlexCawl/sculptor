package org.alexcawl.sculptor.foundation.presenter

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.presenter.ModifierPresenter

abstract class ModifierPresenterTest<MC : ModifierContract> : PresenterTest<MC, Modifier>() {
    abstract override val presenter: ModifierPresenter<MC>

    abstract override val input: MC
    abstract override val expected: Modifier
}
