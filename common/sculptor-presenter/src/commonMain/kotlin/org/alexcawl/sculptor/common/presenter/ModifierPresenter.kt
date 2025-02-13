package org.alexcawl.sculptor.common.presenter

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.layout.ModifierContract

@Suppress(names = ["UNCHECKED_CAST", "EXTENSION_SHADOWED_BY_MEMBER"])
abstract class ModifierPresenter<Input : ModifierContract, Output : Modifier> : CommonPresenter<Input, Output>()
