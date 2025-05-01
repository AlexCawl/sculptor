package org.alexcawl.sculptor.foundation.presenter.modifier

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.core.presenter.ModifierPresenter
import org.alexcawl.sculptor.core.presenter.PresenterScope
import org.alexcawl.sculptor.core.presenter.map
import org.alexcawl.sculptor.foundation.contract.modifier.FillMaxWidth
import org.alexcawl.sculptor.foundation.contract.modifier.RequiredWidth
import org.alexcawl.sculptor.foundation.contract.modifier.RequiredWidthIn
import org.alexcawl.sculptor.foundation.contract.modifier.Width
import org.alexcawl.sculptor.foundation.contract.modifier.WidthIn
import org.alexcawl.sculptor.foundation.contract.modifier.WrapContentWidth
import kotlin.reflect.KClass

public class WidthPresenter : ModifierPresenter<Width>() {
    override val input: KClass<Width> = Width::class

    override suspend fun PresenterScope.transform(input: Width): Modifier {
        return with(input) {
            Modifier.width(
                width = map(width),
            )
        }
    }
}

public class RequiredWidthInPresenter : ModifierPresenter<RequiredWidthIn>() {
    override val input: KClass<RequiredWidthIn> = RequiredWidthIn::class

    override suspend fun PresenterScope.transform(input: RequiredWidthIn): Modifier {
        return with(input) {
            Modifier.requiredWidth(
                width = map(min),
            )
        }
    }
}

public class FillMaxWidthPresenter : ModifierPresenter<FillMaxWidth>() {
    override val input: KClass<FillMaxWidth> = FillMaxWidth::class

    override suspend fun PresenterScope.transform(input: FillMaxWidth): Modifier {
        return with(input) {
            Modifier.fillMaxWidth(
                fraction = map(fraction),
            )
        }
    }
}

public class RequiredWidthPresenter : ModifierPresenter<RequiredWidth>() {
    override val input: KClass<RequiredWidth> = RequiredWidth::class

    override suspend fun PresenterScope.transform(input: RequiredWidth): Modifier {
        return with(input) {
            Modifier.requiredWidth(
                width = map(width),
            )
        }
    }
}

public class WidthInPresenter : ModifierPresenter<WidthIn>() {
    override val input: KClass<WidthIn> = WidthIn::class

    override suspend fun PresenterScope.transform(input: WidthIn): Modifier {
        return with(input) {
            Modifier.widthIn(
                min = map(min),
                max = map(max),
            )
        }
    }
}

public class WrapContentWidthPresenter : ModifierPresenter<WrapContentWidth>() {
    override val input: KClass<WrapContentWidth> = WrapContentWidth::class

    override suspend fun PresenterScope.transform(input: WrapContentWidth): Modifier {
        return with(input) {
            Modifier.wrapContentWidth(
                align = map(align),
                unbounded = unbounded,
            )
        }
    }
}
