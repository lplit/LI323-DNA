package DNAReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Guesser {
    
    Reader[] r;
    String toGuess;

    public Guesser(Reader[] r, String text) {
	this.r = r;
	toGuess = text;
    }

    public void guessGenome() {
	double scoreHigh=-99999;
	String langHigh = "";
	for (Reader a : r) {
	    String lang = a.getLang();
	    double score=0.0;
	    // Simplicity handles
	    double [] percs = a.getPercOf();
	    char[] txt = toGuess.toCharArray();
	    // All teh charz
	    for (char c : txt) {
		double d = a.getLetterPerc(c);
		score+= Math.log(d);
	    }
	    System.out.println("Score "+score+"\tfor"+lang);
	    //Update if
	    if (score > scoreHigh) {
		scoreHigh=score;
		langHigh=lang;
		//System.out.println("New high for "+lang+" with score: "+score);
	    }
	}
	System.out.println("Highest score : "+scoreHigh+" for lang : "+langHigh);
    }
}