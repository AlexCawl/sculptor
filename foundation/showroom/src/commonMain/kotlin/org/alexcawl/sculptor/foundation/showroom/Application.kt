package org.alexcawl.sculptor.foundation.showroom

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import org.alexcawl.sculptor.common.builder.scaffold
import org.alexcawl.sculptor.common.contract.Section
import org.alexcawl.sculptor.common.contract.Scaffold
import org.alexcawl.sculptor.common.contract.id
import org.alexcawl.sculptor.engine.Sculptor
import org.alexcawl.sculptor.engine.SculptorContractor
import org.alexcawl.sculptor.engine.asLaunchMode
import org.alexcawl.sculptor.foundation.client.FoundationContractorState
import org.alexcawl.sculptor.foundation.client.FoundationSculptor
import org.alexcawl.sculptor.foundation.contract.common.Alignment
import org.alexcawl.sculptor.foundation.contract.common.Arrangement
import org.alexcawl.sculptor.foundation.contract.layout.BasicTextState
import org.alexcawl.sculptor.foundation.contract.layout.BasicTextState.TextType
import org.alexcawl.sculptor.foundation.contract.layout.RowState

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

public val scaffold: Scaffold = scaffold {
    singleSection("root") {
        RowState(
            id = "1".id,
            horizontalArrangement = Arrangement.Horizontal.Start,
            verticalAlignment = Alignment.Vertical.Top,
            content = listOf("text1".id, "text2".id)
        )
    }
    singleSection("text1") {
        BasicTextState(
            id = "1".id,
            softWrap = false,
            maxLines = 1,
            minLines = 1,
            textType = TextType.Static(
                text = "Hello World 1"
            ),
        )
    }
    singleSection("text2") {
        BasicTextState(
            id = "1".id,
            softWrap = false,
            maxLines = 1,
            minLines = 1,
            textType = TextType.Static(
                text = "Hello World 2"
            )
        )
    }
}

@Composable
public fun Application() {
    val launchMode = scaffold.asLaunchMode()
    println(SculptorContractor.create(FoundationContractorState).encode(scaffold).getOrNull())
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
