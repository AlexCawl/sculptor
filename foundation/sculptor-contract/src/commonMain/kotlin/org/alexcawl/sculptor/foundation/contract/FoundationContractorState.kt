package org.alexcawl.sculptor.foundation.contract

import kotlinx.serialization.modules.PolymorphicModuleBuilder
import kotlinx.serialization.modules.SerializersModuleBuilder
import kotlinx.serialization.modules.subclass
import org.alexcawl.sculptor.common.contract.LayoutContract
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.contract.SculptorContractor
import org.alexcawl.sculptor.common.contract.StateContract
import org.alexcawl.sculptor.foundation.contract.layout.BasicTextLayoutContract
import org.alexcawl.sculptor.foundation.contract.layout.BasicTextStateContract
import org.alexcawl.sculptor.foundation.contract.layout.BoxLayoutContract
import org.alexcawl.sculptor.foundation.contract.layout.BoxStateContract
import org.alexcawl.sculptor.foundation.contract.layout.ColumnLayoutContract
import org.alexcawl.sculptor.foundation.contract.layout.ColumnStateContract
import org.alexcawl.sculptor.foundation.contract.layout.RowLayoutContract
import org.alexcawl.sculptor.foundation.contract.layout.RowStateContract
import org.alexcawl.sculptor.foundation.contract.modifier.background.Background
import org.alexcawl.sculptor.foundation.contract.modifier.click.Clickable
import org.alexcawl.sculptor.foundation.contract.modifier.click.CombinedClickable
import org.alexcawl.sculptor.foundation.contract.modifier.height.FillMaxHeight
import org.alexcawl.sculptor.foundation.contract.modifier.height.Height
import org.alexcawl.sculptor.foundation.contract.modifier.height.HeightIn
import org.alexcawl.sculptor.foundation.contract.modifier.height.RequiredHeight
import org.alexcawl.sculptor.foundation.contract.modifier.height.RequiredHeightIn
import org.alexcawl.sculptor.foundation.contract.modifier.height.WrapContentHeight
import org.alexcawl.sculptor.foundation.contract.modifier.padding.Padding
import org.alexcawl.sculptor.foundation.contract.modifier.size.FillMaxSize
import org.alexcawl.sculptor.foundation.contract.modifier.size.RequiredSize
import org.alexcawl.sculptor.foundation.contract.modifier.size.RequiredSizeIn
import org.alexcawl.sculptor.foundation.contract.modifier.size.Size
import org.alexcawl.sculptor.foundation.contract.modifier.size.SizeIn
import org.alexcawl.sculptor.foundation.contract.modifier.size.WrapContentSize
import org.alexcawl.sculptor.foundation.contract.modifier.width.FillMaxWidth
import org.alexcawl.sculptor.foundation.contract.modifier.width.RequiredWidth
import org.alexcawl.sculptor.foundation.contract.modifier.width.RequiredWidthIn
import org.alexcawl.sculptor.foundation.contract.modifier.width.Width
import org.alexcawl.sculptor.foundation.contract.modifier.width.WidthIn
import org.alexcawl.sculptor.foundation.contract.modifier.width.WrapContentWidth

object FoundationContractorState : SculptorContractor.State.Json {
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
