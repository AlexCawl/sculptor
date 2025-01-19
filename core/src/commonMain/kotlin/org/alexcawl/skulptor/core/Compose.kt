package org.alexcawl.skulptor.core

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.util.fastFilter
import org.alexcawl.skulptor.core.factory.BaseLayout
import kotlin.properties.Delegates

private val defaultScope: Any = Any()
internal val scopeProvider: ProvidableCompositionLocal<Any> = compositionLocalOf { defaultScope }

private val defaultDispatch: (SkulptorAction) -> Unit = {}
internal val dispatchProvider: ProvidableCompositionLocal<(SkulptorAction) -> Unit> = compositionLocalOf { defaultDispatch }

private val defaultSchema: SkulptorSchema by Delegates.notNull()
internal val schemaProvider: ProvidableCompositionLocal<SkulptorSchema> = compositionLocalOf { defaultSchema }

internal fun SkulptorSchema.getState(id: String): BaseState =
    this.states.fastFilter { it.id == id }.first()

internal fun SkulptorSchema.getLayout(layoutId: String): BaseLayout<out BaseState> =
    this.layouts.fastFilter { it.id == layoutId }.first()
