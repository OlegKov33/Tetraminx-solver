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
    
    // checks the equality of nodes
    override fun equals(other: Any?): Boolean {
        if (other != null) {
            if (other.javaClass != Node::class.java) {
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

    fun setState(inputState : Array<IntArray>){
        nodeState = inputState
    }

    // used in similar way as toString. However, it's used for naming nodes and in path finding
    fun printNode(): String {
        val output = StringBuilder()
        for (i in 0..3) {
            for (j in 0..5) {
                output.append(this.nodeState[i][j])
            }
        }
        return output.toString()
    }


    fun getNodeSide(inputSide: Int): IntArray {
        return this.nodeState[inputSide]
    }

    fun getNodeState() : Array<IntArray> {
        return nodeState
    }

    fun getCost() : Int{
        return cost
    }

    fun getGoalState() : Array<IntArray>{
        return goalState
    }

    fun getName() : String{
        return name
    }

    fun getParent() : String{
        return parent
    }

    // MY COMPARE-TO version. Does not guarantee an optimal solution.
    override fun compareTo(inputNode: Node): Int {
        var correctlyAlignedCells = 0
        var inputCorrectlyAlignedCells = 0

        // checks current nodes number of displaced sides.
        for (i in 0..2) {
            if (this.nodeState[i][0] == goalState[i][0]) {
                correctlyAlignedCells-- //test
                if (this.nodeState[(i - 2) * -1][2] == goalState[(i - 2) * -1][2]) {
                    correctlyAlignedCells += 2
                }
            }
            if (this.nodeState[3][i * 2] == goalState[3][i * 2]) {
                correctlyAlignedCells-- // test
                if (this.nodeState[i][5] == goalState[i][5]) {
                    correctlyAlignedCells += 2
                }
            }
        }


        // checks the inputNodes number of displaced sides.
        for (i in 0..2) {
            if (inputNode.nodeState[i][0] == inputNode.goalState[i][0]) {
                inputCorrectlyAlignedCells-- // test
                if (inputNode.nodeState[(i - 2) * -1][2] == inputNode.goalState[(i - 2) * -1][2]) {
                    inputCorrectlyAlignedCells += 2
                }
            }
            if (inputNode.nodeState[3][i * 2] == inputNode.goalState[3][i * 2]) {
                inputCorrectlyAlignedCells-- //test
                if (this.nodeState[i][5] == goalState[i][5]) {
                    inputCorrectlyAlignedCells += 2
                }
            }
        }

        // the more the cost, the less appealing the node should appear
        correctlyAlignedCells -= (cost) //test
        inputCorrectlyAlignedCells -= (inputNode.cost) //test
        return Integer.compare(inputCorrectlyAlignedCells, correctlyAlignedCells)
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
