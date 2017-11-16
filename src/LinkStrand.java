/* Project 3: DNA and LinkStrand
 * Brian Jordan
 * November 13, 2017
 */
public class LinkStrand implements IDnaStrand{
	// Node SubClass to create LinkList Node Objects
	private class Node {
		String info;
		Node next;
		public Node(String s) {
			info = s;
			next = null;
		}
	}
	// Instance Variables
	private Node myFirst;
	private Node myLast;
	private long mySize;
	private int myAppends;
	public int myIndex;
	public Node myCurrent;
	public int myLocalIndex;
	public LinkStrand(){
		this("");
	}
	// Constructor of LinkStrand Objects
	public LinkStrand(String s){
		initialize(s);
	}
	// Returns length of DNA strand
	@Override
	public long size() {
		return mySize;
	}
	// Initializes the LinkStrand Object instance variables
	@Override
	public void initialize(String source) {
		mySize = source.length();
		myAppends = 0;
		myFirst = new Node(source);
		myLast = myFirst;
		myIndex = 0;
		myCurrent = myFirst;
		myLocalIndex = 0;
	}
	// returns instance of the LinkStrand class
	@Override
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
	}
	// Adds to LinkStrand by appending a new Node to the LinkedList
	@Override
	public IDnaStrand append(String dna) {
		myLast.next = new Node(dna);
		myLast = myLast.next;
		mySize += dna.length();
		myAppends += 1;
		return this;
	}
	// reverses the DNA strand data by reversing the Nodes of the LinkList and the Strings of each of them
	@Override
	public IDnaStrand reverse() {
		StringBuilder reverseString = new StringBuilder(myFirst.info);
		reverseString = reverseString.reverse();
		LinkStrand reverseStrand = new LinkStrand(reverseString.toString());
		Node list = myFirst;
		list = list.next;
		while (list != null){
			StringBuilder nextReverseString = new StringBuilder(list.info);
			nextReverseString = nextReverseString.reverse();
			Node nextFirst = new Node(nextReverseString.toString());
			nextFirst.next = reverseStrand.myFirst;
			reverseStrand.myFirst = nextFirst;
			list = list.next;
		}
		return reverseStrand;
	}
	// returns the amount of Nodes in the LinkStrand
	@Override
	public int getAppendCount() {
		return myAppends;
	}
	// efficiently returns the character at a certain index of the DNA strand by starting from the previous search index
	@Override
	public char charAt(int index) {
		if (index < 0 || index >= mySize){
			throw new IndexOutOfBoundsException("Index is not within the Strand");
		}
		if (myIndex > index){
			myIndex = 0;
			myLocalIndex = 0;
			myCurrent = myFirst;
		}
		while (myIndex != index){
			myIndex++;
			myLocalIndex++;
			if (myLocalIndex >= myCurrent.info.length()){
				myLocalIndex = 0;
				myCurrent = myCurrent.next;
				}
			}
		return myCurrent.info.charAt(myLocalIndex);
}
	// returns the DNA Strand 
	public String toString(){
		Node list = myFirst;
		StringBuilder s = new StringBuilder();
		while (list != null){
			s.append(list.info);
			list = list.next;
		}
		return s.toString();
	}
	
}
