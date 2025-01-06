package org.alexcawl.skulptor.core.modifier.size

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Constraints
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.alignment.AlignmentWrapper
import org.alexcawl.skulptor.core.dimension.DpWrapper
import org.alexcawl.skulptor.core.modifier.SkulptorModifier

@Serializable
sealed interface WidthModifier : SkulptorModifier {
    /**
     * Declare the preferred width of the content to be exactly [width]dp. The incoming measurement
     * [Constraints] may override this value, forcing the content to be either smaller or larger.
     *
     * For a modifier that sets the width of the content regardless of the incoming constraints see
     * [WidthModifier.RequiredWidth]. See [HeightModifier] or [SizeModifier] to set other preferred dimensions.
     * See [WidthModifier.WidthIn], [HeightModifier.HeightIn] or [SizeModifier.SizeIn] to set a preferred size range.
     */
    @Serializable
    @SerialName("modifier@width")
    data class Width(
        @SerialName("width")
        val width: DpWrapper
    ) : WidthModifier {
        @Composable
        override fun asCompose(): Modifier =
            Modifier.width(
                width = width.asCompose()
            )
    }

    /**
     * Constrain the width of the content to be between [min]dp and [max]dp as permitted
     * by the incoming measurement [Constraints]. If the incoming constraints are more restrictive
     * the requested size will obey the incoming constraints and attempt to be as close as possible
     * to the preferred size.
     */
    @Serializable
    @SerialName("modifier@width_in")
    data class WidthIn(
        @SerialName("min")
        val min: DpWrapper,
        @SerialName("max")
        val max: DpWrapper,
    ) : WidthModifier {
        @Composable
        override fun asCompose(): Modifier =
            Modifier.widthIn(
                min = min.asCompose(),
                max = max.asCompose()
            )
    }

    /**
     * Declare the width of the content to be exactly [width]dp. The incoming measurement
     * [Constraints] will not override this value. If the content chooses a size that does not
     * satisfy the incoming [Constraints], the parent layout will be reported a size coerced
     * in the [Constraints], and the position of the content will be automatically offset to be
     * centered on the space assigned to the child by the parent layout under the assumption that
     * [Constraints] were respected.
     *
     * See [WidthModifier.RequiredWidthIn] and [SizeModifier.RequiredSizeIn] to set a size range.
     * See [WidthModifier.Width] to set a preferred width, which is only respected when the incoming
     * constraints allow it.
     */
    @Serializable
    @SerialName("modifier@required_width")
    data class RequiredWidth(
        @SerialName("width")
        val width: DpWrapper
    ) : WidthModifier {
        @Composable
        override fun asCompose(): Modifier =
            Modifier.requiredWidth(
                width = width.asCompose()
            )
    }

    /**
     * Constrain the width of the content to be between [min]dp and [max]dp.
     * If the content chooses a size that does not satisfy the incoming [Constraints], the
     * parent layout will be reported a size coerced in the [Constraints], and the position
     * of the content will be automatically offset to be centered on the space assigned to
     * the child by the parent layout under the assumption that [Constraints] were respected.
     */
    @Serializable
    @SerialName("modifier@required_width_in")
    data class RequiredWidthIn(
        @SerialName("min")
        val min: DpWrapper,
        @SerialName("max")
        val max: DpWrapper,
    ) : WidthModifier {
        @Composable
        override fun asCompose(): Modifier =
            Modifier.requiredWidthIn(
                min = min.asCompose(),
                max = max.asCompose()
            )
    }

    /**
     * Have the content fill (possibly only partially) the [Constraints.maxWidth] of the incoming
     * measurement constraints, by setting the [minimum width][Constraints.minWidth] and the
     * [maximum width][Constraints.maxWidth] to be equal to the [maximum width][Constraints.maxWidth]
     * multiplied by [fraction]. Note that, by default, the [fraction] is 1, so the modifier will
     * make the content fill the whole available width. If the incoming maximum width is
     * [Constraints.Infinity] this modifier will have no effect.
     */
    @Serializable
    @SerialName("modifier@fill_max_width")
    data class FillMaxWidth(
        @SerialName("fraction")
        val fraction: Float
    ) : WidthModifier {
        @Composable
        override fun asCompose(): Modifier =
            Modifier.fillMaxWidth(
                fraction = fraction
            )
    }

    /**
     * Allow the content to measure at its desired width without regard for the incoming measurement
     * [minimum width constraint][Constraints.minWidth], and, if [unbounded] is true, also without
     * regard for the incoming measurement [maximum width constraint][Constraints.maxWidth]. If
     * the content's measured size is smaller than the minimum width constraint, [align]
     * it within that minimum width space. If the content's measured size is larger than the maximum
     * width constraint (only possible when [unbounded] is true), [align] over the maximum
     * width space.
     */
    @Serializable
    @SerialName("modifier@wrap_content_width")
    data class WrapContentWidth(
        @SerialName("align")
        val align: AlignmentWrapper.Horizontal,
        @SerialName("unbounded")
        val unbounded: Boolean,
    ) : WidthModifier {
        @Composable
        override fun asCompose(): Modifier =
            Modifier.wrapContentWidth(
                align = align.asCompose(),
                unbounded = unbounded
            )
    }
}
