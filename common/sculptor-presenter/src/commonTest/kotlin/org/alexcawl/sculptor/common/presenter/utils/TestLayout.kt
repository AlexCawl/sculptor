package org.alexcawl.sculptor.common.presenter.utils

import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.layout.Layout

@Immutable
data class TestLayout(
    override val id: String,
    override val modifier: Modifier,
    val testValue: String,
) : Layout