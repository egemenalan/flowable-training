package org.flowable.ui.task.service.runtime;


import java.util.List;

import org.flowable.ui.common.model.ResultListDataRepresentation;
import org.flowable.ui.common.service.exception.BadRequestException;
import org.flowable.ui.task.model.runtime.CreateProcessInstanceRepresentation;
import org.flowable.ui.task.model.runtime.ProcessDefinitionRepresentation;
import org.flowable.ui.task.model.runtime.ProcessInstanceRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import liquibase.util.StringUtils;

@Service
@Transactional
public class RobustaProcessInstanceService {
	private static final Logger LOGGER = LoggerFactory.getLogger(FlowableProcessInstanceService.class);

	@Autowired
    protected FlowableProcessDefinitionService processDefinitionService;
	
	@Autowired
    protected FlowableProcessInstanceService processInstanceService;
    
    public ProcessInstanceRepresentation startNewProcessInstance(String processDefinitionKey) {
        if (StringUtils.isEmpty(processDefinitionKey)) {
            throw new BadRequestException("Process definition id is required");
        }
        
        ResultListDataRepresentation resultListDataRepresentation = processDefinitionService.getProcessDefinitions(true, processDefinitionKey);
        if (StringUtils.isEmpty(resultListDataRepresentation.getData().toString())) {
            throw new BadRequestException("Process definition data is null");
        }
        ProcessDefinitionRepresentation resultData = (ProcessDefinitionRepresentation)resultListDataRepresentation.getData().get(0);
        CreateProcessInstanceRepresentation startRequest = new CreateProcessInstanceRepresentation();
        startRequest.setName(resultData.getName());
        startRequest.setProcessDefinitionId(resultData.getId());
        return  processInstanceService.startNewProcessInstance(startRequest);


}}
