package org.alexcawl.sculptor.foundation.presenter.layout

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.presenter.LayoutPresenter
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.foundation.contract.layout.BoxLayoutContract
import org.alexcawl.sculptor.foundation.contract.layout.BoxStateContract
import org.alexcawl.sculptor.foundation.layout.BoxLayout
import kotlin.reflect.KClass

public class BoxPresenter : LayoutPresenter<BoxLayoutContract, BoxStateContract>() {
    override val input: KClass<BoxLayoutContract> = BoxLayoutContract::class

    override fun PresenterScope.transform(
        id: String,
        modifier: Modifier,
        state: BoxStateContract
    ): BoxLayout {
        return with(state) {
            BoxLayout(
                id = id,
                modifier = modifier,
                contentAlignment = map(contentAlignment),
                propagateMinConstraints = propagateMinConstraints,
                content = content.map(::getLayout),
            )
        }
    }
}
