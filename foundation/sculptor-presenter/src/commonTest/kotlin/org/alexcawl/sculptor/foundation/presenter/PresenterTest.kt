package org.alexcawl.sculptor.foundation.presenter

import org.alexcawl.sculptor.common.core.InternalSculptorApi
import org.alexcawl.sculptor.common.presenter.Presenter
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.common.presenter.SectionPresenter
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
import kotlin.test.Test
import kotlin.test.assertEquals

abstract class PresenterTest<I : Any, O : Any> {
    protected open val presenters: List<Presenter<*, *>>
        get() = buildList {
            add(SectionPresenter)

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
    protected open val presenterScope: PresenterScope
        get() = PresenterScope(
            presenters = presenters,
            sections = emptyList(),
            values = emptyList(),
        )

    abstract val presenter: Presenter<I, O>
    abstract val input: I
    abstract val expected: O

    @OptIn(InternalSculptorApi::class)
    @Test
    fun transformationTest() {
        val actual = presenter.internalTransform(presenterScope, input)
        assertEquals(
            expected = expected,
            actual = actual,
            message = "Transformation failed for $input",
        )
    }
}
