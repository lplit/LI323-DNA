package DNAReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Gene class
 */
public class Gene {
    private String name;
    private HashMap<String, Integer> sequence;
    private HashMap<String, Double> percents;
    private int total;

    /**
       Constructors
     */
    public Gene() { 
	name="";
	sequence = new HashMap<String, Integer>();
	percents = new HashMap<String, Double>();
	total=0;
    }
    
    public Gene(String n) {
	this();
	name=n;
    }


    public Gene(String n, String body) {
	name=n;
	total=0;
	sequence = new HashMap<String, Integer>();
	percents = new HashMap<String, Double>();

	String s2 = body;
	for (int i = 0 ; i+3<s2.length() ; i+=3 ) {
	    String key = s2.substring(i,i+3);
	    if ( key.equals("UAA") || key.equals("UAG") || key.equals("UGA") ) {
		System.out.println("-Stop codon encountered, skipping.");
		break;
	    }
	    addCodon(key);
	    total++;
	}
	calcPercs();
    }
    
    /**
       GETTERS
    */
    
    /**
     * Name field getter
     * @return Name field
     */
    public String getName() {
	return name;
    }

    /**
     * Sequence getter
     * @return HashMap<String, Integer> sequence
     */
    public HashMap<String, Integer> getSequence() { 
	return sequence;
    }
    
    /**
     * Percents getter
     * @return HashMap<String, Double> percents
     */
    public HashMap<String, Double> getPercs() { 
	return percents;
    }

    /**
     * Calculates sum of logs for eeach codon
     * @param s Sequence to analyse
     * @return double score
     */
    public double getLogSum(String s) {
	if (s.length()<3) {
	    System.out.println("----[getLogSum] Wrong length!\n");
	    return 0;
	} else {
	    double ret = 0.0;
	    String cpy = s;
	    String tmp = "";
	    while (cpy.length()>=3) {
		tmp = cpy.substring(0,3);
		double current = 0.0;
		if (percents.get(tmp) == null) {
		    cpy = cpy.substring(3);
		    continue;
		}
		current = percents.get(tmp);
		ret += Math.log(current);
		cpy = cpy.substring(3);
	    }
	    return ret;
	}
    }
     
    /**
     * Adds a codon to the structure
     * @param key A three letter codon to be added
     */
    public void addCodon(String key) {
	if (key.length() != 3) {
	    System.out.println("----Wrong Codon length!");
	    return;
	} else {
	    total++;
	    if (sequence.get(key)==null) 
		sequence.put(key, 1);
	    else
		sequence.put(key, sequence.get(key)+1);
	}
    }

    
    /**
     * Populates 'percents'
     */
    public void calcPercs() { // Populates the 'percents' parameter.
	for (Map.Entry<String, Integer> entry : sequence.entrySet()) {
	    String n = entry.getKey();
	    Integer i = entry.getValue();
	    Double d = ((double)i)/total;
	    percents.put(n, d);
	}
    }
	
    public String toString() {
	String ret ="";
	for (Map.Entry<String, Integer> entry : sequence.entrySet()) {
	    String key = entry.getKey().toString();
	    Integer value = entry.getValue();
	    Double perc = percents.get(key);
	    ret+=("-"+key + "\t" + value + "\t"+perc+"\n");
	}
	ret+=("Total triplets: "+sequence.size());
	return ret;
    }

    /**
     * Saves file analysis to a separate file
     * @param filename Name of file to analyse, will be saved as "analysis_<<filename>>"
     */
    public void storeTripletsStats(String filename) {
	try {
	String ret = "";
	PrintWriter writer = new PrintWriter("./Data/analysis_"+filename, "UTF-8");
	
	for (Map.Entry<String, Integer> entry : sequence.entrySet()) {
	    String key = entry.getKey().toString();
	    Integer value = entry.getValue();
	    Double perc = percents.get(key);
	    writer.println(key +" "+value+" "+perc);
	}
	writer.close();
	} catch (FileNotFoundException e) {
	    System.out.println("---storeTripletsStats FAIL");
	    e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
	    System.out.println("---storeTripletsStats FAIL");
	    e.printStackTrace();
	}
    }
}
