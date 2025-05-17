package org.alexcawl.sculptor.foundation.client

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
import org.alexcawl.sculptor.runtime.engine.SculptorGlobalBuilder
import org.alexcawl.sculptor.runtime.engine.presenter

internal object FoundationPresenter {
    internal fun SculptorGlobalBuilder.install() {
        // Foundation Common
        presenter(::AlignmentHorizontalPresenter)
        presenter(::AlignmentPresenter)
        presenter(::AlignmentVerticalPresenter)
        presenter(::ArrangementHorizontalPresenter)
        presenter(::ArrangementPresenter)
        presenter(::ArrangementVerticalPresenter)
        presenter(::ColorPresenter)
        presenter(::DpPresenter)
        presenter(::DpSizePresenter)
        presenter(::RolePresenter)
        presenter(::ShapePresenter)

        // Foundation States
        presenter(::BasicTextPresenter)
        presenter(::BoxPresenter)
        presenter(::ColumnPresenter)
        presenter(::RowPresenter)

        // Foundation Modifiers
        presenter(::BackgroundPresenter)
        presenter(::ClickablePresenter)
        presenter(::CombinedClickablePresenter)
        presenter(::HeightPresenter)
        presenter(::FillMaxHeightPresenter)
        presenter(::HeightInPresenter)
        presenter(::RequiredHeightPresenter)
        presenter(::WrapContentHeightPresenter)
        presenter(::WidthPresenter)
        presenter(::FillMaxWidthPresenter)
        presenter(::WidthInPresenter)
        presenter(::RequiredWidthPresenter)
        presenter(::WrapContentWidthPresenter)
        presenter(::SizePresenter)
        presenter(::FillMaxSizePresenter)
        presenter(::SizeInPresenter)
        presenter(::RequiredSizePresenter)
        presenter(::WrapContentSizePresenter)
        presenter(::PaddingPresenter)
    }
}
