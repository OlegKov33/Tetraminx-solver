package olegkov33.solver.screens

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
import olegkov33.solver.logic.utils.Rotator

class SolutionScreen {

    /**
     * Method used to create a button to change to tetra-minx config and will display solution steps
     * @param currentScreen used to change to tetra-minx config screen
     * @param statusMessage used to indicate if goal was found or something went wrong
     * @param nodeStates used to construct the path from initial to goal state
     */
    @Composable
    fun ButtonsMenu(
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
            Text(text = "Back")
        }

        Column {
            displayHeaderText(statusMessage.value)

            nodeStates.forEachIndexed { i, n -> println("  $i: ${n.printNode()}") }
            if(nodeStates.size > 1){
               Row{
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

        val stepCounter = remember {mutableStateOf(0)}
        val maxStepCount = nodeStates.size-1
        val step = stepCounter.value

        val canGoNext = stepCounter.value < maxStepCount
        val canGoPrev = stepCounter.value > 0

        val instruction = when{
            step == 0 -> "Start position"
            else -> instructionsText(
                nodeStates[step-1].getNodeState(),
                nodeStates[step].getNodeState()
            )
        }

        Column{
            Text("Step ${stepCounter.value}/${maxStepCount}")
            Text(instruction)

        Row {
            Button(
                onClick = {
                    if (canGoPrev) {
                        stepCounter.value--
                    }
                },
                enabled = canGoPrev,
                shape = CircleShape
            ) {
                Text(text = "Previous Step")
            }

            Button(
                onClick = {
                    if (canGoNext) {
                        stepCounter.value++
                    }
                },
                enabled = canGoNext,
                shape = CircleShape
            ) {
                Text(text = "Next Step")
            }
        }

        }


    }

    private fun instructionsText(parent: Array<IntArray>, child: Array<IntArray>) : String{

        val rotator = Rotator()

        if (rotator.rotateFrontTopToRight(parent).contentDeepEquals(child))
            return "turning top side right >>>"
        if (rotator.rotateFrontTopToLeft(parent).contentDeepEquals(child))
            return "turning top side left <<<"
        if (rotator.rotateFrontRightSideTowards(parent).contentDeepEquals(child))
            return "turning right side towards you <<<"
        if (rotator.rotateFrontRightSideAway(parent).contentDeepEquals(child))
            return "turning right side away from you >>>"
        if (rotator.rotateFrontLeftSideTowards(parent).contentDeepEquals(child))
            return "turning left side towards you >>>"
        if (rotator.rotateFrontLeftSideAway(parent).contentDeepEquals(child))
            return "turning left side away from you <<<"
        if (rotator.rotateBackSideToRight(parent).contentDeepEquals(child))
            return "turning back side right >>>"
        if (rotator.rotateBackSideToLeft(parent).contentDeepEquals(child))
            return "turning back side left <<<"

        return "WARNING, IMPOSSIBLE ANSWER!"
    }


}