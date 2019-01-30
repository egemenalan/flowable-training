/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.flowable.ui.modeler.rest.app;

import java.util.Iterator;
import java.util.Map;

import org.flowable.ui.common.service.exception.InternalServerErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.databind.node.ValueNode;

@RestController
@RequestMapping("/app")
public class StencilSetResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(StencilSetResource.class);

	@Autowired
	protected ObjectMapper objectMapper;

	@RequestMapping(value = "/rest/stencil-sets/editor", method = RequestMethod.GET, produces = "application/json")
	public JsonNode getStencilSetForEditor() {
		try {
			JsonNode stencilNode = objectMapper
					.readTree(this.getClass().getClassLoader().getResourceAsStream("stencilset_bpmn.json"));
			JsonNode robusta = objectMapper
					.readTree(this.getClass().getClassLoader().getResourceAsStream("custom_stencilset_webapp.json"));
			return merge(stencilNode, robusta);
		} catch (Exception e) {
			LOGGER.error("Error reading bpmn stencil set json", e);
			throw new InternalServerErrorException("Error reading bpmn stencil set json");
		}
	}

	@RequestMapping(value = "/rest/stencil-sets/cmmneditor", method = RequestMethod.GET, produces = "application/json")
	public JsonNode getCmmnStencilSetForEditor() {
		try {
			JsonNode stencilNode = objectMapper
					.readTree(this.getClass().getClassLoader().getResourceAsStream("stencilset_cmmn.json"));
			JsonNode robusta = objectMapper
					.readTree(this.getClass().getClassLoader().getResourceAsStream("custom_stencilset_webapp.json"));
			return merge(stencilNode, robusta);
		} catch (Exception e) {
			LOGGER.error("Error reading bpmn stencil set json", e);
			throw new InternalServerErrorException("Error reading bpmn stencil set json");
		}
	}
	
//	public static JsonNode merge(JsonNode mainNode, JsonNode updateNode) {
//		
//		if (updateNode == null)
//			return mainNode;
//
//		Iterator<String> fieldNames = updateNode.fieldNames();
//		while (fieldNames.hasNext()) {
//
//			String fieldName = fieldNames.next();
//			JsonNode jsonNode = mainNode.get(fieldName);
//			// if field exists and is an embedded object
//			if (jsonNode != null && !jsonNode.isObject()) {
//				merge(jsonNode, updateNode.get(fieldName));
//			} else {
//				if (mainNode instanceof ObjectNode) {
//					// Overwrite field
//					JsonNode value = updateNode.get(fieldName);
//					((ObjectNode) mainNode).put(fieldName, value);
//				}
//			}
//
//		}
//
//		return mainNode;
//	}
//}
	
	/**
	 * Merge two JSON tree into one i.e mergedInTo.
	 *
	 * @param toBeMerged
	 * @param mergedInTo
	 * @return 
	 */
