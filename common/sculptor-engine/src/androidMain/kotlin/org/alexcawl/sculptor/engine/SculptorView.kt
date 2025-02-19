package org.alexcawl.sculptor.engine

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.viewinterop.AndroidView
import org.alexcawl.sculptor.common.engine.R
import kotlin.properties.Delegates

private typealias Provider<T> = () -> T
private fun <T> empty(msg: String): Provider<T> = {
    error("No $msg provided")
}

public class SculptorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AbstractComposeView(context, attrs, defStyleAttr) {
    @get:LayoutRes
    private var loadingLayout: Int by Delegates.notNull()
    @get:LayoutRes
    private var errorLayout: Int by Delegates.notNull()

    private var sculptorStateProvider: Provider<SculptorState> by mutableStateOf(
        empty(msg = "sculptor state")
    )
    private var launchModeProvider: Provider<LaunchMode> by mutableStateOf(
        empty(msg = "launch mode")
    )

    init {
        val attributes = context.theme.obtainStyledAttributes(
            /* set = */ attrs,
            /* attrs = */ R.styleable.SculptorView,
            /* defStyleAttr = */ defStyleAttr,
            /* defStyleRes = */ 0
        )
        with(attributes) {
            try {
                loadingLayout = getResourceId(
                    /* index = */ R.styleable.SculptorView_loadingLayout,
                    /* defValue = */ 0,
                )
                errorLayout = getResourceId(
                    /* index = */ R.styleable.SculptorView_errorLayout,
                    /* defValue = */ 0,
                )
            } finally {
                recycle()
            }
        }
    }

    @Composable
    override fun Content() {
        val sculptorState: SculptorState = remember(sculptorStateProvider)
        val launchMode: LaunchMode = remember(launchModeProvider)
        val modifier = Modifier.fillMaxSize()
        Sculptor(
            sculptorState = sculptorState,
            launchMode = launchMode,
            loading = {
                AndroidView(
                    factory = { context ->
                        LayoutInflater.from(context).inflate(
                            /* resource = */ loadingLayout,
                            /* root = */ null,
                        )
                    },
                )
            },
            error = {
                AndroidView(
                    factory = { context ->
                        LayoutInflater.from(context).inflate(
                            /* resource = */ errorLayout,
                            /* root = */ null,
                        )
                    }
                )
            },
            modifier = modifier,
        )
    }

    public fun render(
        sculptorState: SculptorState,
        launchMode: LaunchMode,
    ) {
        sculptorStateProvider = { sculptorState }
        launchModeProvider = { launchMode }
    }
}
