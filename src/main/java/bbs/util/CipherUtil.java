package bbs.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CipherUtil {
	public static String encrypt(String target){
		MessageDigest md = null;
	    StringBuilder sb = null;
	    try {
	        md = MessageDigest.getInstance("SHA-256");
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    md.update(target.getBytes());
	    sb = new StringBuilder();
	    for (byte b : md.digest()) {
	        String hex = String.format("%02x", b);
	        sb.append(hex);
	    }
	    return sb.toString();
	}
}