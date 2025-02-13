package org.alexcawl.sculptor.common.presenter

import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.id
import org.alexcawl.sculptor.common.contract.layout.LayoutContract
import org.alexcawl.sculptor.common.contract.layout.ModifierContract
import org.alexcawl.sculptor.common.contract.layout.StateContract
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import org.alexcawl.sculptor.common.layout.Layout
import kotlin.reflect.KClass
import kotlin.test.Test
import kotlin.test.assertEquals

class LayoutPresenterTest {
    @OptIn(InternalSculptorApi::class)
    @Test
    fun `Check if presenter can be matched with input and output markers`() {
        val presenter: LayoutPresenter<TestLayoutContract, TestStateContract, TestLayout> = LayoutPresenterMock()
        val presenterScope = PresenterScope(
            delegateTransform = { input, output, _ ->
                when {
                    input is ModifierContract && output is Modifier -> Modifier
                    else -> error("Unknown input and output types")
                }
            }
        )
        val input = TestLayoutContract(
            id = "root".id,
            state = "state1".id,
            modifiers = emptyList(),
            states = listOf(
                TestStateContract(
                    id = "state1".id,
                    modifiers = emptyList(),
                    testValue = "testValue"
                )
            )
        )
        val output = TestLayout(
            id = "root@state1",
            modifier = Modifier,
            testValue = "testValue"
        )

        assertEquals(
            expected = presenter.input,
            actual = input::class,
            message = "Presenter input type is not Int"
        )

        assertEquals(
            expected = presenter.output,
            actual = output::class,
            message = "Presenter output type is not String"
        )

        val transformed = presenter.internalTransform(presenterScope, input)

        assertEquals(
            expected = output,
            actual = transformed,
            message = "Presenter output is not correct"
        )
    }
}

private class LayoutPresenterMock : LayoutPresenter<TestLayoutContract, TestStateContract, TestLayout>() {
    override val input: KClass<TestLayoutContract> = TestLayoutContract::class
    override val output: KClass<TestLayout> = TestLayout::class

    override fun PresenterScope.transform(
        id: String,
        modifier: Modifier,
        state: TestStateContract
    ): TestLayout {
        return with(state) {
            TestLayout(
                id = id,
                modifier = modifier,
                testValue,
            )
        }
    }
}

@Serializable
@SerialName("contract@test")
private data class TestLayoutContract(
    override val id: Identifier,
    override val state: Identifier,
    override val modifiers: List<ModifierContract>,
    override val states: List<TestStateContract>
) : LayoutContract

@Serializable
@SerialName("state@test")
private data class TestStateContract(
    override val id: Identifier,
    override val modifiers: List<ModifierContract>,
    val testValue: String,
) : StateContract

@Immutable
private data class TestLayout(
    override val id: String,
    override val modifier: Modifier,
    val testValue: String,
) : Layout
