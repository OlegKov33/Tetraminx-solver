package olegkov33.tetraminx_solver

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Tetraminx-solver",
    ) {
        App()
    }
}