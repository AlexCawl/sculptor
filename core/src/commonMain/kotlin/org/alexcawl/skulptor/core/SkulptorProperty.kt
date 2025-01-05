package org.alexcawl.skulptor.core

import androidx.compose.runtime.Composable

interface SkulptorProperty<ComposeProperty> {
    fun asCompose(): ComposeProperty
}

interface SkulptorCalculatedProperty<ComposeProperty> {
    @Composable
    fun asCompose(): ComposeProperty
}