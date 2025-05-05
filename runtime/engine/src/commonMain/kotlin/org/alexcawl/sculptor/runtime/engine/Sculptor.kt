package org.alexcawl.sculptor.runtime.engine

import androidx.compose.runtime.Stable
import org.alexcawl.sculptor.internal.di.DiTree
import org.alexcawl.sculptor.runtime.engine.di.SculptorBuilderImpl
import org.alexcawl.sculptor.runtime.engine.di.SculptorConnector
import org.alexcawl.sculptor.runtime.engine.di.SculptorGlobalBuilderImpl

@Stable
public interface Sculptor : AutoCloseable {
    public val connector: SculptorConnector

    public companion object {
        @Volatile
        private var globalDiTreeInstance: DiTree? = null

        @JvmStatic
        public val isInitialized: Boolean
            get() = globalDiTreeInstance != null

        @JvmStatic
        public fun initialize(builder: SculptorGlobalBuilder.() -> Unit) {
            val globalDiTree: DiTree = SculptorGlobalBuilderImpl().apply(builder).build()
            return when (isInitialized) {
                true -> error("Sculptor is already initialized")
                false -> globalDiTreeInstance = globalDiTree
            }
        }

        @JvmStatic
        public fun create(builder: SculptorBuilder.() -> Unit): Sculptor {
            val globalDiTree: DiTree = globalDiTreeInstance ?: error("Sculptor is not initialized")
            return object : Sculptor {
                override fun close() = connector.localDiTree.close()

                override val connector: SculptorConnector by lazy {
                    SculptorConnector(
                        localDiTree = SculptorBuilderImpl(globalDiTree = globalDiTree)
                            .apply(builder)
                            .build()
                    )
                }
            }
        }
    }
}
