package org.alexcawl.skulptor.core.modifier.size

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Constraints
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.alignment.SAlignmentHorizontalAndVertical
import org.alexcawl.skulptor.core.dimension.SDimensionDpSize
import org.alexcawl.skulptor.core.modifier.SkulptorModifier

@Serializable
sealed interface SizeModifier : SkulptorModifier {
    /**
     * Declare the preferred size of the content to be exactly [size]. The incoming
     * measurement [Constraints] may override this value, forcing the content to be either smaller or
     * larger.
     *
     * For a modifier that sets the size of the content regardless of the incoming constraints, see
     * [SizeModifier.RequiredSize]. See [WidthModifier] or [HeightModifier] to set width or height alone.
     * See [WidthModifier.WidthIn], [HeightModifier.HeightIn] or [SizeModifier.SizeIn] to set a preferred size range.
     */
    @Serializable
    @SerialName("modifier@size")
    data class Size(
        @SerialName("size")
        val size: SDimensionDpSize
    ) : SizeModifier {
        @Composable
        override fun asCompose(): Modifier =
            Modifier.size(
                size = size.asCompose()
            )
    }

    /**
     * Constrain the size of the content to be between [min] size and [max] size as permitted by the incoming
     * measurement [Constraints]. If the incoming constraints are more restrictive the requested size
     * will obey the incoming constraints and attempt to be as close as possible to the preferred size.
     */
    @Serializable
    @SerialName("modifier@size_in")
    data class SizeIn(
        @SerialName("min")
        val min: SDimensionDpSize,
        @SerialName("max")
        val max: SDimensionDpSize,
    ) : SizeModifier {
        @Composable
        override fun asCompose(): Modifier {
            val (minWidth, minHeight) = min.asCompose()
            val (maxWidth, maxHeight) = max.asCompose()
            return Modifier.sizeIn(
                minWidth = minWidth,
                minHeight = minHeight,
                maxWidth = maxWidth,
                maxHeight = maxHeight
            )
        }
    }

    /**
     * Declare the size of the content to be exactly [size]. The incoming measurement
     * [Constraints] will not override this value. If the content chooses a size that does not
     * satisfy the incoming [Constraints], the parent layout will be reported a size coerced
     * in the [Constraints], and the position of the content will be automatically offset to be
     * centered on the space assigned to the child by the parent layout under the assumption that
     * [Constraints] were respected.
     */
    @Serializable
    @SerialName("modifier@required_size")
    data class RequiredSize(
        @SerialName("size")
        val size: SDimensionDpSize
    ) : SizeModifier {
        @Composable
        override fun asCompose(): Modifier =
            Modifier.requiredSize(
                size = size.asCompose()
            )
    }

    /**
     * Constrain the size of the content to be between [min] size and [max] size.
     * If the content chooses a size that does not satisfy the incoming [Constraints], the
     * parent layout will be reported a size coerced in the [Constraints], and the position
     * of the content will be automatically offset to be centered on the space assigned to
     * the child by the parent layout under the assumption that [Constraints] were respected.
     */
    @Serializable
    @SerialName("modifier@required_size_in")
    data class RequiredSizeIn(
        @SerialName("min")
        val min: SDimensionDpSize,
        @SerialName("max")
        val max: SDimensionDpSize,
    ) : SizeModifier {
        @Composable
        override fun asCompose(): Modifier {
            val (minWidth, minHeight) = min.asCompose()
            val (maxWidth, maxHeight) = max.asCompose()
            return Modifier.requiredSizeIn(
                minWidth = minWidth,
                minHeight = minHeight,
                maxWidth = maxWidth,
                maxHeight = maxHeight
            )
        }
    }

    /**
     * Have the content fill (possibly only partially) the [Constraints.maxWidth] and
     * [Constraints.maxHeight] of the incoming measurement constraints, by setting the
     * [minimum width][Constraints.minWidth] and the [maximum width][Constraints.maxWidth] to be
     * equal to the [maximum width][Constraints.maxWidth] multiplied by [fraction], as well as
     * the [minimum height][Constraints.minHeight] and the [maximum height][Constraints.minHeight]
     * to be equal to the [maximum height][Constraints.maxHeight] multiplied by [fraction].
     * Note that, by default, the [fraction] is 1, so the modifier will make the content fill
     * the whole available space.
     * If the incoming maximum width or height is [Constraints.Infinity] this modifier will have no
     * effect in that dimension.
     */
    @Serializable
    @SerialName("modifier@fill_max_size")
    data class FillMaxSize(
        @SerialName("fraction")
        val fraction: Float
    ) : SizeModifier {
        @Composable
        override fun asCompose(): Modifier =
            Modifier.fillMaxSize(
                fraction = fraction
            )
    }

    /**
     * Allow the content to measure at its desired size without regard for the incoming measurement
     * [minimum width][Constraints.minWidth] or [minimum height][Constraints.minHeight] constraints,
     * and, if [unbounded] is true, also without regard for the incoming maximum constraints.
     * If the content's measured size is smaller than the minimum size constraint, [align] it
     * within that minimum sized space. If the content's measured size is larger than the maximum
     * size constraint (only possible when [unbounded] is true), [align] within the maximum space.
     */
    @Serializable
    @SerialName("modifier@wrap_content_size")
    data class WrapContentSize(
        @SerialName("align")
        val align: SAlignmentHorizontalAndVertical,
        @SerialName("unbounded")
        val unbounded: Boolean,
    ) : SizeModifier {
        @Composable
        override fun asCompose(): Modifier =
            Modifier.wrapContentSize(
                align = align.asCompose(),
                unbounded = unbounded
            )
    }
}
