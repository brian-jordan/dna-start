/* Project 3: DNA and LinkStrand
 * Brian Jordan
 * November 13, 2017
 */
public class Analysis {
	// Number of Trials per b value
	final static int NUM_TRIALS = 5;
	public static void main(String[] args){
		String myEnzyme = "agtcagtc";
		// mySplice iterations
//		String mySplice = "bbbb";												// s value of 4
//		String mySplice = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";						// s value of 30
																				// s value of 107
		String mySplice = "asaskdfsajdflajsdlfkjalsdkfjlaksjdflaksjdflajsdflajsdlfkjalsdjflaskdfjlasjdfljalsdjkfasldfasdfaskjdflkasdjf";
		// different iterations of b values
//		for (int b = 0; b < 163840; b *= 2){									// StringStrand test
		for (int b = 10000; b < 3638400; b *= 2){								// StringBuilderStrand test, LinkStrand Test
			StringBuilder dna = new StringBuilder();
			float totalTime = 0;
			// appends myEnzyme to dna StringBuilder b times
			for (int i = 0; i < b; i++) {
				dna.append(myEnzyme);
			}
			for (int trial = 0; trial < NUM_TRIALS; trial++) {
				// creating different types of strands
//				IDnaStrand strand = new StringStrand(dna.toString());			// StringStrand test
//				IDnaStrand strand = new StringBuilderStrand(dna.toString());	// StringBuilderStrand test
				IDnaStrand strand = new LinkStrand(dna.toString());				// LinkStrand test
				double start = System.nanoTime();
				strand.cutAndSplice(myEnzyme, mySplice);
				totalTime += (System.nanoTime() - start) / 1e9;
			}
			// average trial times
			totalTime /= NUM_TRIALS;
			System.out.printf("%6d\t%6.4f\n", b, totalTime);
		}
	}
	
}
