package com.robusta.contextListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robusta.util.RobustaConfigModels;
import com.robusta.util.RobustaConfigPathReader;
/**
 * @author Egemen Alan
 */

@WebListener
public class RobustaContexListsener implements ServletContextListener {
	
//	@Autowired
//	protected ObjectMapper objectMapper;

    public void contextInitialized(ServletContextEvent servletContextEvent) {
    	
    	String customerLogoFilePath = null;
//		try {
//			customerLogoFilePath = RobustaConfigPathReader.readPath(objectMapper, RobustaConfigModels.LOGO_PATH_MODUL);
//			
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
    	System.out.println("egemen");
    	System.out.println(System.getProperty("user.dir"));
    	String tomcatHomeDirectory = System.getProperty("catalina.base");
    	
//    	customerLogoFilePath = tomcatHomeDirectory + java.io.File.separator 
//    			+ "customer_configuration" + java.io.File.separator + "customer_logo.png";
//    	String customerLogoFilePath = System.getProperty("user.dir")+ java.io.File.separator 
//    			+ "customer_configuration" + java.io.File.separator + "customer_logo.png";
    	
    	String outputFilePath = tomcatHomeDirectory + java.io.File.separator 
    			// FIXME prje ismi ve klasorler hard coded yazildi ileride degistirilirse sorun olur bunlari toplu bir konfigden almak gerek
    			+ "wtpwebapps"  + java.io.File.separator + "flowable-ui-task-app" + java.io.File.separator + "WEB-INF"  + java.io.File.separator + "classes" + java.io.File.separator + "static"
    			//+ servletContextEvent.getServletContext().getContextPath()
    			+ java.io.File.separator + "images" + java.io.File.separator +  "flowable-logo.png";
    	
    	
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
