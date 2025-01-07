package org.alexcawl.skulptor.core.attribute

import androidx.compose.ui.semantics.Role
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SkulptorAttribute

/**
 * The type of user interface element. Accessibility services might use this to describe the
 * element or do customizations.
 */
@Serializable
sealed interface RoleWrapper : SkulptorAttribute<Role> {
    /**
     * This element is a button control.
     */
    @Serializable
    @SerialName("role@button")
    data object Button : RoleWrapper {
        override fun asCompose(): Role =
            Role.Button
    }

    /**
     * This element is a Checkbox which is a component that represents two states (checked /
     * unchecked).
     */
    @Serializable
    @SerialName("role@checkbox")
    data object Checkbox : RoleWrapper {
        override fun asCompose(): Role =
            Role.Checkbox
    }

    /**
     * This element is a Switch which is a two state toggleable component that provides on/off
     * like options
     */
    @Serializable
    @SerialName("role@switch")
    data object Switch : RoleWrapper {
        override fun asCompose(): Role =
            Role.Switch
    }

    /**
     * This element is a RadioButton which is a component to represent two states, selected and not
     * selected.
     */
    @Serializable
    @SerialName("role@radio_button")
    data object RadioButton : RoleWrapper {
        override fun asCompose(): Role =
            Role.RadioButton
    }

    /**
     * This element is a Tab which represents a single page of content using a text label and/or
     * icon. A Tab also has two states: selected and not selected.
     */
    @Serializable
    @SerialName("role@tab")
    data object Tab : RoleWrapper {
        override fun asCompose(): Role =
            Role.Tab
    }

    /**
     * This element is an image.
     */
    @Serializable
    @SerialName("role@image")
    data object Image : RoleWrapper {
        override fun asCompose(): Role =
            Role.Image
    }

    /**
     * This element is associated with a drop down menu.
     */
    @Serializable
    @SerialName("role@dropdown_list")
    data object DropdownList : RoleWrapper {
        override fun asCompose(): Role =
            Role.DropdownList
    }
}
