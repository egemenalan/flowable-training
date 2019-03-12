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
package com.robusta.json.converter;

import java.util.List;
import java.util.Map;

import org.flowable.bpmn.model.BaseElement;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.ImplementationType;
import org.flowable.bpmn.model.ServiceTask;
import org.flowable.editor.language.json.converter.BaseBpmnJsonConverter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.robusta.constant.RobustaConstants;

/**
 * @author Egemen ALAN
 */
public class RobustaCustomTaskJsonConverter{
	

    public static void fillTypes(Map<String, Class<? extends BaseBpmnJsonConverter>> convertersToBpmnMap, Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> convertersToJsonMap) {
    	// StencilSetteki her bir bolum icin buraya converter yazilir
    	RobustaCustomWebAppTaskJsonConverter.fillTypes(convertersToBpmnMap, convertersToJsonMap);
    }

    public static void add (List<String> DI_RECTANGLES) {
	    DI_RECTANGLES.add(RobustaConstants.STENCIL_TASK_WEBAPP);
	    DI_RECTANGLES.add(RobustaConstants.STENCIL_TASK_WEBMOUSE);
	    DI_RECTANGLES.add(RobustaConstants.STENCIL_TASK_WEBWAIT);
	    DI_RECTANGLES.add(RobustaConstants.STENCIL_TASK_WEBSET);
	    DI_RECTANGLES.add(RobustaConstants.STENCIL_TASK_WEBGET);
	    DI_RECTANGLES.add(RobustaConstants.STENCIL_TASK_WEBCLOSE);
	    DI_RECTANGLES.add(RobustaConstants.STENCIL_TASK_WEBSCROLL);
	    DI_RECTANGLES.add(RobustaConstants.STENCIL_TASK_WEBALERT);
	    DI_RECTANGLES.add(RobustaConstants.STENCIL_TASK_WEBSWITCH);
	    DI_RECTANGLES.add(RobustaConstants.STENCIL_TASK_WEBDOWNLOAD);
	    DI_RECTANGLES.add(RobustaConstants.STENCIL_TASK_WEBCAPTURE);
	    DI_RECTANGLES.add(RobustaConstants.STENCIL_TASK_WEBFUNCTION);
    }

}
