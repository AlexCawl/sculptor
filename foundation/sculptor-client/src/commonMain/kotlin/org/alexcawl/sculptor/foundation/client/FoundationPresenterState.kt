package org.alexcawl.sculptor.foundation.client

import org.alexcawl.sculptor.common.presenter.CommonPresenter
import org.alexcawl.sculptor.common.presenter.LayoutPresenter
import org.alexcawl.sculptor.common.presenter.ModifierPresenter
import org.alexcawl.sculptor.engine.SculptorPresenter
import org.alexcawl.sculptor.foundation.presenter.layout.BasicTextPresenter
import org.alexcawl.sculptor.foundation.presenter.layout.BoxPresenter
import org.alexcawl.sculptor.foundation.presenter.layout.ColumnPresenter
import org.alexcawl.sculptor.foundation.presenter.layout.RowPresenter
import org.alexcawl.sculptor.foundation.presenter.modifier.BackgroundModifierPresenter
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

/**
 * TODO: docs
 */
public object FoundationPresenterState : SculptorPresenter.State {
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
