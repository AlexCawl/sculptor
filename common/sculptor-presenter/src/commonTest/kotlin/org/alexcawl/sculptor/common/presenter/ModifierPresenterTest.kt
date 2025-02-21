package org.alexcawl.sculptor.common.presenter

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.ModifierContract

abstract class ModifierPresenterTest<MC : ModifierContract> : PresenterTest<MC, Modifier>() {
    abstract override val presenter: ModifierPresenter<MC>
    abstract override val input: MC
    abstract override val expected: Modifier
}
