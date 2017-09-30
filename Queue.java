import java.util.*;

public class Queue<Item> implements Iterable<Item> {
    private Node<Item> startPoint; // Starting point of queue.
    private Node<Item> endPoint; // Ending point of queue.
    private int numOfElements; // The number of elements on a Queue

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public Queue() {
    		startPoint = null; // Empty starting point of queue.
    		endPoint = null; // Empty ending point point of queue.
    		numOfElements = 0; // The number of elements on a Queue
    }

    public void clear() {
	    	startPoint = null;
	    	endPoint = null;
	    	numOfElements= 0;
    }
    
    // Checks to see if queue is empty.
    public boolean isEmpty() {
        return startPoint == null;
    }

    // Returns the number of elements in the queue.
    public int size() {
        return numOfElements;
    }
    
    @Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

    // Returns the last element that was added in the queue.
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return startPoint.item;
    }

    // Adding elements to the queue
    public void enqueue(Item item) {
        Node<Item> oldlast = endPoint;
        endPoint = new Node<Item>();
        endPoint.item = item;
        endPoint.next = null;
        if (isEmpty()) startPoint = endPoint;
        else oldlast.next = endPoint;
        numOfElements++;
    }

    // Deletes and returns item on the queue that was added recently.
    public Item dequeue() {
    	 	Item item = startPoint.item;
        startPoint = startPoint.next;
        numOfElements--;
        
        if (isEmpty()) {
        		throw new NoSuchElementException("Queue underflow");
        }
       
        if (isEmpty())  {
        		endPoint = null;   
        }
        return item;
    }
    
    // Adding to the beginning of the queue.
    public void addQueue(Queue<Item> queue) {
        if (!queue.isEmpty()) {
            Node<Item> oldFirst = startPoint;

            if (isEmpty()) {
            	startPoint = queue.startPoint;
            	endPoint = queue.endPoint;
            } else {
            	startPoint = queue.startPoint;
                queue.endPoint.next = oldFirst;
            }
            numOfElements = numOfElements + queue.size();
        }
    }
}