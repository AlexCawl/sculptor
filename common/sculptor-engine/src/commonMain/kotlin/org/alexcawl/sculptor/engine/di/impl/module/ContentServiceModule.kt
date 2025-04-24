package org.alexcawl.sculptor.engine.di.impl.module

import org.alexcawl.sculptor.common.di.Module
import org.alexcawl.sculptor.common.di.get
import org.alexcawl.sculptor.common.di.getAll
import org.alexcawl.sculptor.common.di.module
import org.alexcawl.sculptor.common.di.singleton
import org.alexcawl.sculptor.engine.api.contentService.ContentResolver
import org.alexcawl.sculptor.engine.impl.contentService.LocalMainContentResolverImpl
import org.alexcawl.sculptor.engine.impl.contentService.ContentService
import org.alexcawl.sculptor.engine.impl.contentService.OnlyRemoteContentResolverImpl
import org.alexcawl.sculptor.engine.impl.contentService.RemoteMainContentResolverImpl

internal fun contentServiceModule(): Module = module {
    singleton {
        ContentService(
            contentResolutionStrategy = get(),
            contentResolvers = getAll(),
        )
    }
    singleton<LocalMainContentResolverImpl, ContentResolver> {
        LocalMainContentResolverImpl(
            remoteContentSource = get(),
            localContentSource = get(),
        )
    }
    singleton<OnlyRemoteContentResolverImpl, ContentResolver> {
        OnlyRemoteContentResolverImpl(
            remoteContentSource = get(),
        )
    }
    singleton<RemoteMainContentResolverImpl, ContentResolver> {
        RemoteMainContentResolverImpl(
            remoteContentSource = get(),
            localContentSource = get(),
        )
    }
}
