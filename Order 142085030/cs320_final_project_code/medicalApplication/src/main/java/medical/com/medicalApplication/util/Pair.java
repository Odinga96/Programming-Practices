package medical.com.medicalApplication.util;
/**
 * 
 *This class is used as a utility class to return two string objects
 *
 */
public class Pair {
	private String one;
	private String two;
	
	public Pair(String one, String two) {
		super();
		this.one = one;
		this.two = two;
	}
	public String getOne() {
		return one;
	}
	public void setOne(String one) {
		this.one = one;
	}

	public String getTwo() {
		return two;
	}
	public void setTwo(String two) {
		this.two = two;
	}
}
