import java.util.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1_Converter extends IP_Morse {

	
	public String convert(byte[] data) {

		StringBuffer buf = new StringBuffer();

		for (int i=0; i<data.length; i++) {

			int halfbyte = (data[i] >>> 4) & 0x0F;
			int two_halfs = 0;

			do {

				if ((0 < halfbyte && halfbyte <= 9))
					buf.append((char) ('0' + halfbyte));
				else
					buf.append((char) ('a' + (halfbyte - 10)));
			} while(two_halfs++ < 1);
		}

		String encryption = buf.toString();

		try (BufferedWriter bw = new BufferedWriter(new FileWriter("/home/codebuster/ProjectLab_Trial/Hash/HashFiles.txt"))) {

			bw.write(encryption + "\n");
			bw.newLine();
			bw.flush();
		
		} catch (IOException e) {

			e.printStackTrace();
		}

		return encryption;
	}

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, UnsupportedEncodingException {

		GetIP gp = new GetIP();
		String ip = gp.Ip_Send();
		IP_Morse ms = new IP_Morse();
		String morse = ms.code_encode(ip);
		SHA1_Converter sha = new SHA1_Converter();

		MessageDigest md;
		md = MessageDigest.getInstance("SHA-1");
		byte[] sha1hash = new byte[40];
		md.update(morse.getBytes("iso-8859-1"), 0, morse.length());
		sha1hash = md.digest();
		sha.convert(sha1hash);
		 
	}
}