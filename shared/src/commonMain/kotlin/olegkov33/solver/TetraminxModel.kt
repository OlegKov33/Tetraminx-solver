package olegkov33.solver


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathHitTester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp


class TetraminxModel {
    /**
     * Method creates a flat 1D tetra-minx, all sides 1 - 4 are laid out sequentially
     */
    @Composable
    fun createTetraminx(
        buttonColor: MutableState<Color>,
        innerArray: Array<Array<Color>>,
        arrayOfTetraminxColours: Array<SnapshotStateList<Color>>
    ) {

        Row(modifier = Modifier.width(400.dp).height(400.dp)) {
            for( i in 0 .. 3){
                Box(modifier = Modifier.weight(1f)){

                    createFace(i, buttonColor,
                        innerArray[i], arrayOfTetraminxColours[i]
                    )
                }
            }
        }
    }

    /**
     * Creates a 1D hexagonal face that occupies an entire space it was created in
     * @param faceNum parameter that is used to indicate which face on tetra-minx is being created
     * @return returns a canvas
     */
    @Composable
    fun createFace(faceNum: Int, inputColor: MutableState<Color>,
                   ints: Array<Color>, get: SnapshotStateList<Color>) {

        val pathList = remember { mutableListOf<Path>() }

        return Canvas(modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures{pos ->
                    pathList.forEachIndexed { index, path->

                        // Path tester asks - is the click position (pos) inside any path (path)?
                        if(PathHitTester(path).contains(pos)){
                            get[index] = inputColor.value
                            ints[index] = inputColor.value
                        }
                    }
                }
            }
        ) {

            pathList.clear()

            pathList.add( createFirstEdge(size))
            pathList.add(createSecondEdge(size))
            pathList.add(createThirdEdge(size))
            pathList.add(createFourthEdge(size))
            pathList.add(createFifthEdge(size))
            pathList.add(createSixthEdge(size))

            for( i in pathList.indices){
                drawPath(pathList[i], get[i])
            }
        }
    }


    private fun createFirstEdge(size: Size) : Path{

        return  Path().apply {
                moveTo(size.width / 2f, size.height / 2f)
                lineTo(size.width / 4f, 0f)
                lineTo(0f, size.height / 2f)
                close()
            }

    }

    private fun createSecondEdge(size : Size) : Path {

        return Path().apply {
            moveTo(size.width / 2f, size.height / 2f)
            lineTo(size.width / 4f, 0f)
            lineTo(size.width / (4f / 3f), 0f)
            close()
        }

    }

    private fun createThirdEdge(size : Size) : Path{

        return Path().apply {
            moveTo(size.width / 2f, size.height / 2f)
            lineTo(size.width / (4f / 3f), 0f)
            lineTo(size.width, size.height / 2f)
            close()
        }
    }

    private fun createFourthEdge(size: Size) : Path {
        return Path().apply {
            moveTo(size.width / 2f, size.height / 2f)
            lineTo(size.width / (4f / 3f), size.height)
            lineTo(size.width, size.height / 2f)
            close()
        }
    }

    private fun createFifthEdge(size: Size) : Path {
        return Path().apply {
            moveTo(size.width / 2f, size.height / 2f)
            lineTo(size.width / 4f, size.height)
            lineTo(size.width / (4f / 3f), size.height)
            close()
        }
    }

    private fun createSixthEdge(size: Size) : Path {

        return Path().apply {
            moveTo(size.width / 2f, size.height / 2f)
            lineTo(size.width / 4f, size.height)
            lineTo(0f, size.height / 2f)
            close()
        }
    }
}
