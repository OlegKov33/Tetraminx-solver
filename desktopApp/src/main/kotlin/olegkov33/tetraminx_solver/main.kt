package olegkov33.tetraminx_solver

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import olegkov33.solver.ControllingModelWithButtons
import olegkov33.solver.TetraminxModel
import olegkov33.solver.logic.main_app.Calculations
import olegkov33.solver.logic.main_app.Node
import olegkov33.solver.logic.utils.Scrambler

fun main(): Unit = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Tetraminx-solver",
    ) {

        // TODO REMOVE THIS AT PROD
        val startingNode = Node()
        val finishingNode = Node()

        val scrambler = Scrambler()
        val initState = scrambler.scramble(3)

        startingNode.setState(initState)

        val calculations = Calculations(startingNode, finishingNode)
        println(calculations.start())
        // TODO REMOVE THIS AT PROD

        //ControllingModelWithButtons().generateButtonsAndTetraminx()
    }
}