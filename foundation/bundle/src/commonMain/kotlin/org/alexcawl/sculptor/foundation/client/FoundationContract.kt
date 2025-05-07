package org.alexcawl.sculptor.foundation.client

import kotlinx.serialization.modules.PolymorphicModuleBuilder
import kotlinx.serialization.modules.subclass
import org.alexcawl.sculptor.core.contract.Contractor
import org.alexcawl.sculptor.core.contract.ModifierContract
import org.alexcawl.sculptor.core.contract.StateContract
import org.alexcawl.sculptor.foundation.contract.layout.BasicTextState
import org.alexcawl.sculptor.foundation.contract.layout.BoxState
import org.alexcawl.sculptor.foundation.contract.layout.ColumnState
import org.alexcawl.sculptor.foundation.contract.layout.RowState
import org.alexcawl.sculptor.foundation.contract.modifier.Background
import org.alexcawl.sculptor.foundation.contract.modifier.Clickable
import org.alexcawl.sculptor.foundation.contract.modifier.CombinedClickable
import org.alexcawl.sculptor.foundation.contract.modifier.FillMaxHeight
import org.alexcawl.sculptor.foundation.contract.modifier.FillMaxSize
import org.alexcawl.sculptor.foundation.contract.modifier.FillMaxWidth
import org.alexcawl.sculptor.foundation.contract.modifier.Height
import org.alexcawl.sculptor.foundation.contract.modifier.HeightIn
import org.alexcawl.sculptor.foundation.contract.modifier.Padding
import org.alexcawl.sculptor.foundation.contract.modifier.RequiredHeight
import org.alexcawl.sculptor.foundation.contract.modifier.RequiredHeightIn
import org.alexcawl.sculptor.foundation.contract.modifier.RequiredSize
import org.alexcawl.sculptor.foundation.contract.modifier.RequiredSizeIn
import org.alexcawl.sculptor.foundation.contract.modifier.RequiredWidth
import org.alexcawl.sculptor.foundation.contract.modifier.RequiredWidthIn
import org.alexcawl.sculptor.foundation.contract.modifier.Size
import org.alexcawl.sculptor.foundation.contract.modifier.SizeIn
import org.alexcawl.sculptor.foundation.contract.modifier.Width
import org.alexcawl.sculptor.foundation.contract.modifier.WidthIn
import org.alexcawl.sculptor.foundation.contract.modifier.WrapContentHeight
import org.alexcawl.sculptor.foundation.contract.modifier.WrapContentSize
import org.alexcawl.sculptor.foundation.contract.modifier.WrapContentWidth

internal object FoundationContract : Contractor {
    override val stateContracts: PolymorphicModuleBuilder<StateContract>.() -> Unit = {
        subclass(BasicTextState::class)
        subclass(BoxState::class)
        subclass(ColumnState::class)
        subclass(RowState::class)
    }

    override val modifierContracts: PolymorphicModuleBuilder<ModifierContract>.() -> Unit = {
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
}
