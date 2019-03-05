package org.flowable.ui.task.rest.runtime;

import org.flowable.ui.task.model.runtime.CreateProcessInstanceRepresentation;
import org.flowable.ui.task.model.runtime.ProcessInstanceRepresentation;
import org.flowable.ui.task.service.runtime.RobustaProcessInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/robusta/app")
public class RobustaProcessInstancesResource {
	

    @Autowired
    protected RobustaProcessInstanceService robustaProcessInstanceService;

    @RequestMapping(value = "/rest/process-instances-with-key/{processDefinitionKey}", method = RequestMethod.POST)
    public ProcessInstanceRepresentation startNewProcessInstance(@PathVariable String processDefinitionKey) {
        return robustaProcessInstanceService.startNewProcessInstance(processDefinitionKey);
    }
}
