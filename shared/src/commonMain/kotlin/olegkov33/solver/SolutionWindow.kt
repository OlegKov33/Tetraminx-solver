package olegkov33.solver

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import olegkov33.solver.logic.main_app.Node

class SolutionScreen {
    // TODO ! DONT FORGET TO CLEAR THE SELECTION OF EVERTHING MUTABLE!!!

    @Composable
    fun btnWithChange(
        currentScreen: MutableState<WindowState>,
        statusMessage: MutableState<String>,
        nodeStates: MutableList<Node>
    ) {
        Button(
            onClick = {
                statusMessage.value = ""
                nodeStates.clear()
                currentScreen.value = WindowState.Setup
                      },
            shape = CircleShape
        ){
            Text(text = "Hello?")
        }

        // here you will check this and IF it is not null, do something...
        // otherwise... just display a message and return to other screen

        println("${statusMessage}, and $nodeStates")
        Column {
            displayHeaderText(statusMessage.value)

            if(nodeStates.size >1){
               Row(){
                   displayStepsAndButtons(nodeStates)

               }
            }
        }
    }

    @Composable
    private fun displayHeaderText(text : String){

        Text(text = text)
    }

    @Composable
    private fun displayStepsAndButtons(nodeStates: MutableList<Node>) {
        var stepCounter = remember {mutableStateOf(0)}
        var currentInstruction = remember {mutableStateOf(nodeStates.size - 1)}
        val nextButtonEnabled = remember { mutableStateOf(true) }
        val previousButtonEnabled = remember { mutableStateOf(false) }
        val displayedStep = remember {mutableStateOf("")}

        Button(
            onClick = {
                if(nodeStates.size-1 > stepCounter.value) {
                    stepCounter.value ++
                    previousButtonEnabled.value = true
                }else{
                    nextButtonEnabled.value = false
                }

                displayedStep.value = instructionsText(
                    nodeStates[stepCounter.value-1].getNodeState(),
                    nodeStates[stepCounter.value].getNodeState())
                currentInstruction.value --
          },
            shape = CircleShape
        ) {
            Text("Previous Step")
        }


        Button(
            onClick = {
                if(0 < stepCounter.value) {
                    stepCounter.value --
                    nextButtonEnabled.value = true
                }else{
                    previousButtonEnabled.value = false
                }
                displayedStep.value = instructionsText(
                    nodeStates[stepCounter.value+1].getNodeState(),
                    nodeStates[stepCounter.value].getNodeState())

                currentInstruction.value ++
              },
            shape = CircleShape
        ) {
            Text("Next Step")
        }

        Text(text = displayedStep.value)


    }

    private fun instructionsText(parentNode: Array<IntArray>, currentNode: Array<IntArray>) : String{

        if (currentNode[0][1] == parentNode[1][1]) {
            println("Next move is turning top side right >>>")
            return "Next move is turning top side right >>>"
        }
        if (currentNode[0][1] == parentNode[2][1]) {
            println("Next move is turning top side left <<<")
            return "Next move is turning top side left <<<"
        }


        if (currentNode[0][3] == parentNode[1][5]) {
            println("Next move is turning right side away from you >>>")
            return "Next move is turning right side away from you >>>"
        }
        if (currentNode[0][3] == parentNode[3][1]) {
            println("Next move is turning right side towards you <<<")
            return "Next move is turning right side towards you <<<"
        }


        if (currentNode[0][5] == parentNode[2][3]) {
            println("Next move is turning left side away from you <<<")
            return "Next move is turning left side away from you <<<"
        }
        if (currentNode[0][5] == parentNode[3][5]) {
            println("Next move is turning left side towards you >>>")
            return "Next move is turning left side towards you >>>"
        }


        if (currentNode[1][3] == parentNode[2][5]) {
            println("Next move is turning back side left <<<")
            return "Next move is turning back side left <<<"
        }
        if (currentNode[1][3] == parentNode[3][3]) {
            println("Next move is turning back side right >>>")
            return "Next move is turning back side right >>>"
        }


        return "WARNING, IMPOSSIBLE ANSWER!"
    }

}