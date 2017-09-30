import java.util.*;

// This class is responsible for any sort of calculated movement on the boards, printing sequential boards, visited states.   
public class NodeOps {
    public static List<String> getSuccessors(String state) {
        List<String> successors = new ArrayList<String>();
        
        switch (state.indexOf("0")) {	
            case 0: {
                successors.add(state.replace(state.charAt(0), '*').replace(state.charAt(1), state.charAt(0)).replace('*', state.charAt(1)));
                successors.add(state.replace(state.charAt(0), '*').replace(state.charAt(3), state.charAt(0)).replace('*', state.charAt(3)));
                break;
            }
            case 1: {
                successors.add(state.replace(state.charAt(1), '*').replace(state.charAt(0), state.charAt(1)).replace('*', state.charAt(0)));
                successors.add(state.replace(state.charAt(1), '*').replace(state.charAt(2), state.charAt(1)).replace('*', state.charAt(2)));
                successors.add(state.replace(state.charAt(1), '*').replace(state.charAt(4), state.charAt(1)).replace('*', state.charAt(4)));
                break;
            }
            case 2: {
                successors.add(state.replace(state.charAt(2), '*').replace(state.charAt(1), state.charAt(2)).replace('*', state.charAt(1)));
                successors.add(state.replace(state.charAt(2), '*').replace(state.charAt(5), state.charAt(2)).replace('*', state.charAt(5)));
                break;
            }
            case 3: {
                successors.add(state.replace(state.charAt(3), '*').replace(state.charAt(0), state.charAt(3)).replace('*', state.charAt(0)));
                successors.add(state.replace(state.charAt(3), '*').replace(state.charAt(4), state.charAt(3)).replace('*', state.charAt(4)));
                successors.add(state.replace(state.charAt(3), '*').replace(state.charAt(6), state.charAt(3)).replace('*', state.charAt(6)));
                break;
            }
            case 4: {
                successors.add(state.replace(state.charAt(4), '*').replace(state.charAt(1), state.charAt(4)).replace('*', state.charAt(1)));
                successors.add(state.replace(state.charAt(4), '*').replace(state.charAt(3), state.charAt(4)).replace('*', state.charAt(3)));
                successors.add(state.replace(state.charAt(4), '*').replace(state.charAt(5), state.charAt(4)).replace('*', state.charAt(5)));
                successors.add(state.replace(state.charAt(4), '*').replace(state.charAt(7), state.charAt(4)).replace('*', state.charAt(7)));
                break;
            }
            case 5: {
                successors.add(state.replace(state.charAt(5), '*').replace(state.charAt(2), state.charAt(5)).replace('*', state.charAt(2)));
                successors.add(state.replace(state.charAt(5), '*').replace(state.charAt(4), state.charAt(5)).replace('*', state.charAt(4)));
                successors.add(state.replace(state.charAt(5), '*').replace(state.charAt(8), state.charAt(5)).replace('*', state.charAt(8)));
                break;
            }
            case 6: {
                successors.add(state.replace(state.charAt(6), '*').replace(state.charAt(3), state.charAt(6)).replace('*', state.charAt(3)));
                successors.add(state.replace(state.charAt(6), '*').replace(state.charAt(7), state.charAt(6)).replace('*', state.charAt(7)));
                break;
            }
            case 7: {
                successors.add(state.replace(state.charAt(7), '*').replace(state.charAt(4), state.charAt(7)).replace('*', state.charAt(4)));
                successors.add(state.replace(state.charAt(7), '*').replace(state.charAt(6), state.charAt(7)).replace('*', state.charAt(6)));
                successors.add(state.replace(state.charAt(7), '*').replace(state.charAt(8), state.charAt(7)).replace('*', state.charAt(8)));
                break;
            }
            case 8: {
                successors.add(state.replace(state.charAt(8), '*').replace(state.charAt(5), state.charAt(8)).replace('*', state.charAt(5)));
                successors.add(state.replace(state.charAt(8), '*').replace(state.charAt(7), state.charAt(8)).replace('*', state.charAt(7)));
                break;
            }
        }
        return successors;
    }
    
    // Prints the states leading up to the solution
    public static void solutionPrint(Node goalNode, Set<String> visitedStates, Node root, int time) {
        int totalCost = 0;
        Stack<Node> solution = new Stack<Node>(); // A stack to hold all solutions.
        solution.push(goalNode); // Place the goal node on the stack.
        
        while (!goalNode.getState().equals(root.getState())) { // While the goal states state doesn't equal the roots state.  
        		solution.push(goalNode.getParent()); // Push the parent of the goal node onto the stack.
            goalNode = goalNode.getParent(); // Set the goal node equal to the parent.
        }
        
        String initialState = root.getState(); 
        String destinationState;
        int cost = 0;
        
        for (int i = solution.size() - 1; i >= 0; i--) {
            destinationState = solution.get(i).getState();
            
            if (!initialState.equals(destinationState)) {
            	    System.out.println();
                System.out.println("Moving " + destinationState.charAt(initialState.indexOf('0')) + " " + findTransition(initialState, destinationState));
                cost = Character.getNumericValue(destinationState.charAt(initialState.indexOf('0')));
                totalCost += cost;
            }
                        
            System.out.println("Movement cost: " + cost);
            System.out.println(solution.get(i).getState().substring(0, 3));
            System.out.println(solution.get(i).getState().substring(3, 6));
            System.out.println(solution.get(i).getState().substring(6, 9));
            initialState = destinationState;
        }
        
        System.out.println();
        System.out.println("Number of steps getting to goal board from start board = " + (solution.size() - 1));
        System.out.println("Number of nodes taken out of the queue = " + time);
        System.out.println("Total amount of states that have been visited =  " + (visitedStates.size()));
        System.out.println("Total cost for solution = " + totalCost);
        
    }

    public enum MovementType {Up,Down,Left,Right;} // Enumeration class for movement on the board.
        
    // Returns the movement direction.
    public static MovementType findTransition(String source, String destination) {
        int differenceOfPosition = destination.indexOf('0') - source.indexOf('0'); 
        switch (differenceOfPosition) {
            case -3:
                return MovementType.Up;
            case 3:
                return MovementType.Down;
            case 1:
                return MovementType.Left;
            case -1:
                return MovementType.Right;
        }
        return null;
    }
}