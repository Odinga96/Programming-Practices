import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Code for benchmarking the time taken to simulate cutting
 * and splicing strands of DNA. These benchmark methods are
 * intended to be used in reasoning about tradeoffs in using 
 * a linked list to represent a strand of DNA and to compare
 * this representation with a simple String representation.
 * @author Owen Astrachan
 * @date 2/11/2009
 */

import javax.swing.JFileChooser;

public class DNABenchMark {

    protected static JFileChooser ourChooser = new JFileChooser(System
            .getProperties().getProperty("user.dir"));
    private static String mySource;

    /**
     * Return a string representing the DNA read from the scanner, ignoring
     * any characters can't be part of DNA and converting all characters to
     * lower case.
     * @param s is the Scanner read from
     * @return a string representing the DNA read, characters in the returned
     * string are restricted to 'c', 'g', 't', 'a'
     */
    public static String dnaFromScanner(Scanner s) {
        StringBuilder buf = new StringBuilder();
        while (s.hasNextLine()) {
            String line = s.nextLine().toLowerCase();


            for (int k = 0; k < line.length(); k++) {
                char ch = line.charAt(k);

                if ("acgt".indexOf(ch) != -1) {
                    buf.append(ch);
                }
            }
        }
        return buf.toString();
    }

    public static String strandSpliceBenchmark(String enzyme, String splicee,
            String className) throws InstantiationException,
            IllegalAccessException {

        String dna = mySource;
        IDnaStrand strand;

        try {
            strand = (IDnaStrand) Class.forName(className).newInstance();
            strand.initializeFrom(dna);
            long length = strand.size();

            double start = System.currentTimeMillis();
            IDnaStrand recomb = strand.cutAndSplice(enzyme, splicee);

            long length2 = strand.size();
            long recLength = recomb.size();
            double end = System.currentTimeMillis();

            double time = (end-start)/1000.0;

            String ret = String.format(
            		"%s:\t splicee %,d\t time %1.3f\tbefore %,d\tafter %,d\trecomb %,d",
            		className,splicee.length(),time,length,length2,recLength);

            return ret;
        } catch (ClassNotFoundException e) {
            return "could not create class " + className;
        }
    }

    public static void main(String[] args) throws FileNotFoundException,
            InstantiationException, IllegalAccessException,
            ClassNotFoundException {
        int retval = ourChooser.showOpenDialog(null);
        if (retval == JFileChooser.APPROVE_OPTION) {
            File file = ourChooser.getSelectedFile();
            mySource = dnaFromScanner(new Scanner(file));
            
            System.out.println("dna length = " + mySource.length());
            String enzyme = "gaattc";

            for(int j=8; j <= 32; j++) {
	            StringBuilder b = new StringBuilder("");
	            int spSize = (1 << j);


	            for (int k = 0; k < spSize; k++) {
	                b.append("c");
	            }

	            String splicee = b.toString();

	            String results = strandSpliceBenchmark(enzyme, splicee,"LinkStrand");
	            System.out.println(j+"\t"+results);

            }

        }
        System.exit(0);
    }
}
