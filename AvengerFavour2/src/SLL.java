import java.util.Comparator;

/**
 * This class allows for the creation and manipulation of a single linked list
 * 
 * @author Jordan Henkelman
 * @author John Valiente
 * @author Shargeel Hayat  
 *
 * @param Comparator <T> - takes in a comparator object so that the list data can be compared using it
 */
public class SLL<T extends Comparable<T>>
{

	private Node<T> head;
	private Node<T> tail;
	private int size;
	private Comparator<T> comparator;

	/**
	 * Constructor method for lists that wont use a comparator
	 */
	public SLL()
	{
		head = null;
		size = 0;
		comparator = null;
	}

	/**
	 * Constructor method for lists that will use a comparator
	 * @param externalComp - comparator object 
	 */
	public SLL(Comparator<T> externalComp)
	{
		head = null;
		size = 0;
		comparator = externalComp;
	}
	
	/**
	 * getter method
	 * @return the head of the list, if the list is empty, returns null
	 */
	public Node<T> getHead()
	{
		return head;
	}
	
	/**
	 * Getter method. 
	 * @return size of the linked list
	 */
	public int getSize()
	{
		return size;
	}

	/**
	 * Adds a node to the head of the singly linked list
	 * @param n - a node of type T
	 */
	public void addHead(Node<T> n)
	{
		// if list is empty, sets head and tail = n
		if (head == null)
		{
			head = tail = n;
			size++;
		} 
		// sets new node as head
		else
		{
			n.setNext(head);
			head = n;
			size++;
		}
	}
	
	/**
	 * Adds a node to the tail of the singly linked list
	 * @param n - a node of type T
	 */
	public void addTail(Node<T> n)
	{
		// if list is not empty
		if (tail != null)
		{
			tail.setNext(n);
			tail = n;
			size++;
		} 
		// if list is empty
		else
		{
			addHead(n);
		}
	}

	/**
	 * Searches for the node in the list containing a specific data set 
	 * @param key - the data that the class is searching for within the nodes
	 * @return - the Node containing the desired data, or null if the data was not found
	 */
	public Node<T> findNode(T key)
	{
		Node<T> mover = head;

		while (mover != null)
		{
			if (mover.getData().equals(key))
			{
				return mover;
			}
			mover = mover.getNext();
		}
		return null;
	}

	/**
	 * Checks if a set of data is included within a node in the list
	 * @param key - the data that is being searched for within the list
	 * @return boolean found or not. True if a match is found, false if not
	 */
	public boolean checkNode(T key)
	{
		boolean found = true;
		
		Node<T> mover = head;

		while (mover != null)
		{
			if (mover.getData().equals(key))
			{
				return found;
			}
			mover = mover.getNext();
		}
		found = false;
		return found;
	}
	
	/**
	 * Adds an object to the singly linked list in the correct position
	 * @param n - the data that is to be stored in a node and inserted into the SLL
	 */
	public void addInOrder(T n)
	{

		Node<T> newNode = new Node<T>(n);

		// Adds a node containing n to head if the list is empty or if n is 
		// supposed to go before the current head's data
		if (head == null || compare(n, head.getData()) <= 0)
		{
			addHead(newNode);
			size++;
		}

		// Adds node containing n to the tail if n is supposed to go after te
		// current tail's data
		else if (compare(n, tail.getData()) > 0)
		{
			addTail(newNode);
			size++;
		}

		// Adds node containing n into the correct place of the SLL if the node falls somewhere
		// in between the head and tail nodes
		else
		{
			Node<T> mover = head;

			// while n is larger than the nodes's data, it keeps searching until n is smaller
			while (mover.getNext() != null && compare(n, mover.getNext().getData()) > 0)
			{
				mover = mover.getNext();
			}
			// when the node containing n is smaller than mover.getnext, we link the n node to mover.getNext,\
			// then set mover.next to the n node, inserting the new node into the list at the correct position
			newNode.setNext(mover.getNext());
			mover.setNext(newNode);
			size++;
		}
	}

	/**
	 * Compares two objects in the desired way depending on if the SLL was 
	 * created using a comparator as the parameter. If there is no comparator, 
	 * it compares alphabetically
	 * 
	 * @param a1 - object one
	 * @param a2 - object two
	 * @return - int difference. A negative int means a1 goes before a2, 0 means they are tied, 
	 * a positive int means a1 goes after a2 
	 */
	private int compare(T a1, T a2)
	{
		// If only one object is passed in
		if (a1 == null || a2 == null)
		{
			return 0;
		}
		
		// If there is no comparator: compares alphabetically 
		if (comparator == null)
		{
			return ((Avenger) a1).getHeroAlias().compareTo(((Avenger) a2).getHeroAlias());
		}

		// Uses the comparator
		else
		{
			return comparator.compare(a1, a2);
		}
	}

	/**
	 * Checks if the list is empty
	 * @return boolean true if the list is empty, false if not 
	 */
	public boolean isEmpty()
	{

		if (head == null)
		{
			return true;
		}

		else
		{
			return false;
		}
	}

	/**
	 * Prints the singly linked list
	 */
	public void printTheList()
	{
		Node<T> mover = head;
		while (mover != null)
		{
			System.out.println(mover.toString());
			mover = mover.getNext();
		}
	}

	/**
	 * Prints the data from the first four nodes in the list
	 */
	public void printListFour()
	{
		Node<T> mover = head;

		for (int i = 0; i < 4 && mover != null; i++)
		{
			System.out.println(mover.toString());
			mover = mover.getNext();
		}
	}

}