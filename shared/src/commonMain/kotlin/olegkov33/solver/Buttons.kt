package olegkov33.solver

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class ColorButtons(inputColor: MutableState<Color>){

    private var currentButtonColorSelected : MutableState<Color> = inputColor

    /**
     * Function used to create a row with predetermined buttons.
     * Buttons are used to control Tetra-minx color per cell
     */
    @Composable
    fun ColourButtons() {
        return Row(){
            createDoubleColourButton(Color.Red, Color.LightGray, 80.dp)
            createDoubleColourButton(Color.Green, Color.Magenta, 80.dp)
            createDoubleColourButton(Color.Blue, Color.Cyan, 80.dp)
            createDoubleColourButton(Color.Yellow, Color.DarkGray, 80.dp)
        }
    }


    @Composable
    private fun createDoubleColourButton(topColor: Color,
                                         bottomColor: Color,
                                         buttonSize : Dp){

        return Column(modifier = Modifier
            .size(buttonSize)
            .padding(10.dp)
            .clip(RoundedCornerShape(70))) {
            Box(
                modifier = Modifier.background(
                    color = topColor,
                )
                    .weight(1f)
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .clickable{
                        //println(topColor)
                        currentButtonColorSelected.value = topColor
                    }
            )
            Box(
                modifier = Modifier
                    .background(
                        color = bottomColor,
                    )
                    .weight(1f)
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .clickable{
                        //println(bottomColor)
                        currentButtonColorSelected.value = bottomColor
                    }
            )
        }
    }
}

