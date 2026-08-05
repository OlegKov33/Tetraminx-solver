package olegkov33.solver.model_and_buttons

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color

class ControllingModelWithButtons {


    /**
     * Method is used to generate buttons and 1D tetra-minx model. Buttons can be pressed
     * and them upon pressing a cell, it will change color to that which you picked
     * @param innerArray parameter used to color in the Tetra-minx model via buttons
     */
    @Composable
    fun generateButtonsAndTetraminx(
        innerArray: Array<Array<Color>>,
        arrayOfTetraminxColours: Array<SnapshotStateList<Color>>
    ) {
        val currentButtonColorSelected : MutableState<Color> = remember { mutableStateOf(Color.LightGray) }
        val buttons = ColorButtons(currentButtonColorSelected)
        val model = TetraminxModel()

        Column{
            model.CreateAllSidedTetraminx(
                currentButtonColorSelected,
                innerArray,
                arrayOfTetraminxColours
            )

        }

        buttons.GenerateColourButtons(arrayOfTetraminxColours)
    }
}