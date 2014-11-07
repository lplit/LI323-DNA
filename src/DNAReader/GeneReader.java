package DNAReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

/**
 * Reads genes from files and stocks them
 * Corresponding files: 
 * NC_018520_genes.fna
 * NC_017696_genes.fna
 * NC_019896_genes.fna
 * Extraits/parts of Reader
 */
public class GeneReader {

    ArrayList<Gene> genes;

    /**
     * Constructor method
     */
    public GeneReader(String file) {
	try {
	    genes = new ArrayList<Gene>();
	    BufferedReader br = new BufferedReader(new FileReader("./Data/"+file));
	    String sCurrentLine;
	    
	    while ((sCurrentLine = br.readLine()) != null) { // [>name \n body]
		char[] chr = sCurrentLine.toCharArray();
		String tmp_name="";
		String tmp_body="";
		if (chr[0]=='>') { // Reads the name
		    tmp_name = sCurrentLine;
		    sCurrentLine = br.readLine(); // To get to the body
		    tmp_body+=sCurrentLine;
		    Gene g = new Gene(tmp_name, tmp_body);
		    genes.add(g); // Add current genome to the list
		} else { // If not name skip to next line
		    continue;
		}
	    }
	} catch (IOException e) {
	    System.out.println("File read error!\n");
	    e.printStackTrace();
	}
    }
    

    /**
     * Getter
     */
    public ArrayList<Gene> getGenes() {
	return genes;
    }

    /**
     * Calculates the gene with highest score from all file
     *
     *
     */
    public double getBestGeneFile(String filename, int offset) {
	double score = -9999999.;
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
		    System.out.println("Test case: "+tmp_name);
		    double tmp = getBestGene(tmp_body);
		    if (tmp>score) score=tmp;
		} else 
		    sCurrentLine=br.readLine();
	    }
	} catch (IOException e) {
	    System.out.println("File read error!\n");
	    e.printStackTrace();
	} finally {
	    return score;
	}	
    }
    
    public double analysebyX(String body) {
	ArrayList<Double> arr = new ArrayList<Double>();
	for (Gene g : genes) {
	    arr.add(g.getLogSum(body));
	}
	return Collections.max(arr);
    }

    public double getBestGene(String s) {
	double best=-999999.;
	String bestName="";
	Gene bg=null;
	for (Gene g : genes) {
	    double tmp = g.getLogSum(s);
	    if (tmp > best) {
		best = tmp;
		bg = g;
		bestName= g.getName();
	    }
	}
	System.out.println("Best score ("+best+"):\t" + bestName+"\n");
	return best;
    }
    
    public String toString() {
	String s = "";
	for (Gene g : genes) {
	    s+=g.toString();
	}
	return s;
    }

    public void printLetters() {
	for (Gene g : genes) {
	    System.out.println(g);
	    System.out.println("\n");
	}
    }
    
    public String getNames() { 
	String s="";
	for (Gene g : genes) {
	    s+=g.getName()+" ";
	}
	return s;
    }
}
