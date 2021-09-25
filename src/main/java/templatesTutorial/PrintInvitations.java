package templatesTutorial;

import java.awt.List;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class PrintInvitations {
	
	
	public static void main(String[] args) {

		// XMl Decoder
       XMLDecoder decoder = null;
		try {
			decoder = new XMLDecoder(
			           new BufferedInputStream(
			               new FileInputStream("friends.xml")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			// Get XML parent object
			Object allObjects = decoder.readObject();
			decoder.close();

			
			// Get children of object
			ArrayList<Person> persons = (ArrayList<Person>) allObjects;
			
			for (Person person : persons)
			{
				System.out.println(person.getFirstName());

				
				VelocityContext context = new VelocityContext();
				context.put( "person", person);

				
				
				Template template = Velocity.getTemplate("invitation.vm");
				
				// Write to an invitations file, that shall contain all invitations
				FileWriter out = null;
				try {
					out = new FileWriter("invitations.txt");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				template.merge(context, out);
				
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
			
	}

}
