package org.alexcawl.sculptor.foundation.presenter.modifier

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.core.presenter.ModifierPresenter
import org.alexcawl.sculptor.core.presenter.PresenterScope
import org.alexcawl.sculptor.core.presenter.map
import org.alexcawl.sculptor.foundation.contract.modifier.Padding
import kotlin.reflect.KClass

public class PaddingPresenter : ModifierPresenter<Padding>() {
    override val input: KClass<Padding> = Padding::class

    override suspend fun PresenterScope.dslTransform(input: Padding): Modifier {
        return with(input) {
            Modifier.padding(
                start = map(start),
                top = map(top),
                end = map(end),
                bottom = map(bottom),
            )
        }
    }
}
