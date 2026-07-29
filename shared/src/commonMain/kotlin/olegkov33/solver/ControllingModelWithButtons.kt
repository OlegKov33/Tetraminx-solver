package olegkov33.solver

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color

class ControllingModelWithButtons {


    /**
     * Method is used to generate buttons and 1D tetra-minx model. Buttons can be pressed
     * and them upon pressing a cell, it will change color to that which you picked
     */
    @Composable
    fun generateButtonsAndTetraminx(innerArray: Array<Array<Color>>) {
        val currentButtonColorSelected : MutableState<Color> = remember { mutableStateOf( Color.LightGray) }
        val arrayOfTetraminxColours : Array<SnapshotStateList<Color>> =
            Array(4){ mutableStateListOf(* Array(6){Color.Black}) }

        val buttons = ColorButtons(currentButtonColorSelected)
        val model = TetraminxModel()

        Column {
            buttons.ColourButtons(arrayOfTetraminxColours)
            model.createTetraminx(
                currentButtonColorSelected,
                innerArray,
                arrayOfTetraminxColours)
        }


    }
}