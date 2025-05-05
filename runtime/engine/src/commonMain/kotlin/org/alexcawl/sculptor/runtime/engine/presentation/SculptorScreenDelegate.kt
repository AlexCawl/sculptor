package org.alexcawl.sculptor.runtime.engine.presentation

import androidx.lifecycle.ViewModelStoreOwner
import org.alexcawl.sculptor.runtime.engine.Sculptor
import org.alexcawl.sculptor.runtime.engine.SculptorBuilder
import org.alexcawl.sculptor.runtime.engine.SculptorScreen
import kotlin.properties.ReadOnlyProperty

public fun sculptorScreen(
    builder: SculptorBuilder.() -> Unit = {},
): ReadOnlyProperty<ViewModelStoreOwner, SculptorScreen> {
    val sculptor: Sculptor = Sculptor.create(builder)
    return sculptorScreen(sculptor)
}

public fun sculptorScreen(
    sculptor: Sculptor,
): ReadOnlyProperty<ViewModelStoreOwner, SculptorScreen> {
    return ReadOnlyProperty<ViewModelStoreOwner, SculptorScreen> { thisRef: ViewModelStoreOwner, _ ->
        SculptorScreenDelegateImpl(
            sculptorConnector = sculptor.connector,
            viewModelStoreOwner = thisRef,
        )
    }
}
