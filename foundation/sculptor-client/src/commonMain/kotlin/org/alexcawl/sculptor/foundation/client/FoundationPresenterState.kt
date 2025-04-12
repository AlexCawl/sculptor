package org.alexcawl.sculptor.foundation.client

import org.alexcawl.sculptor.common.presenter.StatePresenter
import org.alexcawl.sculptor.common.presenter.ModifierPresenter
import org.alexcawl.sculptor.foundation.presenter.layout.BasicTextPresenter
import org.alexcawl.sculptor.foundation.presenter.layout.BoxPresenter
import org.alexcawl.sculptor.foundation.presenter.layout.ColumnPresenter
import org.alexcawl.sculptor.foundation.presenter.layout.RowPresenter
import org.alexcawl.sculptor.foundation.presenter.modifier.BackgroundPresenter
import org.alexcawl.sculptor.foundation.presenter.common.ColorPresenter
import org.alexcawl.sculptor.foundation.presenter.common.DpPresenter
import org.alexcawl.sculptor.foundation.presenter.common.DpSizePresenter
import org.alexcawl.sculptor.foundation.presenter.common.RolePresenter
import org.alexcawl.sculptor.foundation.presenter.common.ShapePresenter
import org.alexcawl.sculptor.foundation.presenter.common.alignment.AlignmentHorizontalPresenter
import org.alexcawl.sculptor.foundation.presenter.common.alignment.AlignmentPresenter
import org.alexcawl.sculptor.foundation.presenter.common.alignment.AlignmentVerticalPresenter
import org.alexcawl.sculptor.foundation.presenter.common.arrangement.ArrangementHorizontalPresenter
import org.alexcawl.sculptor.foundation.presenter.common.arrangement.ArrangementPresenter
import org.alexcawl.sculptor.foundation.presenter.common.arrangement.ArrangementVerticalPresenter
import org.alexcawl.sculptor.foundation.presenter.modifier.ClickablePresenter
import org.alexcawl.sculptor.foundation.presenter.modifier.CombinedClickablePresenter
import org.alexcawl.sculptor.foundation.presenter.modifier.FillMaxHeightPresenter
import org.alexcawl.sculptor.foundation.presenter.modifier.FillMaxSizePresenter
import org.alexcawl.sculptor.foundation.presenter.modifier.FillMaxWidthPresenter
import org.alexcawl.sculptor.foundation.presenter.modifier.HeightInPresenter
import org.alexcawl.sculptor.foundation.presenter.modifier.HeightPresenter
import org.alexcawl.sculptor.foundation.presenter.modifier.PaddingPresenter
import org.alexcawl.sculptor.foundation.presenter.modifier.RequiredHeightPresenter
import org.alexcawl.sculptor.foundation.presenter.modifier.RequiredSizePresenter
import org.alexcawl.sculptor.foundation.presenter.modifier.RequiredWidthPresenter
import org.alexcawl.sculptor.foundation.presenter.modifier.SizeInPresenter
import org.alexcawl.sculptor.foundation.presenter.modifier.SizePresenter
import org.alexcawl.sculptor.foundation.presenter.modifier.WidthInPresenter
import org.alexcawl.sculptor.foundation.presenter.modifier.WidthPresenter
import org.alexcawl.sculptor.foundation.presenter.modifier.WrapContentHeightPresenter
import org.alexcawl.sculptor.foundation.presenter.modifier.WrapContentSizePresenter
import org.alexcawl.sculptor.foundation.presenter.modifier.WrapContentWidthPresenter

/**
 * TODO: docs
 */
public object FoundationPresenterState : SculptorPresenter.State {
    override val statePresenters: List<StatePresenter<*>> = buildList {
        add(BasicTextPresenter())
        add(BoxPresenter())
        add(ColumnPresenter())
        add(RowPresenter())
    }

    override val modifierPresenters: List<ModifierPresenter<*>> = buildList {
        add(BackgroundPresenter())

        add(ClickablePresenter())
        add(CombinedClickablePresenter())

        add(HeightPresenter())
        add(FillMaxHeightPresenter())
        add(HeightInPresenter())
        add(RequiredHeightPresenter())
        add(WrapContentHeightPresenter())

        add(WidthPresenter())
        add(FillMaxWidthPresenter())
        add(WidthInPresenter())
        add(RequiredWidthPresenter())
        add(WrapContentWidthPresenter())

        add(SizePresenter())
        add(FillMaxSizePresenter())
        add(SizeInPresenter())
        add(RequiredSizePresenter())
        add(WrapContentSizePresenter())

        add(PaddingPresenter())
    }

    override val commonPresenters: List<CommonPresenter<*, *>> = buildList {
        add(AlignmentHorizontalPresenter())
        add(AlignmentPresenter())
        add(AlignmentVerticalPresenter())

        add(ArrangementHorizontalPresenter())
        add(ArrangementPresenter())
        add(ArrangementVerticalPresenter())

        add(ColorPresenter())
        add(DpPresenter())
        add(DpSizePresenter())
        add(RolePresenter())
        add(ShapePresenter())
    }
}
