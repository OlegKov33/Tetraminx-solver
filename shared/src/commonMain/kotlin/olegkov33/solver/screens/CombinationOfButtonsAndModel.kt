package olegkov33.solver.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import olegkov33.solver.logic.main_app.Calculations
import olegkov33.solver.logic.main_app.Node
import olegkov33.solver.logic.utils.Scrambler
import olegkov33.solver.model_and_buttons.ControllingModelWithButtons


class CombinationOfButtonsAndModel {

    /**
     * This is the implementation that creates buttons and tetra-minx model that is modified via
     * buttons
     * @param currentScreen parameter used to change to solution screen
     * @param statusMessage parameter used as placeholder, which is shown in solution screen
     * @param nodeStates parameter used when mapping solution in solution screen
     * @param innerArray invisible version of arrayOfTetraminxColours, not visible, logic that used for solving
     * @param arrayOfTetraminxColours visible version of innerArray, used to show user what they selected
     */
    @Composable
    fun AddingModelAndButtons(
        currentScreen: MutableState<WindowState>,
        statusMessage: MutableState<String>,
        nodeStates: MutableList<Node>,
        innerArray: Array<Array<Color>>,
        arrayOfTetraminxColours: Array<SnapshotStateList<Color>>
    ) {

        val model = ControllingModelWithButtons()


        Row {
            Button(onClick = {
                configureBeforeSolving(innerArray, statusMessage, nodeStates)
                currentScreen.value = WindowState.Solving
            }) {
                Text(text = "Solve", color = Color.White)
            }
            Button(onClick = {
                beginScrambling(statusMessage, nodeStates)
                currentScreen.value = WindowState.Solving
            }) {
                Text(text = "Scramble", color = Color.White)
            }
            Button(onClick = {
                currentScreen.value = WindowState.Info
            }) {
                Text(text = "?", color = Color.White)
            }
        }


        model.GenerateButtonsAndTetraminx(innerArray, arrayOfTetraminxColours)

    }

    private fun configureBeforeSolving(
        innerArray: Array<Array<Color>>,
        statusMessage: MutableState<String>,
        nodeStates: MutableList<Node>
    ) {

        val workingArray = Array(4) {
            IntArray(6)
            IntArray(6)
            IntArray(6)
            IntArray(6)
        }

        for (side in innerArray.indices) {
            for (cell in innerArray[side].indices) {

                if (innerArray[side][cell] == Color.Red || innerArray[side][cell] == Color.LightGray) {
                    workingArray[side][cell] = 0
                }
                if (innerArray[side][cell] == Color.Green || innerArray[side][cell] == Color.Magenta) {
                    workingArray[side][cell] = 1
                }
                if (innerArray[side][cell] == Color.Blue || innerArray[side][cell] == Color.Cyan) {
                    workingArray[side][cell] = 2
                }
                if (innerArray[side][cell] == Color.Yellow || innerArray[side][cell] == Color.DarkGray) {
                    workingArray[side][cell] = 3
                }
            }
        }

        beginSolving(
            workingArray,
            statusMessage,
            nodeStates
        )


    }

    private fun beginSolving(
        workingArray: Array<IntArray>,
        statusMessage: MutableState<String>,
        nodeStates: MutableList<Node>
    ) {

        val startingNode = Node()
        startingNode.setName("start")
        startingNode.setState(workingArray)

        val calculations = Calculations(startingNode)
        calculations.setStatusMessage(statusMessage)
        val result = calculations.start()

        if (result != null) {

            nodeStates.addAll(result)
        }
    }

    private fun beginScrambling(
        statusMessage: MutableState<String>,
        nodeStates: MutableList<Node>
    ) {

        val scrambler = Scrambler()
        val startingNode = Node()
        startingNode.setName("start")

        startingNode.setGoalState(scrambler.scramble((3..10).random()))

        val calculations = Calculations(startingNode)
        calculations.setStatusMessage(statusMessage)
        val result = calculations.scramblerStart()

        if (result != null) {

            nodeStates.addAll(result)
            statusMessage.value = "Follow the instructions to reach our scrambled state"
        }

    }
}