package org.alexcawl.sculptor.foundation.presenter.modifier

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.presenter.ModifierPresenter
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.foundation.contract.modifier.Padding
import kotlin.reflect.KClass

public class PaddingPresenter : ModifierPresenter<Padding>() {
    override val input: KClass<Padding> = Padding::class

    override fun PresenterScope.transform(input: Padding): Modifier {
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
