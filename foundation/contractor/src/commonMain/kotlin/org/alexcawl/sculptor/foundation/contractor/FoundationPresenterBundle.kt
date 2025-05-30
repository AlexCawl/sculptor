package org.alexcawl.sculptor.foundation.contractor

import org.alexcawl.sculptor.core.contractor.PresenterBundle
import org.alexcawl.sculptor.core.contractor.presenter
import org.alexcawl.sculptor.foundation.contractor.presenter.common.ColorPresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.common.DpPresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.common.DpSizePresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.common.FontStylePresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.common.RolePresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.common.ShapePresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.common.TextDecorationPresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.common.TextStylePresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.common.TextUnitPresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.common.alignment.AlignmentHorizontalPresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.common.alignment.AlignmentPresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.common.alignment.AlignmentVerticalPresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.common.arrangement.ArrangementHorizontalPresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.common.arrangement.ArrangementPresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.common.arrangement.ArrangementVerticalPresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.layout.BasicTextPresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.layout.BoxPresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.layout.ColumnPresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.layout.RowPresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.modifier.BackgroundPresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.modifier.ClickablePresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.modifier.CombinedClickablePresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.modifier.FillMaxHeightPresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.modifier.FillMaxSizePresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.modifier.FillMaxWidthPresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.modifier.HeightInPresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.modifier.HeightPresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.modifier.PaddingPresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.modifier.RequiredHeightInPresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.modifier.RequiredHeightPresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.modifier.RequiredSizeInPresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.modifier.RequiredSizePresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.modifier.RequiredWidthInPresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.modifier.RequiredWidthPresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.modifier.SizeInPresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.modifier.SizePresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.modifier.WidthInPresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.modifier.WidthPresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.modifier.WrapContentHeightPresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.modifier.WrapContentSizePresenter
import org.alexcawl.sculptor.foundation.contractor.presenter.modifier.WrapContentWidthPresenter

internal object FoundationPresenterBundle : PresenterBundle {
    override val presenters: PresenterBundle.Consumer.() -> Unit = {
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
        presenter(::FontStylePresenter)
        presenter(::TextStylePresenter)
        presenter(::TextUnitPresenter)
        presenter(::TextDecorationPresenter)

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
        presenter(::RequiredHeightInPresenter)
        presenter(::WrapContentHeightPresenter)
        presenter(::WidthPresenter)
        presenter(::FillMaxWidthPresenter)
        presenter(::WidthInPresenter)
        presenter(::RequiredWidthPresenter)
        presenter(::RequiredWidthInPresenter)
        presenter(::WrapContentWidthPresenter)
        presenter(::SizePresenter)
        presenter(::FillMaxSizePresenter)
        presenter(::SizeInPresenter)
        presenter(::RequiredSizePresenter)
        presenter(::RequiredSizeInPresenter)
        presenter(::WrapContentSizePresenter)
        presenter(::PaddingPresenter)
    }
}
