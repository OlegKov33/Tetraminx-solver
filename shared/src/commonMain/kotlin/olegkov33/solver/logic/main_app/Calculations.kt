package olegkov33.solver.logic.main_app


import androidx.compose.runtime.MutableState
import olegkov33.solver.logic.utils.Rotator
import kotlin.collections.*

class Calculations(
    inputStartingNode: Node
) {
    private val startingNode: Node
    private var pathNodes: MutableMap<String, Node> = HashMap<String, Node>()
    private lateinit var statusString : MutableState<String>

    init {
        startingNode = inputStartingNode
    }

    /**
     * Method used to determine how to get from initial state to goal state.
     *
     * @return returns null or array of nodes if it was successful in finding goal
     */
    fun start(): MutableList<Node>?{
        if(!configuringGoalState()){
            statusString.value = "Something went wrong"
            return null
        }

        if (startingNode.printNode() == startingNode.printGoalState()) {
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

        val open = ArrayDeque<Node>()
        val visited: HashSet<String> = HashSet()

        open.add(startingNode)
        visited.add(startingNode.printNode())
        pathNodes.clear()
        pathNodes[startingNode.getName()] = startingNode

        while(open.isNotEmpty()){
            val node = open.removeFirst()

            if(node.printNode() == startingNode.printGoalState()) {
                println(
                    ("Goal found!\nCost of node : " + node.getCost()
                            + "\nPath nodes explored: " + pathNodes.size + "\nOpen Queue: " + open.size + "\n")
                )
                return constructPath(node)
            }

            val rotator = Rotator()
            val children = rotator.rotateAll(node)

            for( newState in children){
                val name = stateToName(newState)
                if(name in visited){
                    continue
                }

                visited.add(name)
                val child = Node(newState, node.getCost()+1,
                    node.getGoalState(), name,
                    node.getName())

                open.add(child)
                pathNodes[name] = child
            }
        }

        println("NOTHING FOUND?!")
        return null
    }

    fun scramblerStart() : MutableList<Node>?{

        if (startingNode.printNode() == startingNode.printGoalState()) {
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

        val open = ArrayDeque<Node>()
        val visited: HashSet<String> = HashSet()

        open.add(startingNode)
        visited.add(startingNode.printNode())
        pathNodes.clear()
        pathNodes[startingNode.getName()] = startingNode

        while(open.isNotEmpty()){
            val node = open.removeFirst()

            if(node.printNode() == startingNode.printGoalState()) {
                println(
                    ("Goal found!\nCost of node : " + node.getCost()
                            + "\nPath nodes explored: " + pathNodes.size + "\nOpen Queue: " + open.size + "\n")
                )
                return constructPath(node)
            }

            val rotator = Rotator()
            val children = rotator.rotateAll(node)

            for( newState in children){
                val name = stateToName(newState)
                if(name in visited){
                    continue
                }

                visited.add(name)
                val child = Node(newState, node.getCost()+1,
                    node.getGoalState(), name,
                    node.getName())

                open.add(child)
                pathNodes[name] = child
            }
        }

        println("NOTHING FOUND?!")
        return null
    }


    /**
     * Method used to set the status string.
     * @param inputMessage use the reference to set the status string
     */
    fun setStatusMessage(inputMessage: MutableState<String>) {
        statusString = inputMessage
    }

    private fun configuringGoalState() : Boolean{

        var startingNodeState = startingNode.getNodeState().map {it.clone()}.toTypedArray()
        val totalBaseCounter = MutableList(4){0}
        val listOfExploredStates = HashSet<Array<IntArray>>()

        val rotate = Rotator()
        var currentBaseScore = countingBases(startingNodeState)
        var foundAllBases = false

        for(side in startingNodeState){
            for(i in 0 .. 2){
                totalBaseCounter[side[i*2+1]] ++
            }
        }

        for( item in totalBaseCounter.indices){
            if(totalBaseCounter[item] != 3){
                return false
            }
        }


        if(currentBaseScore == 12){
            foundAllBases = true
        }
        val newStates = rotate.rotateAll(startingNodeState)
        val promisingStates = mutableListOf<Array<IntArray>>()

        println("how many current bases are there? ${currentBaseScore}")
        while(!foundAllBases){
            for(item in newStates){
                val itemBaseCount = countingBases(item)
                if(itemBaseCount == 12){
                    startingNodeState = item
                    foundAllBases = true
                    break

                }
                if(currentBaseScore <= itemBaseCount && !listOfExploredStates.contains(item)){
                    promisingStates.addAll(rotate.rotateAll(item))
                    listOfExploredStates.add(item)
                }
            }
            if(!promisingStates.isEmpty()){
                currentBaseScore = countingBases( promisingStates.first() )
            }else{
                break
            }

            if(currentBaseScore == 12){
                startingNodeState = promisingStates.first()
                foundAllBases = true
                break
            }

            newStates.clear()
            newStates.addAll(promisingStates)
            promisingStates.clear()

        }

        if(!foundAllBases){
            return false
        }

        for(side in startingNodeState.indices){
            //println("success")
            //println("show me side ${startingNodeState[side].contentToString()}")
            for(i in 0 ..2){
                startingNodeState[side][i*2] = startingNodeState[side][i*2+1]
            }
        }

        println("the output ")
        for(side in startingNodeState.indices){
            println("show me side ${startingNodeState[side].contentToString()}")

        }

        //finishingNode.setState(startingNodeState)
        startingNode.setGoalState(startingNodeState)
        return true
    }

    private fun countingBases(inputState : Array<IntArray>) : Int{

        var totalBases = 0

        for(side in inputState){
            var pairs = 0

            for(i in 0 .. 2){
                if(side[i*2+1] == side[(i*2+3)%6]){
                    pairs++
                }
            }

            when (pairs) {
                1 -> {
                    totalBases += 2
                }
                3 -> {
                    totalBases += 3
                }
                else -> {
                    totalBases++
                }
            }
        }

        return totalBases
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
                        if (startingNode.getGoalState()[side][edgeOrBase * 2] == colourNumber) {
                            numberOfEdges--
                        }
                        if (startingNode.getGoalState()[side][edgeOrBase * 2 + 1] == colourNumber) {
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
        var currentNode: Node? = finalNode

        while (currentNode != null) {
            nodesPathArray.add(currentNode)
            val parentName = currentNode.getParent()
            currentNode = pathNodes[parentName]

            if(parentName == "none"){
                break
            }
        }

        nodesPathArray.reverse()
        return nodesPathArray
    }

}
