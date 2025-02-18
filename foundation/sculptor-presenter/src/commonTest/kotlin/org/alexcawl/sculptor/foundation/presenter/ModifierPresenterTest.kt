package org.alexcawl.sculptor.foundation.presenter

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import org.alexcawl.sculptor.common.presenter.ModifierPresenter
import kotlin.test.Test
import kotlin.test.assertEquals

abstract class ModifierPresenterTest<MC : ModifierContract> : PresenterTest<MC, Modifier> {
    abstract override val presenter: ModifierPresenter<MC>

    abstract val input: MC
    abstract val expected: Modifier

    @OptIn(InternalSculptorApi::class)
    @Test
    final override fun transformationTest() {
        val actual = presenter.internalTransform(presenterScope, input)
        assertEquals(
            expected = expected,
            actual = actual,
            message = "Transformation failed for $input",
        )
    }
}