//	public static JsonNode merge(JsonNode toBeMerged, JsonNode mergedInTo) {
//	    Iterator<Map.Entry<String, JsonNode>> incomingFieldsIterator = toBeMerged.fields();
//	    Iterator<Map.Entry<String, JsonNode>> mergedIterator = mergedInTo.fields();
//
//	    while (incomingFieldsIterator.hasNext()) {
//	        Map.Entry<String, JsonNode> incomingEntry = incomingFieldsIterator.next();
//
//	        JsonNode subNode = incomingEntry.getValue();
//
//	        if (subNode.getNodeType().equals(JsonNodeType.OBJECT)) {
//	            boolean isNewBlock = true;
//	            mergedIterator = mergedInTo.fields();
//	            while (mergedIterator.hasNext()) {
//	                Map.Entry<String, JsonNode> entry = mergedIterator.next();
//	                if (entry.getKey().equals(incomingEntry.getKey())) {
//	                    merge(incomingEntry.getValue(), entry.getValue());
//	                    isNewBlock = false;
//	                }
//	            }
//	            if (isNewBlock) {
//	                ((ObjectNode) mergedInTo).replace(incomingEntry.getKey(), incomingEntry.getValue());
//	            }
//	        } else if (subNode.getNodeType().equals(JsonNodeType.ARRAY)) {
//	            boolean newEntry = true;
//	            mergedIterator = mergedInTo.fields();
//	            while (mergedIterator.hasNext()) {
//	                Map.Entry<String, JsonNode> entry = mergedIterator.next();
//	                if (entry.getKey().equals(incomingEntry.getKey())) {
//	                    updateArray(incomingEntry.getValue(), entry);
//	                    newEntry = false;
//	                }
//	            }
//	            if (newEntry) {
//	                ((ObjectNode) mergedInTo).replace(incomingEntry.getKey(), incomingEntry.getValue());
//	            }
//	        }
//	        
//	        ValueNode valueNode = null;
//	        JsonNode incomingValueNode = incomingEntry.getValue();
//	        switch (subNode.getNodeType()) {
//	            case STRING:
//	                valueNode = new TextNode(incomingValueNode.textValue());
//	                break;
//	            case NUMBER:
//	                valueNode = new IntNode(incomingValueNode.intValue());
//	                break;
//	            case BOOLEAN:
//	                valueNode = BooleanNode.valueOf(incomingValueNode.booleanValue());
//	        }
//	        if (valueNode != null) {
//	            updateObject(mergedInTo, valueNode, incomingEntry);
//	        }
//	    }
//	    return toBeMerged;
//	}
//
//	private static void updateArray(JsonNode valueToBePlaced, Map.Entry<String, JsonNode> toBeMerged) {
//	    toBeMerged.setValue(valueToBePlaced);
//	}
//
//	private static void updateObject(JsonNode mergeInTo, ValueNode valueToBePlaced,
//	                                 Map.Entry<String, JsonNode> toBeMerged) {
//	    boolean newEntry = true;
//	    Iterator<Map.Entry<String, JsonNode>> mergedIterator = mergeInTo.fields();
//	    while (mergedIterator.hasNext()) {
//	        Map.Entry<String, JsonNode> entry = mergedIterator.next();
//	        if (entry.getKey().equals(toBeMerged.getKey())) {
//	            newEntry = false;
//	            entry.setValue(valueToBePlaced);
//	        }
//	    }
//	    if (newEntry) {
//	        ((ObjectNode) mergeInTo).replace(toBeMerged.getKey(), toBeMerged.getValue());
//	    }
//	}
	public static JsonNode merge(JsonNode mainNode, JsonNode updateNode) {
	Iterator<String> fieldNames = updateNode.fieldNames();

    while (fieldNames.hasNext()) {
        String updatedFieldName = fieldNames.next();
        JsonNode valueToBeUpdated = mainNode.get(updatedFieldName);
        JsonNode updatedValue = updateNode.get(updatedFieldName);

        // If the node is an @ArrayNode
        if (valueToBeUpdated != null && valueToBeUpdated.isArray() && 
            updatedValue.isArray()) {
            // running a loop for all elements of the updated ArrayNode
            for (int i = 0; i < updatedValue.size(); i++) {
                JsonNode updatedChildNode = updatedValue.get(i);
                // Create a new Node in the node that should be updated, if there was no corresponding node in it
                // Use-case - where the updateNode will have a new element in its Array
                if (valueToBeUpdated.size() <= i) {
                    ((ArrayNode) valueToBeUpdated).add(updatedChildNode);
                }
                // getting reference for the node to be updated
                JsonNode childNodeToBeUpdated = valueToBeUpdated.get(i);
                merge(childNodeToBeUpdated, updatedChildNode);
            }
        // if the Node is an @ObjectNode
        } else if (valueToBeUpdated != null && valueToBeUpdated.isObject()) {
            merge(valueToBeUpdated, updatedValue);
        } else {
            if (mainNode instanceof ObjectNode) {
                ((ObjectNode) mainNode).replace(updatedFieldName, updatedValue);
            }
        }
    }
    return mainNode;
}
}
