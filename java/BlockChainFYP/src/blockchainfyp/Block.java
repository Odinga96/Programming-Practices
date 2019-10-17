
package blockchainfyp;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

public class Block {
    
   
	private Date TimeGen;
	private String hash;
	private String previousHash;
	private String data;
	
	public Block(Date timestamp, String data) {
		
		this.TimeGen = timestamp;
		this.data = data;
		this.hash = computeHash();
	}
	
	public String computeHash() {
		
		String dataToHash = "" + this.TimeGen + this.previousHash + this.data;
		
		MessageDigest digest;
		String encoded = null;
		
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));
			encoded = Base64.getEncoder().encodeToString(hash);
		} catch (NoSuchAlgorithmException e) {}
		
		
		this.hash = encoded;
		return encoded;
		
	}

	public Date getTime() {
		return TimeGen;
	}

	public void setTime(Date timestamp) {
		TimeGen = timestamp;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getPreviousHash() {
		return previousHash;
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
}

