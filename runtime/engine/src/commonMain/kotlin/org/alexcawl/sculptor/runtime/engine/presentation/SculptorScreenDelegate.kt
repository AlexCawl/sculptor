package org.alexcawl.sculptor.runtime.engine.presentation

import androidx.lifecycle.ViewModelStoreOwner
import org.alexcawl.sculptor.runtime.engine.Sculptor
import org.alexcawl.sculptor.runtime.engine.SculptorBuilder
import org.alexcawl.sculptor.runtime.engine.SculptorUi
import kotlin.properties.ReadOnlyProperty

public fun sculptorUi(
    builder: SculptorBuilder.() -> Unit = {},
): ReadOnlyProperty<ViewModelStoreOwner, SculptorUi> {
    val sculptor: Sculptor = Sculptor.create(builder)
    return sculptorUi(sculptor)
}

public fun sculptorUi(
    sculptor: Sculptor,
): ReadOnlyProperty<ViewModelStoreOwner, SculptorUi> {
    return ReadOnlyProperty<ViewModelStoreOwner, SculptorUi> { thisRef: ViewModelStoreOwner, _ ->
        SculptorUiDelegateImpl(
            sculptorConnector = sculptor.connector,
            viewModelStoreOwner = thisRef,
        )
    }
}
