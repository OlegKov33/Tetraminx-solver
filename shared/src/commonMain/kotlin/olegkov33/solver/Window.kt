package olegkov33.solver

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

        if(currentScreen.value == WindowState.Setup){
            ImplementationOfButtonsAndModel().addingModelAndButtons(currentScreen, statusMessage, finalNodes)
        }else{
            SolutionScreen().btnWithChange(currentScreen, statusMessage, finalNodes)
        }
    }

}
enum class WindowState {
    Setup,
    Solving
}