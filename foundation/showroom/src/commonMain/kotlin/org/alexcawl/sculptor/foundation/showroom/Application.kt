package org.alexcawl.sculptor.foundation.showroom

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import org.alexcawl.sculptor.engine.Sculptor
import org.alexcawl.sculptor.engine.SculptorContractor
import org.alexcawl.sculptor.engine.asLaunchMode
import org.alexcawl.sculptor.foundation.client.FoundationContractorState
import org.alexcawl.sculptor.foundation.client.FoundationSculptor

public val response: String = """
    {
        "values": [],
        "sections": [
            {
                "id": "root",
                "modifiers": [],
                "states": [
                    {
                        "type": "row@state",
                        "id": "1",
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
                "id": "text1",
                "modifiers": [],
                "states": [
                    {
                        "type": "basic_text@state",
                        "id": "1",
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
                "id": "text2",
                "modifiers": [],
                "states": [
                    {
                        "type": "basic_text@state",
                        "id": "1",
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

@Composable
public fun Application() {
    val string = SculptorContractor.create(FoundationContractorState).decode(response).getOrThrow()
    val launchMode = string.asLaunchMode()
    Sculptor(
        sculptorState = FoundationSculptor(),
        launchMode = launchMode,
        loading = {
            BasicText(text = "Loading...")
        },
        error = {
            BasicText(text = "Error")
        },
    )
}
