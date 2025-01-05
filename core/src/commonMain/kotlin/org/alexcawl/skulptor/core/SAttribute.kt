package org.alexcawl.skulptor.core

import androidx.compose.runtime.Composable

interface SAttribute<ComposeAttribute> {
    fun asCompose(): ComposeAttribute
}

interface SProperty<ComposeProperty> {
    @Composable
    fun asCompose(): ComposeProperty
}