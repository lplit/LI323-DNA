package DNAReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GeneReader {

    ArrayList<Gene> genes;

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
    
    public ArrayList<Gene> getGenes() {
	return genes;
    }


    public void getBestGeneFile(String filename) {
	try {
	    BufferedReader br = new BufferedReader(new FileReader("./Data/"+filename));
	    String sCurrentLine = br.readLine();
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
		    Gene g = getBestGene(tmp_body);

		} else 
		    sCurrentLine=br.readLine();
	    }
	} catch (IOException e) {
	    System.out.println("File read error!\n");
	    e.printStackTrace();
	}
    }
    
    public Gene getBestGene(String s) {
	double best=0.;
	String bestName="";
	Gene bg=null;
	for (Gene g : genes) {
	    double tmp = g.getLogSum(s);
	    if (tmp < best) {
		best = tmp;
		bg = g;
		bestName= g.getName();
	    }
	}
	System.out.println("Best score ("+best+"):\t" + bestName+"\n");
	return bg;
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
	    g.printTriplets();
	    System.out.println("\n");
	}
    }
}
