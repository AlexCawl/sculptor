package org.alexcawl.sculptor.runtime.engine

import androidx.compose.runtime.Immutable

@Immutable
public abstract class SculptorIntent(public open val payload: Any)

@Immutable
public data class SculptorStringIntent(override val payload: String) : SculptorIntent(payload)
