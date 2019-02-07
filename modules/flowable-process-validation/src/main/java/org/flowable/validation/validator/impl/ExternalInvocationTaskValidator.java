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
package org.flowable.validation.validator.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.CaseServiceTask;
import org.flowable.bpmn.model.FieldExtension;
import org.flowable.bpmn.model.TaskWithFieldExtensions;
import org.flowable.validation.ValidationError;
import org.flowable.validation.validator.Problems;
import org.flowable.validation.validator.ProcessLevelValidator;

public abstract class ExternalInvocationTaskValidator extends ProcessLevelValidator {

    protected void validateFieldDeclarationsForEmail(org.flowable.bpmn.model.Process process, TaskWithFieldExtensions task, List<FieldExtension> fieldExtensions, List<ValidationError> errors) {
        boolean toDefined = false;
        boolean textOrHtmlDefined = false;

        for (FieldExtension fieldExtension : fieldExtensions) {
            if (fieldExtension.getFieldName().equals("to")) {
                toDefined = true;
            }
            if (fieldExtension.getFieldName().equals("html")) {
                textOrHtmlDefined = true;
            }
            if (fieldExtension.getFieldName().equals("htmlVar")) {
                textOrHtmlDefined = true;
            }
            if (fieldExtension.getFieldName().equals("text")) {
                textOrHtmlDefined = true;
            }
            if (fieldExtension.getFieldName().equals("textVar")) {
                textOrHtmlDefined = true;
            }
        }

        if (!toDefined) {
            addError(errors, Problems.MAIL_TASK_NO_RECIPIENT, process, task, "No recipient is defined on the mail activity");
        }
        if (!textOrHtmlDefined) {
            addError(errors, Problems.MAIL_TASK_NO_CONTENT, process, task, "Text, html, textVar or htmlVar field should be provided");
        }
    }

    protected void validateFieldDeclarationsForShell(org.flowable.bpmn.model.Process process, TaskWithFieldExtensions task, List<FieldExtension> fieldExtensions, List<ValidationError> errors) {
        boolean shellCommandDefined = false;

        for (FieldExtension fieldExtension : fieldExtensions) {
            String fieldName = fieldExtension.getFieldName();
            String fieldValue = fieldExtension.getStringValue();

            if (fieldName.equals("command")) {
                shellCommandDefined = true;
            }

            if ((fieldName.equals("wait") || fieldName.equals("redirectError") || fieldName.equals("cleanEnv")) && !fieldValue.toLowerCase().equals("true") && !fieldValue.toLowerCase().equals("false")) {
                addError(errors, Problems.SHELL_TASK_INVALID_PARAM, process, task, "Undefined parameter value for shell field");
            }

        }

        if (!shellCommandDefined) {
            addError(errors, Problems.SHELL_TASK_NO_COMMAND, process, task, "No shell command is defined on the shell activity");
        }
    }

