package org.alexcawl.skulptor.core

import androidx.compose.ui.semantics.Role
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The type of user interface element. Accessibility services might use this to describe the
 * element or do customizations.
 */
@Serializable
sealed interface SRole : SAttribute<Role> {
    /**
     * This element is a button control.
     */
    @Serializable
    @SerialName("role@button")
    data object Button : SRole {
        override fun asCompose(): Role =
            Role.Button
    }

    /**
     * This element is a Checkbox which is a component that represents two states (checked /
     * unchecked).
     */
    @Serializable
    @SerialName("role@checkbox")
    data object Checkbox : SRole {
        override fun asCompose(): Role =
            Role.Checkbox
    }

    /**
     * This element is a Switch which is a two state toggleable component that provides on/off
     * like options
     */
    @Serializable
    @SerialName("role@switch")
    data object Switch : SRole {
        override fun asCompose(): Role =
            Role.Switch
    }

    /**
     * This element is a RadioButton which is a component to represent two states, selected and not
     * selected.
     */
    @Serializable
    @SerialName("role@radio_button")
    data object RadioButton : SRole {
        override fun asCompose(): Role =
            Role.RadioButton
    }

    /**
     * This element is a Tab which represents a single page of content using a text label and/or
     * icon. A Tab also has two states: selected and not selected.
     */
    @Serializable
    @SerialName("role@tab")
    data object Tab : SRole {
        override fun asCompose(): Role =
            Role.Tab
    }

    /**
     * This element is an image.
     */
    @Serializable
    @SerialName("role@image")
    data object Image : SRole {
        override fun asCompose(): Role =
            Role.Image
    }

    /**
     * This element is associated with a drop down menu.
     */
    @Serializable
    @SerialName("role@dropdown_list")
    data object DropdownList : SRole {
        override fun asCompose(): Role =
            Role.DropdownList
    }
}
