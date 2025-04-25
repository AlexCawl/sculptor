package org.alexcawl.sculptor.common.presenter

import kotlin.reflect.KClass

public fun interface PresenterProvider {
    public suspend fun findPresenter(inputClass: KClass<out Any>, outputClass: KClass<out Any>): Presenter<*, *>
}
