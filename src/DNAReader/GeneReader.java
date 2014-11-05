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
	    String tmp = br.readLine();


	    while ((sCurrentLine = br.readLine()) != null) { // [>name \n body]
		char[] chr = sCurrentLine.toCharArray();
		String tmp_name="";
		String tmp_body="";
		if (chr[0]=='>') { // Reads the name
		    tmp_name = sCurrentLine;
		    sCurrentLine = br.readLine(); // To get to the body
		    tmp_body+=sCurrentLine;
		} else { // If not name skip to next line
		    continue;
		}
		Gene g = new Gene(tmp_name, tmp_body);
		genes.add(g); // Add current genome to the list

	    }
	} catch (IOException e) {
	    System.out.println("File read error!\n");
	    e.printStackTrace();
	}
    }
    
    public ArrayList<Gene> getGenes() {
	return genes;
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
    
    public class Gene {
	private String name;
	private String body;
	private HashMap<String, Integer> sequence;
	private HashMap<String, Double> percents;
	private int total;

	public Gene(String n, String b) {
	    name=n;
	    body=b;
	    total=0;
	    sequence=new HashMap<String, Integer>();
	    percents = new HashMap<String, Double>();


	    String s2 = body;
	    while (s2.length() >=3) {
		String key = s2.substring(0,3);
		if ( key.equals("UAA") || key.equals("UAG") || key.equals("UGA") ) {
		    System.out.println("Stop codon encountered, skipping.");
		    continue;
		}
		addCodon(key);
		total++;
		s2=s2.substring(3);
	    }
	    calcPercs();
	}

	public String getName() {
	    return name;
	}
	
	public String getBody() {
	    return body;
	}

	public HashMap<String, Integer> getSequence() { 
	    return sequence;
	}

	public HashMap<String, Double> getPercs() { 
	    return percents;
	}

	public HashMap<String, Integer> getCodons() {
	    return sequence;
	}

	private void addCodon(String key) {
	    if (key.length() != 3) {
		System.out.println("Wrong Codon length!");
		return;
	    } else {
		if (sequence.get(key)==null) 
		    sequence.put(key, 1);
		else
		    sequence.put(key, sequence.get(key)+1);
	    }
	}


	private void calcPercs() { // Populates the 'percents' parameter.
	    for (Map.Entry<String, Integer> entry : sequence.entrySet()) {
		String n = entry.getKey();
		Integer i = entry.getValue();
		Double d = ((double)i)/total;
		percents.put(n, d);
	    }
	}
	
	public String toString() {
	    return (name+"\n"+body);
	}

	public void printTriplets() {
	    for (Map.Entry<String, Integer> entry : sequence.entrySet()) {
		String key = entry.getKey().toString();
		Integer value = entry.getValue();
		Double perc = percents.get(key);
		System.out.println("---"+key + "\t" + value + "\t"+perc);
	    }
	    System.out.println("Total triplets: "+sequence.size());
	}
    }
}