    protected void validateFieldDeclarationsForDmn(org.flowable.bpmn.model.Process process, TaskWithFieldExtensions task, List<FieldExtension> fieldExtensions, List<ValidationError> errors) {
        boolean keyDefined = false;

        for (FieldExtension fieldExtension : fieldExtensions) {
            String fieldName = fieldExtension.getFieldName();
            String fieldValue = fieldExtension.getStringValue();

            if (fieldName.equals("decisionTableReferenceKey") && fieldValue != null && fieldValue.length() > 0) {
                keyDefined = true;
            }
        }

        if (!keyDefined) {
            addError(errors, Problems.DMN_TASK_NO_KEY, process, task, "No decision table reference key is defined on the dmn activity");
        }
    }

     
    // Robusta Custom Part Egemen ALAN
    protected void validateFieldDeclarationsForBrowser(org.flowable.bpmn.model.Process process, TaskWithFieldExtensions task, List<FieldExtension> fieldExtensions, List<ValidationError> errors) {
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
            addError(errors, Problems.BRW_URL_NO_KEY, process, task, "No url address is defined on the browser activity");
        }
        if (!nameDefined) {
            addError(errors, Problems.BRW_NAME_NO_KEY, process, task, "No browser name is defined on the browser activity");
        }
        if (!typeDefined) {
            addError(errors, Problems.BRW_TYPE_NO_KEY, process, task, "No browser type is defined on the browser activity(e.g. CHROME)");
        }
    }

    protected void validateFieldDeclarationsForBrwMouse(org.flowable.bpmn.model.Process process, TaskWithFieldExtensions task, List<FieldExtension> fieldExtensions, List<ValidationError> errors) {
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
            addError(errors, Problems.BRW_FIELD_NO_KEY, process, task, "No field is defined on the browser");
        }
        if (!nameDefined) {
            addError(errors, Problems.BRW_NAME_NO_KEY, process, task, "No browser name is defined on the browser");
        }
    }

    protected void validateFieldDeclarationsForBrwFunction(org.flowable.bpmn.model.Process process, TaskWithFieldExtensions task, List<FieldExtension> fieldExtensions, List<ValidationError> errors) {
        boolean fieldDefined = false;
        boolean nameDefined = false;
        boolean functionDefined = false;
        boolean resultDefined = false;

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
            if (fieldName.equals("function") && ((fieldValue != null && fieldValue.length() > 0) || (fieldExpression != null && fieldExpression.length() > 0))) {
                functionDefined = true;
            }
            if (fieldName.equals("resultVariable") && ((fieldValue != null && fieldValue.length() > 0) || (fieldExpression != null && fieldExpression.length() > 0))) {
                resultDefined = true;
            }
        }

        if (!fieldDefined) {
            addError(errors, Problems.BRW_FIELD_NO_KEY, process, task, "No field is defined on the browser");
        }
        if (!nameDefined) {
            addError(errors, Problems.BRW_NAME_NO_KEY, process, task, "No browser name is defined on the browser");
        }
        if (!functionDefined) {
            addError(errors, Problems.BRW_FUNCTION_NO_KEY, process, task, "No function is defined on the browser");
        }
        if (!resultDefined) {
            addError(errors, Problems.SERVICE_TASK_RESULT_VAR_NAME_WITH_DELEGATE, process, task, "No result variable is defined on the browser");
        }        
    }

    protected void validateFieldDeclarationsForBrwScroll(org.flowable.bpmn.model.Process process, TaskWithFieldExtensions task, List<FieldExtension> fieldExtensions, List<ValidationError> errors) {
        boolean nameDefined = false;

        for (FieldExtension fieldExtension : fieldExtensions) {
            String fieldName = fieldExtension.getFieldName();
            String fieldValue = fieldExtension.getStringValue();
            String fieldExpression = fieldExtension.getExpression();

            if (fieldName.equals("browserName") && ((fieldValue != null && fieldValue.length() > 0) || (fieldExpression != null && fieldExpression.length() > 0))) {
                nameDefined = true;
            }
            
        }

        if (!nameDefined) {
            addError(errors, Problems.BRW_NAME_NO_KEY, process, task, "No browser name is defined on the browser");
        }
    }

    protected void validateFieldDeclarationsForBrwClose(org.flowable.bpmn.model.Process process, TaskWithFieldExtensions task, List<FieldExtension> fieldExtensions, List<ValidationError> errors) {
        boolean nameDefined = false;

        for (FieldExtension fieldExtension : fieldExtensions) {
            String fieldName = fieldExtension.getFieldName();
            String fieldValue = fieldExtension.getStringValue();
            String fieldExpression = fieldExtension.getExpression();

            if (fieldName.equals("browserName") && ((fieldValue != null && fieldValue.length() > 0) || (fieldExpression != null && fieldExpression.length() > 0))) {
                nameDefined = true;
            }
            
        }

        if (!nameDefined) {
            addError(errors, Problems.BRW_NAME_NO_KEY, process, task, "No browser name is defined on the browser");
        }
    }

    protected void validateFieldDeclarationsForBrwWait(org.flowable.bpmn.model.Process process, TaskWithFieldExtensions task, List<FieldExtension> fieldExtensions, List<ValidationError> errors) {
        boolean fieldDefined = false;
        boolean nameDefined = false;
        boolean typeDefined = false;

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
            if (fieldName.equals("waitType") && ((fieldValue != null && fieldValue.length() > 0) || (fieldExpression != null && fieldExpression.length() > 0))) {
                typeDefined = true;
            }
            
        }

        if (!fieldDefined) {
            addError(errors, Problems.BRW_FIELD_NO_KEY, process, task, "No field is defined on the browser");
        }
        if (!nameDefined) {
            addError(errors, Problems.BRW_NAME_NO_KEY, process, task, "No browser name is defined on the browser");
        }
        if (!typeDefined) {
            addError(errors, Problems.BRW_WAIT_TYPE_NO_KEY, process, task, "No wait option is defined on the browser");
        }
    }


    protected void validateFieldDeclarationsForBrwDownload(org.flowable.bpmn.model.Process process, TaskWithFieldExtensions task, List<FieldExtension> fieldExtensions, List<ValidationError> errors) {
        boolean nameDefined = false;

        for (FieldExtension fieldExtension : fieldExtensions) {
            String fieldName = fieldExtension.getFieldName();
            String fieldValue = fieldExtension.getStringValue();
            String fieldExpression = fieldExtension.getExpression();

            if (fieldName.equals("browserName") && ((fieldValue != null && fieldValue.length() > 0) || (fieldExpression != null && fieldExpression.length() > 0))) {
                nameDefined = true;
            }
        }
        if (!nameDefined) {
            addError(errors, Problems.BRW_NAME_NO_KEY, process, task, "No browser name is defined on the browser");
        }
    }

    protected void validateFieldDeclarationsForBrwSwitch(org.flowable.bpmn.model.Process process, TaskWithFieldExtensions task, List<FieldExtension> fieldExtensions, List<ValidationError> errors) {
        boolean fieldDefined = false;
        boolean nameDefined = false;
        boolean typeDefined = false;

        for (FieldExtension fieldExtension : fieldExtensions) {
            String fieldName = fieldExtension.getFieldName();
            String fieldValue = fieldExtension.getStringValue();
            String fieldExpression = fieldExtension.getExpression();

            if (fieldName.equals("browserName") && ((fieldValue != null && fieldValue.length() > 0) || (fieldExpression != null && fieldExpression.length() > 0))) {
                nameDefined = true;
            }
            if (fieldName.equals("switchType") && ((fieldValue != null && fieldValue.length() > 0) || (fieldExpression != null && fieldExpression.length() > 0))) {
                typeDefined = true;
            }
            
        }

        if (!nameDefined) {
            addError(errors, Problems.BRW_NAME_NO_KEY, process, task, "No browser name is defined on the browser");
        }
        if (!typeDefined) {
            addError(errors, Problems.BRW_SWITCH_TYPE_NO_KEY, process, task, "No switch action is defined on the browser");
        }
    }

    protected void validateFieldDeclarationsForBrwAlert(org.flowable.bpmn.model.Process process, TaskWithFieldExtensions task, List<FieldExtension> fieldExtensions, List<ValidationError> errors) {
        boolean fieldDefined = false;
        boolean nameDefined = false;
        boolean typeDefined = false;

        for (FieldExtension fieldExtension : fieldExtensions) {
            String fieldName = fieldExtension.getFieldName();
            String fieldValue = fieldExtension.getStringValue();
            String fieldExpression = fieldExtension.getExpression();
            if (fieldName.equals("browserName") && ((fieldValue != null && fieldValue.length() > 0) || (fieldExpression != null && fieldExpression.length() > 0))) {
                nameDefined = true;
            }
            if (fieldName.equals("alertType") && ((fieldValue != null && fieldValue.length() > 0) || (fieldExpression != null && fieldExpression.length() > 0))) {
                typeDefined = true;
            }
            
        }

        if (!nameDefined) {
            addError(errors, Problems.BRW_NAME_NO_KEY, process, task, "No browser name is defined on the browser");
        }
//        if (!typeDefined) {
//            addError(errors, Problems.BRW_WAIT_TYPE_NO_KEY, process, task, "No wait option is defined on the browser");
//        }
    }

    protected void validateFieldDeclarationsForBrwSet(org.flowable.bpmn.model.Process process, TaskWithFieldExtensions task, List<FieldExtension> fieldExtensions, List<ValidationError> errors) {
        boolean fieldDefined = false;
        boolean nameDefined = false;
        boolean typeDefined = false;

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
            if (fieldName.equals("textType") && ((fieldValue != null && fieldValue.length() > 0) || (fieldExpression != null && fieldExpression.length() > 0))) {
                typeDefined = true;
            }
            
        }

        if (!fieldDefined) {
            addError(errors, Problems.BRW_FIELD_NO_KEY, process, task, "No field is defined on the browser");
        }
        if (!nameDefined) {
            addError(errors, Problems.BRW_NAME_NO_KEY, process, task, "No browser name is defined on the browser");
        }
        if (!typeDefined) {
            addError(errors, Problems.BRW_TEXT_TYPE_NO_KEY, process, task, "No setting target option is defined on the browser");
        }
    }

    protected void validateFieldDeclarationsForBrwCapture(org.flowable.bpmn.model.Process process, TaskWithFieldExtensions task, List<FieldExtension> fieldExtensions, List<ValidationError> errors) {
        boolean fieldDefined = false;
        boolean nameDefined = false;
        boolean typeDefined = false;

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
            addError(errors, Problems.BRW_FIELD_NO_KEY, process, task, "No field is defined on the browser");
        }
        if (!nameDefined) {
            addError(errors, Problems.BRW_NAME_NO_KEY, process, task, "No browser name is defined on the browser");
        }
    }

    protected void validateFieldDeclarationsForBrwGet(org.flowable.bpmn.model.Process process, TaskWithFieldExtensions task, List<FieldExtension> fieldExtensions, List<ValidationError> errors) {
        boolean fieldDefined = false;
        boolean nameDefined = false;
        boolean typeDefined = false;
        boolean resultDefined = false;

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
            if (fieldName.equals("textType") && ((fieldValue != null && fieldValue.length() > 0) || (fieldExpression != null && fieldExpression.length() > 0))) {
                typeDefined = true;
            }
            if (fieldName.equals("resultVariable") && ((fieldValue != null && fieldValue.length() > 0) || (fieldExpression != null && fieldExpression.length() > 0))) {
                resultDefined = true;
            }
            
        }

        if (!fieldDefined) {
            addError(errors, Problems.BRW_FIELD_NO_KEY, process, task, "No field is defined on the browser");
        }
        if (!nameDefined) {
            addError(errors, Problems.BRW_NAME_NO_KEY, process, task, "No browser name is defined on the browser");
        }
        if (!typeDefined) {
            addError(errors, Problems.BRW_TEXT_TYPE_NO_KEY, process, task, "No setting target option is defined on the browser");
        }
        if (!resultDefined) {
            addError(errors, Problems.SERVICE_TASK_RESULT_VAR_NAME_WITH_DELEGATE, process, task, "No result variable is defined on the browser");
        }        
    }

    protected void validateFieldDeclarationsForHttp(org.flowable.bpmn.model.Process process, TaskWithFieldExtensions task, List<FieldExtension> fieldExtensions, List<ValidationError> errors) {
        boolean requestMethodDefined = false;
        boolean requestUrlDefined = false;

        for (FieldExtension fieldExtension : fieldExtensions) {

            String fieldName = fieldExtension.getFieldName();
            String fieldValue = fieldExtension.getStringValue();
            String fieldExpression = fieldExtension.getExpression();

            if (fieldName.equals("requestMethod") && ((fieldValue != null && fieldValue.length() > 0) || (fieldExpression != null && fieldExpression.length() > 0))) {
                requestMethodDefined = true;
            }

            if (fieldName.equals("requestUrl") && ((fieldValue != null && fieldValue.length() > 0) || (fieldExpression != null && fieldExpression.length() > 0))) {
                requestUrlDefined = true;
            }
        }

        if (!requestMethodDefined) {
            addError(errors, Problems.HTTP_TASK_NO_REQUEST_METHOD, process, task, "No request method is defined on the http activity");
        }

        if (!requestUrlDefined) {
            addError(errors, Problems.HTTP_TASK_NO_REQUEST_URL, process, task, "No request url is defined on the http activity");
        }

    }
    
    protected void validateFieldDeclarationsForCase(org.flowable.bpmn.model.Process process, CaseServiceTask caseServiceTask, List<ValidationError> errors) {
        if (StringUtils.isEmpty(caseServiceTask.getCaseDefinitionKey())) {
            addError(errors, Problems.CASE_TASK_NO_CASE_DEFINITION_KEY, process, caseServiceTask, "No case definition key is defined on the case task");
        }
    }

}