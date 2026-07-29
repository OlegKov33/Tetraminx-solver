package olegkov33.solver

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import olegkov33.solver.logic.main_app.Calculations
import olegkov33.solver.logic.main_app.Node


class LogicAndButton {
    // I can now do this:
    // add color to tetra minx
    // change colors
    // mock test
    // TIME FOR REAL TESTING;
    // ITS WORKING!!! LET'S GOOO!!!
    // now, let's add proper message output
    // how to solve it (steps)
    // animations... I'll probably do them with AI



    @Composable
    fun addingModelAndButtons(){
        val innerArray = remember{
            arrayOf(
                arrayOf(Color.Unspecified,Color.Unspecified,Color.Unspecified ,Color.Unspecified,Color.Unspecified,Color.Unspecified),
                arrayOf(Color.Unspecified,Color.Unspecified,Color.Unspecified ,Color.Unspecified,Color.Unspecified,Color.Unspecified),
                arrayOf(Color.Unspecified,Color.Unspecified,Color.Unspecified ,Color.Unspecified,Color.Unspecified,Color.Unspecified),
                arrayOf(Color.Unspecified,Color.Unspecified,Color.Unspecified ,Color.Unspecified,Color.Unspecified,Color.Unspecified)
            )
        }


        val model = ControllingModelWithButtons()
        //model.generateButtonsAndTetraminx(innerArray)

        Row{
            Button(
                onClick = {
                    for (item in innerArray){
                        println(item.contentToString())
//                        for(element in item){
//                            print("${element.colorSpace.name} ")
//                        }
//                        println("\n")
                    }
                },
                shape = CircleShape
            ){
                Text("show innerArray")
            }
            buttonToStartTraining(innerArray)
            model.generateButtonsAndTetraminx(innerArray)
        }

    }

    @Composable
    fun buttonToStartTraining(innerArray: Array<Array<Color>>) {
        Button(onClick = {
            training(innerArray)
        }){
            Text(text = "Start Training", color = Color.White)
        }


    }

    private fun training(innerArray: Array<Array<Color>>) {
        // transform array into ints :)
        val workingArray = Array(4){
            IntArray(6)
            IntArray(6)
            IntArray(6)
            IntArray(6)
        }

        for(side in innerArray.indices){
            for( cell in innerArray[side].indices){

                if(innerArray[side][cell] == Color.Red || innerArray[side][cell] == Color.LightGray){
                    workingArray[side][cell] = 0
                }
                if(innerArray[side][cell] == Color.Green || innerArray[side][cell] == Color.Magenta){
                    workingArray[side][cell] = 1
                }
                if (innerArray[side][cell] == Color.Blue || innerArray[side][cell] == Color.Cyan){
                    workingArray[side][cell] = 2
                }
                if(innerArray[side][cell] == Color.Yellow || innerArray[side][cell] == Color.DarkGray){
                    workingArray[side][cell] = 3
                }
            }
        }

        val startingNode = Node()
        startingNode.setState(workingArray)
        val goalNode = Node()
        val calculations = Calculations(startingNode, goalNode)

        calculations.start()

    }

}