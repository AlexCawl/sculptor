package org.alexcawl.sculptor.foundation.presenter.modifier

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.core.presenter.ModifierPresenter
import org.alexcawl.sculptor.core.presenter.PresenterScope
import org.alexcawl.sculptor.core.presenter.map
import org.alexcawl.sculptor.foundation.contract.modifier.Background
import kotlin.reflect.KClass

public class BackgroundPresenter : ModifierPresenter<Background>() {
    override val input: KClass<Background> = Background::class

    public override suspend fun PresenterScope.dslTransform(input: Background): Modifier {
        return Modifier.background(
            color = map(input.color),
            shape = map(input.shape),
        )
    }
}
