package org.alexcawl.skulptor.core.alignment

import org.alexcawl.skulptor.core.BaseSerializationTest
import org.junit.Test

class SAlignmentHorizontalAndVerticalTest : BaseSerializationTest() {
    private inline fun serializationTest(
        identifier: String,
        factory: () -> SAlignmentHorizontalAndVertical
    ) = baseSerializationTest(
        string = """{"type":"alignment@${identifier}"}""",
        factory = factory
    )

    @Test
    fun `Test SAlignment_TopStart`() = serializationTest(
        identifier = "top_start",
        factory = { SAlignmentHorizontalAndVertical.TopStart }
    )

    @Test
    fun `Test SAlignment_TopCenter`() = serializationTest(
        identifier = "top_center",
        factory = { SAlignmentHorizontalAndVertical.TopCenter }
    )

    @Test
    fun `Test SAlignment_TopEnd`() = serializationTest(
        identifier = "top_end",
        factory = { SAlignmentHorizontalAndVertical.TopEnd }
    )

    @Test
    fun `Test SAlignment_CenterStart`() = serializationTest(
        identifier = "center_start",
        factory = { SAlignmentHorizontalAndVertical.CenterStart }
    )

    @Test
    fun `Test SAlignment_Center`() = serializationTest(
        identifier = "center",
        factory = { SAlignmentHorizontalAndVertical.Center }
    )

    @Test
    fun `Test SAlignment_CenterEnd`() = serializationTest(
        identifier = "center_end",
        factory = { SAlignmentHorizontalAndVertical.CenterEnd }
    )

    @Test
    fun `Test SAlignment_BottomStart`() = serializationTest(
        identifier = "bottom_start",
        factory = { SAlignmentHorizontalAndVertical.BottomStart }
    )

    @Test
    fun `Test SAlignment_BottomCenter`() = serializationTest(
        identifier = "bottom_center",
        factory = { SAlignmentHorizontalAndVertical.BottomCenter }
    )

    @Test
    fun `Test SAlignment_BottomEnd`() = serializationTest(
        identifier = "bottom_end",
        factory = { SAlignmentHorizontalAndVertical.BottomEnd }
    )
}
