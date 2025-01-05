package org.alexcawl.skulptor.core

import androidx.compose.runtime.Composable

interface SProperty<ComposeProperty> {
    @Composable
    fun asCompose(): ComposeProperty
}
