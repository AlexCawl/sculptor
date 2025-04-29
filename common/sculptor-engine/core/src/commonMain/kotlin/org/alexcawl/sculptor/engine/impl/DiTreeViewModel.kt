package org.alexcawl.sculptor.engine.impl

import androidx.lifecycle.ViewModel
import org.alexcawl.sculptor.internal.di.DiTree

internal class DiTreeViewModel(private val diTree: DiTree) : ViewModel(), DiTree by diTree
