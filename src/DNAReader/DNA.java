package DNAReader;

/**
 * Main class
 */
public class DNA {

    public static void main(String[] args) {

	String[] tester = {"AGCAGGACGCGCGGCGCGTGGCGGCGGCCAACATCATGAGCGATGTTGAGCTGGTGCGGGATCAGGCTGGTACGCTGACCGCGCAGGCCGCTGTACGGCATACGGAACTGTTCGCTGGAATACTTACGCGCGCGATAACG", "GGGTTGTAAGCATCGTGCTGAGCTTCGGCATAACGCTCATCATCAAACAGCGCAAAGCGCAGGTTGAAACGGCGCAGCATTTCTACCAGCCACTGATGCTGTAAGGTTTCCGGGACGATAATCAGCACACGTTCAGCAGCGCCAGAGAGCAGTTGCTGATGAAGGATCATCCCGGCTTCAATGGTTTTCCCTAAACCCACTTCGTCAGCCAGCAGGACGCGCGGCGCGTGGCGGCGGCCAACATCATGAGCGATGTTGAGCTGGTGCGGGATCAGGCTGGTACGCTGACCGCGCAGGCCGCTGTACGGCATACGGAACTGTTCGCTGGAATACTTACGCGCGCGATAACG", "GTCGCGGTCAGCAGCAGAACGCCCGGCACGTGCTCTGCCAGTTGTTCAATGGCCTGATATTCACGGCTTGGCGCATCTTCGCTCCACACCAGGTGATGGGCTTCATCGACCACCAGCAGGTCCCATTCGGCTTCACAGAGATGTTCCAGCCGCTGTTTGCTACGACGGGCAAAATCCAGCGAGCAAATCACCAGCTGTTCGGTGTCGAACGGGTTGTAAGCATCGTGCTGAGCTTCGGCATAACGCTCATCATCAAACAGCGCAAAGCGCAGGTTGAAACGGCGCAGCATTTCTACCAGCCACTGATGCTGTAAGGTTTCCGGGACGATAATCAGCACACGTTCAGCAGCGCCAGAGAGCAGTTGCTGATGAAGGATCATCCCGGCTTCAATGGTTTTCCCTAAACCCACTTCGTCAGCCAGCAGGACGCGCGGCGCGTGGCGGCGGCCAACATCATGAGCGATGTTGAGCTGGTGCGGGATCAGGCTGGTACGCTGACCGCGCAGGCCGCTGTACGGCATACGGAACTGTTCGCTGGAATACTTACGCGCGCGATAACG", "GTTGAAGTGAAAGTCATCAACGTTGAAAAAGACGGGAAAATTGGTTTATCTATTAAAAAAGCTAAAGACCGTCCGCAAGCCAGACCTAGAAATGATTTCCGTCCGAAAGAATCTTTTGAACAGAAAATGAATAAGTTTTT", "TAAGCAAACGATCTTTTTATAAGCCTAAGGAGGAGCACTTTTTTTATGTCGATTGAAGTTGGCAGCAAGTTGCAAGGGAAAATTACAGGTATTACAAATTTTGGAGCATTTGTTGAATTGCCTGGAGGCTCAACCGGTCTCGTTCACATTAGTGAGGTAGCTGATAATTATGTCAAAGACATTAACGACCACTTAAAAGTCGGCGACCAAGTTGAAGTGAAAGTCATCAACGTTGAAAAAGACGGGAAAATTGGTTTATCTATTAAAAAAGCTAAAGACCGTCCGCAAGCCAGACCTAGAAATGATTTCCGTCCGAAAGAATCTTTTGAACAGAAAATGAATAAGTTTTT", "AAAGAAAGAACAGCTTGAAAAAGAACTGAAAAGTTTAAAGACAAAACAAACGGATTTAAAAGAAGAAATATCCAAATTGAAGGATGAGGATTACGTCACAGAGCTTGCCAGAAGGGACTTATTCATGTCTGGAGACGGAGAAATTATCTTCAATGTGGAGAAGAAGAGCAAGTAGCCTTGTTGACACTTAAATTTTTATTTAGGTATAATTAAGCAAACGATCTTTTTATAAGCCTAAGGAGGAGCACTTTTTTTATGTCGATTGAAGTTGGCAGCAAGTTGCAAGGGAAAATTACAGGTATTACAAATTTTGGAGCATTTGTTGAATTGCCTGGAGGCTCAACCGGTCTCGTTCACATTAGTGAGGTAGCTGATAATTATGTCAAAGACATTAACGACCACTTAAAAGTCGGCGACCAAGTTGAAGTGAAAGTCATCAACGTTGAAAAAGACGGGAAAATTGGTTTATCTATTAAAAAAGCTAAAGACCGTCCGCAAGCCAGACCTAGAAATGATTTCCGTCCGAAAGAATCTTTTGAACAGAAAATGAATAAGTTTTT", "TGCCGCCGTGGGCTTGTTCAAACAGGCCGGGCTGGTCGACAGCGCCGGTAAACGCGCCTTTTTTCGTTCCGAACAAGATGCTTTCCACAAGGCTGTCTGGCAGTGCCGCACAGTTTTGCGAAATAAACGGGCCGCCAGAA", "TGACGACACTCAGCCTGTAATACAAGTCCTTCCGCATTCGTTCGCCTGCAATTGCATCAATTGGGTCTTCATTCATCGTGGCAATAATACGTACGTCAATCGGTGTATCCTTTGTCGAACCGATGCGCCTGATTTTCCGTTCTTGAAGGGCGCGCAGCAGCTTCGCCTGAAGGCTGAGATTCAGCGAGTTGATTTCATCTAACAGCAGCGTGCCGCCGTGGGCTTGTTCAAACAGGCCGGGCTGGTCGACAGCGCCGGTAAACGCGCCTTTTTTCGTTCCGAACAAGATGCTTTCCACAAGGCTGTCTGGCAGTGCCGCACAGTTTTGCGAAATAAACGGGCCGCCAGAA", "GCTCGTCCGTCATGAAATTCATTGCGCCTTCGATCATATGCTCAAGCTCGCGAATATTTCCCGGCCAGTCATAAGAAAGGAAAAATTGCTTCACGTCATCGCTGATGTGTTCAACATTCATTTGGAATAGATGATTGTTTTTCTGGATGAACTCCGACGCGAGAAGCAAAATGTCTTCTTTTCGTTCCCGAAGCGGCGGGATGATCAGTGTGACGACACTCAGCCTGTAATACAAGTCCTTCCGCATTCGTTCGCCTGCAATTGCATCAATTGGGTCTTCATTCATCGTGGCAATAATACGTACGTCAATCGGTGTATCCTTTGTCGAACCGATGCGCCTGATTTTCCGTTCTTGAAGGGCGCGCAGCAGCTTCGCCTGAAGGCTGAGATTCAGCGAGTTGATTTCATCTAACAGCAGCGTGCCGCCGTGGGCTTGTTCAAACAGGCCGGGCTGGTCGACAGCGCCGGTAAACGCGCCTTTTTTCGTTCCGAACAAGATGCTTTCCACAAGGCTGTCTGGCAGTGCCGCACAGTTTTGCGAAATAAACGGGCCGCCAGAA"};


	
	/** Extraits */
	GeneReader gr = new GeneReader("NC_018520_genes.fna");
	System.out.println("\n\nNC_018520_genes.fna");
	gr.getBestGeneFile("test_genome.fna");

	GeneReader gr2 = new GeneReader("NC_017626_genes.fna");
	System.out.println("\n\nNC_017626_genes.fna");
	gr2.getBestGeneFile("test_genome.fna");

	GeneReader gr3 = new GeneReader("NC_019896_genes.fna");
	System.out.println("\n\nNC_019896_genes.fna");
	gr3.getBestGeneFile("test_genome.fna");
	
	
	/** Whole files */
	Reader r1 = new Reader("NC_017626.fna");
	r1.printLettersStats();

	Reader r2 = new Reader("NC_018520.fna");
	r2.printLettersStats();

	Reader r3 = new Reader("NC_019896.fna");
	r3.printLettersStats();
	
	/** put this inside a loop that goes through test names and gets corresponding reader */
	double r1d = r1.getBestGeneFile("test_17626.fna");
	System.out.println();
	double r2d = r2.getBestGeneFile("test_18520.fna");
	System.out.println();
	double r3d = r3.getBestGeneFile("test_19896.fna");

	System.out.println("r1: "+r1d+" r2: "+r2d+" r3: "+r3d);
	Reader[] readers = {r1, r2, r3}; // Languages - 17-18-19

	return;
    }

}
