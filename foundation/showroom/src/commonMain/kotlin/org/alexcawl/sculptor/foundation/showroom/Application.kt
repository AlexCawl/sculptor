package org.alexcawl.sculptor.foundation.showroom

import androidx.compose.runtime.Composable
import org.alexcawl.sculptor.common.contract.Scaffold
import org.alexcawl.sculptor.common.contract.id
import org.alexcawl.sculptor.engine.asLaunchMode
import org.alexcawl.sculptor.foundation.contract.layout.BasicTextLayoutContract
import org.alexcawl.sculptor.foundation.contract.layout.BasicTextStateContract
import org.alexcawl.sculptor.foundation.contract.layout.RowLayoutContract
import org.alexcawl.sculptor.foundation.contract.layout.RowStateContract
import org.alexcawl.sculptor.foundation.contract.property.Alignment
import org.alexcawl.sculptor.foundation.contract.property.Arrangement

@Composable
fun Application() {
    val scaffold = Scaffold(
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
                    BasicTextStateContract.Static(
                        id = "1".id,
                        modifiers = listOf(),
                        softWrap = false,
                        maxLines = 1,
                        minLines = 1,
                        text = "Hello World 1!"
                    )
                ),
            ),
            BasicTextLayoutContract(
                id = "text2".id,
                modifiers = listOf(),
                state = "1".id,
                states = listOf(
                    BasicTextStateContract.Static(
                        id = "1".id,
                        modifiers = listOf(),
                        softWrap = false,
                        maxLines = 1,
                        minLines = 1,
                        text = "Hello World 1!"
                    )
                ),
            ),
        )
    )

    val launchMode = scaffold.asLaunchMode()
    val screen = foundationSculptor.launch(launchMode)
    screen()
}
