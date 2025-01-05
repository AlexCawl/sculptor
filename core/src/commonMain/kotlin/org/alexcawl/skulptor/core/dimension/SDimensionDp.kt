package org.alexcawl.skulptor.core.dimension

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SkulptorAttribute
import org.alexcawl.skulptor.core.dimension.SDimensionDp.Hairline

/**
 * Dimension value representing device-independent pixels (dp). Component APIs specify their
 * dimensions such as line thickness in DP with Dp objects. Hairline (1 pixel) thickness
 * may be specified with [Hairline], a dimension that take up no space. Dp are normally
 * defined using [dp], which can be applied to [Int], [Double], and [Float].
 */
@Serializable
sealed interface SDimensionDp : SkulptorAttribute<Dp> {
    @Serializable
    sealed interface DpFromNumber : SDimensionDp {
        val value: Number

        /**
         * Create a [SDimensionDp] using an [Int].
         */
        @Serializable
        @SerialName("dimension@dp_int")
        data class DpFromInt(
            @SerialName("value")
            override val value: Int
        ) : DpFromNumber {
            override fun asCompose(): Dp =
                value.dp
        }

        /**
         * Create a [SDimensionDp] using an [Float].
         */
        @Serializable
        @SerialName("dimension@dp_float")
        data class DpFromFloat(
            @SerialName("value")
            override val value: Float
        ) : DpFromNumber {
            override fun asCompose(): Dp =
                value.dp
        }

        /**
         * Create a [SDimensionDp] using an [Double].
         */
        @Serializable
        @SerialName("dimension@dp_double")
        data class DpFromDouble(
            @SerialName("value")
            override val value: Double
        ) : DpFromNumber {
            override fun asCompose(): Dp =
                value.dp
        }
    }

    /**
     * A dimension used to represent a hairline drawing element. Hairline elements take up no
     * space, but will draw a single pixel, independent of the device's resolution and density.
     */
    @Serializable
    @SerialName("dimension@dp_hairline")
    data object Hairline : SDimensionDp {
        override fun asCompose(): Dp =
            Dp.Hairline
    }

    /**
     * Infinite dp dimension.
     */
    @Serializable
    @SerialName("dimension@dp_infinity")
    data object Infinity : SDimensionDp {
        override fun asCompose(): Dp =
            Dp.Infinity
    }

    /**
     * Constant that means unspecified Dp
     */
    @Serializable
    @SerialName("dimension@dp_unspecified")
    data object Unspecified : SDimensionDp {
        override fun asCompose(): Dp =
            Dp.Unspecified
    }
}
