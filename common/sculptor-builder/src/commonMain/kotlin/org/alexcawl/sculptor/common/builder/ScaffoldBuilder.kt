package org.alexcawl.sculptor.common.builder

import org.alexcawl.sculptor.common.builder.scopes.SectionPlacer
import org.alexcawl.sculptor.common.builder.scopes.StatePlacer
import org.alexcawl.sculptor.common.builder.scopes.ValuePlacer
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.contract.Scaffold
import org.alexcawl.sculptor.common.contract.Section
import org.alexcawl.sculptor.common.contract.StateContract
import org.alexcawl.sculptor.common.contract.ValueContract
import org.alexcawl.sculptor.common.contract.id

@SculptorDsl
public interface ScaffoldBuilder : ValuePlacer, SectionPlacer, Builder<Scaffold> {
    public companion object Factory {
        public fun create(
            forcedSection: String? = null,
            builder: ScaffoldBuilder.() -> Unit,
        ): Scaffold = ScaffoldBuilderImpl(
            forcedSection = forcedSection
        ).apply(builder).build()
    }
}

private class ScaffoldBuilderImpl(
    private val forcedSection: String? = null,
) : ScaffoldBuilder {
    private val _values: MutableList<ValueContract> = mutableListOf()
    private val _sections: MutableList<Section> = mutableListOf()

    override fun value(value: ValueContract) {
        _values.add(value)
    }

    override fun <VC : ValueContract> value(builder: () -> VC) = value(builder())

    override fun section(section: Section) {
        _sections.add(section)
    }

    override fun section(
        id: String,
        modifiers: List<ModifierContract>,
        forcedState: String?,
        builder: StatePlacer.() -> Unit
    ) {
        SectionBuilder.create(
            identifier = id.id,
            modifiers = modifiers,
            forcedState = forcedState?.id,
            builder = builder
        ).also(block = ::section)
    }

    override fun <SC : StateContract> singleSection(
        id: String,
        modifiers: List<ModifierContract>,
        builder: () -> SC
    ) {
        SectionBuilder.create(
            identifier = id.id,
            modifiers = modifiers,
            builder = { state(builder()) },
        ).also(block = ::section)
    }

    override fun build(): Scaffold = Scaffold(
        values = _values,
        sections = _sections,
        forcedSection = forcedSection?.id,
    )
}
