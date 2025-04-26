package org.alexcawl.sculptor.common.presenter

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.ModifierContract
import kotlin.reflect.KClass

/**
 * Abstract presenter class responsible for managing UI modifiers based on input data.
 *
 * This class extends the [Presenter] class and is designed to work with input data that
 * implements or extends the [ModifierContract] interface. The output of this presenter
 * is always of type [Modifier].
 *
 * @param Input The type of input data that this presenter will handle. It must implement or extend
 *              the [ModifierContract] interface.
 */
public abstract class ModifierPresenter<Input : ModifierContract> : Presenter<Input, Modifier>() {
    /**
     * The output type of this presenter, which is always [Modifier].
     */
    public final override val output: KClass<Modifier> = Modifier::class
}
