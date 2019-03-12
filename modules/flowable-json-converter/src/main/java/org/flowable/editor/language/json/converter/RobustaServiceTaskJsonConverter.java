package org.flowable.editor.language.json.converter;

/*
 * Author Egemen ALAN
 */

import org.flowable.bpmn.model.BaseElement;
import org.flowable.bpmn.model.ServiceTask;

import com.fasterxml.jackson.databind.node.ObjectNode;


public class RobustaServiceTaskJsonConverter extends RobustaWebTaskServiceJsonConverter {

	public void convertElementToJson(ObjectNode propertiesNode, BaseElement baseElement,ServiceTask serviceTask) {
		if (serviceTask.getType().startsWith("customWeb")) {
			webConvertToJson(propertiesNode, baseElement, serviceTask);
		} 
	}
}