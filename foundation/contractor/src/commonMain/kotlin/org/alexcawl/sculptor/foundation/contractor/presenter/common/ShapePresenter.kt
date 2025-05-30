package org.alexcawl.sculptor.foundation.contractor.presenter.common

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.RectangleShape
import org.alexcawl.sculptor.core.contractor.presenter.Presenter
import org.alexcawl.sculptor.core.contractor.presenter.PresenterScope
import org.alexcawl.sculptor.core.contractor.presenter.map
import kotlin.reflect.KClass
import androidx.compose.ui.graphics.Shape as ComposeShape
import androidx.compose.ui.unit.Dp as ComposeDp
import org.alexcawl.sculptor.foundation.contract.common.Dp as SculptorDp
import org.alexcawl.sculptor.foundation.contract.common.Shape as SculptorShape

internal class ShapePresenter : Presenter<SculptorShape, ComposeShape>() {
    override val input: KClass<SculptorShape> = SculptorShape::class
    override val output: KClass<ComposeShape> = ComposeShape::class

    override suspend fun PresenterScope.dslTransform(input: SculptorShape): ComposeShape {
        return when (input) {
            SculptorShape.Circle -> CircleShape
            SculptorShape.Rectangle -> RectangleShape
            is SculptorShape.CutCorner -> transformCut(input)
            is SculptorShape.RoundedCorner -> transformRounded(input)
        }
    }

    private suspend fun PresenterScope.transformCut(input: SculptorShape.CutCorner): ComposeShape {
        return when (input) {
            is SculptorShape.CutCorner.DPixel -> CutCornerShape(
                topStart = map<SculptorDp, ComposeDp>(input.topStart),
                topEnd = map<SculptorDp, ComposeDp>(input.topEnd),
                bottomEnd = map<SculptorDp, ComposeDp>(input.bottomEnd),
                bottomStart = map<SculptorDp, ComposeDp>(input.bottomStart),
            )

            is SculptorShape.CutCorner.Percent -> CutCornerShape(
                topStartPercent = input.topStart,
                topEndPercent = input.topEnd,
                bottomEndPercent = input.bottomEnd,
                bottomStartPercent = input.bottomStart,
            )
        }
    }

    private suspend fun PresenterScope.transformRounded(input: SculptorShape.RoundedCorner): ComposeShape {
        return when (input) {
            is SculptorShape.RoundedCorner.DPixel -> RoundedCornerShape(
                topStart = map<SculptorDp, ComposeDp>(input.topStart),
                topEnd = map<SculptorDp, ComposeDp>(input.topEnd),
                bottomEnd = map<SculptorDp, ComposeDp>(input.bottomEnd),
                bottomStart = map<SculptorDp, ComposeDp>(input.bottomStart),
            )

            is SculptorShape.RoundedCorner.Percent -> RoundedCornerShape(
                topStartPercent = input.topStart,
                topEndPercent = input.topEnd,
                bottomEndPercent = input.bottomEnd,
                bottomStartPercent = input.bottomStart,
            )
        }
    }
}
