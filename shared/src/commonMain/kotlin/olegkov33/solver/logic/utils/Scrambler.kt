package olegkov33.solver.logic.utils //This class generates a new node using an input as its starting point.


import olegkov33.solver.logic.main_app.Node
import kotlin.math.floor

class Scrambler {
    private val innerNode: Node

    constructor() {
        innerNode = Node()
    }

    constructor(inputNode: Node) {
        innerNode = inputNode
    }

    /**
     * Method used to scramble the given node. Has a chance to scramble the solution
     * @param turnsNumber how many turns to scramble for
     */
    fun scramble(turnsNumber: Int): Array<IntArray> {
        val turnRotator = Rotator()
        val stateList: MutableList<Array<IntArray>> = ArrayList(turnsNumber + 1)
        var result: Array<IntArray> = innerNode.getNodeState()
        stateList.add(result)

        for (i in 0..<turnsNumber) {
            val randomNumber = floor(Math.random() * 8).toInt()

            when (randomNumber) {
                0 -> {
                    result = turnRotator.rotateFrontTopToRight(result)
                    stateList.add(result)
                }

                1 -> {
                    result = turnRotator.rotateFrontTopToLeft(result)
                    stateList.add(result)
                }

                2 -> {
                    result = turnRotator.rotateFrontRightSideTowards(result)
                    stateList.add(result)
                }

                3 -> {
                    result = turnRotator.rotateFrontRightSideAway(result)
                    stateList.add(result)
                }

                4 -> {
                    result = turnRotator.rotateFrontLeftSideTowards(result)
                    stateList.add(result)
                }

                5 -> {
                    result = turnRotator.rotateFrontLeftSideAway(result)
                    stateList.add(result)
                }

                6 -> {
                    result = turnRotator.rotateBackSideToRight(result)
                    stateList.add(result)
                }

                7 -> {
                    result = turnRotator.rotateBackSideToLeft(result)
                    stateList.add(result)
                }
            }
        }

        // used for display ONLY
        for (state in stateList) {
            showNode(state)
        }
        return result
    }


    // Shows the node in a ready to use state: Use int[][] nodeName = CTRL + V the code in console
    private fun showNode(inputState: Array<IntArray>) {
        var output = "{"
        for (i in inputState.indices) {
            output += "{"
            for (j in inputState[0].indices) {
                output += inputState[i][j].toString() + ","
            }
            output = output.substring(0, output.length - 1)
            output += "},"
        }
        output = output.substring(0, output.length - 1)
        output += "};"
        println(output)
    }
} //Author OlegKov33
