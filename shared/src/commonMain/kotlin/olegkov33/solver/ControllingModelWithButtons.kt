package olegkov33.solver

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color

class ControllingModelWithButtons {

    val innerArray = arrayOf(
        arrayOf(0,0,0,0,0,0),
        arrayOf(1,1,1,1,1,1),
        arrayOf(2,2,2,2,2,2),
        arrayOf(3,3,3,3,3,3)
    )

    /**
     * Method is used to generate buttons and 1D tetra-minx model. Buttons can be pressed
     * and them upon pressing a cell, it will change color to that which you picked
     */
    @Composable
    fun generateButtonsAndTetraminx(){
        val currentButtonColorSelected : MutableState<Color> = remember { mutableStateOf( Color.LightGray) }
        val buttons = ColorButtons(currentButtonColorSelected)
        val model = TetraminxModel()

        Column {
            buttons.ColourButtons()
            model.createTetraminx(currentButtonColorSelected)
        }


    }
}