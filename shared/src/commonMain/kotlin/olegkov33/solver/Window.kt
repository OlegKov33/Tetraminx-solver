package olegkov33.solver

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import olegkov33.solver.logic.main_app.Node


class Window {

    /**
     * Method used to combine the buttons + tetra-minx with solution window
     */
    @Composable
    fun windowLogic(){
        val currentScreen = remember{ mutableStateOf(WindowState.Setup)}
        val statusMessage = remember{ mutableStateOf("")}
        val finalNodes : MutableList<Node> = remember { mutableListOf() }
        val arrayOfTetraminxColours : Array<SnapshotStateList<Color>> =
            remember { Array(4){ mutableStateListOf(*Array(6) { Color.Black }) }}

        val innerArray = remember{
            arrayOf(
                arrayOf(Color.Unspecified,Color.Unspecified,Color.Unspecified ,Color.Unspecified,Color.Unspecified,Color.Unspecified),
                arrayOf(Color.Unspecified,Color.Unspecified,Color.Unspecified ,Color.Unspecified,Color.Unspecified,Color.Unspecified),
                arrayOf(Color.Unspecified,Color.Unspecified,Color.Unspecified ,Color.Unspecified,Color.Unspecified,Color.Unspecified),
                arrayOf(Color.Unspecified,Color.Unspecified,Color.Unspecified ,Color.Unspecified,Color.Unspecified,Color.Unspecified)
            )
        }

        if(currentScreen.value == WindowState.Setup){
            CombinationOfButtonsAndModel()
                .AddingModelAndButtons(currentScreen, statusMessage,
                    finalNodes, innerArray,
                    arrayOfTetraminxColours)
        }else{
            SolutionScreen().ButtonsMenu(currentScreen, statusMessage, finalNodes)
        }
    }

}
enum class WindowState {
    Setup,
    Solving
}