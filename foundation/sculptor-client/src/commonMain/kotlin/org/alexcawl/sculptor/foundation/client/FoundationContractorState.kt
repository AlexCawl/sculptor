package org.alexcawl.sculptor.foundation.client

import kotlinx.serialization.modules.PolymorphicModuleBuilder
import kotlinx.serialization.modules.SerializersModuleBuilder
import kotlinx.serialization.modules.subclass
import org.alexcawl.sculptor.common.contract.LayoutContract
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.contract.StateContract
import org.alexcawl.sculptor.engine.SculptorContractor
import org.alexcawl.sculptor.foundation.contract.layout.BasicTextLayoutContract
import org.alexcawl.sculptor.foundation.contract.layout.BasicTextStateContract
import org.alexcawl.sculptor.foundation.contract.layout.BoxLayoutContract
import org.alexcawl.sculptor.foundation.contract.layout.BoxStateContract
import org.alexcawl.sculptor.foundation.contract.layout.ColumnLayoutContract
import org.alexcawl.sculptor.foundation.contract.layout.ColumnStateContract
import org.alexcawl.sculptor.foundation.contract.layout.RowLayoutContract
import org.alexcawl.sculptor.foundation.contract.layout.RowStateContract
import org.alexcawl.sculptor.foundation.contract.modifier.Background
import org.alexcawl.sculptor.foundation.contract.modifier.Clickable
import org.alexcawl.sculptor.foundation.contract.modifier.CombinedClickable
import org.alexcawl.sculptor.foundation.contract.modifier.FillMaxHeight
import org.alexcawl.sculptor.foundation.contract.modifier.Height
import org.alexcawl.sculptor.foundation.contract.modifier.HeightIn
import org.alexcawl.sculptor.foundation.contract.modifier.RequiredHeight
import org.alexcawl.sculptor.foundation.contract.modifier.RequiredHeightIn
import org.alexcawl.sculptor.foundation.contract.modifier.WrapContentHeight
import org.alexcawl.sculptor.foundation.contract.modifier.Padding
import org.alexcawl.sculptor.foundation.contract.modifier.FillMaxSize
import org.alexcawl.sculptor.foundation.contract.modifier.RequiredSize
import org.alexcawl.sculptor.foundation.contract.modifier.RequiredSizeIn
import org.alexcawl.sculptor.foundation.contract.modifier.Size
import org.alexcawl.sculptor.foundation.contract.modifier.SizeIn
import org.alexcawl.sculptor.foundation.contract.modifier.WrapContentSize
import org.alexcawl.sculptor.foundation.contract.modifier.FillMaxWidth
import org.alexcawl.sculptor.foundation.contract.modifier.RequiredWidth
import org.alexcawl.sculptor.foundation.contract.modifier.RequiredWidthIn
import org.alexcawl.sculptor.foundation.contract.modifier.Width
import org.alexcawl.sculptor.foundation.contract.modifier.WidthIn
import org.alexcawl.sculptor.foundation.contract.modifier.WrapContentWidth

/**
 * TODO: docs
 */
public object FoundationContractorState : SculptorContractor.State {
    override val modifiers: PolymorphicModuleBuilder<ModifierContract>.() -> Unit = {
        subclass(Background::class)

        subclass(Clickable::class)
        subclass(CombinedClickable::class)

        subclass(Padding::class)

        subclass(FillMaxHeight::class)
        subclass(Height::class)
        subclass(HeightIn::class)
        subclass(RequiredHeight::class)
        subclass(RequiredHeightIn::class)
        subclass(WrapContentHeight::class)

        subclass(FillMaxSize::class)
        subclass(RequiredSize::class)
        subclass(RequiredSizeIn::class)
        subclass(Size::class)
        subclass(SizeIn::class)
        subclass(WrapContentSize::class)

        subclass(FillMaxWidth::class)
        subclass(RequiredWidth::class)
        subclass(RequiredWidthIn::class)
        subclass(Width::class)
        subclass(WidthIn::class)
        subclass(WrapContentWidth::class)
    }

    override val layouts: PolymorphicModuleBuilder<LayoutContract>.() -> Unit = {
        subclass(BasicTextLayoutContract::class)
        subclass(BoxLayoutContract::class)
        subclass(ColumnLayoutContract::class)
        subclass(RowLayoutContract::class)
    }

    override val states: PolymorphicModuleBuilder<StateContract>.() -> Unit = {
        subclass(BasicTextStateContract::class)
        subclass(BoxStateContract::class)
        subclass(ColumnStateContract::class)
        subclass(RowStateContract::class)
    }

    override val contractBuilder: SerializersModuleBuilder.() -> Unit = {}
}
