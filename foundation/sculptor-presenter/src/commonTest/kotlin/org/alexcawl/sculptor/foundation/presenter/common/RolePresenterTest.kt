package org.alexcawl.sculptor.foundation.presenter.common

import org.alexcawl.sculptor.foundation.presenter.CommonPresenterTest
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import androidx.compose.ui.semantics.Role as ComposeRole
import org.alexcawl.sculptor.foundation.contract.common.Role as SculptorRole

@RunWith(Parameterized::class)
class RolePresenterTest(state: State) : CommonPresenterTest<SculptorRole, ComposeRole>() {
    override val presenter: RolePresenter = RolePresenter()
    override val input: SculptorRole = state.input
    override val expected: ComposeRole = state.expected

    data class State(
        val input: SculptorRole,
        val expected: ComposeRole,
    )

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{0}")
        fun run(): List<State> = listOf(
            State(
                input = SculptorRole.Button,
                expected = ComposeRole.Button,
            ),
            State(
                input = SculptorRole.Checkbox,
                expected = ComposeRole.Checkbox,
            ),
            State(
                input = SculptorRole.DropdownList,
                expected = ComposeRole.DropdownList,
            ),
            State(
                input = SculptorRole.Image,
                expected = ComposeRole.Image,
            ),
            State(
                input = SculptorRole.RadioButton,
                expected = ComposeRole.RadioButton,
            ),
            State(
                input = SculptorRole.Switch,
                expected = ComposeRole.Switch,
            ),
            State(
                input = SculptorRole.Tab,
                expected = ComposeRole.Tab,
            ),
        )
    }
}
