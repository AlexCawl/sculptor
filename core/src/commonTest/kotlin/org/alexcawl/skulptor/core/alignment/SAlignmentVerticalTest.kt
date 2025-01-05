package org.alexcawl.skulptor.core.alignment

import org.alexcawl.skulptor.core.BaseSerializationTest
import org.junit.Test

class SAlignmentVerticalTest : BaseSerializationTest() {
    private inline fun serializationTest(
        identifier: String,
        factory: () -> SAlignmentVertical
    ) = baseSerializationTest(
        string = """{"type":"alignment@${identifier}"}""",
        factory = factory
    )

    @Test
    fun `Test SAlignment_Top`() = serializationTest(
        identifier = "top",
        factory = { SAlignmentVertical.Top }
    )

    @Test
    fun `Test SAlignment_CenterVertically`() = serializationTest(
        identifier = "center_vertically",
        factory = { SAlignmentVertical.CenterVertically }
    )

    @Test
    fun `Test SAlignment_Bottom`() = serializationTest(
        identifier = "bottom",
        factory = { SAlignmentVertical.Bottom }
    )
}
