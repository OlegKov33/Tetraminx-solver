package olegkov33.tetraminx_solver

import androidx.compose.foundation.layout.Column
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import olegkov33.solver.Window

fun main(): Unit = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Tetraminx-solver",
    ) {
        Column{
            Window().windowLogic()
        }
    }
}