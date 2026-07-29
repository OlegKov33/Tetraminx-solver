package olegkov33.tetraminx_solver

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import olegkov33.solver.ControllingModelWithButtons
import olegkov33.solver.LogicAndButton
import olegkov33.solver.TetraminxModel
import olegkov33.solver.logic.main_app.Calculations
import olegkov33.solver.logic.main_app.Node
import olegkov33.solver.logic.utils.Scrambler

fun main(): Unit = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Tetraminx-solver",
    ) {

        //LogicAndButton().logicMeetsButton()
        LogicAndButton().addingModelAndButtons()
    }
}