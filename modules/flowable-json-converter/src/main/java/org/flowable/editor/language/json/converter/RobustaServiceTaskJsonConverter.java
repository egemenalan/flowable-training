package org.flowable.editor.language.json.converter;

/*
 * Author Egemen ALAN
 */

import org.flowable.bpmn.model.BaseElement;
import org.flowable.bpmn.model.ServiceTask;

import com.fasterxml.jackson.databind.node.ObjectNode;


public class RobustaServiceTaskJsonConverter extends RobustaWebServiceTaskJsonConverter {

	public void robustaConvertElementToJson(ObjectNode propertiesNode, BaseElement baseElement,ServiceTask serviceTask) {
		if ("customweb".equalsIgnoreCase(serviceTask.getType())) {
			webConvertToJson(propertiesNode, baseElement, serviceTask);
		} else if (serviceTask.getType().startsWith("customWin")) {
		}
	}
}