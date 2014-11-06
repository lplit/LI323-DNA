package DNAReader;

public class DNA {

    public static void main(String[] args) {

	String[] tester = {"AGCAGGACGCGCGGCGCGTGGCGGCGGCCAACATCATGAGCGATGTTGAGCTGGTGCGGGATCAGGCTGGTACGCTGACCGCGCAGGCCGCTGTACGGCATACGGAACTGTTCGCTGGAATACTTACGCGCGCGATAACG", "GGGTTGTAAGCATCGTGCTGAGCTTCGGCATAACGCTCATCATCAAACAGCGCAAAGCGCAGGTTGAAACGGCGCAGCATTTCTACCAGCCACTGATGCTGTAAGGTTTCCGGGACGATAATCAGCACACGTTCAGCAGCGCCAGAGAGCAGTTGCTGATGAAGGATCATCCCGGCTTCAATGGTTTTCCCTAAACCCACTTCGTCAGCCAGCAGGACGCGCGGCGCGTGGCGGCGGCCAACATCATGAGCGATGTTGAGCTGGTGCGGGATCAGGCTGGTACGCTGACCGCGCAGGCCGCTGTACGGCATACGGAACTGTTCGCTGGAATACTTACGCGCGCGATAACG", "GTCGCGGTCAGCAGCAGAACGCCCGGCACGTGCTCTGCCAGTTGTTCAATGGCCTGATATTCACGGCTTGGCGCATCTTCGCTCCACACCAGGTGATGGGCTTCATCGACCACCAGCAGGTCCCATTCGGCTTCACAGAGATGTTCCAGCCGCTGTTTGCTACGACGGGCAAAATCCAGCGAGCAAATCACCAGCTGTTCGGTGTCGAACGGGTTGTAAGCATCGTGCTGAGCTTCGGCATAACGCTCATCATCAAACAGCGCAAAGCGCAGGTTGAAACGGCGCAGCATTTCTACCAGCCACTGATGCTGTAAGGTTTCCGGGACGATAATCAGCACACGTTCAGCAGCGCCAGAGAGCAGTTGCTGATGAAGGATCATCCCGGCTTCAATGGTTTTCCCTAAACCCACTTCGTCAGCCAGCAGGACGCGCGGCGCGTGGCGGCGGCCAACATCATGAGCGATGTTGAGCTGGTGCGGGATCAGGCTGGTACGCTGACCGCGCAGGCCGCTGTACGGCATACGGAACTGTTCGCTGGAATACTTACGCGCGCGATAACG", "GTTGAAGTGAAAGTCATCAACGTTGAAAAAGACGGGAAAATTGGTTTATCTATTAAAAAAGCTAAAGACCGTCCGCAAGCCAGACCTAGAAATGATTTCCGTCCGAAAGAATCTTTTGAACAGAAAATGAATAAGTTTTT", "TAAGCAAACGATCTTTTTATAAGCCTAAGGAGGAGCACTTTTTTTATGTCGATTGAAGTTGGCAGCAAGTTGCAAGGGAAAATTACAGGTATTACAAATTTTGGAGCATTTGTTGAATTGCCTGGAGGCTCAACCGGTCTCGTTCACATTAGTGAGGTAGCTGATAATTATGTCAAAGACATTAACGACCACTTAAAAGTCGGCGACCAAGTTGAAGTGAAAGTCATCAACGTTGAAAAAGACGGGAAAATTGGTTTATCTATTAAAAAAGCTAAAGACCGTCCGCAAGCCAGACCTAGAAATGATTTCCGTCCGAAAGAATCTTTTGAACAGAAAATGAATAAGTTTTT", "AAAGAAAGAACAGCTTGAAAAAGAACTGAAAAGTTTAAAGACAAAACAAACGGATTTAAAAGAAGAAATATCCAAATTGAAGGATGAGGATTACGTCACAGAGCTTGCCAGAAGGGACTTATTCATGTCTGGAGACGGAGAAATTATCTTCAATGTGGAGAAGAAGAGCAAGTAGCCTTGTTGACACTTAAATTTTTATTTAGGTATAATTAAGCAAACGATCTTTTTATAAGCCTAAGGAGGAGCACTTTTTTTATGTCGATTGAAGTTGGCAGCAAGTTGCAAGGGAAAATTACAGGTATTACAAATTTTGGAGCATTTGTTGAATTGCCTGGAGGCTCAACCGGTCTCGTTCACATTAGTGAGGTAGCTGATAATTATGTCAAAGACATTAACGACCACTTAAAAGTCGGCGACCAAGTTGAAGTGAAAGTCATCAACGTTGAAAAAGACGGGAAAATTGGTTTATCTATTAAAAAAGCTAAAGACCGTCCGCAAGCCAGACCTAGAAATGATTTCCGTCCGAAAGAATCTTTTGAACAGAAAATGAATAAGTTTTT", "TGCCGCCGTGGGCTTGTTCAAACAGGCCGGGCTGGTCGACAGCGCCGGTAAACGCGCCTTTTTTCGTTCCGAACAAGATGCTTTCCACAAGGCTGTCTGGCAGTGCCGCACAGTTTTGCGAAATAAACGGGCCGCCAGAA", "TGACGACACTCAGCCTGTAATACAAGTCCTTCCGCATTCGTTCGCCTGCAATTGCATCAATTGGGTCTTCATTCATCGTGGCAATAATACGTACGTCAATCGGTGTATCCTTTGTCGAACCGATGCGCCTGATTTTCCGTTCTTGAAGGGCGCGCAGCAGCTTCGCCTGAAGGCTGAGATTCAGCGAGTTGATTTCATCTAACAGCAGCGTGCCGCCGTGGGCTTGTTCAAACAGGCCGGGCTGGTCGACAGCGCCGGTAAACGCGCCTTTTTTCGTTCCGAACAAGATGCTTTCCACAAGGCTGTCTGGCAGTGCCGCACAGTTTTGCGAAATAAACGGGCCGCCAGAA", "GCTCGTCCGTCATGAAATTCATTGCGCCTTCGATCATATGCTCAAGCTCGCGAATATTTCCCGGCCAGTCATAAGAAAGGAAAAATTGCTTCACGTCATCGCTGATGTGTTCAACATTCATTTGGAATAGATGATTGTTTTTCTGGATGAACTCCGACGCGAGAAGCAAAATGTCTTCTTTTCGTTCCCGAAGCGGCGGGATGATCAGTGTGACGACACTCAGCCTGTAATACAAGTCCTTCCGCATTCGTTCGCCTGCAATTGCATCAATTGGGTCTTCATTCATCGTGGCAATAATACGTACGTCAATCGGTGTATCCTTTGTCGAACCGATGCGCCTGATTTTCCGTTCTTGAAGGGCGCGCAGCAGCTTCGCCTGAAGGCTGAGATTCAGCGAGTTGATTTCATCTAACAGCAGCGTGCCGCCGTGGGCTTGTTCAAACAGGCCGGGCTGGTCGACAGCGCCGGTAAACGCGCCTTTTTTCGTTCCGAACAAGATGCTTTCCACAAGGCTGTCTGGCAGTGCCGCACAGTTTTGCGAAATAAACGGGCCGCCAGAA"};


	Reader r1 = new Reader("NC_017626.fna");
	r1.printLettersStats();

	Reader r2 = new Reader("NC_018520.fna");
	r2.printLettersStats();

	Reader r3 = new Reader("NC_019896.fna");
	r3.printLettersStats();

	Reader[] readers = {r1, r2, r3};

	GeneReader gr = new GeneReader("NC_018520_genes.fna");

	GeneReader gr2 = new GeneReader("NC_017626_genes.fna");

	GeneReader gr3 = new GeneReader("NC_019896_genes.fna");

	System.out.println("NC_018520_genes.fna");
	gr.getBestGeneFile("test_genome.fna");

	System.out.println("\n\nNC_017626_genes.fna");
	gr2.getBestGeneFile("test_genome.fna");

	System.out.println("\n\nNC_019896_genes.fna");
	gr3.getBestGeneFile("test_genome.fna");

	return;
    }

}
