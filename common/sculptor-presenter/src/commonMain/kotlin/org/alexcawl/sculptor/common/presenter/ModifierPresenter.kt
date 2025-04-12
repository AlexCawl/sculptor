package org.alexcawl.sculptor.common.presenter

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.ModifierContract
import kotlin.reflect.KClass

public abstract class ModifierPresenter<Input : ModifierContract> : Presenter<Input, Modifier>() {
    public final override val output: KClass<Modifier> = Modifier::class
}
