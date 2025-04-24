package org.alexcawl.sculptor.engine.impl

import androidx.lifecycle.ViewModel
import org.alexcawl.sculptor.common.di.DiTree

internal class SculptorViewModel(private val diTree: DiTree) : ViewModel(), DiTree by diTree {
    override fun onCleared(): Unit = diTree.close()
}
