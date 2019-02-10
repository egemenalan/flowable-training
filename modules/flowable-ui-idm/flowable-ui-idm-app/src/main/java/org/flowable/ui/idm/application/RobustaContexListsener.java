package org.flowable.ui.idm.application;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
/**
 * @author Egemen Alan
 */

@WebListener
public class RobustaContexListsener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
    	
    	System.out.println("Egemen-İDM");
    	System.out.println(System.getProperty("user.dir"));
    	String tomcatHomeDirectory = System.getProperty("catalina.base");
    	
    	String customerLogoFilePath = tomcatHomeDirectory + java.io.File.separator 
    			+ "customer_configuration" + java.io.File.separator + "customer_logo.png";
//    	String customerLogoFilePath = System.getProperty("user.dir")+ java.io.File.separator 
//    			+ "customer_configuration" + java.io.File.separator + "customer_logo.png";
    	
    	String outputFilePath = tomcatHomeDirectory + java.io.File.separator 
    			// FIXME prje ismi ve klasorler hard coded yazildi ileride degistirilirse sorun olur bunlari toplu bir konfigden almak gerek
    			+ "wtpwebapps"  + java.io.File.separator + "flowable-ui-idm-app" + java.io.File.separator + "WEB-INF"  + java.io.File.separator + "classes" + java.io.File.separator + "static"
    			//+ servletContextEvent.getServletContext().getContextPath()
    			+ java.io.File.separator + "images" + java.io.File.separator +  "flowable-logo.png";
    	
//    	String outputFilePath = "wtpwebapps"  + java.io.File.separator + "flowable-ui-idm-app" + java.io.File.separator + "WEB-INF"  + java.io.File.separator + "classes" + java.io.File.separator + "static"
//    			+ servletContextEvent.getServletContext().getContextPath()
//    			+ java.io.File.separator + "images" + java.io.File.separator +  "flowable-logo.png";
    			//C:\Flowable-Engine-WS\flowable-engine\modules\flowable-ui-idm\flowable-ui-idm-app\src\main
    			
    			//\wtpwebapps\flowable-ui-idm-app\WEB-INF\classes\static\images
    			
    			
    			/*"flowable-ui-idm-app" + java.io.File.separator 
    			+ "src" + java.io.File.separator 
    			+ "main" + java.io.File.separator 
    			+ "resources" + java.io.File.separator 
    			+ "static" + java.io.File.separator 
    			+ "images" + java.io.File.separator +  "flowable-logo@2x.png";
    			*/
    	//read
    	//C:\Flowable-Engine-WS\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\customer_configuration\customer_logo.png
    	//C:\Flowable-Engine-WS\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\webapps\flowable-ui-admin-app\images\flowable-logo.png
    	
    	//write
    	
    	BufferedImage img = null;
//    	try {
//    	    img = ImageIO.read(new File(customerLogoFilePath));
//    	    
//    	    ImageIO.write(img, "png", new File(outputFilePath));
//    	    
//    	} catch (IOException e) {
//    		e.printStackTrace();
//    	}
    	
    	System.out.println(tomcatHomeDirectory);
    	System.out.println(customerLogoFilePath);
    	System.out.println(outputFilePath);
    	
    	System.out.println(servletContextEvent.getServletContext().getServerInfo());
    	
    	System.out.println(servletContextEvent.getServletContext().getContextPath());
    	
    	
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
	
}
