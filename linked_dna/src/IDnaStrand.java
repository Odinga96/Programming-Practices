/**
 * Interface for DNA/strand experiments
 * 
 * @author Owen Astrachan
 * @date February, 2008
 */
public interface IDnaStrand {

    /**
     * Simulate a restriction enzyme cut by finding the first occurrence of
     * enzyme in this strand and replacing this strand with what comes before
     * the enzyme while returning a new strand representing what comes after the
     * enzyme. If the enzyme isn't found, this strand is unaffected and an empty
     * strand with size equal to zero is returned.
     * 
     * @param enzyme is the string being searched for
     * @return the part of the strand that comes after the enzyme
     */
    public IDnaStrand cutWith(String enzyme);
    
    
    /**
     * Cut this strand at every occurrence of enzyme, essentially replacing
     * every occurrence of enzyme with splicee.
     * @param enzyme is the pattern/strand searched for and replaced
     * @param splicee is the pattern/strand replacing each occurrence of enzyme
     * @return the new strand leaving the original strand unchanged.
     */
    public IDnaStrand cutAndSplice(String enzyme, String splicee);

    /**
     * Returns the number of elements/base-pairs/nucleotides in this strand.
     * @return the number of base-pairs in this strand
     */
    public long size();

    /**
     * Initialize by copying DNA data from the string into this strand,
     * replacing any data that was stored. The parameter should contain only
     * valid DNA characters, no error checking is done by the this method.
     * 
     * @param source is the string used to initialize this strand
     */
    public void initializeFrom(String source);

    /**
     * Return some string identifying this class.
     * @return a string representing this strand and its characteristics
     */
    public String strandInfo();

    /**
     * Appends the parameter to this strand changing this strand to represent
     * the addition of new characters/base-pairs, e.g., changing this strand's
     * size.
     * <P>
     * If possible implementations should take advantage of optimizations
     * possible if the parameter is of the same type as the strand to which data
     * is appended.
     * 
     * @param dna is the strand being appended
     * @return this strand after the data has been added
     */
    public IDnaStrand append(IDnaStrand dna);

    /**
     * Similar to append with an <code>IDnaStrand</code> parameter in
     * functionality, but fewer optimizations are possible. Typically this
     * method will be called by the  <code>append</code> method with an IDNAStrand
     * parameter if the
     * <code>IDnaStrand</code> passed to that other append method isn't the same class as the strand
     * being appended to.
     * 
     * @param dna is the string appended to this strand
     * @return this strand after the data has been added
     */
    public IDnaStrand append(String dna);
}
