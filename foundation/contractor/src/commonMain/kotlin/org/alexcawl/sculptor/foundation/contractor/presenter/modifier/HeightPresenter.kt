package org.alexcawl.sculptor.foundation.contractor.presenter.modifier

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.core.contractor.presenter.ModifierPresenter
import org.alexcawl.sculptor.core.contractor.presenter.PresenterScope
import org.alexcawl.sculptor.core.contractor.presenter.map
import org.alexcawl.sculptor.foundation.contract.modifier.FillMaxHeight
import org.alexcawl.sculptor.foundation.contract.modifier.Height
import org.alexcawl.sculptor.foundation.contract.modifier.HeightIn
import org.alexcawl.sculptor.foundation.contract.modifier.RequiredHeight
import org.alexcawl.sculptor.foundation.contract.modifier.RequiredHeightIn
import org.alexcawl.sculptor.foundation.contract.modifier.WrapContentHeight
import kotlin.reflect.KClass

internal class HeightPresenter : ModifierPresenter<Height>() {
    override val input: KClass<Height> = Height::class

    override suspend fun PresenterScope.dslTransform(input: Height): Modifier {
        return with(input) {
            Modifier.height(
                height = map(height),
            )
        }
    }
}

internal class FillMaxHeightPresenter : ModifierPresenter<FillMaxHeight>() {
    override val input: KClass<FillMaxHeight> = FillMaxHeight::class

    override suspend fun PresenterScope.dslTransform(input: FillMaxHeight): Modifier {
        return with(input) {
            Modifier.fillMaxHeight(
                fraction = fraction,
            )
        }
    }
}

internal class HeightInPresenter : ModifierPresenter<HeightIn>() {
    override val input: KClass<HeightIn> = HeightIn::class

    override suspend fun PresenterScope.dslTransform(input: HeightIn): Modifier {
        return with(input) {
            Modifier.heightIn(
                min = map(min),
                max = map(max),
            )
        }
    }
}

internal class RequiredHeightPresenter : ModifierPresenter<RequiredHeight>() {
    override val input: KClass<RequiredHeight> = RequiredHeight::class

    override suspend fun PresenterScope.dslTransform(input: RequiredHeight): Modifier {
        return with(input) {
            Modifier.requiredHeight(
                height = map(height),
            )
        }
    }
}

internal class RequiredHeightInPresenter : ModifierPresenter<RequiredHeightIn>() {
    override val input: KClass<RequiredHeightIn> = RequiredHeightIn::class

    override suspend fun PresenterScope.dslTransform(input: RequiredHeightIn): Modifier {
        return with(input) {
            Modifier.requiredHeightIn(
                min = map(min),
                max = map(max),
            )
        }
    }
}

internal class WrapContentHeightPresenter : ModifierPresenter<WrapContentHeight>() {
    override val input: KClass<WrapContentHeight> = WrapContentHeight::class

    override suspend fun PresenterScope.dslTransform(input: WrapContentHeight): Modifier {
        return with(input) {
            Modifier.wrapContentHeight(
                align = map(align),
                unbounded = unbounded,
            )
        }
    }
}
