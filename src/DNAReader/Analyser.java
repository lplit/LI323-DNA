package DNAReader;

public class Analyser { 
    
    public double calcProbSeq(String seq, Reader r) { 
	char[]chartmp=seq.toCharArray();
	double prob = 1.0;
	for (int i = 0 ; i<chartmp.length ; i++) {
	    prob*=r.getLetterPerc(chartmp[i]);
	}
	return prob;
    }


    public double calcProbSeqLog(String seq, Reader r) { 
	char[]chartmp=seq.toCharArray();
	double prob = 1.0;
	for (int i = 0 ; i<chartmp.length ; i++) {
	    prob+=Math.log(r.getLetterPerc(chartmp[i]));
	}
	return prob;
    }


    public static void guessSpecies(Reader[] tab, String seq) {
	String guessedLanguage="";
	double prob=0.0;
	char[]chars = seq.toCharArray();
	for (Reader r : tab) {
	    double tmp = calcProbSeq(seq, r);
	    if ( tmp > prob ) { 
		prob = tmp;
		guessedLanguage = r.getLang();
	    }
	}
	System.out.println("This sequence's species is: "+guessedLanguage+" probability: "+prob+"%\n");
    }
}
