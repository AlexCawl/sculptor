package org.alexcawl.skulptor.core

import androidx.compose.runtime.Composable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private typealias ComposeRole = androidx.compose.ui.semantics.Role

@Serializable
sealed interface SkulptorRole : SkulptorProperty<ComposeRole> {
    /**
     * This element is a button control.
     */
    @Serializable
    @SerialName("role@button")
    data object Button : SkulptorRole {
        @Composable
        override fun asCompose(): ComposeRole = ComposeRole.Button
    }

    /**
     * This element is a Checkbox which is a component that represents two states (checked /
     * unchecked).
     */
    @Serializable
    @SerialName("role@checkbox")
    data object Checkbox : SkulptorRole {
        @Composable
        override fun asCompose(): ComposeRole = ComposeRole.Checkbox
    }

    /**
     * This element is a Switch which is a two state toggleable component that provides on/off
     * like options
     */
    @Serializable
    @SerialName("role@switch")
    data object Switch : SkulptorRole {
        @Composable
        override fun asCompose(): ComposeRole = ComposeRole.Switch
    }

    /**
     * This element is a RadioButton which is a component to represent two states, selected and not
     * selected.
     */
    @Serializable
    @SerialName("role@radio_button")
    data object RadioButton : SkulptorRole {
        @Composable
        override fun asCompose(): ComposeRole = ComposeRole.RadioButton
    }

    /**
     * This element is a Tab which represents a single page of content using a text label and/or
     * icon. A Tab also has two states: selected and not selected.
     */
    @Serializable
    @SerialName("role@tab")
    data object Tab : SkulptorRole {
        @Composable
        override fun asCompose(): ComposeRole = ComposeRole.Tab
    }

    /**
     * This element is an image.
     */
    @Serializable
    @SerialName("role@image")
    data object Image : SkulptorRole {
        @Composable
        override fun asCompose(): ComposeRole = ComposeRole.Image
    }

    /**
     * This element is associated with a drop down menu.
     */
    @Serializable
    @SerialName("role@dropdown_list")
    data object DropdownList : SkulptorRole {
        @Composable
        override fun asCompose(): ComposeRole = ComposeRole.DropdownList
    }
}
