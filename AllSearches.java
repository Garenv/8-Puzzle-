import java.util.*;

public class AllSearches {
    private Node root;
    private String goalState;
    
    // Mutator functions (getters and setters).
    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public String getGoalSate() {
        return goalState;
    }

    public void setGoalSate(String goalState) {
        this.goalState = goalState;
    }

    public AllSearches(Node root, String goalState) {
        this.root = root;
        this.goalState = goalState;
    }
    
    // Each node is a state which may a be a potential candidate for solution 
    // It expands nodes from the root of the tree and then generates one level 
    // of the tree at a time until a solution is found.
    public void breadthFirstSearch() {
        Set<String> visitedNodes = new HashSet<String>(); // Holds nodes that are already visited.
        int time = 0; 
        Node node = new Node(root.getState());
        LinkedList<Node> queue = new LinkedList<Node>();
        Node currNode = node;
        
        while (!currNode.getState().equals(goalState)) { // While the current nodes state doesn't equal the goal state.
        		visitedNodes.add(currNode.getState()); // Add current nodes state to visitedNodes hash set.
            List<String> nodeSuccessors = NodeOps.getSuccessors(currNode.getState());
            
            for (String n : nodeSuccessors) {
                if (visitedNodes.contains(n)) { // If a nodes visited contain valid input.
                    continue; // Continue searching.
                }
                
                visitedNodes.add(n); // Add visited nodes to the queue.
                Node child = new Node(n);
                currNode.addChild(child); 
                child.setParent(currNode);
                queue.add(child);
            }
            currNode = queue.poll();
            time += 1;
        }
        NodeOps.solutionPrint(currNode, visitedNodes, root, time);
    }
    
    // This depthFirstSearch method is going as deep as possible down one path before trying a new one.
    public void depthFirstSearch() {
        Set<String> visitedNodes = new HashSet<String>(); // Holds nodes that are already visited.
        int time = 0; // Time it'll take to run.
        Node node = new Node(root.getState());
        Queue<Node> expansionQueue = new Queue<>(); // Holds nodes that need expansion.
        Queue<Node> successorsQueue = new Queue<>(); // Holds successors of nodes.
        Node currNode = node;
        
        while (!currNode.getState().equals(goalState)) { // While the current nodes state doesn't equal the goal state.
        		visitedNodes.add(currNode.getState()); // Add current state of node into the hash set visitedNodes
            List<String> nodeSuccessors = NodeOps.getSuccessors(currNode.getState()); 
            
            for (String n : nodeSuccessors) {
                if (visitedNodes.contains(n)) { // If the visited nodes contain node successors
                    continue; // Continue the program.
                }
                
                visitedNodes.add(n); // Add visited nodes to the hash set.
                Node child = new Node(n); 
                currNode.addChild(child); // Add child to the current node.
                child.setParent(currNode); // Set parent as current node.
                successorsQueue.enqueue(child); // Place child in successors queue.

            }
            expansionQueue.addQueue(successorsQueue); // Adding a queue that has successor of node in the beginning of queue.
            
            successorsQueue.clear(); // Clearing the successor queue to put in next successor.
            currNode = expansionQueue.dequeue();
            time += 1;
            nodeSuccessors.clear();
        }
        NodeOps.solutionPrint(currNode, visitedNodes, root, time);
    }
        
    public enum Heuristic {Heuristic} // Enumeration class used to make sure the numbers used on board never change. 
      
    // Uses Heuristic in order to be more accurate search. 
    public void aStar(Heuristic heuristic) {
        Set<String> visitedNodes = new HashSet<String>(); // Holds nodes that are already visited.
        
        int time = 0;
        Node node = new Node(root.getState());
        node.setTotalCost(0);
        
        CompareNodes compareNodes = new CompareNodes(); // This will compare cost values and sort the cost values.
        
        PriorityQueue<Node> nodeQueue = new PriorityQueue<Node>(10, compareNodes); // Holds cost values that are sorted.
        Node currNode = node;
        while (!currNode.getState().equals(goalState)) { // While the current nodes state isn't equal to the goal state.
        		visitedNodes.add(currNode.getState()); // Add current nodes state to visitedNodes hash set.
        		List<String> nodeSuccessors = NodeOps.getSuccessors(currNode.getState());
            
        		for (String n : nodeSuccessors) {
                if (visitedNodes.contains(n)) { 
                    continue;
                }
                visitedNodes.add(n);
                Node childNode = new Node(n);
                currNode.addChild(childNode); // Add child node to current node.
                childNode.setParent(currNode); // Set the parent node as current node.
                
                if (heuristic == Heuristic.Heuristic) { // If the heuristic function is working.
                		// set the cost of the child node appropriately to better fit other incoming nodes.
	                	childNode.setTotalCost(currNode.getTotalCost() + Character.getNumericValue(childNode.getState().charAt(childNode.getParent().getState().indexOf('0'))), heuristic(childNode.getState(), goalState));
	                nodeQueue.add(childNode); // Add the child node to the queue.
                }
            }
            currNode = nodeQueue.poll(); 
            time += 1;
        }
        NodeOps.solutionPrint(currNode, visitedNodes, root, time);
    }

