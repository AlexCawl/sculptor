package org.alexcawl.sculptor.foundation.showroom

import androidx.compose.runtime.Composable
import org.alexcawl.sculptor.common.contract.Scaffold
import org.alexcawl.sculptor.common.contract.id
import org.alexcawl.sculptor.engine.Sculptor
import org.alexcawl.sculptor.engine.asLaunchMode
import org.alexcawl.sculptor.foundation.client.FoundationSculptor
import org.alexcawl.sculptor.foundation.contract.common.Alignment
import org.alexcawl.sculptor.foundation.contract.common.Arrangement
import org.alexcawl.sculptor.foundation.contract.layout.BasicTextLayoutContract
import org.alexcawl.sculptor.foundation.contract.layout.BasicTextStateContract
import org.alexcawl.sculptor.foundation.contract.layout.BasicTextStateContract.TextType
import org.alexcawl.sculptor.foundation.contract.layout.RowLayoutContract
import org.alexcawl.sculptor.foundation.contract.layout.RowStateContract

public val response: String = """
    {
        "rootLayoutId": "root",
        "values": [],
        "layouts": [
            {
                "type": "row@layout",
                "id": "root",
                "state": "1",
                "modifiers": [],
                "states": [
                    {
                        "id": "1",
                        "modifiers": [],
                        "horizontal_arrangement": {
                            "type": "horizontal@start"
                        },
                        "vertical_alignment": {
                            "type": "vertical@top"
                        },
                        "content": [
                            "text1",
                            "text2"
                        ]
                    }
                ]
            },
            {
                "type": "basic_text@layout",
                "id": "text1",
                "state": "1",
                "modifiers": [],
                "states": [
                    {
                        "id": "1",
                        "modifiers": [],
                        "soft_wrap": false,
                        "max_lines": 1,
                        "min_lines": 1,
                        "text": {
                            "type": "static",
                            "text": "Hello World 1"
                        }
                    }
                ]
            },
            {
                "type": "basic_text@layout",
                "id": "text2",
                "state": "1",
                "modifiers": [],
                "states": [
                    {
                        "id": "1",
                        "modifiers": [],
                        "soft_wrap": false,
                        "max_lines": 1,
                        "min_lines": 1,
                        "text": {
                            "type": "static",
                            "text": "Hello World 2"
                        }
                    }
                ]
            }
        ]
    }
""".trimIndent()

public val scaffold: Scaffold = Scaffold(
    rootLayoutId = "root".id,
    values = listOf(),
    layouts = listOf(
        RowLayoutContract(
            id = "root".id,
            modifiers = listOf(),
            state = "1".id,
            states = listOf(
                RowStateContract(
                    id = "1".id,
                    modifiers = listOf(),
                    horizontalArrangement = Arrangement.Horizontal.Start,
                    verticalAlignment = Alignment.Vertical.Top,
                    content = listOf("text1".id, "text2".id)
                )
            ),
        ),
        BasicTextLayoutContract(
            id = "text1".id,
            modifiers = listOf(),
            state = "1".id,
            states = listOf(
                BasicTextStateContract(
                    id = "1".id,
                    modifiers = listOf(),
                    softWrap = false,
                    maxLines = 1,
                    minLines = 1,
                    textType = TextType.Static(
                        text = "Hello World 1"
                    ),
                )
            ),
        ),
        BasicTextLayoutContract(
            id = "text2".id,
            modifiers = listOf(),
            state = "1".id,
            states = listOf(
                BasicTextStateContract(
                    id = "1".id,
                    modifiers = listOf(),
                    softWrap = false,
                    maxLines = 1,
                    minLines = 1,
                    textType = TextType.Static(
                        text = "Hello World 2"
                    )
                )
            ),
        ),
    )
)

@Composable
public fun Application() {
    val launchMode = scaffold.asLaunchMode()
    Sculptor(
        sculptorState = FoundationSculptor(),
        launchMode = launchMode,
        loading = {},
        error = {},
    )
}
