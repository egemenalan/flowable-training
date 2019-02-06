package com.robusta.util;

import java.io.IOException;
import java.util.Iterator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robusta.exception.PathNotFoundInConfigException;

public class RobustaConfigPathReader {

	public static String readPath(ObjectMapper objectMapper, String configModulName) throws IOException {
		String path = null;
		JsonNode robustaConfig = objectMapper
				.readTree(RobustaConfigPathReader.class.getClassLoader().getResourceAsStream("robusta_config.json"));
		
		robustaConfig.get("modulesfiles");
		JsonNode modulesfiles = robustaConfig.path("modulesfiles");
		Iterator<JsonNode> elements = modulesfiles.elements();
		while(elements.hasNext()){
			JsonNode modulesfile = elements.next();
			String module = modulesfile.get("module").asText();
			if (configModulName.equals(module)) {
				path = modulesfile.get("path").asText();
				break;
			}
		}
		if (path == null) {
			throw new PathNotFoundInConfigException("with model = " + configModulName);
		}
		return path;
	}

}
