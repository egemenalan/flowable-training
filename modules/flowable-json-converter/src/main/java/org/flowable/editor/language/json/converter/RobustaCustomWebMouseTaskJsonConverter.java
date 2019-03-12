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
package org.flowable.editor.language.json.converter;

import java.util.Map;

import org.flowable.bpmn.model.BaseElement;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.ImplementationType;
import org.flowable.bpmn.model.ServiceTask;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author Egemen ALAN
 */
public class RobustaCustomWebMouseTaskJsonConverter extends BaseBpmnJsonConverter {

	public static void fillTypes(Map<String, Class<? extends BaseBpmnJsonConverter>> convertersToBpmnMap, Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> convertersToJsonMap) {
        fillJsonTypes(convertersToBpmnMap);
        fillBpmnTypes(convertersToJsonMap);
    }

    public static void fillJsonTypes(Map<String, Class<? extends BaseBpmnJsonConverter>> convertersToBpmnMap) {
        //convertersToBpmnMap.put(STENCIL_TASK_WEBAPP, RobustaCustomWebAppTaskJsonConverter.class);
    	//Mouse
    	  convertersToBpmnMap.put(STENCIL_TASK_WEBMOUSE, RobustaCustomWebMouseTaskJsonConverter.class);
    }

    public static void fillBpmnTypes(Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> convertersToJsonMap) {
    }

    @Override
    protected String getStencilId(BaseElement baseElement) {
        //return STENCIL_TASK_WEBAPP;
    	//Mouse
          return STENCIL_TASK_WEBMOUSE;
    }

    @Override
    protected void convertElementToJson(ObjectNode propertiesNode, BaseElement baseElement) {
        // done in service task
    }

    @Override
    protected FlowElement convertJsonToElement(JsonNode elementNode, JsonNode modelNode, Map<String, JsonNode> shapeMap) {
        ServiceTask task = new ServiceTask();
        
        /*task.setType("customWebApp");
        addField("url", PROPERTY_BROWSERTASK_URL, elementNode, task);
        addField("type", PROPERTY_BROWSERTASK_TYPE, elementNode, task);
        addField("resultVariable", PROPERTY_BROWSERTASK_RESULT_VARIABLE, elementNode, task);
        addField("pageLoad", PROPERTY_BROWSERTASK_PAGE_LOAD, elementNode, task);
        addField("download", PROPERTY_BROWSERTASK_DOWNLOAD, elementNode, task);
        addField("timeout", PROPERTY_BROWSERTASK_TIMEOUT, elementNode, task);
        addField("profilePath", PROPERTY_BROWSERTASK_PROFILE_PATH, elementNode, task);
        addField("maximize", PROPERTY_BROWSERTASK_MAXIMIZE, elementNode, task);
        task.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_CLASS);
        task.setImplementation("com.robusta.robot.browser.open");*/
        
        //Mouse
        task.setType("customWebMouse");
        addField("browserId", PROPERTY_BROWSERTASK_NAME, elementNode, task);
        addField("xOffset", PROPERTY_BROWSERTASK_XOFFSET, elementNode, task);
        addField("yOffset", PROPERTY_BROWSERTASK_YOFFSET, elementNode, task);
        addField("field", PROPERTY_BROWSERTASK_FIELD, elementNode, task);
        addField("dbl", PROPERTY_BROWSERTASK_DBL, elementNode, task);
        addField("hover", PROPERTY_BROWSERTASK_HOVER, elementNode, task);
        addField("button", PROPERTY_BROWSERTASK_BUTTON, elementNode, task);
        addField("visible", PROPERTY_BROWSERTASK_ITEM_VISIBLE, elementNode, task);
        addField("pageload", PROPERTY_BROWSERTASK_PAGE_LOAD, elementNode, task);
    	
        task.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_CLASS);
        //Mouse
        task.setImplementation("com.robusta.robot.browser.mouse");
        
        return task;
    }
}
