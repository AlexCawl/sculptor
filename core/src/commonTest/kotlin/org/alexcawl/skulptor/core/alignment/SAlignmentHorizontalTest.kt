package org.alexcawl.skulptor.core.alignment

import org.alexcawl.skulptor.core.BaseSerializationTest
import org.junit.Test

class SAlignmentHorizontalTest : BaseSerializationTest() {
    private inline fun serializationTest(
        identifier: String,
        factory: () -> SAlignmentHorizontal
    ) = baseSerializationTest(
        string = """{"type":"alignment@${identifier}"}""",
        factory = factory
    )

    @Test
    fun `Test SAlignment_Start`() = serializationTest(
        identifier = "start",
        factory = { SAlignmentHorizontal.Start }
    )

    @Test
    fun `Test SAlignment_CenterHorizontally`() = serializationTest(
        identifier = "center_horizontally",
        factory = { SAlignmentHorizontal.CenterHorizontally }
    )

    @Test
    fun `Test SAlignment_End`() = serializationTest(
        identifier = "end",
        factory = { SAlignmentHorizontal.End }
    )
}
