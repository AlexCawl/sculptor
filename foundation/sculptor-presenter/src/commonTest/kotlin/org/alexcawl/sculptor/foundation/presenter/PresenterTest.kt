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
import org.junit.Test
import kotlin.reflect.KClass

abstract class PresenterTest<I : Any, O : Any> {
    private val presenters: List<Presenter<*, *>> = buildList {
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
    protected val presenterScope: PresenterScope = PresenterScope(
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

    abstract val presenter: Presenter<I, O>

    open val scaffold: Scaffold = Scaffold(
        rootLayoutId = "root".id,
        values = listOf(),
        layouts = listOf()
    )

    @Test
    abstract fun transformationTest()
}
