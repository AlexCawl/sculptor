package org.alexcawl.sculptor.common.builder.scopes

import org.alexcawl.sculptor.common.builder.SculptorDsl
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.contract.Section
import org.alexcawl.sculptor.common.contract.StateContract

@SculptorDsl
public interface SectionPlacer {
    public fun section(section: Section)

    public fun section(
        id: String,
        modifiers: List<ModifierContract> = emptyList(),
        forcedState: String? = null,
        builder: StatePlacer.() -> Unit,
    )

    public fun <SC : StateContract> singleSection(
        id: String,
        modifiers: List<ModifierContract> = emptyList(),
        builder: () -> SC
    )
}
