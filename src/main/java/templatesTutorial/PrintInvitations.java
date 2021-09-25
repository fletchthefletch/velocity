package templatesTutorial;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PrintInvitations {
	
	
	public static void main(String[] args) {

		// XMl Decoder
       XMLDecoder d = null;
		try {
			d = new XMLDecoder(
			           new BufferedInputStream(
			               new FileInputStream("friends.xml")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Object result = d.readObject();
		d.close();
		
		System.out.println(d.toString());
	}

}
