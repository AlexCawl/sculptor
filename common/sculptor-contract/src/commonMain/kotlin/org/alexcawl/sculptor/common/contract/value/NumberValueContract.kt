package org.alexcawl.sculptor.common.contract.value

import kotlinx.serialization.Serializable

@Serializable
abstract class NumberValueContract : ValueContract {
    abstract val value: Number
}
