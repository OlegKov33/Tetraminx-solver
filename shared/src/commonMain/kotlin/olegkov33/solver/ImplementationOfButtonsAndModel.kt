package olegkov33.solver

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import olegkov33.solver.logic.main_app.Calculations
import olegkov33.solver.logic.main_app.Node
import olegkov33.solver.model_and_buttons.ControllingModelWithButtons


class ImplementationOfButtonsAndModel {
    // I can now do this:
    // add color to tetra minx
    // change colors
    // mock test
    // TIME FOR REAL TESTING;
    // ITS WORKING!!! LET'S GOOO!!!
    // now, let's add proper message output
    // how to solve it (steps)
    // animations... I'll probably do them with AI


    @Composable
    fun addingModelAndButtons(
        currentScreen: MutableState<WindowState>,
        statusMessage: MutableState<String>,
        nodeStates: MutableList<Node>
    ) {
        val innerArray = remember{
            arrayOf(
                arrayOf(Color.Unspecified,Color.Unspecified,Color.Unspecified ,Color.Unspecified,Color.Unspecified,Color.Unspecified),
                arrayOf(Color.Unspecified,Color.Unspecified,Color.Unspecified ,Color.Unspecified,Color.Unspecified,Color.Unspecified),
                arrayOf(Color.Unspecified,Color.Unspecified,Color.Unspecified ,Color.Unspecified,Color.Unspecified,Color.Unspecified),
                arrayOf(Color.Unspecified,Color.Unspecified,Color.Unspecified ,Color.Unspecified,Color.Unspecified,Color.Unspecified)
            )
        }


        val model = ControllingModelWithButtons()

        Row{
            Button(
                onClick = {
                    //currentScreen.value = WindowState.Solving
                    for (item in innerArray){
                        println(item.contentToString())
                    }
                },
                shape = CircleShape
            ){
                Text("show innerArray")
            }

            //buttonToStartTraining(innerArray)
            model.generateButtonsAndTetraminx(innerArray)

            Button(onClick = {
                training(innerArray, statusMessage, nodeStates)
                currentScreen.value = WindowState.Solving
            }){
                Text(text = "Start Training", color = Color.White)
            }
        }

    }

//    @Composable
//    fun buttonToStartTraining(innerArray: Array<Array<Color>>) {
//
//
//
//    }

    private fun training(
        innerArray: Array<Array<Color>>,
        statusMessage: MutableState<String>,
        nodeStates: MutableList<Node>
    ) {
        // transform array into ints :)
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

        val startingNode = Node()
        startingNode.setState(workingArray)
        val goalNode = Node()
        val calculations = Calculations(startingNode, goalNode)

        calculations.setStatusMessage(statusMessage)
        val result = calculations.start()
        if( result != null){

            nodeStates.addAll( result)
        }
    }

}