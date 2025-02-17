package org.alexcawl.sculptor.foundation.presenter.common

import org.alexcawl.sculptor.common.presenter.CommonPresenter
import org.alexcawl.sculptor.common.presenter.PresenterScope
import kotlin.reflect.KClass
import androidx.compose.ui.semantics.Role as ComposeRole
import org.alexcawl.sculptor.foundation.contract.common.Role as SculptorRole

public class RolePresenter : CommonPresenter<SculptorRole, ComposeRole>() {
    override val input: KClass<SculptorRole> = SculptorRole::class
    override val output: KClass<ComposeRole> = ComposeRole::class

    override fun PresenterScope.transform(input: SculptorRole): ComposeRole {
        return when (input) {
            SculptorRole.Button -> ComposeRole.Button
            SculptorRole.Checkbox -> ComposeRole.Checkbox
            SculptorRole.DropdownList -> ComposeRole.DropdownList
            SculptorRole.Image -> ComposeRole.Image
            SculptorRole.RadioButton -> ComposeRole.RadioButton
            SculptorRole.Switch -> ComposeRole.Switch
            SculptorRole.Tab -> ComposeRole.Tab
        }
    }
}
