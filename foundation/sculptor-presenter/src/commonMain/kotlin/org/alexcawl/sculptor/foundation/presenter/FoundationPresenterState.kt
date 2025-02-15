package org.alexcawl.sculptor.foundation.presenter

import org.alexcawl.sculptor.common.presenter.CommonPresenter
import org.alexcawl.sculptor.common.presenter.LayoutPresenter
import org.alexcawl.sculptor.common.presenter.ModifierPresenter
import org.alexcawl.sculptor.common.presenter.PresenterState
import org.alexcawl.sculptor.foundation.presenter.modifier.BackgroundModifierPresenter
import org.alexcawl.sculptor.foundation.presenter.property.ColorPresenter
import org.alexcawl.sculptor.foundation.presenter.property.DpPresenter
import org.alexcawl.sculptor.foundation.presenter.property.DpSizePresenter
import org.alexcawl.sculptor.foundation.presenter.property.RolePresenter
import org.alexcawl.sculptor.foundation.presenter.property.ShapePresenter
import org.alexcawl.sculptor.foundation.presenter.property.alignment.AlignmentHorizontalPresenter
import org.alexcawl.sculptor.foundation.presenter.property.alignment.AlignmentPresenter
import org.alexcawl.sculptor.foundation.presenter.property.alignment.AlignmentVerticalPresenter
import org.alexcawl.sculptor.foundation.presenter.property.arrangement.ArrangementHorizontalPresenter
import org.alexcawl.sculptor.foundation.presenter.property.arrangement.ArrangementPresenter
import org.alexcawl.sculptor.foundation.presenter.property.arrangement.ArrangementVerticalPresenter

object FoundationPresenterState : PresenterState {
    override val layoutPresenters: List<LayoutPresenter<*, *>> = buildList {
        add(BasicTextPresenter())
        add(BoxPresenter())
        add(ColumnPresenter())
        add(RowPresenter())
    }

    override val modifierPresenters: List<ModifierPresenter<*>> = buildList {
        add(BackgroundModifierPresenter())
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
