package olegkov33.solver

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import olegkov33.solver.logic.main_app.Calculations
import olegkov33.solver.logic.main_app.Node
import olegkov33.solver.model_and_buttons.ControllingModelWithButtons


class CombinationOfButtonsAndModel {

    /**
     * This is the implementation that creates buttons and tetra-minx model that is modified via
     * buttons
     * @param currentScreen parameter used to change to solution screen
     * @param statusMessage parameter used as placeholder, which is shown in solution screen
     * @param nodeStates parameter used when mapping solution in solution screen
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


        Row{
            Button(onClick = {
                beginSolving(innerArray, statusMessage, nodeStates)
                currentScreen.value = WindowState.Solving
            }){
                Text(text = "Start Training", color = Color.White)
            }
        }


        model.generateButtonsAndTetraminx(innerArray, arrayOfTetraminxColours)

    }

    private fun beginSolving(
        innerArray: Array<Array<Color>>,
        statusMessage: MutableState<String>,
        nodeStates: MutableList<Node>
    ) {

        val startingNode = Node()
        val workingArray = Array(4){
            IntArray(6)
            IntArray(6)
            IntArray(6)
            IntArray(6)
        }

        for(side in innerArray.indices){
            for( cell in innerArray[side].indices){

                if(innerArray[side][cell] == Color.Red || innerArray[side][cell] == Color.LightGray){
                    workingArray[side][cell] = 0
                }
                if(innerArray[side][cell] == Color.Green || innerArray[side][cell] == Color.Magenta){
                    workingArray[side][cell] = 1
                }
                if (innerArray[side][cell] == Color.Blue || innerArray[side][cell] == Color.Cyan){
                    workingArray[side][cell] = 2
                }
                if(innerArray[side][cell] == Color.Yellow || innerArray[side][cell] == Color.DarkGray){
                    workingArray[side][cell] = 3
                }
            }
        }

        startingNode.setName("start")
        startingNode.setState(workingArray)

        val calculations = Calculations(startingNode)
        calculations.setStatusMessage(statusMessage)
        val result = calculations.start()

        if( result != null){

            nodeStates.addAll( result)
        }
    }

}