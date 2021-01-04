import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * COMP 2503 Winter 2020 Assignment 2
 * 
 * This program must read a input stream and keeps track of the frequency at
 * which an avenger is mentioned either by name or alias.
 *
 * @author Maryam Elahi
 * @author John Valiente
 * @author Shargeel Hayat
 * @author Jordan Henkelman
 * 
 * @date October 2020
 */

public class A2
{

	public String[][] avengerRoster =
	{
			{ "captainamerica", "rogers" },
			{ "ironman", "stark" },
			{ "blackwidow", "romanoff" },
			{ "hulk", "banner" },
			{ "blackpanther", "tchalla" },
			{ "thor", "odinson" },
			{ "hawkeye", "barton" },
			{ "warmachine", "rhodes" },
			{ "spiderman", "parker" },
			{ "wintersoldier", "barnes" } };

	private int totalwordcount = 0;

	private Scanner input;
	private Scanner kb;

  //creating the four desired linked lists 
	private SLL<Avenger> mentionList = new SLL<Avenger>();
	private SLL<Avenger> alphabetList = new SLL<Avenger>();
	private SLL<Avenger> leastPopList = new SLL<Avenger>(new CompLeastPopAvengers());
	private SLL<Avenger> mostPopList = new SLL<Avenger>(new CompMostPopAvengers());

	/**
	 * Creates A1 class object calls run() method
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException
	{
		A2 a2 = new A2();
		a2.run();
	}

	/**
	 * loads the method required for program to begin
	 * 
	 * @throws FileNotFoundException
	 */
	public void run() throws FileNotFoundException
	{
		readInput();
		printResults();
	}


	/**
	 * In a loop, while the scanner object has not reached end of stream, - read a
	 * word. - clean up the word - if the word is not empty, add the word count. -
	 * Check if the word is either an avenger alias or last name then - Create a new
	 * avenger object with the corresponding alias and last name. 
	 * 
	 * After going through while loop, it calls addNewList 3 times to create the 3 new singly 
	 * linked list
	 * 
	 * 
	 * @throws FileNotFoundException
	 * 
	 */
	private void readInput() throws FileNotFoundException
	{

		String words;
		String filename;

		kb = new Scanner(System.in);
		System.out.println("Placeholder - enter file name without extensions");
		filename = kb.next() + ".txt";

		File inFile = new File(filename);
		input = new Scanner(inFile);

		while (input.hasNext())
		{
			// Trims and cleans the input word
			words = input.next().trim().toLowerCase().replaceAll("[^a-z's]", "");

			// this variable removes words with apostrophe s
			String words2 = words.replaceAll("'s", "");

			// this ensures that numbers are NOT included; needed this for cleaning
			// inputstream;
			String words3 = words2.replaceAll("[0-9]", "");

			// this ensures that words with an apostrophe after a word is removed (ex.
			// Rhodes' turns to rhodes)
			String words4 = words3.replaceAll("[']", "");

			// Total word counter
			if (!words4.equals(""))
			{
				totalwordcount = totalwordcount + 1;
			}

			for (int row = 0; row < avengerRoster.length; row++)
			{

				// If condition statement to detect input stream match
				if (avengerRoster[row][0].equals(words4) || avengerRoster[row][1].equals(words4))
				{
					
					//creates avenger from specific 2d array
					createAvenger(avengerRoster[row][0], avengerRoster[row][1]);

				}
			}
		}
		
		//takes the Objects in mentionList and adds 3 new Lists in order
		addNewList(mentionList, mostPopList);		
		addNewList(mentionList, leastPopList);
		addNewList(mentionList, alphabetList);

	}

	/**
	 * Creates a new node type avenger, an if else statement that checks if 
	 * the avenger is already a part of the mentionList List. If Avenger is already there
	 * it adds one to its frequency. If it is not there, it creates a new node avenger
	 * and adds to the tail of the mentionList.
	 *
	 * @param alias - alias of the Avenger
	 * @param name  - name of Avenger
	 * @param freq  - initial amount an Avenger is mentioned
	 */
	public void createAvenger(String alias, String name)
	{
		Avenger a = new Avenger();
		a.setHeroAlias(alias);
		a.setHeroName(name);

		if (mentionList.checkNode(a))
		{

			mentionList.findNode(a).getData().freqPlusOne();
		} 
		else
		{
			Node<Avenger> nodeAvenger = new Node<Avenger>(a);
			mentionList.addTail(nodeAvenger);
		}
	}

	/**
	 * Prints the results from the input stream from the 4 different lists
	 */
	private void printResults()
	{
		System.out.println("Total number of words: " + totalwordcount);
		System.out.println("Number of Avengers Mentioned: " + mentionList.getSize());
		System.out.println();

		System.out.println("All avengers in the order they appeared in the input stream:");
		mentionList.printTheList();

		System.out.println();

		System.out.println("Top 4 most popular avengers:");
		mostPopList.printListFour();
		
		System.out.println();
		
		System.out.println("Top 4 least popular avengers:");
		leastPopList.printListFour();

		System.out.println();
		
		System.out.println("All mentioned avengers in alphabetical order:");
		alphabetList.printTheList();
	}


	/**
	 * This method creates new list based off of the mentionList that was initially created.
	 * The 3 other lists (mostPopList, leastPopList, and alphabetList are created in order)
	 * 
	 * @param mentionList - the list of Avengers in the order they are read
	 * @param otherList - any of the other three lists that will be populated using mentionList
	 */
	public void addNewList(SLL<Avenger> mentionList, SLL<Avenger> otherList) 
	{
	    Node <Avenger> mover = mentionList.getHead();
	    
      // if mention list is empty
	    if (mover == null)
	    {
	    	return;
	    }
      // while the mentionList is not empty, uses addInOrder to populate the otherList 
	    while (mover != null) 
	    {
	      otherList.addInOrder(mover.getData());
	      mover = mover.getNext();
	    }
	  }
}