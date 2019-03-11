package org.flowable.validation.validator.impl;

/**
 * @author Egemen Alan
 */

import java.util.List;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.FieldExtension;
import org.flowable.bpmn.model.Process;
import org.flowable.bpmn.model.ServiceTask;
import org.flowable.bpmn.model.TaskWithFieldExtensions;
import org.flowable.validation.ValidationError;
import org.flowable.validation.validator.ProcessLevelValidator;
import org.flowable.validation.validator.robusta.Problems.RobustaWebProblems;

public class RobustaWebTaskValidator extends ProcessLevelValidator  {
	
	public void validateFieldDeclarationsForWeb(org.flowable.bpmn.model.Process process, TaskWithFieldExtensions task,ServiceTask serviceTask, List<FieldExtension> fieldExtensions, List<ValidationError> errors) {
		
		// validate edilecek her bir stencil icin ayri prosedur olacak
		if (serviceTask.getType().equalsIgnoreCase("customwebapp")) {
		    validateForBrowser(process, task, fieldExtensions, errors);
		}
		if (serviceTask.getType().equalsIgnoreCase("customwebclose")) {
		    validateForClose(process, task, fieldExtensions, errors);
		}
		if (serviceTask.getType().equalsIgnoreCase("customwebmouse")) {
		    validateForMouse(process, task, fieldExtensions, errors);
		}
		if (serviceTask.getType().equalsIgnoreCase("customwebget")) {
		    validateForGet(process, task, fieldExtensions, errors);
		}
	}
	


	protected void validateForBrowser(org.flowable.bpmn.model.Process process, TaskWithFieldExtensions task, List<FieldExtension> fieldExtensions, List<ValidationError> errors) {
    	boolean urlDefined = false;
    	boolean typeDefined = false;
        boolean nameDefined = false;

        for (FieldExtension fieldExtension : fieldExtensions) {
            String fieldName = fieldExtension.getFieldName();
            String fieldValue = fieldExtension.getStringValue();
            String fieldExpression = fieldExtension.getExpression();

            if (fieldName.equals("url") && ((fieldValue != null && fieldValue.length() > 0) || (fieldExpression != null && fieldExpression.length() > 0))) {
                urlDefined = true;
            }
            if (fieldName.equals("type") && ((fieldValue != null && fieldValue.length() > 0) || (fieldExpression != null && fieldExpression.length() > 0))) {
                typeDefined = true;
            }
            if (fieldName.equals("resultVariable") && ((fieldValue != null && fieldValue.length() > 0) || (fieldExpression != null && fieldExpression.length() > 0))) {
                nameDefined = true;
            }
        }

        if (!urlDefined) {
            addError(errors, RobustaWebProblems.BRW_URL_NO_KEY, process, task, "No url address is defined on the browser activity");
        }
        if (!nameDefined) {
            addError(errors, RobustaWebProblems.BRW_NAME_NO_KEY, process, task, "No browser name is defined on the browser activity");
        }
        if (!typeDefined) {
            addError(errors, RobustaWebProblems.BRW_TYPE_NO_KEY, process, task, "No browser type is defined on the browser activity(e.g. CHROME)");
        }
    }

	private void validateForClose(Process process, TaskWithFieldExtensions task, List<FieldExtension> fieldExtensions,
			List<ValidationError> errors) {
		
        boolean nameDefined = false;

        for (FieldExtension fieldExtension : fieldExtensions) {
            String fieldName = fieldExtension.getFieldName();
            String fieldValue = fieldExtension.getStringValue();
            String fieldExpression = fieldExtension.getExpression();
           
            if (fieldName.equals("resultVariable") && ((fieldValue != null && fieldValue.length() > 0) || (fieldExpression != null && fieldExpression.length() > 0))) {
                nameDefined = true;
            }
        }
       
        if (!nameDefined) {
            addError(errors, RobustaWebProblems.BRW_NAME_NO_KEY, process, task, "No browser name is defined on the browser activity");
        }
        	
	}
	
    protected void validateForMouse(org.flowable.bpmn.model.Process process, TaskWithFieldExtensions task, List<FieldExtension> fieldExtensions, List<ValidationError> errors) {
        boolean fieldDefined = false;
        boolean nameDefined = false;

        for (FieldExtension fieldExtension : fieldExtensions) {
            String fieldName = fieldExtension.getFieldName();
            String fieldValue = fieldExtension.getStringValue();
            String fieldExpression = fieldExtension.getExpression();

            if (fieldName.equals("field") && ((fieldValue != null && fieldValue.length() > 0) || (fieldExpression != null && fieldExpression.length() > 0))) {
                fieldDefined = true;
            }
            if (fieldName.equals("browserName") && ((fieldValue != null && fieldValue.length() > 0) || (fieldExpression != null && fieldExpression.length() > 0))) {
                nameDefined = true;
            }
            
        }

        if (!fieldDefined) {
            addError(errors, RobustaWebProblems.BRW_FIELD_NO_KEY, process, task, "No field is defined on the browser");
        }
        if (!nameDefined) {
            addError(errors, RobustaWebProblems.BRW_NAME_NO_KEY, process, task, "No browser name is defined on the browser");
        }
    }
	
	private void validateForGet(Process process, TaskWithFieldExtensions task, List<FieldExtension> fieldExtensions,
			List<ValidationError> errors) {
		boolean nameDefined = false;
    	boolean fieldDefined = false;
    	boolean attribute = false;

        for (FieldExtension fieldExtension : fieldExtensions) {
            String fieldName = fieldExtension.getFieldName();
            String fieldValue = fieldExtension.getStringValue();
            String fieldExpression = fieldExtension.getExpression();

            if (fieldName.equals("browserName") && ((fieldValue != null && fieldValue.length() > 0) || (fieldExpression != null && fieldExpression.length() > 0))) {
            	nameDefined = true;
            }
            if (fieldName.equals("field") && ((fieldValue != null && fieldValue.length() > 0) || (fieldExpression != null && fieldExpression.length() > 0))) {
            	fieldDefined = true;
            }
            if (fieldName.equals("field") && ((fieldValue != null && fieldValue.length() > 0) || (fieldExpression != null && fieldExpression.length() > 0))) {
            	attribute = true;
            }
        }

        
        if (!nameDefined) {
            addError(errors, RobustaWebProblems.BRW_NAME_NO_KEY, process, task, "No browser name is defined on the browser activity");
        }
        if (!fieldDefined) {
            addError(errors, RobustaWebProblems.BRW_FIELD_NO_KEY, process, task, "No Field is defined on the browser activity(e.g. CHROME)");
        }
        if (!attribute) {
            addError(errors, RobustaWebProblems.BRW_ATTRIBUTE_NO_KEY, process, task, "No url address is defined on the browser activity");
        }
        
		
	}


	@Override
	protected void executeValidation(BpmnModel bpmnModel, Process process, List<ValidationError> errors) {
		// TODO Auto-generated method stub
		
	}

}