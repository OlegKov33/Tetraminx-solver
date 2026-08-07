package olegkov33.solver.logic.main_app

class Node : Comparable<Node> {
    private var nodeState: Array<IntArray>
    private var goalState: Array<IntArray>
    private var cost: Int = 0
    private var name: String = "none"
    private var parent: String = "none"

    constructor() {
        this.nodeState = arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 0),
            intArrayOf(1, 1, 1, 1, 1, 1),
            intArrayOf(2, 2, 2, 2, 2, 2),
            intArrayOf(3, 3, 3, 3, 3, 3)
        )
        goalState = arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 0),
            intArrayOf(1, 1, 1, 1, 1, 1),
            intArrayOf(2, 2, 2, 2, 2, 2),
            intArrayOf(3, 3, 3, 3, 3, 3)
        )
        cost = 0
    }


    constructor(
        inputState: Array<IntArray>, inputCost: Int,
        inputGoalState: Array<IntArray>, inputName: String,
        inputParent: String
    ) {
        this.nodeState = inputState
        this.cost = inputCost
        this.goalState = inputGoalState
        this.name = inputName
        this.parent = inputParent
    }

    fun setGoalState(startingNodeState: Array<IntArray>) {
        goalState = startingNodeState
    }

    fun setName(name: String) {
        this.name = name
    }

    /**
     * Method checks the equality of 2 nodes
     * @param other given value, must be node
     */
    override fun equals(other: Any?): Boolean {

        if (other != null) {
            if (other !is Node) {
                return false
            }
        }

        for (i in 0..3) {
            for (j in 0..5) {
                if ((other as Node).getNodeSide(i)[j] != this.nodeState[i][j]) {
                    return false
                }
            }
        }
        return true
    }

    fun setState(inputState: Array<IntArray>) {
        nodeState = inputState
    }

    /**
     * Method used to turn node state into name
     * @return returns a string of node state. e.g. default state [000000, 111..., 22..., 3...]
     * will be turned into 000000111111222222333333
     */
    fun printNode(): String {
        val output = StringBuilder()
        for (i in 0..3) {
            for (j in 0..5) {
                output.append(nodeState[i][j])
            }
        }
        return output.toString()
    }

    fun printGoalState(): String {
        val output = StringBuilder()
        for (i in 0..3) {
            for (j in 0..5) {
                output.append(goalState[i][j])
            }
        }
        return output.toString()
    }


    fun getNodeSide(inputSide: Int): IntArray {
        return this.nodeState[inputSide]
    }

    fun getNodeState(): Array<IntArray> {
        return nodeState
    }

    fun getCost(): Int {
        return cost
    }

    fun getGoalState(): Array<IntArray> {
        return goalState
    }

    fun getName(): String {
        return name
    }

    fun getParent(): String {
        return parent
    }


    override fun compareTo(other: Node): Int {
        return this.cost.compareTo(other.cost)
    }

    override fun toString(): String {
        val builder = StringBuilder()
        for (i in 0..3) {
            for (j in 0..5) {
                builder.append(this.nodeState[i][j])
            }
            builder.append(" ")
        }
        return builder.toString()
    }
}
