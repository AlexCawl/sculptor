package org.alexcawl.skulptor.core

import androidx.compose.ui.Modifier

/**
 * An ordered, immutable collection of modifier elements that decorate or add
 * behavior to Compose UI elements. For example, backgrounds, padding and click event listeners
 * decorate or add behavior to rows, text or buttons.
 */
interface SkulptorModifier {
    fun chain(initial: Modifier, skulptor: Skulptor, scope: Any): Modifier
}
