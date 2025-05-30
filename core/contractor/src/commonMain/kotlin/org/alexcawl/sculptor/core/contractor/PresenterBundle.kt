package org.alexcawl.sculptor.core.contractor

import org.alexcawl.sculptor.core.contractor.presenter.Presenter
import kotlin.reflect.KClass

public interface PresenterBundle {
    public val presenters: Consumer.() -> Unit

    public interface Consumer {
        public fun <K : Presenter<*, *>> presenter(key: KClass<K>, presenter: () -> K)
    }
}

public inline fun <reified K : Presenter<*, *>> PresenterBundle.Consumer.presenter(noinline presenter: () -> K) {
    presenter(key = K::class, presenter = presenter)
}
