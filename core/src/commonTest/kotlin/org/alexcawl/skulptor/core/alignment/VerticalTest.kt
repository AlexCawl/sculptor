package org.alexcawl.skulptor.core.alignment

import org.alexcawl.skulptor.core.BaseSerializationTest
import org.alexcawl.skulptor.core.attribute.AlignmentWrapper
import org.junit.Test

class VerticalTest : BaseSerializationTest() {
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
