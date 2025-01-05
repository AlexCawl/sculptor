package org.alexcawl.skulptor.core.modifier

import androidx.compose.ui.Modifier
import org.alexcawl.skulptor.core.SkulptorCalculatedProperty

/**
 * An ordered, immutable collection of modifier elements that decorate or add
 * behavior to Compose UI elements. For example, backgrounds, padding and click event listeners
 * decorate or add behavior to rows, text or buttons.
 */
interface SkulptorModifier : SkulptorCalculatedProperty<Modifier>
