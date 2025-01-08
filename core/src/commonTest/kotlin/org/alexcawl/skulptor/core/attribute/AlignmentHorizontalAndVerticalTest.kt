package org.alexcawl.skulptor.core.attribute

import org.alexcawl.skulptor.core.BaseSerializationTest
import org.alexcawl.skulptor.core.provider.AlignmentWrapper
import org.junit.Test

class AlignmentHorizontalAndVerticalTest : BaseSerializationTest() {
    private inline fun serializationTest(
        identifier: String,
        factory: () -> AlignmentWrapper.HorizontalAndVertical
    ) = baseSerializationTest(
        string = """{"type":"alignment@${identifier}"}""",
        factory = factory
    )

    @Test
    fun `Test Alignment_TopStart`() = serializationTest(
        identifier = "top_start",
        factory = { AlignmentWrapper.HorizontalAndVertical.TopStart }
    )

    @Test
    fun `Test Alignment_TopCenter`() = serializationTest(
        identifier = "top_center",
        factory = { AlignmentWrapper.HorizontalAndVertical.TopCenter }
    )

    @Test
    fun `Test Alignment_TopEnd`() = serializationTest(
        identifier = "top_end",
        factory = { AlignmentWrapper.HorizontalAndVertical.TopEnd }
    )

    @Test
    fun `Test Alignment_CenterStart`() = serializationTest(
        identifier = "center_start",
        factory = { AlignmentWrapper.HorizontalAndVertical.CenterStart }
    )

    @Test
    fun `Test Alignment_Center`() = serializationTest(
        identifier = "center",
        factory = { AlignmentWrapper.HorizontalAndVertical.Center }
    )

    @Test
    fun `Test Alignment_CenterEnd`() = serializationTest(
        identifier = "center_end",
        factory = { AlignmentWrapper.HorizontalAndVertical.CenterEnd }
    )

    @Test
    fun `Test Alignment_BottomStart`() = serializationTest(
        identifier = "bottom_start",
        factory = { AlignmentWrapper.HorizontalAndVertical.BottomStart }
    )

    @Test
    fun `Test Alignment_BottomCenter`() = serializationTest(
        identifier = "bottom_center",
        factory = { AlignmentWrapper.HorizontalAndVertical.BottomCenter }
    )

    @Test
    fun `Test Alignment_BottomEnd`() = serializationTest(
        identifier = "bottom_end",
        factory = { AlignmentWrapper.HorizontalAndVertical.BottomEnd }
    )
}
