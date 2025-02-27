package org.alexcawl.sculptor.foundation.showroom

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import org.alexcawl.sculptor.common.builder.scaffold
import org.alexcawl.sculptor.common.contract.Scaffold
import org.alexcawl.sculptor.common.contract.Style
import org.alexcawl.sculptor.common.contract.id
import org.alexcawl.sculptor.engine.Sculptor
import org.alexcawl.sculptor.engine.SculptorContractor
import org.alexcawl.sculptor.engine.asLaunchMode
import org.alexcawl.sculptor.foundation.client.FoundationContractorState
import org.alexcawl.sculptor.foundation.client.FoundationSculptor
import org.alexcawl.sculptor.foundation.contract.common.Alignment
import org.alexcawl.sculptor.foundation.contract.common.Arrangement
import org.alexcawl.sculptor.foundation.contract.common.Color
import org.alexcawl.sculptor.foundation.contract.common.Shape
import org.alexcawl.sculptor.foundation.contract.common.dp
import org.alexcawl.sculptor.foundation.contract.layout.BasicTextState
import org.alexcawl.sculptor.foundation.contract.layout.ColumnState
import org.alexcawl.sculptor.foundation.contract.modifier.Background
import org.alexcawl.sculptor.foundation.contract.modifier.Padding
import org.alexcawl.sculptor.foundation.contract.modifier.Size

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
        ColumnState(
            id = "1".id,
            verticalArrangement = Arrangement.Vertical.SpacedBy(
                space = 16.dp,
                alignment = Alignment.Vertical.Top,
            ),
            horizontalAlignment = Alignment.Horizontal.Center,
            content = (0 .. 9).map { "text$it".id }
        )
    }
    (0 .. 9).forEach {
        singleSection(
            id = "text$it",
            modifiers = Style plus Size(
                width = 200.dp,
                height = 72.dp,
            ) plus Background(
                shape = Shape.RoundedCorner.DPixel(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp,
                ),
                color = Color.RGB("00FFFF")
            ) plus Padding(
                all = 16.dp
            )
        ) {
            BasicTextState(
                id = "1".id,
                softWrap = false,
                maxLines = 1,
                minLines = 1,
                text = "Hello World $it",
            )
        }
    }
}

@Composable
public fun Application() {
    val string = SculptorContractor.create(FoundationContractorState).encode(scaffold).getOrThrow()
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
