package com.robusta.json.converter;

import java.util.Map;

import org.flowable.bpmn.model.BaseElement;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.ImplementationType;
import org.flowable.bpmn.model.ServiceTask;
import org.flowable.editor.language.json.converter.BaseBpmnJsonConverter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;


// stencil setteki her bir node icin bunun gibi bir dosya olusturulur
public class RobustaCustomWebAppTaskJsonConverter extends BaseBpmnJsonConverter{
    public static void fillTypes(Map<String, Class<? extends BaseBpmnJsonConverter>> convertersToBpmnMap, Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> convertersToJsonMap) {
        fillJsonTypes(convertersToBpmnMap);
        fillBpmnTypes(convertersToJsonMap);
    }

    public static void fillJsonTypes(Map<String, Class<? extends BaseBpmnJsonConverter>> convertersToBpmnMap) {
    	// .class olan bolume dikkat edilir
    	// stencil_task ile baslayan kesin constantsdan gelir
        convertersToBpmnMap.put(STENCIL_TASK_WEBAPP, RobustaCustomWebAppTaskJsonConverter.class);
    }

    public static void fillBpmnTypes(Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> convertersToJsonMap) {
    }

    @Override
    protected String getStencilId(BaseElement baseElement) {
    	// buraya dikkat dogru stencil name olmali
        return STENCIL_TASK_WEBAPP;
    }

    @Override
    protected void convertElementToJson(ObjectNode propertiesNode, BaseElement baseElement) {
        // done in service task
    }

    @Override
    protected FlowElement convertJsonToElement(JsonNode elementNode, JsonNode modelNode, Map<String, JsonNode> shapeMap) {
    	// burada servicetask olusturulur gibi yapilir
        ServiceTask task = new ServiceTask();
        // stencil adi
        task.setType("customWebApp");
        // stencile ait attributeler tanimlanir degisken isimlerine dikkat
        addField("url", PROPERTY_BROWSERTASK_URL, elementNode, task);
        addField("type", PROPERTY_BROWSERTASK_TYPE, elementNode, task);
        addField("resultVariable", PROPERTY_BROWSERTASK_RESULT_VARIABLE, elementNode, task);
        addField("pageLoad", PROPERTY_BROWSERTASK_PAGE_LOAD, elementNode, task);
        addField("download", PROPERTY_BROWSERTASK_DOWNLOAD, elementNode, task);
        addField("timeout", PROPERTY_BROWSERTASK_TIMEOUT, elementNode, task);
        addField("profilePath", PROPERTY_BROWSERTASK_PROFILE_PATH, elementNode, task);
        addField("maximize", PROPERTY_BROWSERTASK_MAXIMIZE, elementNode, task);
        task.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_CLASS);
        // bu islem sonucunda calistirilacak java code
        task.setImplementation("com.robusta.robot.browser.open");
        return task;
    }

}
