package org.alexcawl.sculptor.foundation.presenter

import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.Scaffold
import org.alexcawl.sculptor.common.contract.id
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import org.alexcawl.sculptor.common.presenter.Presenter
import org.alexcawl.sculptor.common.presenter.PresenterScope
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
import org.alexcawl.sculptor.foundation.presenter.layout.BasicTextPresenter
import org.alexcawl.sculptor.foundation.presenter.layout.BoxPresenter
import org.alexcawl.sculptor.foundation.presenter.layout.ColumnPresenter
import org.alexcawl.sculptor.foundation.presenter.layout.RowPresenter
import org.alexcawl.sculptor.foundation.presenter.modifier.BackgroundPresenter
import kotlin.reflect.KClass

interface PresenterTest<I : Any, O : Any> {
    val presenters: List<Presenter<*, *>>
        get() = buildList {
            add(BasicTextPresenter())
            add(BoxPresenter())
            add(ColumnPresenter())
            add(RowPresenter())
            add(BackgroundPresenter())

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

    @OptIn(InternalSculptorApi::class)
    val presenterScope: PresenterScope
        get() = PresenterScope(
            presenterProvider = { inputClass: KClass<out Any>, outputClass: KClass<out Any> ->
                presenters
                    .firstOrNull { it.input == inputClass && it.output == outputClass }
                    ?: error("No presenter found for $inputClass -> $outputClass")
            },
            layoutProvider = { identifier: Identifier ->
                scaffold.layouts
                    .find { it.id == identifier }
                    ?: error("No layout found for $identifier")
            },
            valueProvider = { identifier: Identifier ->
                scaffold.values
                    .find { it.id == identifier }
                    ?: error("No value found for $identifier")
            },
        )

    val scaffold: Scaffold
        get() = Scaffold(
            rootLayoutId = "root".id,
            values = listOf(),
            layouts = listOf()
        )

    val presenter: Presenter<I, O>

    fun transformationTest()
}
