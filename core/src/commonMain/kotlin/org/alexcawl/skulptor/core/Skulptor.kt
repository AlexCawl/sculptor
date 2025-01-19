package org.alexcawl.skulptor.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier

@Composable
fun Skulptor(
    rootId: String,
    schema: SkulptorSchema,
    modifier: Modifier = Modifier,
    scope: Any = Any(),
    dispatch: (SkulptorAction) -> Unit = {},
) {
    CompositionLocalProvider(value = schemaProvider provides schema) {
        CompositionLocalProvider(value = scopeProvider provides scope) {
            CompositionLocalProvider(value = dispatchProvider provides dispatch) {
                val layout = schema.getLayout(rootId)
                layout.factory.invoke(
                    layoutId = layout.id,
                    stateProvider = { schema.getState(rootId) },
                    modifiers = layout.modifiers,
                    initial = modifier
                ).invoke()
            }
        }
    }
}
