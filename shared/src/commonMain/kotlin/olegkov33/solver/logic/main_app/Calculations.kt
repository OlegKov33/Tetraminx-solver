package olegkov33.solver.logic.main_app


import androidx.compose.runtime.MutableState
import olegkov33.solver.logic.utils.Rotator
import java.util.*

class Calculations(
    inputStartingNode: Node,
    inputFinishingNode: Node
) {
    // take input, take output
    // check if input variables match output variables in terms of data numbers 1:1
    // take first node and add it to the queue
    // start looping through queue exploring nodes that are:
    // a) not been explored before
    // b) seem to be the most closely to the end
    // before exploring nodes, check if the one you are about to explore matches the goal
    private val startingNode: Node
    private val finishingNode: Node
    private val unexploredNodes: Queue<Node> = PriorityQueue()
    private val exploredStates: MutableSet<String> = HashSet<String>()
    private var pathNodes: MutableMap<String, Node> = HashMap<String, Node>()
    private var notFoundGoal = true
    private lateinit var statusString : MutableState<String>

    init {
        startingNode = inputStartingNode
        finishingNode = inputFinishingNode
        unexploredNodes.add(startingNode)
    }

    //TODO change this method to return a list of states till the end
    fun start(): MutableList<Node>? {
        // checks if the input is equal to the goal

        if (startingNode.printNode().equals(finishingNode.printNode())) {
            println("The input is equal to the goal!")
            statusString.value = "The input is equal to the goal"
            return null
        }

        // checks if the input is valid by counting numbers
        if (!this.isSolvable) {
            println("not solvable")
            statusString.value = "Not solvable"
            return null
        }

        while (notFoundGoal) {
            if (unexploredNodes.isEmpty()) {
                statusString.value = "No unexplored nodes were found"
                return null
            }

            // node we are about to explore
            val node = unexploredNodes.poll()

            // adds a node to a list if visited nodes, used to determine if the node was explored already
            exploredStates.add(node.printNode())

            // adds a node to a hash list, but unlike the list above, will be used for path construction later
            pathNodes[node.getName()] = node

            if(node.printNode().equals(finishingNode.printNode())) {
                println(
                    ("Goal found!\nCost of node : " + unexploredNodes.peek().getCost()
                            + "\nTotal nodes explored: " + exploredStates.size + "\nGenerated nodes: " + unexploredNodes.size + "\n")
                )

                statusString.value = "Goal found"
//                return constructPath(unexploredNodes.peek())
                return constructPath(node)
            }


            // removes and explores the most promising node according to compareTo method in main_app.Node.java class
            unexploredNodes.addAll(exploringNode(node))


            //if you didn't find the goal within 23,000 nodes, there is a problem.
            if (exploredStates.size > 23000) {
                notFoundGoal = false
                println("The goal was not found, please check your inputs again.")
                statusString.value = "The goal was not found in a given time, please make a few turns and try again"
                return null
            }
        }

        statusString.value = "This message should be unreachable, how did you get here?"
        return null
    }

    fun setStatusMessage(inputMessage: MutableState<String>) {
        statusString = inputMessage
    }

    // generates all possible turns from a given node
    private fun exploringNode(givenNode: Node): Queue<Node> {
        val returnList: Queue<Node> = PriorityQueue()
        val stateRotator = Rotator()
        val listOfNewStates: MutableList<Array<IntArray>> = stateRotator.rotateAll(givenNode)

        for (state in listOfNewStates) {
            if (!exploredStates.contains(stateToName(state))) {
                returnList.add(
                    Node(
                        state, givenNode.getCost() + 1, givenNode.getGoalState(),
                        stateToName(state), givenNode.getName()
                    )
                )
            }
        }
        return returnList
    }

    // used by exploring main_app.Node to set new node names to their states, similar to main_app.Node.java printNode method
    private fun stateToName(inputState: Array<IntArray>): String {
        val output = StringBuilder()
        for (i in 0..3) {
            for (j in 0..5) {
                output.append(inputState[i][j])
            }
        }
        return output.toString()
    }


    private val isSolvable: Boolean
        // checks if there is an equal number of bases and edges for each color (it must be 3 and 3 for each)
        get() {
            var numberOfBases = 0
            var numberOfEdges = 0

            for (colourNumber in 0..3) {
                for (side in 0..3) {
                    for (edgeOrBase in 0..2) {
                        if (startingNode.getNodeState()[side][edgeOrBase * 2] == colourNumber) {
                            numberOfEdges++
                        }
                        if (startingNode.getNodeState()[side][edgeOrBase * 2 + 1] == colourNumber) {
                            numberOfBases++
                        }
                    }
                }

                for (side in 0..3) {
                    for (edgeOrBase in 0..2) {
                        if (finishingNode.getNodeState()[side][edgeOrBase * 2] == colourNumber) {
                            numberOfEdges--
                        }
                        if (finishingNode.getNodeState()[side][edgeOrBase * 2 + 1] == colourNumber) {
                            numberOfBases--
                        }
                    }
                }
                if (numberOfBases != 0 || numberOfEdges != 0) {
                    return false
                }
            }
            return true
        }


    //goes from back to fount:  goal >> node before goal ... initial node
    private fun constructPath(finalNode: Node): MutableList<Node> {
        val nodesPathArray: MutableList<Node> = ArrayList<Node>()
        nodesPathArray.add(finalNode)
        var tempParentNode: String = finalNode.getParent()


        while (tempParentNode != "none") {
            nodesPathArray.add(pathNodes.get(tempParentNode)!!)
            tempParentNode = nodesPathArray.get(nodesPathArray.size-1).getParent()
        }
        nodesPathArray.add(pathNodes.get(tempParentNode)!!)
        turnOrder(nodesPathArray);
        return nodesPathArray
    }

    // uses the path nodes to determine the turn order going from initial node to goal node
    private fun turnOrder(nodesPathArray: MutableList<Node>) {
        println("inside Turn Order")
        for (i in nodesPathArray.size - 1 downTo 1) {
            if ((i + 1) % 4 == 0) {
                //used for readability only.
                println()
            }
            movesInstructions(nodesPathArray.get(i - 1).getNodeState(), nodesPathArray.get(i).getNodeState())
        }
    }

    private fun movesInstructions(parentNode: Array<IntArray>, currentNode: Array<IntArray>) {
        //currentNode is current state
        //parentNode is node before currentNode (closer to initial state)
        if (currentNode[0][1] == parentNode[1][1]) {
            println("Next move is turning top side right >>>")
        }
        if (currentNode[0][1] == parentNode[2][1]) {
            println("Next move is turning top side left <<<")
        }


        if (currentNode[0][3] == parentNode[1][5]) {
            println("Next move is turning right side away from you >>>")
        }
        if (currentNode[0][3] == parentNode[3][1]) {
            println("Next move is turning right side towards you <<<")
        }


        if (currentNode[0][5] == parentNode[2][3]) {
            println("Next move is turning left side away from you <<<")
        }
        if (currentNode[0][5] == parentNode[3][5]) {
            println("Next move is turning left side towards you >>>")
        }


        if (currentNode[1][3] == parentNode[2][5]) {
            println("Next move is turning back side left <<<")
        }
        if (currentNode[1][3] == parentNode[3][3]) {
            println("Next move is turning back side right >>>")
        }
    }
}
