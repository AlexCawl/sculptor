package org.alexcawl.sculptor.foundation.presenter.modifier

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.core.presenter.ModifierPresenter
import org.alexcawl.sculptor.core.presenter.PresenterScope
import org.alexcawl.sculptor.core.presenter.map
import org.alexcawl.sculptor.foundation.contract.modifier.Clickable
import org.alexcawl.sculptor.foundation.contract.modifier.CombinedClickable
import kotlin.reflect.KClass

public class ClickablePresenter : ModifierPresenter<Clickable>() {
    override val input: KClass<Clickable> = Clickable::class

    public override suspend fun PresenterScope.transform(input: Clickable): Modifier {
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

public class CombinedClickablePresenter : ModifierPresenter<CombinedClickable>() {
    override val input: KClass<CombinedClickable> = CombinedClickable::class

    @OptIn(ExperimentalFoundationApi::class)
    override suspend fun PresenterScope.transform(input: CombinedClickable): Modifier {
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