    // Using Depth First Search Algorithm, the limit of the depth begins at 1 and increases more and more depending on user input.
    public void iterativeDeepening(int depthLimit) {
        Node currNode = new Node(root.getState()); // Current node that contains root state.
        Queue<Node> expansionQueue = new Queue<>(); // Holds nodes that need expansion.
        Queue<Node> successorsQueue = new Queue<>(); // Holds successors of nodes.
        Node node = new Node(root.getState()); // Node that holds a nodes state.
        List<String> nodeSuccessors = null; // Successor nodes.
        boolean solutionFound = false; 
        
        Set<String> visitedNodes = new HashSet<String>(); // Holds nodes that are already visited.
        Set<String> totalVisitedStates = new HashSet<>(); // Holds total amount of nodes visited states.
        int time = 0;
        
        for (int i = 1; i < depthLimit; i++) {
        		visitedNodes.clear(); // After iterating through, clear all nodes that have been already visited.
            expansionQueue.enqueue(node);
            currNode = node;            
            visitedNodes.add(currNode.getState());
            
            while (!expansionQueue.isEmpty()) { // While the expansion queue isn't empty.
            	    currNode = expansionQueue.dequeue(); // Dequeue nodes from the expansion queue.
                time += 1; // Increment time.
                if (currNode.getState().equals(goalState)) { // If the current state equals the goal state.
                    solutionFound = true; // A solution is found.
                    break; // Break from the while loop.
                }
                if (currNode.getDepth() < i) { // If the current nodes dephth is less than maximum depth.
                    nodeSuccessors = NodeOps.getSuccessors(currNode.getState()); // Grab the next states node.
                    for (String n : nodeSuccessors) {
                    	
                        if (visitedNodes.contains(n)) {
                            continue;
                        }
                        
                        visitedNodes.add(n);
                        Node child = new Node(n);
                        currNode.addChild(child);
                        child.setParent(currNode);
                        child.setVisited(true);
                        child.setDepth(currNode.getDepth() + 1);
                        successorsQueue.enqueue(child);
                    }
                    // Adding a queue that has successor of node in the beginning of queue.
                    expansionQueue.addQueue(successorsQueue);
                  
                    successorsQueue.clear(); // Clearing the successor queue to put in next successor.
                }
            }
            if (solutionFound) // If solution is found.
                break; // Break out of the if statement.
            totalVisitedStates.addAll(visitedNodes); // Add all the visited nodes inside the visited states queue.
        }
        if (!solutionFound) // If solution isn't found.
            System.out.println("No solution Found!"); // Print message saying there's no solution.
        else {
            NodeOps.solutionPrint(currNode, totalVisitedStates, root, time); // Else, print out the solution.
        }
    }

    	// Obtaining cost of movements on the board by calculating the Manhattan distance and returning the difference. 
    private int heuristic(String currState, String goalState) {
    		int difference = 0; // Store difference of Manhattan distance.
    		int manhattanDistance = 0; // Store Manhattan distance.
          
        for (int i = 0; i < currState.length(); i += 1)
            for (int j = 0; j < goalState.length(); j += 1)
                if (currState.charAt(i) == goalState.charAt(j)) // If the current states is equal to the goal state 
                    manhattanDistance = (Math.abs(i % 3 - j % 3)) + Math.abs(i / 3 + j / 3); // Calculate Manhattan distance.
        difference = difference + 2 * manhattanDistance - 1;
        return difference;
    }
}