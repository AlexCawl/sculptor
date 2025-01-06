package org.alexcawl.skulptor.core.dimension

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SkulptorAttribute
import org.alexcawl.skulptor.core.dimension.DpWrapper.Hairline

/**
 * Dimension value representing device-independent pixels (dp). Component APIs specify their
 * dimensions such as line thickness in DP with Dp objects. Hairline (1 pixel) thickness
 * may be specified with [Hairline], a dimension that take up no space. Dp are normally
 * defined using [dp], which can be applied to [Int], [Double], and [Float].
 */
@Serializable
sealed interface DpWrapper : SkulptorAttribute<Dp> {
    @Serializable
    sealed interface FromNumber : DpWrapper {
        val value: Number

        /**
         * Create a [DpWrapper] using an [Int].
         */
        @Serializable
        @SerialName("dimension@dp_int")
        data class FromInt(
            @SerialName("value")
            override val value: Int
        ) : FromNumber {
            override fun asCompose(): Dp =
                value.dp
        }

        /**
         * Create a [DpWrapper] using an [Float].
         */
        @Serializable
        @SerialName("dimension@dp_float")
        data class FromFloat(
            @SerialName("value")
            override val value: Float
        ) : FromNumber {
            override fun asCompose(): Dp =
                value.dp
        }

        /**
         * Create a [DpWrapper] using an [Double].
         */
        @Serializable
        @SerialName("dimension@dp_double")
        data class FromDouble(
            @SerialName("value")
            override val value: Double
        ) : FromNumber {
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
    data object Hairline : DpWrapper {
        override fun asCompose(): Dp =
            Dp.Hairline
    }

    /**
     * Infinite dp dimension.
     */
    @Serializable
    @SerialName("dimension@dp_infinity")
    data object Infinity : DpWrapper {
        override fun asCompose(): Dp =
            Dp.Infinity
    }

    /**
     * Constant that means unspecified Dp
     */
    @Serializable
    @SerialName("dimension@dp_unspecified")
    data object Unspecified : DpWrapper {
        override fun asCompose(): Dp =
            Dp.Unspecified
    }
}
