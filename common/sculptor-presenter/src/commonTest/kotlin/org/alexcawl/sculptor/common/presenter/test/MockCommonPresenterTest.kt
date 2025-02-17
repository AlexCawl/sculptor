package org.alexcawl.sculptor.common.presenter.test

import org.alexcawl.sculptor.common.presenter.CommonPresenter
import org.alexcawl.sculptor.common.presenter.CommonPresenterTest
import org.alexcawl.sculptor.common.presenter.commonPresenter
import org.alexcawl.sculptor.common.presenter.mock.MockProperty
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MockCommonPresenterTest : CommonPresenterTest<MockProperty, String>() {
    override val presenter: CommonPresenter<MockProperty, String> = commonPresenter(
        input = MockProperty::class,
        output = String::class,
        transformer = { input -> input.testValue },
    )

    override val input: MockProperty = MockProperty(
        testValue = "Hello World!",
    )

    override val expected: String = "Hello World!"
}
