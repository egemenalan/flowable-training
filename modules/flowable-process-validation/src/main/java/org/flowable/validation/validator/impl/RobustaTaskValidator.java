package org.flowable.validation.validator.impl;

/**
 * @author Egemen Alan
 */

import java.util.List;

import org.flowable.bpmn.model.FieldExtension;
import org.flowable.bpmn.model.ServiceTask;
import org.flowable.validation.ValidationError;

public class RobustaTaskValidator extends RobustaWebTaskValidator {

	public void robustaVerifyType(org.flowable.bpmn.model.Process process, ServiceTask serviceTask,
			List<FieldExtension> fieldExtensions, List<ValidationError> errors) {
		if (serviceTask.getType().startsWith("customWeb")) {
			validateFieldDeclarationsForWeb(process, serviceTask, serviceTask, fieldExtensions, errors);
//		} else if (serviceTask.getType().startsWith("customWin")) {
//			validateFieldDeclarationsForWin(process, serviceTask,serviceTask, fieldExtensions, errors);
//		}
		}
	}
}