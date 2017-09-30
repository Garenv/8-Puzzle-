public class Main {	
	public static void main(String[] args) {
		String easyBoard = "134862705";
		String mediumBoard = "281043765";
		String hardBoard = "281463075";
		String worstBoard = "567408321";
		String goalBoard = "123804765";
		String anyNumbersYouWant = "";
	
		String startState = easyBoard; // You can the start state to whichever you like or create your own.
		long startTime = System.currentTimeMillis(); // Take start time in milliseconds.
	
		AllSearches search = new AllSearches(new Node(startState), goalBoard);
		System.out.println("***Breadth First Search***");
		search.breadthFirstSearch(); 
		System.out.println();
		System.out.println("***Depth First Search***");
		search.depthFirstSearch();
		
		long finishTime = System.currentTimeMillis(); // Take finish time in milliseconds.
		long totalTime = finishTime - startTime; // Take finish time in milliseconds.
		System.out.println("Time of the program when user hits run = " + totalTime);
	}
}