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
	    String tmp_name="";
	    String tmp_body="";

	    while ((sCurrentLine = br.readLine()) != null) {
		char[] chr = sCurrentLine.toCharArray();
		
		if (chr[0]=='>') { // Reads the name
		    tmp_name = sCurrentLine;
		} else { // If not name, concatenate line
		    tmp_body+=sCurrentLine;
		}
		Gene g = new Gene(tmp_name, tmp_body);
		genes.add(g); // Add current genome to the list
		tmp_body=""; // Reset to empty for next iteration
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

	public Gene(String n, String b) {
	    name=n;
	    body=b;
	    sequence=new HashMap<String, Integer>();

	    String s2 = body;
	    while (s2.length() >=3) {
		String key = s2.substring(0,3);
		addCodon(key);
		s2=s2.substring(3);
	    }
	}

	public String getName() {
	    return name;
	}
	
	public String getBody() {
	    return body;
	}

	public HashMap<String, Integer> getCodons() {
	    return sequence;
	}

	public void addCodon(String key) {
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
	
	public String toString() {
	    return (name+"\n"+body);
	}

	public void printTriplets() {
	    for (Map.Entry<String, Integer> entry : sequence.entrySet()) {
		String key = entry.getKey().toString();
		Integer value = entry.getValue();
		System.out.println("---"+key + "\t" + value);
	    }
	    System.out.println("Total triplets: "+sequence.size());
	}
    }
}
