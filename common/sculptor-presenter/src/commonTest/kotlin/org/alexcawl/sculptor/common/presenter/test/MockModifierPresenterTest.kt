package org.alexcawl.sculptor.common.presenter.test

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.presenter.ModifierPresenter
import org.alexcawl.sculptor.common.presenter.ModifierPresenterTest
import org.alexcawl.sculptor.common.presenter.mock.Mock
import org.alexcawl.sculptor.common.presenter.mock.MockModifierContract
import org.alexcawl.sculptor.common.presenter.modifierPresenter
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MockModifierPresenterTest : ModifierPresenterTest<MockModifierContract>() {
    override val presenter: ModifierPresenter<MockModifierContract> = modifierPresenter(
        modifierContract = MockModifierContract::class,
        transformer = { Modifier }
    )

    override val input: MockModifierContract = MockModifierContract(
        value = Mock(
            data = "Hello World!",
        ),
    )

    override val expected: Modifier = Modifier
}
