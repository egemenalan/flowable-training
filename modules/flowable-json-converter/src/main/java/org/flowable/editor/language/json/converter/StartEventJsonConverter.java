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

import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.BaseElement;
import org.flowable.bpmn.model.ErrorEventDefinition;
import org.flowable.bpmn.model.Event;
import org.flowable.bpmn.model.EventDefinition;
import org.flowable.bpmn.model.EventSubProcess;
import org.flowable.bpmn.model.ExtensionElement;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.MessageEventDefinition;
import org.flowable.bpmn.model.SignalEventDefinition;
import org.flowable.bpmn.model.StartEvent;
import org.flowable.bpmn.model.TimerEventDefinition;
import org.flowable.editor.language.json.model.ModelInfo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author Tijs Rademakers
 */
public class StartEventJsonConverter extends BaseBpmnJsonConverter implements FormAwareConverter, FormKeyAwareConverter {

    protected Map<String, String> formMap;
    protected Map<String, ModelInfo> formKeyMap;

    public static void fillTypes(Map<String, Class<? extends BaseBpmnJsonConverter>> convertersToBpmnMap, Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> convertersToJsonMap) {

        fillJsonTypes(convertersToBpmnMap);
        fillBpmnTypes(convertersToJsonMap);
    }

    public static void fillJsonTypes(Map<String, Class<? extends BaseBpmnJsonConverter>> convertersToBpmnMap) {
        convertersToBpmnMap.put(STENCIL_EVENT_START_NONE, StartEventJsonConverter.class);
        convertersToBpmnMap.put(STENCIL_EVENT_START_TIMER, StartEventJsonConverter.class);
        convertersToBpmnMap.put(STENCIL_EVENT_START_ERROR, StartEventJsonConverter.class);
        convertersToBpmnMap.put(STENCIL_EVENT_START_MESSAGE, StartEventJsonConverter.class);
        convertersToBpmnMap.put(STENCIL_EVENT_START_SIGNAL, StartEventJsonConverter.class);
    }

    public static void fillBpmnTypes(Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> convertersToJsonMap) {
        convertersToJsonMap.put(StartEvent.class, StartEventJsonConverter.class);
    }

    @Override
    protected String getStencilId(BaseElement baseElement) {
        Event event = (Event) baseElement;
        if (event.getEventDefinitions().size() > 0) {
            EventDefinition eventDefinition = event.getEventDefinitions().get(0);
            if (eventDefinition instanceof TimerEventDefinition) {
                return STENCIL_EVENT_START_TIMER;
            } else if (eventDefinition instanceof ErrorEventDefinition) {
                return STENCIL_EVENT_START_ERROR;
            } else if (eventDefinition instanceof MessageEventDefinition) {
                return STENCIL_EVENT_START_MESSAGE;
            } else if (eventDefinition instanceof SignalEventDefinition) {
                return STENCIL_EVENT_START_SIGNAL;
            }
        }
        return STENCIL_EVENT_START_NONE;
    }

    @Override
    protected void convertElementToJson(ObjectNode propertiesNode, BaseElement baseElement) {
        StartEvent startEvent = (StartEvent) baseElement;
        if (StringUtils.isNotEmpty(startEvent.getInitiator())) {
            propertiesNode.put(PROPERTY_NONE_STARTEVENT_INITIATOR, startEvent.getInitiator());
        }

        if (StringUtils.isNotEmpty(startEvent.getFormKey())) {
            if (formKeyMap != null && formKeyMap.containsKey(startEvent.getFormKey())) {
                ObjectNode formRefNode = objectMapper.createObjectNode();
                ModelInfo modelInfo = formKeyMap.get(startEvent.getFormKey());
                formRefNode.put("id", modelInfo.getId());
                formRefNode.put("name", modelInfo.getName());
                formRefNode.put("key", modelInfo.getKey());
                propertiesNode.set(PROPERTY_FORM_REFERENCE, formRefNode);

            } else {
                setPropertyValue(PROPERTY_FORMKEY, startEvent.getFormKey(), propertiesNode);
            }
        }

        setPropertyValue(PROPERTY_FORM_FIELD_VALIDATION, startEvent.getValidateFormFields(), propertiesNode);

        if (startEvent.getSubProcess() instanceof EventSubProcess && !startEvent.isInterrupting()) {
            propertiesNode.put(PROPERTY_INTERRUPTING, false);
        } else {
            propertiesNode.put(PROPERTY_INTERRUPTING, true);
        }

        addFormProperties(startEvent.getFormProperties(), propertiesNode);
        addEventProperties(startEvent, propertiesNode);
    }

    @Override
    protected FlowElement convertJsonToElement(JsonNode elementNode, JsonNode modelNode, Map<String, JsonNode> shapeMap) {
        StartEvent startEvent = new StartEvent();
        startEvent.setInitiator(getPropertyValueAsString(PROPERTY_NONE_STARTEVENT_INITIATOR, elementNode));
        String stencilId = BpmnJsonConverterUtil.getStencilId(elementNode);
        if (STENCIL_EVENT_START_NONE.equals(stencilId)) {
            String formKey = getPropertyValueAsString(PROPERTY_FORMKEY, elementNode);
            if (StringUtils.isNotEmpty(formKey)) {
                startEvent.setFormKey(formKey);
            } else {
                JsonNode formReferenceNode = getProperty(PROPERTY_FORM_REFERENCE, elementNode);
                if (formReferenceNode != null && formReferenceNode.get("id") != null) {

                    if (formMap != null && formMap.containsKey(formReferenceNode.get("id").asText())) {
                        startEvent.setFormKey(formMap.get(formReferenceNode.get("id").asText()));
                    }
                }
            }
            String validateFormFields = getPropertyValueAsString(PROPERTY_FORM_FIELD_VALIDATION, elementNode);
            if (StringUtils.isNotEmpty(validateFormFields)) {
                startEvent.setValidateFormFields(validateFormFields);
            }
            convertJsonToFormProperties(elementNode, startEvent);

        } else if (STENCIL_EVENT_START_TIMER.equals(stencilId)) {
            convertJsonToTimerDefinition(elementNode, startEvent);
        } else if (STENCIL_EVENT_START_ERROR.equals(stencilId)) {
            convertJsonToErrorDefinition(elementNode, startEvent);
        } else if (STENCIL_EVENT_START_MESSAGE.equals(stencilId)) {
            convertJsonToMessageDefinition(elementNode, startEvent);
        } else if (STENCIL_EVENT_START_SIGNAL.equals(stencilId)) {
            convertJsonToSignalDefinition(elementNode, startEvent);
        }

        if (!getPropertyValueAsBoolean(PROPERTY_INTERRUPTING, elementNode)) {
            startEvent.setInterrupting(false);
        }

        return startEvent;
    }

    protected void addExtensionElement(String name, String elementText, Event event) {
        ExtensionElement extensionElement = new ExtensionElement();
        extensionElement.setNamespace(NAMESPACE);
        extensionElement.setNamespacePrefix("modeler");
        extensionElement.setName(name);
        extensionElement.setElementText(elementText);
        event.addExtensionElement(extensionElement);
    }

    @Override
    public void setFormMap(Map<String, String> formMap) {
        this.formMap = formMap;
    }

    @Override
    public void setFormKeyMap(Map<String, ModelInfo> formKeyMap) {
        this.formKeyMap = formKeyMap;
    }
}
