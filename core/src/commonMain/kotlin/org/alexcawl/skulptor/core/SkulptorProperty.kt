package org.alexcawl.skulptor.core

import androidx.compose.runtime.Composable

interface SkulptorProperty<ComposeProperty> {
    @Composable
    fun asCompose(): ComposeProperty
}
