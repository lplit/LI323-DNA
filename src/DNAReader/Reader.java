package DNAReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class should be instanced for each language to be analysed. 
 * 
 *
 */

public class Reader {
    
    private final int nbLttrs=5;
    private final char[] sequenceLetters = {'A', 'C', 'G', 'T', 'N'};
    private int[] letterOccurences = new int[nbLttrs]; //letterOccurences[0]= # of 'A' in text etc
    private double[] percOf;
    private int totalLetters;
    private String lang;

    public static void main(String[] args) {

	Reader r1 = new Reader("NC_017626.fna");
	r1.printLettersStats();

	Reader r2 = new Reader("NC_018520.fna");
	r2.printLettersStats();

	Reader r3 = new Reader("NC_019896.fna");
	r3.printLettersStats();

	System.out.println("LAAAAAAAAAAAAAAAAAAAAAAAAAAALW00000000000000T!!!!!!!111111oneoneone");
	return;
    }

    /**
     * By-default constructor method, initializes the fields.
     */
    public Reader() { 
	letterOccurences = new int[nbLttrs];
	percOf = new double[nbLttrs]; 
	totalLetters = 0;
	lang="";
    }
    
    /**
     * Constructor, reads file, allocates the class variables etc etc.
     * @param filename Name of the file to read WITH extention (ex: english.txt). Attention, file MUST exist within /Data. Throws IOException if file not found.
     */
    public Reader(String filename) {
	this(); // Allocation
	try (BufferedReader br = new BufferedReader(new FileReader("./Data/"+filename))) {
		String sCurrentLine;
		lang = br.readLine(); // Stock 'language'
		while ((sCurrentLine = br.readLine()) != null) { // Stock letterOccurences
		    char[] chr = sCurrentLine.toCharArray();
		    for (int i=0 ; i < chr.length ; i++) {
			switch (chr[i]) {
			case 'A' : 
			    letterOccurences[0]++;
			    break;
			case 'C' :
			    letterOccurences[1]++;
			    break;
			case 'G' :
			    letterOccurences[2]++;
			    break;
			case 'T' : 
			    letterOccurences[3]++;
			    break;
			case 'N' : 
			    letterOccurences[4]++;
			    break; 
			default : 
			    System.out.println("[Reader] Unknown character!");
			    break;
			}
			totalLetters++; // Counts letters 
		    }
		}
		
		percOf=percCalc(letterOccurences); // Stock
	    } catch (IOException e) {
	    System.out.println("File read error!\n");
	    e.printStackTrace();
	}
    }


    /**
     * Used in constructor to create percOf
     * @param tab Table with # of occurences of each letter
     * @return double[] with percentage
     */
    private double[] percCalc(int[] tab) { 
	double[] ret = new double[nbLttrs];
	for (int i=0 ; i < tab.length ; i++) {
	    // Simplicity handles
	    int curr = tab[i];
	    double pourc= ((((double)curr)/totalLetters));
	    ret[i]=pourc;
	}
	return ret;
    }

    /* GETTERS */

    /**
     * letterOccurences getter
     * @return letterOccurences array
     */
    public int[] getLetterOccurences() {
	return letterOccurences;
    }

    /**
     * Field percOf getter
     * @return percOf field
     */
    public double[] getPercOf() {
	return percOf;
    }

    /**
     * Field totalLetters getter
     * @return totalLetters field
     */
    public int getTotalLetters() {
	return totalLetters;
    }


    /** Single letter stat getter
     * @param letter Letter to check the stat of
     * @return Returns % for a @param letter
     */
    public double getLetterPerc(char a) {
	switch (a) {
	case 'A' : 
	    return letterOccurences[0];
	case 'C' :
	    return letterOccurences[1];
	case 'G' :
	    return letterOccurences[2];
	case 'T' : 
	    return letterOccurences[3];
	case 'N' : 
	    return letterOccurences[4];
	default : 
	    System.out.println("[Reader] Unknown character!");
	    return 0.0;
	}
    }
    
    
    /* STRING/VISUAL OUTPUT */

    /**
     * Method overwrite, toString
     */
    @Override public String toString() { 
	String s = "";
	for (int i=0 ; i< letterOccurences.length ; i++) {
	    // Simplicity handles
	    int curr = letterOccurences[i];
	    char currChar = sequenceLetters[i];
	    double pourc= percOf[i];

	    s += (currChar + "\t" + curr + "\t" + pourc + "%\n");
	}
	return s+=("Total:\t"+totalLetters+" for "+lang+"\n");
    }

    /**
     * Calculates number of occurences for each letter, prints it, as well as % of total
     */
    public void printLettersStats() {
	for (int i=0 ; i< letterOccurences.length ; i++) {
	    // Simplicity handles
	    int curr = letterOccurences[i];
	    char currChar = sequenceLetters[i];
	    double pourc= percOf[i];

	    System.out.println(currChar +"\t#"+curr+"\t"+(pourc*100)+"%");
	}
	System.out.println("Total:\t"+totalLetters+" for "+lang+"\n");
    }
}
