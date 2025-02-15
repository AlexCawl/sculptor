package org.alexcawl.sculptor.foundation.presenter.modifier

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.presenter.ModifierPresenter
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.foundation.contract.modifier.background.Background
import kotlin.reflect.KClass

class BackgroundModifierPresenter : ModifierPresenter<Background>() {
    override val input: KClass<Background> = Background::class

    override fun PresenterScope.transform(input: Background): Modifier {
        return Modifier.background(
            color = map(input.color),
            shape = map(input.shape),
        )
    }
}
