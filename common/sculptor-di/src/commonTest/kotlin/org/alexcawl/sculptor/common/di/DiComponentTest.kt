package org.alexcawl.sculptor.common.di

import org.alexcawl.sculptor.common.di.mocks.Facade
import org.alexcawl.sculptor.common.di.mocks.Interactor
import org.alexcawl.sculptor.common.di.mocks.InteractorImpl
import org.alexcawl.sculptor.common.di.mocks.Service
import org.alexcawl.sculptor.common.di.mocks.ServiceImplV1
import org.alexcawl.sculptor.common.di.mocks.ServiceImplV2
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertIs
import kotlin.test.assertNotNull

class DiComponentTest {
    @Test
    fun `Test that DiComponent works correctly with single value`() {
        val diComponent = DiComponent()
        assertFailsWith(IllegalStateException::class) {
            assertEquals(
                expected = "Hello",
                actual = diComponent.get(String::class),
            )
        }
        diComponent.addDeclaration(
            declaration = singletonDeclaration(
                key = String::class,
                type = String::class,
                factory = { "Hello" }
            ),
            autoCloseable = false
        )
        assertEquals(
            expected = "Hello",
            actual = diComponent.get(String::class),
        )
    }

    @Test
    fun `Test that DiComponent works correctly with value overrides`() {
        val diComponent = DiComponent()
        diComponent.addDeclaration(
            declaration = singletonDeclaration(
                key = String::class,
                type = String::class,
                factory = { "Hello" }
            ),
            autoCloseable = false
        )
        assertEquals(
            expected = "Hello",
            actual = diComponent.get(String::class),
        )
        diComponent.addDeclaration(
            declaration = singletonDeclaration(
                key = String::class,
                type = String::class,
                factory = { "World" }
            ),
            autoCloseable = false
        )
        assertEquals(
            expected = "World",
            actual = diComponent.get(String::class),
        )
        assertEquals(
            expected = 1,
            actual = diComponent.getAll(String::class).size,
        )
    }

    @Test
    fun `Test that DiComponent works correctly with multiple values`() {
        val diComponent = DiComponent()
        diComponent.addDeclarations(
            listOf(
                singletonDeclaration(
                    key = ServiceImplV1::class,
                    type = Service::class,
                    factory = { ServiceImplV1() },
                ),
                singletonDeclaration(
                    key = ServiceImplV2::class,
                    type = Service::class,
                    factory = { ServiceImplV2() },
                ),
            ),
            autoCloseable = false
        )
        assertEquals(
            expected = 2,
            actual = diComponent.getAll(Service::class).size,
        )
    }

    @Test
    fun `Test that DiComponent works correctly with module`() {
        val diComponent = DiComponent()
        diComponent.addModule(
            module {
                singleton(
                    key = ServiceImplV1::class,
                    type = Service::class,
                    factory = { ServiceImplV1() }
                )
                singleton(
                    key = ServiceImplV2::class,
                    type = Service::class,
                    factory = { ServiceImplV2() }
                )
                singleton(
                    key = InteractorImpl::class,
                    type = Service::class,
                    factory = { InteractorImpl() }
                )
            }
        )
        assertEquals(
            expected = 3,
            actual = diComponent.getAll(Service::class).size,
        )
    }

    @Test
    fun `Test that DiComponent works correctly with modules`() {
        val diComponent = DiComponent()
        diComponent.addModules(
            module {
                singleton(
                    key = InteractorImpl::class,
                    type = Service::class,
                    factory = { InteractorImpl() }
                )
            },
            module {
                singleton(
                    key = ServiceImplV1::class,
                    type = Service::class,
                    factory = { ServiceImplV1() }
                )
                singleton(
                    key = ServiceImplV2::class,
                    type = Service::class,
                    factory = { ServiceImplV2() }
                )
            }
        )
        assertEquals(
            expected = 3,
            actual = diComponent.getAll(Service::class).size,
        )
    }

    @Test
    fun `Test that DiComponent works correctly when it is cloned`() {
        val diComponent = DiComponent()
        diComponent.addDeclaration(
            declaration = singletonDeclaration(
                key = ServiceImplV1::class,
                type = Service::class,
                factory = { ServiceImplV1() },
            ),
            autoCloseable = false
        )
        assertIs<ServiceImplV1>(
            value = diComponent.getAll(Service::class).first(),
        )
        val clonedDiComponent = diComponent.clone()
        clonedDiComponent.addDeclaration(
            declaration = singletonDeclaration(
                key = ServiceImplV2::class,
                type = Service::class,
                factory = { ServiceImplV2() },
            ),
            autoCloseable = false
        )
        assertIs<ServiceImplV2>(
            value = clonedDiComponent.getAll(Service::class).first(),
        )
        assertIs<ServiceImplV1>(
            value = diComponent.getAll(Service::class).first(),
        )
    }

    @Test
    fun `Test that DiComponent works correctly when initializing value`() {
        val diComponent = DiComponent()
        diComponent.addDeclarations(
            listOf(
                singletonDeclaration(
                    key = ServiceImplV1::class,
                    type = Service::class,
                    factory = { ServiceImplV1() },
                ),
                singletonDeclaration(
                    key = ServiceImplV2::class,
                    type = Service::class,
                    factory = { ServiceImplV2() },
                ),
                singletonDeclaration(
                    key = Interactor::class,
                    type = Interactor::class,
                    factory = { InteractorImpl() }
                ),
                singletonDeclaration(
                    key = Facade::class,
                    type = Facade::class,
                    factory = { Facade(services = getAll(), interactor = get()) }
                )
            ),
            autoCloseable = false
        )
        assertNotNull(
            actual = diComponent.getOrNull(Facade::class),
        )
    }
}
