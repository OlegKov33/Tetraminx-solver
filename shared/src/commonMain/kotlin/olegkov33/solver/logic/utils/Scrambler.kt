package olegkov33.solver.logic.utils


import olegkov33.solver.logic.main_app.Node

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
            val randomNumber = (0..8).random()

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

        return result
    }

} //Author OlegKov33
