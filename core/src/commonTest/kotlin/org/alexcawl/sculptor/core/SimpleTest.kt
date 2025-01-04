package org.alexcawl.sculptor.core

import kotlin.test.Test
import kotlin.test.assertEquals

class SimpleTest {
    @Test
    fun simpleTest() {
        assertEquals(
            expected = 4,
            actual = 2 + 2,
            message = "Dude, how..."
        )
    }
}
