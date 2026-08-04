package olegkov33.tetramix_solver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import olegkov33.AndroidVersion
import olegkov33.solver.Window
import olegkov33.solver.model_and_buttons.ColorButtons
import olegkov33.solver.model_and_buttons.ControllingModelWithButtons

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            AndroidVersion().generatingAndroidVersion()
        }
    }
}
