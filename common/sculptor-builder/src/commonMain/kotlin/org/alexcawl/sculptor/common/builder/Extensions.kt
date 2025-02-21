package org.alexcawl.sculptor.common.builder

import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.LayoutContract
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.contract.Scaffold
import org.alexcawl.sculptor.common.contract.StateContract
import org.alexcawl.sculptor.common.contract.ValueContract

public inline fun <reified LC : LayoutContract, SC : StateContract> ScaffoldBuilder.layout(
    identifier: Identifier,
    modifier: List<ModifierContract>,
    crossinline builder: LayoutBuilder<LC, SC>.() -> LC,
) {
    val layoutBuilder = object : LayoutBuilder<LC, SC>(
        identifier = identifier,
        defaultModifiers = modifier
    ) {
        override fun build(): LC = this.builder()
    }
    val layout = layoutBuilder.build()
    this.addLayout(layout)
}

public inline fun <reified LC : LayoutContract, SC : StateContract> LayoutBuilder<LC, SC>.state(
    identifier: Identifier,
    modifier: List<ModifierContract>,
    crossinline builder: StateBuilder<SC>.() -> SC,
) {
    val stateBuilder: StateBuilder<SC> = object : StateBuilder<SC>(
        identifier = identifier,
        defaultModifiers = modifier,
    ) {
        override fun build(): SC = this.builder()
    }
    val state = stateBuilder.build()
    this.addState(state)
}

public inline fun <reified VC : ValueContract> ScaffoldBuilder.value(
    identifier: Identifier,
    crossinline builder: ValueBuilder<VC>.() -> VC,
) {
    val valueBuilder = object : ValueBuilder<VC>(identifier) {
        override fun build(): VC = this.builder()
    }
    val value = valueBuilder.build()
    this.addValue(value)
}

public inline fun scaffold(builder: ScaffoldBuilder.() -> Unit): Scaffold {
    val scaffoldBuilder = ScaffoldBuilder()
    scaffoldBuilder.builder()
    return scaffoldBuilder.build()
}
