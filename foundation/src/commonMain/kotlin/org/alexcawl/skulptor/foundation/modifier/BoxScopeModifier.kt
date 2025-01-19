package org.alexcawl.skulptor.foundation.modifier

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.ui.Modifier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SkulptorModifier
import org.alexcawl.skulptor.provider.AlignmentProvider

@Serializable
sealed class BoxScopeModifier : SkulptorModifier() {
    @Serializable
    @SerialName("align")
    data class Align(val alignment: AlignmentProvider.HorizontalAndVertical) : BoxScopeModifier() {
        override fun ModifierScope.chain(initial: Modifier): Modifier {
            return with(this.scope as BoxScope) {
                initial.align(alignment.invoke())
            }
        }
    }

    @Serializable
    @SerialName("match_parent_size")
    data object MatchParentSize : BoxScopeModifier() {
        override fun ModifierScope.chain(initial: Modifier): Modifier {
            return with(this.scope as BoxScope) {
                initial.matchParentSize()
            }
        }
    }
}
