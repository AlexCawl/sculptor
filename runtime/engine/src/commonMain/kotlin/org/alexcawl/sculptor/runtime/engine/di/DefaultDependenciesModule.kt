package org.alexcawl.sculptor.runtime.engine.di

import org.alexcawl.sculptor.internal.di.Module
import org.alexcawl.sculptor.internal.di.module
import org.alexcawl.sculptor.internal.di.singleton
import org.alexcawl.sculptor.runtime.engine.dependencies.dataSource.LocalContentSource
import org.alexcawl.sculptor.runtime.engine.dependencies.dataSource.impl.InMemoryLocalContentSourceImpl
import org.alexcawl.sculptor.runtime.engine.dependencies.logger.SculptorLogger
import org.alexcawl.sculptor.runtime.engine.dependencies.logger.impl.NoOpSculptorLoggerImpl

internal fun defaultDependenciesModule(): Module = module {
    singleton<SculptorLogger> {
        NoOpSculptorLoggerImpl
    }
    singleton<LocalContentSource> {
        InMemoryLocalContentSourceImpl()
    }
}
