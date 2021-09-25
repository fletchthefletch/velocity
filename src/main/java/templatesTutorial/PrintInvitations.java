package templatesTutorial;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
			e.printStackTrace();
			return;
		}
		
		try {
			// Get XML parent object
			Object allObjects = decoder.readObject();
			decoder.close();
			
			// Get children of object
			@SuppressWarnings("unchecked")
			ArrayList<Person> persons = (ArrayList<Person>) allObjects;
			
			
			// Get invitation template
			Template template = Velocity.getTemplate("invitation.vm");
			
			// Write to an invitations file, that shall contain all invitations
			FileWriter out = null;
			// Create / open file
			out = new FileWriter("invitations.txt");
			
			// Create context
			VelocityContext context = new VelocityContext();
			
			// Iterate through invitation list
			for (Person person : persons)
			{
				context.put( "person", person);
				context.put( "address", person.getAddress());
				template.merge(context, out);
			}

			// Close file
			out.close();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		
			
	}

}
