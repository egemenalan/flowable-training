package com.robusta.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class RobustaJsonMerger {

	/**
	 * Author Egemen ALAN
	 * 
	 * Robusta Custom Stencils Merge two JSON tree into one i.e mainNode.
	 *
	 * @param mainNode
	 * @param updateNode
	 * @return
	 * @throws IOException
	 */

	public static JsonNode insert(JsonNode mainNode, JsonNode updateNode) throws IOException {
		ArrayNode opp = (ArrayNode) mainNode.get("propertyPackages");
		ArrayNode robustaPropertyPackages = (ArrayNode) updateNode.get("propertyPackages");

		for (int i = 0; i < robustaPropertyPackages.size(); i++) {
			opp.add(robustaPropertyPackages.get(i));
		}

		ArrayNode os = (ArrayNode) mainNode.get("stencils");
		ArrayNode robustaStencil = (ArrayNode) updateNode.get("stencils");

		for (int i = 0; i < robustaStencil.size(); i++) {
			os.add(robustaStencil.get(i));
		}

		return mainNode;
	}
}
