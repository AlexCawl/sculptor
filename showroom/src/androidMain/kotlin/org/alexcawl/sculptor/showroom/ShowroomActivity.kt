package org.alexcawl.sculptor.showroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.alexcawl.sculptor.runtime.engine.Sculptor
import org.alexcawl.sculptor.runtime.engine.ui.SculptorStringIntent

public class ShowroomActivity : ComponentActivity() {
    private val sculptor: Sculptor by Sculptor.create { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            sculptor.open(
                intent = SculptorStringIntent(payload = "showroom://gallery"),
                placeholderScreen = {
                    BasicText(text = "Placeholder")
                },
                loadingScreen = {
                    BasicText(text = "Loading")
                },
                errorScreen = {
                    BasicText(text = "Error")
                },
                modifier = Modifier
            )
        }
    }
}

@Preview
@Composable
private fun AppAndroidPreview() {
    App()
}
