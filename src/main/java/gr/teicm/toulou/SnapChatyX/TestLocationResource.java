package gr.teicm.toulou.SnapChatyX;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import java.awt.Toolkit;

@Path("locationtest")
public class TestLocationResource {


    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String postLocation(String location) {
    	//Beep Alert
    	Toolkit.getDefaultToolkit().beep();
    	System.out.println("Received Geolocation: " + location);
        return ("Got it!");
    }

}