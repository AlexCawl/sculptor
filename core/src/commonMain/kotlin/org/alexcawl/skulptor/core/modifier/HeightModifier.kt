package org.alexcawl.skulptor.core.modifier

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Constraints
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.Skulptor
import org.alexcawl.skulptor.core.SkulptorModifier
import org.alexcawl.skulptor.core.attribute.DpSerializable
import org.alexcawl.skulptor.core.attribute.alignment.AlignmentVerticalSerializable

sealed interface HeightModifier : SkulptorModifier {
    /**
     * Declare the preferred height of the content to be exactly [height]dp. The incoming measurement
     * [Constraints] may override this value, forcing the content to be either smaller or larger.
     *
     * For a modifier that sets the height of the content regardless of the incoming constraints see
     * [HeightModifier.RequiredHeight]. See [WidthModifier] or [SizeModifier] to set other preferred dimensions.
     * See [WidthModifier.WidthIn], [HeightModifier.HeightIn] or [SizeModifier.SizeIn] to set a preferred size range.
     */
    @Serializable
    @SerialName("modifier@height")
    data class Height(
        @SerialName("height")
        val height: DpSerializable
    ) : HeightModifier {
        override fun chain(initial: Modifier, skulptor: Skulptor, scope: Any): Modifier =
            initial.height(height = height)
    }

    /**
     * Constrain the height of the content to be between [min]dp and [max]dp as permitted
     * by the incoming measurement [Constraints]. If the incoming constraints are more restrictive
     * the requested size will obey the incoming constraints and attempt to be as close as possible
     * to the preferred size.
     */
    @Serializable
    @SerialName("modifier@height_in")
    data class HeightIn(
        @SerialName("min")
        val min: DpSerializable,
        @SerialName("max")
        val max: DpSerializable,
    ) : HeightModifier {
        override fun chain(initial: Modifier, skulptor: Skulptor, scope: Any): Modifier =
            initial.heightIn(
                min = min,
                max = max
            )
    }

    /**
     * Declare the height of the content to be exactly [height]dp. The incoming measurement
     * [Constraints] will not override this value. If the content chooses a size that does not
     * satisfy the incoming [Constraints], the parent layout will be reported a size coerced
     * in the [Constraints], and the position of the content will be automatically offset to be
     * centered on the space assigned to the child by the parent layout under the assumption that
     * [Constraints] were respected.
     */
    @Serializable
    @SerialName("modifier@required_height")
    data class RequiredHeight(
        @SerialName("height")
        val height: DpSerializable
    ) : HeightModifier {
        override fun chain(initial: Modifier, skulptor: Skulptor, scope: Any): Modifier =
            initial.requiredHeight(
                height = height
            )
    }

    /**
     * Constrain the height of the content to be between [min]dp and [max]dp.
     * If the content chooses a size that does not satisfy the incoming [Constraints], the
     * parent layout will be reported a size coerced in the [Constraints], and the position
     * of the content will be automatically offset to be centered on the space assigned to
     * the child by the parent layout under the assumption that [Constraints] were respected.
     */
    @Serializable
    @SerialName("modifier@required_height_in")
    data class RequiredHeightIn(
        @SerialName("min")
        val min: DpSerializable,
        @SerialName("max")
        val max: DpSerializable,
    ) : HeightModifier {
        override fun chain(initial: Modifier, skulptor: Skulptor, scope: Any): Modifier =
            initial.requiredHeightIn(
                min = min,
                max = max
            )
    }

    /**
     * Have the content fill (possibly only partially) the [Constraints.maxHeight] of the incoming
     * measurement constraints, by setting the [minimum height][Constraints.minHeight] and the
     * [maximum height][Constraints.maxHeight] to be equal to the
     * [maximum height][Constraints.maxHeight] multiplied by [fraction]. Note that, by default,
     * the [fraction] is 1, so the modifier will make the content fill the whole available height.
     * If the incoming maximum height is [Constraints.Infinity] this modifier will have no effect.
     */
    @Serializable
    @SerialName("modifier@fill_max_height")
    data class FillMaxHeight(
        @SerialName("fraction")
        val fraction: Float
    ) : HeightModifier {
        override fun chain(initial: Modifier, skulptor: Skulptor, scope: Any): Modifier =
            initial.fillMaxHeight(
                fraction = fraction
            )
    }

    /**
     * Allow the content to measure at its desired height without regard for the incoming measurement
     * [minimum height constraint][Constraints.minHeight], and, if [unbounded] is true, also without
     * regard for the incoming measurement [maximum height constraint][Constraints.maxHeight]. If the
     * content's measured size is smaller than the minimum height constraint, [align] it within
     * that minimum height space. If the content's measured size is larger than the maximum height
     * constraint (only possible when [unbounded] is true), [align] over the maximum height space.
     */
    @Serializable
    @SerialName("modifier@wrap_content_height")
    data class WrapContentHeight(
        @SerialName("align")
        val align: AlignmentVerticalSerializable,
        @SerialName("unbounded")
        val unbounded: Boolean,
    ) : HeightModifier {
        override fun chain(initial: Modifier, skulptor: Skulptor, scope: Any): Modifier =
            initial.wrapContentHeight(
                align = align,
                unbounded = unbounded
            )
    }
}
