import java.util.Arrays;

/**
 * Simple but inefficient implementation of IDnaStrand. This implementation uses
 * Strings to represent genomic/DNA data. A StringBuilder is used where
 * appropriate for concatenation efficency.
 * @author ola
 * @date January 2008, modified and commented September 2008
 */

public class SimpleStrand implements IDnaStrand {
    private String myInfo;
    public static SimpleStrand EMPTYSTRAND = new SimpleStrand();

    /**
     * Create a strand representing an empty DNA strand, length of zero.
     */
    public SimpleStrand() {
        this("");
    }

    /**
     * Create a strand representing s. No error checking is done to see if s represents
     * valid genomic/DNA data.
     * @param s is the source of cgat data for this strand
     */
    public SimpleStrand(String s) {
        myInfo = s;
    }

    /**
     * Perform a cut with the enzyme as the source of the location for the cut. The strand
     * before the enzyme-cut becomes this strand (if no enzyme found, no cut is made). The strand
     * after the enzyme-cut is returned as a newly created Strand by this method. The first
     * occurrence of enzyme is the source of the cut being made by this method.
     * @param enzyme is the enzyme/genome at which a cut is made.
     */
    public IDnaStrand cutWith(String enzyme) {

        int pos = myInfo.indexOf(enzyme);
        if (pos == -1)
            return EMPTYSTRAND;

        // found occurrence of enzyme, cleave, and replace
        IDnaStrand ret = new SimpleStrand(myInfo.substring(pos + enzyme.length()));
        initializeFrom(myInfo.substring(0, pos));
        return ret;
    }


    public IDnaStrand cutAndSplice(String enzyme, String splicee){
    	String toSplit = " "+myInfo+" ";
    	String[] all = toSplit.split(enzyme);


    	if (all[0].trim().equals(myInfo)){
    		return EMPTYSTRAND;
    	}
    	StringBuilder recombined = new StringBuilder(all[0]);
    	for (int k = 1; k < all.length; k++) {
    		recombined.append(splicee);
    		recombined.append(all[k]);
    	}

    	return new SimpleStrand(recombined.toString().trim());
    }

    /**
     * Initialize this strand so that it represents <code>source</code>, no error
     * checking is done.
     * @param source is the source of this enzyme
     */
    public void initializeFrom(String source) {
        myInfo = source;
    }

    /**
     * Returns the number of nucleotides/base-pairs in this strand.
     * @return the number of base-pairs in this IDnaStrand
     */
    public long size() {
        return myInfo.length();
    }

    @Override
    public String toString() {
        return myInfo.toString();
    }

    public String strandInfo() {
        return this.getClass().getName();
    }

    /**
     * Append a strand of DNA to this strand. If the strand being appended
     * is represented by a SimpleStrand object then an efficient append is
     * performed, otherwise the append is inefficient (even though it doesn't have to be).
     * @param dna is the strand being appended
     */
    public IDnaStrand append(IDnaStrand dna) {
        if (dna instanceof SimpleStrand) {
            SimpleStrand ss = (SimpleStrand) dna;
            StringBuilder sb = new StringBuilder(myInfo);
            sb.append(ss.myInfo);
            myInfo = sb.toString();
            return this;
        } else {
            return append(dna.toString());
        }
    }

    /**
     * Simply append a strand of dna data to this strand. No error checking is done, this
     * method isn't efficient, it doesn't use a StringBuilder or a StringBuffer
     * @param dna is the String appended to this strand
     */
    public IDnaStrand append(String dna) {
        myInfo += dna;
        return this;
    }
}
