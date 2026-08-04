package olegkov33.tetramix_solver

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import olegkov33.solver.Window

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Tetramix-solver",
    ) {
        Window().windowLogic()
    }
}