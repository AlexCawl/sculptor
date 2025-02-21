package org.alexcawl.sculptor.common.builder

import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.LayoutContract
import org.alexcawl.sculptor.common.contract.Scaffold
import org.alexcawl.sculptor.common.contract.ValueContract

public class ScaffoldBuilder(
    private val rootLayoutId: Identifier? = null,
) : Builder<Scaffold> {
    private val values: MutableList<ValueContract> = mutableListOf()
    private val layouts: MutableList<LayoutContract> = mutableListOf()

    public fun addValue(value: ValueContract) {
        values.add(value)
    }

    public fun addLayout(layout: LayoutContract) {
        layouts.add(layout)
    }

    override fun build(): Scaffold = Scaffold(
        rootLayoutId = rootLayoutId ?: layouts.first().id,
        values = values,
        layouts = layouts,
    )
}
