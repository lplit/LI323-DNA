package DNAReader;

/**
 * Main class
 */
public class DNA {

    final static int nbTests=3;

    public static void main(String[] args) {

	double[] 
	    results_17=new double[nbTests],
	    results_18=new double[nbTests],
	    results_19=new double[nbTests];

	GeneReader
	    gr1 = new GeneReader("NC_017626_genes.fna"),
	    gr2 = new GeneReader("NC_018520_genes.fna"),
	    gr3 = new GeneReader("NC_019896_genes.fna");

	Reader 
	    r1 = new Reader("NC_017626.fna"),
	    r2 = new Reader("NC_018520.fna"),
	    r3 = new Reader("NC_019896.fna");

	double bestgr1, bestgr2, bestgr3, bestr1, bestr2, bestr3;


	for (int j = 0 ; j<nbTests ; j++) {
	    System.out.println("************************************************\n"+
			       "OFFSET: "+j+"\n"+
			       "************************************************\n");
	    
	    
	    /** Extraits */
	    System.out.println("NC_017626_genes.fna");
	    bestgr1 = gr1.getBestGeneFile("test_17626.fna", j);

	    System.out.println("\n\nNC_018520_genes.fna");
	    bestgr2 = gr2.getBestGeneFile("test_18520.fna", j);

	    System.out.println("\n\nNC_019896_genes.fna");
	    bestgr3 = gr3.getBestGeneFile("test_19896.fna", j);

	
	    /** Whole files */
	    // r1.printLettersStats();
	    // r2.printLettersStats();
	    // r3.printLettersStats();
	
	    bestr1 = r1.getBestGeneFile("test_17626.fna", j);
	    System.out.println();
	    bestr2 = r2.getBestGeneFile("test_18520.fna", j);
	    System.out.println();
	    bestr3 = r3.getBestGeneFile("test_19896.fna", j);
	
	    System.out.println("\nr1: "+r1.getLang());
	    System.out.println("r2: "+r2.getLang());
	    System.out.println("r3: "+r3.getLang());
	
	    System.out.println("\ngr1: NC_017626_genes.fna");
	    System.out.println("gr2: NC_018520_genes.fna");
	    System.out.println("gr3: NC_019896_genes.fna");
	
	    System.out.println("\ngr1: "+bestgr1+" gr2: "+bestgr2+" gr3: "+bestgr3);
	    System.out.println("r1: "+bestr1+" r2: "+bestr2+" r3: "+bestr3);
	
	    Double[] languages = {bestr1, bestr2, bestr3};
	    Double[] bits = {bestgr1, bestgr2, bestgr3};
	    Double[] divs = new Double[languages.length];

	    for (int i = 0 ; i < languages.length ; i++) {
		divs[i]=bits[i]/languages[i];
		System.out.println("gr[i]/r[i] i: "+i+" "+divs[i]);
		switch (i) {
		case 0:
		    results_17[j]=divs[i];
		    break;
		case 1: 
		    results_18[j]=divs[i];
		    break;
		case 2:
		    results_19[j]=divs[i];
		    break;
		}
	    }
	    
	    System.out.println("************************************************\n"+
			       "END OF TEST FOR OFFSET: "+j+"\n"+
			       "************************************************\n");
	}
	
	double top1 = getMaxArray(results_17);
	double top2 = getMaxArray(results_18);
	double top3 = getMaxArray(results_19);

	System.out.println("Max 017626: "+ top1);
	System.out.println("Max 018520: "+ top2);
	System.out.println("Max 019896: "+ top3);

	int step = 90;
	
	/* MASSIVELY slow */
	//r1.analyseByXChar(step, "NC_017626.fna", top1, gr1);
	//r2.analyseByXChar(step, "NC_018520.fna", top2, gr2);
	//r3.analyseByXChar(step, "NC_019896.fna", top3, gr3);

	/** PARTIE 3 **/

	Simulation s = new Simulation(100, 10000);
	s.simulate();
	
	return;
    }
    
    public static double getMaxArray(double[] arr) {
	if (arr.length == 0) {
	    System.out.println("[DNA$getMaxArray]Empty array!");
	    return 0.;
	} else {
	    double ret = arr[0];
	    for (double d : arr)
		if (ret < d) ret=d;
	    return ret;
	}
    }
}
