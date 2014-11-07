package DNAReader;

import java.io.*;

/** LANGUAGE CLASS 
 * Corresponding files: 
 * NC_017626.fna
 * NC_018520.fna
 * NC_019896.fna
 */
public class Reader {
    
    private final int nbLttrs=5;
    private final char[] sequenceLetters = {'A', 'C', 'G', 'T', 'N'};
    private int[] letterOccurences = new int[nbLttrs]; //letterOccurences[0]= # of 'A' in text etc
    private double[] percOf;
    private int totalLetters;
    private String lang;
    private Gene g;

    /**
     * By default constructor method, initializes the fields.
     */
    public Reader() { 
	letterOccurences = new int[nbLttrs];
	percOf = new double[nbLttrs]; 
	totalLetters = 0;
	lang="";
    }

    /**
     * Constructor, reads file, allocates the class variables etc etc.
     * @param filename Name of the file to read WITH extention (ex: english.txt). 
     * Attention, file MUST exist within /Data. Throws IOException if file not found.
     */
    public Reader(String filename) {
	this(); // Allocation
	try (BufferedReader br = new BufferedReader(new FileReader("./Data/"+filename))) {
		String sCurrentLine;
		lang = br.readLine(); // Stock 'language' - lol...
		while ((sCurrentLine = br.readLine()) != null) { // Reading file line by line
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
			default : 
			    letterOccurences[4]++;
			    break; 
			}
			totalLetters++;
		    }
		}
		percOf=percCalc(letterOccurences);
		g = analyseFile(filename); // Triplets stats
	    } catch (IOException e) {
	    System.out.println("File read error!\n");
	    e.printStackTrace();
	}
    }

    public double getLogSum(String s) {
	return g.getLogSum(s);
    }

    public double getBestGeneFile(String filename, int offset) { 
	double ret = -999999.;
	try {
	    BufferedReader br = new BufferedReader(new FileReader("./Data/"+filename));
	    String sCurrentLine = br.readLine();
	    sCurrentLine = sCurrentLine.substring(offset);
	    while (sCurrentLine != null) { // Read file till EOF
		char[] chr = sCurrentLine.toCharArray();
		if ( chr[0] =='>' ) { // Name line
		    String tmp_name = sCurrentLine;
		    String tmp_body = "";
		    sCurrentLine = br.readLine(); // Obligatory body line
		    char[] chr2 = sCurrentLine.toCharArray();
		    while ((chr2[0] != '>') && ((sCurrentLine = br.readLine()) != null)) { // Get full body
			tmp_body+=sCurrentLine;
			chr2 = sCurrentLine.toCharArray();
		    }
		    double tmp=g.getLogSum(tmp_body);
		    if (tmp>ret) ret=tmp;
		    System.out.println("["+lang+"] Test case: "+tmp_name+ " score: "+tmp);
		} else
		    sCurrentLine=br.readLine();
	    }
	} catch (IOException e) {
	    System.out.println("File read error!\n");
	    e.printStackTrace();
	} finally {
	    if (ret == 0.) 
		System.out.println("[Reader$getBestGeneFile]Something went wrong!");
	    return ret;
	}
    }
    
    // This is practically a Gene constructor but from a file
    private Gene analyseFile(String filename) {
	try (BufferedReader br = new BufferedReader(new FileReader("./Data/"+filename))) {
		String sCurrentLine="";;
		String crp="";
		String lan = br.readLine(); // Stock 'language' - lol...
		if (g==null) g=new Gene(lan);
		while ((sCurrentLine = br.readLine()) != null) { // Reading file line by line
		    crp+=sCurrentLine;
		    while (crp.length() > 3) {
			String currcodon = crp.substring(0,3);
			g.addCodon(currcodon);
			crp = crp.substring(3);
			//g.addToBody(currcodon);
		    }
		}
		g.calcPercs();
		g.storeTripletsStats(filename);
		return g;
	    } catch (IOException e) {
	    System.out.println("File read error!\n");
	    e.printStackTrace();
	} finally {
	    return g;
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

    public String getLang() {
	return lang;
    }
    
    public double getLetterPerc(char c) {
	switch (c) {
	case 'A' :
	    return letterOccurences[0];

	case 'C' : 
	    return letterOccurences[1];

	case 'G' :
	    return letterOccurences[2];

	case 'T' :
	    return letterOccurences[3];
	default : 
	    return letterOccurences[4];
	}
    }

    /* STRING/VISUAL OUTPUT */

    /**
     * Method overwrite, toString
     */
    @Override public String toString() { 
	String s = "";
	for (int i=0 ; i< letterOccurences.length ; i++) {
	    int curr = letterOccurences[i];
	    double pourc= ((((double)curr)/totalLetters)*100);
	    s += (((char) (i+97)) + " " + curr + "\t" + pourc + "%\n");
	}
	return s;
    }

    /**
     * Calculates number of occurences for each letter, prints it, as well as % of total
     */
    public void printLettersStats() {
	System.out.println("Total:\t"+totalLetters+" for "+lang);

	for (int i=0 ; i< letterOccurences.length ; i++) {
	    // Simplicity handles
	    int curr = letterOccurences[i];
	    char currChar = sequenceLetters[i];
	    double pourc= percOf[i];
	    System.out.println(currChar + "\t#"+curr+"\t" + (pourc*100) + "%");
	}
	System.out.println(this.g);
	System.out.println();

    }
}
