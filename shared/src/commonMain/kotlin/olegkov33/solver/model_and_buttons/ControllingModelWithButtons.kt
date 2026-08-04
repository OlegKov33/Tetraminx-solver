package olegkov33.solver.model_and_buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class ControllingModelWithButtons {


    /**
     * Method is used to generate buttons and 1D tetra-minx model. Buttons can be pressed
     * and them upon pressing a cell, it will change color to that which you picked
     * @param innerArray parameter used to color in the Tetra-minx model via buttons
     */
    @Composable
    fun generateButtonsAndTetraminx(innerArray: Array<Array<Color>>) {
        val currentButtonColorSelected : MutableState<Color> = remember { mutableStateOf(Color.LightGray) }
        val arrayOfTetraminxColours : Array<SnapshotStateList<Color>> =
            Array(4){ mutableStateListOf(*Array(6) { Color.Black }) }

        val buttons = ColorButtons(currentButtonColorSelected)
        val model = TetraminxModel()



        Column{
            model.createTetraminx(
                currentButtonColorSelected,
                innerArray,
                arrayOfTetraminxColours
            )

        }

        buttons.ColourButtons(arrayOfTetraminxColours)


    }
}