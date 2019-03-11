package org.flowable.editor.language.json.converter;

import org.flowable.bpmn.model.BaseElement;
import org.flowable.bpmn.model.ServiceTask;

/*
 * Author Egemen ALAN
 */

import com.fasterxml.jackson.databind.node.ObjectNode;

public class RobustaWebTaskServiceJsonConverter extends ServiceTaskJsonConverter {
	// RobustaServiceTaskWebJsonConverter

	protected void webConvertToJson(ObjectNode propertiesNode, BaseElement baseElement, ServiceTask serviceTask) {

		// her bir stencilset icin burada karsilastirma yapilir
		if ("customwebopen".equalsIgnoreCase(serviceTask.getType())) {
			// her bir stencilsetin propertyleri ile ayni isimde buraya yazilir
			setPropertyFieldValue(PROPERTY_BROWSERTASK_URL, "url", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_RESULT_VARIABLE, "resultVariable", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_TYPE, "type", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_PAGE_LOAD, "pageLoad", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_DOWNLOAD, "download", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_TIMEOUT, "timeout", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_PROFILE_PATH, "profilePath", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_MAXIMIZE, "maximize", serviceTask, propertiesNode);

		} else if ("customwebclose".equalsIgnoreCase(serviceTask.getType())) {

			setPropertyFieldValue(PROPERTY_BROWSERTASK_NAME, "browserName", serviceTask, propertiesNode);

		} else if ("customwebmouse".equalsIgnoreCase(serviceTask.getType())) {

			setPropertyFieldValue(PROPERTY_BROWSERTASK_NAME, "browserName", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_FIELD, "field", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_DBL, "dbl", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_HOVER, "hover", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_XOFFSET, "xOffset", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_YOFFSET, "yOffset", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_BUTTON, "button", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_PAGE_LOAD, "pageLoad", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_ITEM_VISIBLE, "visible", serviceTask, propertiesNode);

		} else if ("customwebscroll".equalsIgnoreCase(serviceTask.getType())) {

			setPropertyFieldValue(PROPERTY_BROWSERTASK_NAME, "browserName", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_XOFFSET, "xOffset", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_YOFFSET, "yOffset", serviceTask, propertiesNode);

		} else if ("customwebdownload".equalsIgnoreCase(serviceTask.getType())) {

			setPropertyFieldValue(PROPERTY_BROWSERTASK_NAME, "browserName", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_URL, "url", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_DOWNLOAD, "directory", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_FILENAME, "filename", serviceTask, propertiesNode);

		} else if ("customwebcapture".equalsIgnoreCase(serviceTask.getType())) {

			setPropertyFieldValue(PROPERTY_BROWSERTASK_NAME, "browserName", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_FIELD, "field", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_DOWNLOAD, "directory", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_FILENAME, "filename", serviceTask, propertiesNode);

		} else if ("customwebswitch".equalsIgnoreCase(serviceTask.getType())) {

			setPropertyFieldValue(PROPERTY_BROWSERTASK_NAME, "browserName", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_FIELD, "field", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_SWITCH_TYPE, "switchType", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_TITLE, "title", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_MATCH_TYPE, "matchType", serviceTask, propertiesNode);

		} else if ("customwebwait".equalsIgnoreCase(serviceTask.getType())) {

			setPropertyFieldValue(PROPERTY_BROWSERTASK_NAME, "browserName", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_FIELD, "field", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_WAIT_TYPE, "waitType", serviceTask, propertiesNode);

		} else if ("customwebfunction".equalsIgnoreCase(serviceTask.getType())) {

			setPropertyFieldValue(PROPERTY_BROWSERTASK_NAME, "browserName", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_FIELD, "field", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_FUNCTION, "function", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_RET_RESULT_VARIABLE, "resultVariable", serviceTask,
					propertiesNode);

		} else if ("customwebalert".equalsIgnoreCase(serviceTask.getType())) {

			setPropertyFieldValue(PROPERTY_BROWSERTASK_NAME, "browserName", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_ALERT_TYPE, "alertType", serviceTask, propertiesNode);

		} else if ("customwebset".equalsIgnoreCase(serviceTask.getType())) {

			setPropertyFieldValue(PROPERTY_BROWSERTASK_NAME, "browserName", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_FIELD, "field", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_TEXT_TYPE, "textType", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_TEXT, "text", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_ATTR_NAME, "attrName", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_ITEM_VISIBLE, "visible", serviceTask, propertiesNode);

		} else if ("customwebget".equalsIgnoreCase(serviceTask.getType())) {

			setPropertyFieldValue(PROPERTY_BROWSERTASK_NAME, "browserName", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_FIELD, "field", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_TEXT_TYPE, "textType", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_ATTR_NAME, "attrName", serviceTask, propertiesNode);
			setPropertyFieldValue(PROPERTY_BROWSERTASK_RET_RESULT_VARIABLE, "resultVariable", serviceTask,
					propertiesNode);

		}

//  } else if ("customexcelapp".equalsIgnoreCase(serviceTask.getType())) {
//  	setPropertyFieldValue(PROPERTY_EXCELTASK_FILENAME, "filename", serviceTask, propertiesNode);
//      setPropertyFieldValue(PROPERTY_EXCELTASK_RET_EXCEL_NAME, "resultVariable", serviceTask, propertiesNode);
//  }else if ("customexcelclose".equalsIgnoreCase(serviceTask.getType())) {
//  	setPropertyFieldValue(PROPERTY_EXCELTASK_FILENAME, "filename", serviceTask, propertiesNode);
//      setPropertyFieldValue(PROPERTY_EXCELTASK_NAME, "excelName", serviceTask, propertiesNode);
//  } else if ("customexcelget".equalsIgnoreCase(serviceTask.getType())) {
//      setPropertyFieldValue(PROPERTY_EXCELTASK_NAME, "excelName", serviceTask, propertiesNode);
//      setPropertyFieldValue(PROPERTY_EXCELTASK_SHEET, "sheet", serviceTask, propertiesNode);
//      setPropertyFieldValue(PROPERTY_EXCELTASK_RESULT_VARIABLE, "resultVariable", serviceTask, propertiesNode);
//      setPropertyFieldValue(PROPERTY_EXCELTASK_GETACTION, "action", serviceTask, propertiesNode);
//      setPropertyFieldValue(PROPERTY_EXCELTASK_ROW, "row", serviceTask, propertiesNode);
//      setPropertyFieldValue(PROPERTY_EXCELTASK_COL, "col", serviceTask, propertiesNode);
//  } else if ("customexcelset".equalsIgnoreCase(serviceTask.getType())) {
//      setPropertyFieldValue(PROPERTY_EXCELTASK_NAME, "excelName", serviceTask, propertiesNode);
//      setPropertyFieldValue(PROPERTY_EXCELTASK_VALUE, "value", serviceTask, propertiesNode);
//      setPropertyFieldValue(PROPERTY_EXCELTASK_SHEET, "sheet", serviceTask, propertiesNode);
//      setPropertyFieldValue(PROPERTY_EXCELTASK_GETACTION, "action", serviceTask, propertiesNode);
//      setPropertyFieldValue(PROPERTY_EXCELTASK_ROW, "row", serviceTask, propertiesNode);
//      setPropertyFieldValue(PROPERTY_EXCELTASK_COL, "col", serviceTask, propertiesNode);
//  }else if ("customscrapexcel".equalsIgnoreCase(serviceTask.getType())) {
//      setPropertyFieldValue(PROPERTY_EXCELTASK_NAME, "excelName", serviceTask, propertiesNode);
//      setPropertyFieldValue(PROPERTY_EXCELTASK_RANGE, "range", serviceTask, propertiesNode);
//      setPropertyFieldValue(PROPERTY_EXCELTASK_SHEET, "sheet", serviceTask, propertiesNode);
//      setPropertyFieldValue(PROPERTY_EXCELTASK_FILTER, "filter", serviceTask, propertiesNode);
//      setPropertyFieldValue(PROPERTY_EXCELTASK_FETCH_DATA, "fetchData", serviceTask, propertiesNode);
//      setPropertyFieldValue(PROPERTY_EXCELTASK_FIRST_RECORD_HEADER, "firstRecordHeader", serviceTask, propertiesNode);
//      setPropertyFieldValue(PROPERTY_EXCELTASK_FIRST_RECORD_NUM, "firstRecordNum", serviceTask, propertiesNode);
//      setPropertyFieldValue(PROPERTY_SCRAPTASK_NAME, "scrapName", serviceTask, propertiesNode);
//  }else if ("customscrapbrowser".equalsIgnoreCase(serviceTask.getType())) {
//  	System.out.println("ServiceTaskJsonConverter-convertElementToJSON");
//  	setPropertyFieldValue(PROPERTY_BROWSERTASK_NAME, "browserName", serviceTask, propertiesNode);
//  	setPropertyFieldValue(PROPERTY_BROWSERTASK_TABLE, "table", serviceTask, propertiesNode);
//  	setPropertyFieldValue(PROPERTY_BROWSERTASK_HEADER, "header", serviceTask, propertiesNode);
//  	setPropertyFieldValue(PROPERTY_BROWSERTASK_ROW, "row", serviceTask, propertiesNode);
//  	setPropertyFieldValue(PROPERTY_BROWSERTASK_COL, "col", serviceTask, propertiesNode);
//  	setPropertyFieldValue(PROPERTY_BROWSERTASK_AD, "ad", serviceTask, propertiesNode);
//  	setPropertyFieldValue(PROPERTY_BROWSERTASK_FIRSTRECORDHEADER, "firstRecordHeader", serviceTask, propertiesNode);
//  	setPropertyFieldValue(PROPERTY_BROWSERTASK_INITIALCLICK, "initialClick", serviceTask, propertiesNode);
//  	setPropertyFieldValue(PROPERTY_BROWSERTASK_POPUP, "popup", serviceTask, propertiesNode);
//  	setPropertyFieldValue(PROPERTY_BROWSERTASK_WAIT, "wait", serviceTask, propertiesNode);
//  	setPropertyFieldValue(PROPERTY_BROWSERTASK_NEXTPAGE, "nextPage", serviceTask, propertiesNode);
//      setPropertyFieldValue(PROPERTY_SCRAPTASK_NAME, "scrapName", serviceTask, propertiesNode);
//  } else if ("customscrapget".equalsIgnoreCase(serviceTask.getType())) {
//      setPropertyFieldValue(PROPERTY_SCRAPTASK_NAME, "scrapName", serviceTask, propertiesNode);
//      setPropertyFieldValue(PROPERTY_EXCELTASK_RESULT_VARIABLE, "resultVariable", serviceTask, propertiesNode);
//      setPropertyFieldValue(PROPERTY_EXCELTASK_GETACTION, "action", serviceTask, propertiesNode);
//      setPropertyFieldValue(PROPERTY_EXCELTASK_ROW, "row", serviceTask, propertiesNode);
//      setPropertyFieldValue(PROPERTY_EXCELTASK_COL, "col", serviceTask, propertiesNode);
//  } else if ("customscrapexportcsv".equalsIgnoreCase(serviceTask.getType())) {
//      setPropertyFieldValue(PROPERTY_SCRAPTASK_NAME, "scrapName", serviceTask, propertiesNode);
//      setPropertyFieldValue(PROPERTY_SCRAPTASK_FILENAME, "fileName", serviceTask, propertiesNode);
//      setPropertyFieldValue(PROPERTY_SCRAPTASK_DELIMETER, "delimeter", serviceTask, propertiesNode);
//      setPropertyFieldValue(PROPERTY_SCRAPTASK_APPEND, "append", serviceTask, propertiesNode);

	}

}
