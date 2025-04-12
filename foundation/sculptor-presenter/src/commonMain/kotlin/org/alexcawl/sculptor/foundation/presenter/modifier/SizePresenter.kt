package org.alexcawl.sculptor.foundation.presenter.modifier

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.presenter.ModifierPresenter
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.common.presenter.map
import org.alexcawl.sculptor.foundation.contract.modifier.FillMaxSize
import org.alexcawl.sculptor.foundation.contract.modifier.RequiredSize
import org.alexcawl.sculptor.foundation.contract.modifier.RequiredSizeIn
import org.alexcawl.sculptor.foundation.contract.modifier.Size
import org.alexcawl.sculptor.foundation.contract.modifier.SizeIn
import org.alexcawl.sculptor.foundation.contract.modifier.WrapContentSize
import kotlin.reflect.KClass

public class SizePresenter : ModifierPresenter<Size>() {
    override val input: KClass<Size> = Size::class

    override suspend fun PresenterScope.transform(input: Size): Modifier {
        return with(input) {
            Modifier.size(
                width = map(width),
                height = map(height),
            )
        }
    }
}

public class FillMaxSizePresenter : ModifierPresenter<FillMaxSize>() {
    override val input: KClass<FillMaxSize> = FillMaxSize::class

    override suspend fun PresenterScope.transform(input: FillMaxSize): Modifier {
        return with(input) {
            Modifier.fillMaxWidth(
                fraction = fraction,
            )
        }
    }
}

public class RequiredSizePresenter : ModifierPresenter<RequiredSize>() {
    override val input: KClass<RequiredSize> = RequiredSize::class

    override suspend fun PresenterScope.transform(input: RequiredSize): Modifier {
        return with(input) {
            Modifier.requiredSize(
                width = map(width),
                height = map(height),
            )
        }
    }
}

public class RequiredSizeInPresenter : ModifierPresenter<RequiredSizeIn>() {
    override val input: KClass<RequiredSizeIn> = RequiredSizeIn::class

    override suspend fun PresenterScope.transform(input: RequiredSizeIn): Modifier {
        return with(input) {
            Modifier.requiredSizeIn(
                minWidth = map(minWidth),
                minHeight = map(minHeight),
                maxWidth = map(maxWidth),
                maxHeight = map(maxHeight),
            )
        }
    }
}

public class SizeInPresenter : ModifierPresenter<SizeIn>() {
    override val input: KClass<SizeIn> = SizeIn::class

    override suspend fun PresenterScope.transform(input: SizeIn): Modifier {
        return with(input) {
            Modifier.sizeIn(
                minWidth = map(minWidth),
                minHeight = map(minHeight),
                maxWidth = map(maxWidth),
                maxHeight = map(maxHeight),
            )
        }
    }
}

public class WrapContentSizePresenter : ModifierPresenter<WrapContentSize>() {
    override val input: KClass<WrapContentSize> = WrapContentSize::class

    override suspend fun PresenterScope.transform(input: WrapContentSize): Modifier {
        return with(input) {
            Modifier.wrapContentSize(
                align = map(align),
                unbounded = unbounded,
            )
        }
    }
}
