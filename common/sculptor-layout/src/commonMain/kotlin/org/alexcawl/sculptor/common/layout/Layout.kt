package org.alexcawl.sculptor.common.layout

import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier

@Stable
interface Layout {
    val id: String
    val modifier: Modifier
}
