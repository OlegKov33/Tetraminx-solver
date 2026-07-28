package olegkov33.solver.logic.utils

import olegkov33.solver.logic.main_app.Node

class Rotator {

    fun rotateAll(inputNode: Node): MutableList<Array<IntArray>> {

        val resultList: MutableList<Array<IntArray>> = ArrayList(8)
        resultList.add(rotateFrontTopToRight(inputNode.getNodeState()))
        resultList.add(rotateFrontTopToLeft(inputNode.getNodeState()))
        resultList.add(rotateFrontRightSideTowards(inputNode.getNodeState()))
        resultList.add(rotateFrontRightSideAway(inputNode.getNodeState()))

        resultList.add(rotateFrontLeftSideTowards(inputNode.getNodeState()))
        resultList.add(rotateFrontLeftSideAway(inputNode.getNodeState()))
        resultList.add(rotateBackSideToRight(inputNode.getNodeState()))
        resultList.add(rotateBackSideToLeft(inputNode.getNodeState()))

        return resultList
    }

    fun rotateFrontTopToRight(inputState: Array<IntArray>): Array<IntArray> {
        val newState = copyState(inputState)

        for (j in 0..2) {
            newState[0][j] = inputState[2][j]
            newState[1][j] = inputState[0][j]
            newState[2][j] = inputState[1][j]
        }

        return newState
    }

    fun rotateFrontTopToLeft(inputState: Array<IntArray>): Array<IntArray> {
        val newState = copyState(inputState)
        for (j in 0..2) {
            /*Example:
             * Side (0)     Side(1)     Side(2)
             * 000000       111111      222222
             * will be:
             * 111000       222111      000222
             */
            newState[0][j] = inputState[1][j]
            newState[1][j] = inputState[2][j]
            newState[2][j] = inputState[0][j]
        }
        return newState
    }

    // >>>
    fun rotateFrontRightSideTowards(inputState: Array<IntArray>): Array<IntArray> {
        val newState = copyState(inputState)

        for (j in 0..2) {
            if (j + 4 != 6) {
                newState[0][j + 2] = inputState[1][j + 4] //array length is 0-5, will throw err.
                newState[1][j + 4] = inputState[3][j]
            } else {
                newState[0][j + 2] = inputState[1][0]
                newState[1][0] = inputState[3][j]
            }

            newState[3][j] = inputState[0][j + 2]
        }

        return newState
    }

    // <<<
    fun rotateFrontRightSideAway(inputState: Array<IntArray>): Array<IntArray> {
        val newState = copyState(inputState)

        for (j in 0..2) {
            newState[0][j + 2] = inputState[3][j]
            if (j + 4 != 6) {
                newState[1][j + 4] = inputState[0][j + 2] //same here
                newState[3][j] = inputState[1][j + 4]
            } else {
                newState[1][0] = inputState[0][j + 2]
                newState[3][j] = inputState[1][0]
            }
        }
        return newState
    }

    // right(back side >>>)
    fun rotateFrontLeftSideTowards(inputState: Array<IntArray>): Array<IntArray> {
        val newState = copyState(inputState)

        for (j in 0..2) {
            if (j + 4 != 6) {
                newState[0][j + 4] = inputState[2][j + 2]
                newState[2][j + 2] = inputState[3][j + 4]
                newState[3][j + 4] = inputState[0][j + 4]
            } else {
                newState[0][0] = inputState[2][j + 2]
                newState[2][j + 2] = inputState[3][0]
                newState[3][0] = inputState[0][0]
            }
        }

        return newState
    }

    fun rotateFrontLeftSideAway(inputState: Array<IntArray>): Array<IntArray> {
        val newState = copyState(inputState)

        for (j in 0..2) {
            if (j + 4 != 6) {
                newState[0][j + 4] = inputState[3][j + 4]
                newState[2][j + 2] = inputState[0][j + 4]
                newState[3][j + 4] = inputState[2][j + 2]
            } else {
                newState[0][0] = inputState[3][0]
                newState[2][j + 2] = inputState[0][0]
                newState[3][0] = inputState[2][j + 2]
            }
        }

        return newState
    }

    // left(back side >>>)
    fun rotateBackSideToRight(inputState: Array<IntArray>): Array<IntArray> {
        val newState = copyState(inputState)
        for (j in 0..2) {
            if (j + 4 != 6) {
                newState[1][j + 2] = inputState[2][j + 4]
                newState[2][j + 4] = inputState[3][j + 2]
            } else {
                newState[1][j + 2] = inputState[2][0]
                newState[2][0] = inputState[3][j + 2]
            }
            newState[3][j + 2] = inputState[1][j + 2]
        }
        return newState
    }

    fun rotateBackSideToLeft(inputState: Array<IntArray>): Array<IntArray> {
        val newState = copyState(inputState)

        for (j in 0..2) {
            if (j + 4 != 6) {
                newState[3][j + 2] = inputState[2][j + 4] //proper
                newState[2][j + 4] = inputState[1][j + 2] //new
            } else {
                newState[3][j + 2] = inputState[2][0] //proper
                newState[2][0] = inputState[1][j + 2] //new
            }
            newState[1][j + 2] = inputState[3][j + 2] //new
        }
        return newState
    }

    private fun copyState(inputState: Array<IntArray>): Array<IntArray> {
        val result = Array(4) { IntArray(6) }
        for (i in 0..3) {
            result[i] = inputState[i].clone()
        }
        return result
    }
}
