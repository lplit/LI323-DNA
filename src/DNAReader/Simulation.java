package DNAReader;

import java.util.ArrayList;

public class Simulation { 

    ArrayList<String> simuls;
    final static String[] words = {"ATCTG", "ATATAT", "AAAAA"};


    /**
     * Constructor
     * @param nb Nb of random sequences
     * @param Sequence length
     */    
    public Simulation (int nb, int len) {
	simuls = new ArrayList<String>();
	for (int i = 0 ; i<nb ; i++) {
	    if (Math.random() < 0.5) 
		simuls.add(randomSeq(len, 1));
	    else 
		simuls.add(randomSeq(len, 2));
	}
    }
    
    /**
     * User in constructor. Will create chain of length len.
     * @param len length of DNA chain to be generated
     * @param type Can be either 1 or 2. First is equiprobable, second with 
     * predefined probabilities: G 0.1, C 0.1, T 0.4, A 0.4
     */
    public String randomSeq(int len, int type) {
	String chain="";
	if (type==1) {
	    for (int i = 0 ; i<len ; i++) {
		double ran = Math.random();
		if (ran<0.25) 
		    chain+='A';
		else if (ran<0.5)
		    chain+='T';
		else if (ran<0.75)
		    chain+='C';
		else 
		    chain+='G';
	    }
	} else if (type==2) {
	    for (int i = 0 ; i<len ; i++) {
		double ran = Math.random();
		if (ran<0.1) 
		    chain+='G';
		else if (ran<0.2)
		    chain+='C';
		else if (ran<0.6)
		    chain+='T';
		else 
		    chain+='A';
	    }
	}
	return chain;
    }

    public int nbOcc(String word, String body) { 
	int occ=0,
	    wl = word.length();
	String cpy=body;
	for (int i = 0 ; i+wl < body.length() ; i+=word.length() ) {
	    String comp = cpy.substring(0,wl);
	    if (word.equals(comp)) occ++;
	    cpy=cpy.substring(wl);
	}
	return occ;
    }

    public void simulate() {
	int probEmp=0;
	for (String s : words) {
	    for (int i = 0 ; (i+1)<simuls.size() ; i++) {
		int nocc = nbOcc(s, simuls.get(i));
		int n2occ = nbOcc(s, simuls.get(i+1));
		if (n2occ > nocc) probEmp++;
	    }
	    System.out.println("ProbEmp "+s+" "+probEmp);
	    probEmp=0;
	}
    }
}
