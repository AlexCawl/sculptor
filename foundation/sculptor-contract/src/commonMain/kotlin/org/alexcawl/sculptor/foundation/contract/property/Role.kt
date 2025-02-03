package org.alexcawl.sculptor.foundation.contract.property

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Role {
    @SerialName("button")
    Button,

    @SerialName("checkbox")
    Checkbox,

    @SerialName("dropdown_list")
    DropdownList,

    @SerialName("image")
    Image,

    @SerialName("radio_button")
    RadioButton,

    @SerialName("switch")
    Switch,

    @SerialName("tab")
    Tab,
}
