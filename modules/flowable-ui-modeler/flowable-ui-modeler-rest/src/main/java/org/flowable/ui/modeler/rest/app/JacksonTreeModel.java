package org.flowable.ui.modeler.rest.app;

import java.io.File;
import java.io.FileWriter;

import org.flowable.ui.common.service.exception.InternalServerErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class JacksonTreeModel {
	private static final Logger LOGGER = LoggerFactory.getLogger(StencilSetResource.class);

	public static void main(String[] args) {
		

		
			try {
				ObjectMapper mapper = new ObjectMapper();

				JsonNode robustaNode = mapper.readTree(new File("c:\\custom_stencilset_webapp.json"));
				JsonNode stencilNode = mapper.readTree(new File("c:\\custom_stencilset_webapp2.json"));
				
				ArrayNode opp = (ArrayNode)stencilNode.get("propertyPackages");
				ArrayNode robustaPropertyPackages = (ArrayNode)robustaNode.get("propertyPackages");
				
				for (int i = 0; i < robustaPropertyPackages.size(); i++) {
					opp.add(robustaPropertyPackages.get(i));
				}
				
				
				ArrayNode os = (ArrayNode)stencilNode.get("stencils");
				ArrayNode robustaStencil = (ArrayNode)robustaNode.get("stencils");
				
				for (int i = 0; i < robustaStencil.size(); i++) {
					os.add(robustaStencil.get(i));
				}
				
				try(FileWriter file = new FileWriter("C:\\Temp\\test.json")) {
					file.write((stencilNode).toString());
					file.write((robustaNode).toString());
					System.out.println("Successfully Copied JSON Object to File...");
					System.out.println("\nJSON Object: " + stencilNode);
					System.out.println("\nJSON Object: " + robustaNode);
				}
			} catch (Exception e) {
				LOGGER.error("Error reading bpmn stencil set json", e);
				throw new InternalServerErrorException("Error reading bpmn stencil set json");
			}
	}
}
