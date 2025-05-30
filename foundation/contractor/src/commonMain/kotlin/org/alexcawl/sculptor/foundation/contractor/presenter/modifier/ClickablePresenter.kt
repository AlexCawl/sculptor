package org.alexcawl.sculptor.foundation.contractor.presenter.modifier

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.core.contractor.presenter.ModifierPresenter
import org.alexcawl.sculptor.core.contractor.presenter.PresenterScope
import org.alexcawl.sculptor.core.contractor.presenter.map
import org.alexcawl.sculptor.foundation.contract.modifier.Clickable
import org.alexcawl.sculptor.foundation.contract.modifier.CombinedClickable
import kotlin.reflect.KClass

internal class ClickablePresenter : ModifierPresenter<Clickable>() {
    override val input: KClass<Clickable> = Clickable::class

    override suspend fun PresenterScope.dslTransform(input: Clickable): Modifier {
        return with(input) {
            Modifier.clickable(
                enabled = enabled,
                role = map(role),
                onClickLabel = onClickLabel,
                onClick = { /* TODO */ },
            )
        }
    }
}

internal class CombinedClickablePresenter : ModifierPresenter<CombinedClickable>() {
    override val input: KClass<CombinedClickable> = CombinedClickable::class

    @OptIn(ExperimentalFoundationApi::class)
    override suspend fun PresenterScope.dslTransform(input: CombinedClickable): Modifier {
        return with(input) {
            Modifier.combinedClickable(
                enabled = enabled,
                role = map(role),
                onClickLabel = onClickLabel,
                onClick = { /* TODO */ },
                onLongClick = { /* TODO */ },
                onLongClickLabel = onLongClickLabel,
                onDoubleClick = { /* TODO */ },
            )
        }
    }
}
