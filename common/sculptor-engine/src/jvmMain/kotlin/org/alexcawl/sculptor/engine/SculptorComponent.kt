package org.alexcawl.sculptor.engine

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposePanel
import androidx.compose.ui.awt.SwingPanel
import java.awt.Component

public fun sculptorComponent(
    sculptorState: SculptorState,
    launchMode: LaunchMode,
    loadingPanel: Component,
    errorPanel: Component,
): Component = ComposePanel().apply {
    setContent {
        Sculptor(
            sculptorState = sculptorState,
            launchMode = launchMode,
            loading = {
                SwingPanel(
                    factory = {
                        loadingPanel
                    },
                )
            },
            error = {
                SwingPanel(
                    factory = {
                        errorPanel
                    },
                )
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}
