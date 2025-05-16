package org.alexcawl.sculptor.runtime.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import okio.Path.Companion.toPath
import org.alexcawl.sculptor.runtime.engine.dependencies.LocalContentSource
import java.io.File

public actual class SculptorLocalContentSource(
    private val dataStore: DataStore<Preferences>,
) : LocalContentSource {
    override suspend fun find(key: String): String? {
        val contentKey: Preferences.Key<String> = stringPreferencesKey(key)
        return dataStore.data
            .map { preferences: Preferences -> preferences[contentKey] }
            .first()
    }

    override suspend fun save(key: String, content: String) {
        val contentKey: Preferences.Key<String> = stringPreferencesKey(key)
        dataStore.edit { preferences: MutablePreferences ->
            preferences[contentKey] = content
        }
    }

    public actual class Factory(
        private val context: Context,
        private val coroutineScope: CoroutineScope,
    ) {
        public actual fun create(): SculptorLocalContentSource {
            val dataStore: DataStore<Preferences> = PreferenceDataStoreFactory
                .createWithPath(
                    scope = coroutineScope,
                    produceFile = {
                        File(
                            /* parent = */ context.filesDir,
                            /* child = */ "sculptor.preferences"
                        ).path.toPath()
                    }
                )
            return SculptorLocalContentSource(dataStore = dataStore)
        }
    }
}
