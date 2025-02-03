package org.alexcawl.sculptor.common.contract

interface Contract {
    val id: String
    val modifier: List<ContractModifier>
}
