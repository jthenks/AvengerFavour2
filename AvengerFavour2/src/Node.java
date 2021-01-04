/**
 * Node Class with setters and getters to access linked list 
 *   
 * @author johnvaliente
 *
 * @param <T> d - the data to be stored in the node
 */

public class Node <T extends Comparable <T>> 
{

	// the data stored in the node
	private T data;
	
	private Node<T> next;

	/**
	 * Constructor for objects of class Node
	 */
	public Node(T d) {
		data = d;
		next = null;
	}

	/**
	 * getter method, gets data
	 * 
	 * @return the data stored in the node
	 */
	public T getData() {
		return data;
	}

	/**
	 * Setter method, sets data
	 * 
	 * @param o - the new data to be set in the node
	 */
	public void setData(T o) {
		data = o;
	}

	/**
	 * Gets next object in linked list
	 * @return the next object 
	 */
	public Node<T> getNext() {
		return next;
	}

	/**
	 * Sets next node in linked list
	 * 
	 * @param Node<T> n - the new next node in the list
	 */
	public void setNext(Node<T> n) {
		next = n;
	}

	/**
	 * toString method
	 * @return the String of the data 
	 */
  @Override 
	public String toString() {
		return getData().toString();
	}
	
}