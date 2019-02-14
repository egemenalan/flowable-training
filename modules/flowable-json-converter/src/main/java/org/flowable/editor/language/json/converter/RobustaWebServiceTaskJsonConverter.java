package org.flowable.editor.language.json.converter;

/*
 * Author Egemen ALAN
 */

import org.flowable.bpmn.model.BaseElement;
import org.flowable.bpmn.model.ServiceTask;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class RobustaWebServiceTaskJsonConverter extends ServiceTaskJsonConverter {

	protected void webConvertToJson(ObjectNode propertiesNode, BaseElement baseElement,ServiceTask serviceTask) {


		setPropertyFieldValue(PROPERTY_BROWSERTASK_URL, "url", serviceTask, propertiesNode);
		setPropertyFieldValue(PROPERTY_BROWSERTASK_RESULT_VARIABLE, "resultVariable", serviceTask, propertiesNode);
		setPropertyFieldValue(PROPERTY_BROWSERTASK_TYPE, "type", serviceTask, propertiesNode);
		setPropertyFieldValue(PROPERTY_BROWSERTASK_PAGE_LOAD, "pageLoad", serviceTask, propertiesNode);
		setPropertyFieldValue(PROPERTY_BROWSERTASK_DOWNLOAD, "download", serviceTask, propertiesNode);
		setPropertyFieldValue(PROPERTY_BROWSERTASK_TIMEOUT, "timeout", serviceTask, propertiesNode);
		setPropertyFieldValue(PROPERTY_BROWSERTASK_PROFILE_PATH, "profilePath", serviceTask, propertiesNode);
		setPropertyFieldValue(PROPERTY_BROWSERTASK_MAXIMIZE, "maximize", serviceTask, propertiesNode);

	}

}
