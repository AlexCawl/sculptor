package org.alexcawl.sculptor.internal.di.compose

import androidx.lifecycle.ViewModel
import org.alexcawl.sculptor.internal.di.DiTree

@PublishedApi
internal class DiTreeViewModel(private val diTree: DiTree) : ViewModel(), DiTree by diTree {
    override fun onCleared(): Unit = diTree.close()
}
