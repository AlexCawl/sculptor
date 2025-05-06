package org.alexcawl.sculptor.runtime.engine

import org.alexcawl.sculptor.internal.di.DiTree
import org.alexcawl.sculptor.runtime.engine.dependencies.ContentResolutionStrategy
import org.alexcawl.sculptor.runtime.engine.dependencies.RemoteContentSource
import org.alexcawl.sculptor.runtime.engine.di.SculptorBuilderImpl
import org.alexcawl.sculptor.runtime.engine.di.SculptorGlobalBuilderImpl
import org.alexcawl.sculptor.runtime.engine.domain.SculptorStore
import kotlin.test.Test
import kotlin.test.assertFailsWith

class SculptorBuilderTest {
    private val globalBuilder: SculptorGlobalBuilderImpl
        get() = SculptorGlobalBuilderImpl()
    private val builder: SculptorBuilderImpl
        get() = SculptorBuilderImpl(globalDiTree = globalBuilder.build())

    @Test
    fun `Check WHEN sculptor builder is not fully built THEN SculptorStore is not available`() {
        val diTree: DiTree = builder.build()
        assertFailsWith<IllegalStateException> {
            diTree.get(SculptorStore::class)
        }
    }

    @Test
    fun `Check WHEN sculptor builder is fully built THEN SculptorStore is available`() {
        val diTree: DiTree = builder.build()
        with(diTree.diComponent) {
            singleton(
                key = RemoteContentSource::class,
                type = RemoteContentSource::class,
                factory = {
                    object : RemoteContentSource {
                        override suspend fun fetch(url: String): Result<String> =
                            Result.success(value = "test")
                    }
                }
            )
            singleton(
                key = ContentResolutionStrategy::class,
                type = ContentResolutionStrategy::class,
                factory = { ContentResolutionStrategy.RemoteFirst },
            )
        }
        diTree.get(SculptorStore::class)
    }
}
