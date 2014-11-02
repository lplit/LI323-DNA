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
	// Reads gene by gene from file and creates hashmap with 3 letter occurences
	
	try {
    
	    genes = new ArrayList<Gene>();
	    BufferedReader br = new BufferedReader(new FileReader("./Data/"+file));
	    String sCurrentLine;
	    String tmp = br.readLine();
	    String tmp_name="";
	    String tmp_body="";

	    while ((sCurrentLine = br.readLine()) != null) {
		char[] chr = sCurrentLine.toCharArray();
		
		// Reads the name
		if (chr[0]=='>') {
		    tmp_name = sCurrentLine;
		} else { // If not name, concatenate line
		    tmp_body+=sCurrentLine;
		}
		Gene g = new Gene(tmp_name, tmp_body);
		genes.add(g); // 
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
    
    public class Gene {
	private String name;
	private String body;
	private HashMap<String, Integer> sequence;

	/**
	   Initialize hashmap, then for each triletters do .put(Key, Value+1);
	*/

	public Gene(String n, String b) {
	    name=n;
	    body=b;
	    sequence=new HashMap();
	}	

	public String getName() {
	    return name;
	}
	
	public String getBody() {
	    return body;
	}

	public Map<String, Integer> getCodons() {
	    return sequence;
	}

	public void addCodon(String key) {
	 
	    if (key.length() != 3)
		System.out.println("Wrong Codon length!");
	    else {
		int value = -10;
		value=sequence.get(key);
		if (value==-10)
		    sequence.put(key, 1);
		else
		    sequence.put(key, value+1);
	    }
	}
	
	public String toString() {
	    return (name+"\n"+body);
	}
    
    }

}