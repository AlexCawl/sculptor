package org.alexcawl.skulptor.core.attribute

import org.alexcawl.skulptor.core.BaseSerializationTest
import org.junit.Test

class AlignmentVerticalTest : BaseSerializationTest() {
    private inline fun serializationTest(
        identifier: String,
        factory: () -> AlignmentWrapper.Vertical
    ) = baseSerializationTest(
        string = """{"type":"alignment@${identifier}"}""",
        factory = factory
    )

    @Test
    fun `Test SAlignment_Top`() = serializationTest(
        identifier = "top",
        factory = { AlignmentWrapper.Vertical.Top }
    )

    @Test
    fun `Test SAlignment_CenterVertically`() = serializationTest(
        identifier = "center_vertically",
        factory = { AlignmentWrapper.Vertical.CenterVertically }
    )

    @Test
    fun `Test SAlignment_Bottom`() = serializationTest(
        identifier = "bottom",
        factory = { AlignmentWrapper.Vertical.Bottom }
    )
}
