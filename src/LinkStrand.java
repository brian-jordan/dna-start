
public class LinkStrand implements IDnaStrand{
	private class Node {
		String info;
		Node next;
		public Node(String s) {
			info = s;
			next = null;
		}
	}
	private Node myFirst,myLast;
	private long mySize;
	private int myAppends;
	public int myIndex;
	public Node myCurrent;
	public int myLocalIndex;
	public LinkStrand(){
		this("");
	}
	
	public LinkStrand(String s){
		initialize(s);
	}
	@Override
	public long size() {
		return mySize;
	}

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
	@Override
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
	}
	@Override
	public IDnaStrand append(String dna) {
		Node list = myFirst;
		for (int i = 0; i < myAppends; i++){
			list = list.next;
		}
		list.next = new Node(dna);
		list = list.next;
		myLast = list;
		mySize += dna.length();
		myAppends += 1;
		return this;
	}
	// TODO
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
	@Override
	public int getAppendCount() {
		return myAppends;
	}
	// TODO
	@Override
	public char charAt(int index) {
		if (index < 0 || index > mySize){
			throw new IndexOutOfBoundsException("Index is not within the Strand");
		}
		else{
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
	}
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
