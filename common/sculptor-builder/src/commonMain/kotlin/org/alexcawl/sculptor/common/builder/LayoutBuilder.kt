package org.alexcawl.sculptor.common.builder

//import org.alexcawl.sculptor.common.builder.placer.LayoutPlacer
//import org.alexcawl.sculptor.common.builder.placer.StatePlacer
//import org.alexcawl.sculptor.common.contract.Identifier
//import org.alexcawl.sculptor.common.contract.LayoutContract
//import org.alexcawl.sculptor.common.contract.ModifierContract
//import org.alexcawl.sculptor.common.contract.StateContract
//import org.alexcawl.sculptor.common.contract.id
//
//public sealed interface LayoutBuilder<LC : LayoutContract, SC : StateContract> : Builder<LC>, StatePlacer {
//    public companion object Factory {
//        public operator fun <LC : LayoutContract, SC : StateContract> invoke(
//            scope: LayoutPlacer,
//            identifier: String,
//            modifiers: List<ModifierContract> = emptyList(),
//            layoutBuilderBlock: LayoutBuilder<LC, SC>.(Identifier, List<ModifierContract>, List<SC>) -> LC,
//        ): LayoutBuilder<LC, SC> = LayoutBuilderImpl(
//            identifier = identifier.id,
//            modifiers = modifiers,
//            builderBlock = layoutBuilderBlock,
//            scope = scope,
//        )
//    }
//}
//
//private class LayoutBuilderImpl<LC : LayoutContract, SC : StateContract>(
//    private val identifier: Identifier,
//    private val modifiers: List<ModifierContract>,
//    private val builderBlock: LayoutBuilder<LC, SC>.(Identifier, List<ModifierContract>, List<SC>) -> LC,
//    private val scope: LayoutPlacer,
//) : LayoutBuilder<LC, SC> {
//    private val states: MutableList<SC> = mutableListOf()
//
//    override fun <SC : StateContract> state(
//        identifier: String,
//        modifiers: List<ModifierContract>,
//        builderBlock: StateBuilder<SC>.(Identifier, List<ModifierContract>) -> SC
//    ) = StateBuilder.invoke(
//        scope = scope,
//        identifier = identifier,
//        modifiers = modifiers,
//        builderBlock = builderBlock
//    ).build().let(::place)
//
//    override fun place(state: StateContract) {
//        states.add(state as SC)
//    }
//
//    override fun build(): LC = this.builderBlock(identifier, modifiers, states.toList())
//}
