package org.alexcawl.sculptor.common.presenter.test

import org.alexcawl.sculptor.common.presenter.CommonPresenter
import org.alexcawl.sculptor.common.presenter.CommonPresenterTest
import org.alexcawl.sculptor.common.presenter.commonPresenter
import org.alexcawl.sculptor.common.presenter.mock.Mock
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MockCommonPresenterTest : CommonPresenterTest<Mock, String>() {
    override val presenter: CommonPresenter<Mock, String> = commonPresenter(
        input = Mock::class,
        output = String::class,
        transformer = { input -> input.data },
    )

    override val input: Mock = Mock(
        data = "Hello World!",
    )

    override val expected: String = "Hello World!"
}
