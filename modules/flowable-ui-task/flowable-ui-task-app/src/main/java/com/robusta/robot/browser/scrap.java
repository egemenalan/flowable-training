package com.robusta.robot.browser;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.flowable.common.engine.api.FlowableException;
import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.engine.delegate.BpmnError;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class scrap implements JavaDelegate {

	private static final long serialVersionUID = 1L;

    private Expression browserId;
    private Expression table;
    private Expression header;
    private Expression row;
    private Expression col;
    private Expression ad;
    private Expression firstRecorderHeader;
    private Expression popup;
    private Expression nextPage;
    

	
    @Override
    public void execute(DelegateExecution execution) {
    	String browserId = this.getStringFromField(this.browserId, execution);
    	String table = this.getStringFromField(this.table, execution);
    	String header = this.getStringFromField(this.header, execution);
    	String row = this.getStringFromField(this.row, execution);
    	String col = this.getStringFromField(this.col, execution);
    	String ad = this.getStringFromField(this.ad, execution);
    	String firstRecorderHeader = this.getStringFromField(this.firstRecorderHeader, execution);
    	String popup = this.getStringFromField(this.popup, execution);
    	String nextPage = this.getStringFromField(this.nextPage, execution);
    	
    	String usernameValue = null;
    	String passwordValue = null;


    	System.out.println("Web Application Scrap Parameters");
    	System.out.println("browserId :"+browserId);
    	System.out.println("table :"+table);
    	System.out.println("header :"+header);
    	System.out.println("row :"+row);
    	System.out.println("col :"+col);
    	System.out.println("ad :"+ad);
    	System.out.println("firstRecorderHeader :"+firstRecorderHeader);
    	System.out.println("popup :"+popup);
    	System.out.println("nextPage :"+nextPage);
    	
    	HttpClientBuilder clientBuilder = HttpClientBuilder.create();

        if (usernameValue != null && passwordValue != null) {
            CredentialsProvider provider = new BasicCredentialsProvider();
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(usernameValue, passwordValue);
            provider.setCredentials(new AuthScope("localhost", -1, "mule-realm"), credentials);
            clientBuilder.setDefaultCredentialsProvider(provider);
        }

        HttpClient client = clientBuilder.build();

        HttpPost request = new HttpPost( ClientConstants.STENCIL_TASK_WEBHOST_URL + "scrap");

        try {
        	ObjectMapper mapper = new ObjectMapper();
        	ObjectNode node = mapper.createObjectNode();
     	    node.put("browserId", browserId);
        	node.put("table", table);
        	node.put("header", header);
        	node.put("row", row);
        	node.put("col", col);
        	node.put("ad", ad);
        	node.put("firstRecorderHeader",firstRecorderHeader);
        	node.put("popup",popup);
        	node.put("nextPage", nextPage);
        	
        	
        	System.out.println(node.toString());
        	StringEntity input = new StringEntity(node.toString(),"UTF-8");
    		input.setContentType("application/json");
    		input.setContentEncoding("UTF-8");
            request.setEntity(input);

        } catch (Exception e) {
        	throw new BpmnError("WA-0001", "Error setting message payload");
        }
		String retVal = "";
		try {
			HttpResponse response = client.execute(request);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
			   retVal += line;
			}
			rd.close();
			System.out.println("Sonuc :"+retVal);
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(retVal);
			JsonNode jResult = rootNode.path("result");
			JsonNode jValue = rootNode.path("value");
			String sResult = jResult.asText();
			browserId = jValue.asText();
			if (sResult.equals("success")) {
//	            if (resultVariableValue != null) {
//	                execution.setVariable(resultVariableValue, browserId);
//	            }
			} else {
	        	throw new BpmnError("WA-0002", "Error on opening page "+browserId);
			}
			
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("Exception:"+e.getMessage());
        	throw new BpmnError("WA-0003", "Error on connecting "+e.getMessage());
		}
    }

    protected String getStringFromField(Expression expression, DelegateExecution execution) {
        if (expression != null) {
            Object value = expression.getValue(execution);
            if (value != null) {
                return value.toString();
            }
        }
        return null;
    }

	public Expression getBrowserId() {
		return browserId;
	}

	public void setBrowserId(Expression browserId) {
		this.browserId = browserId;
	}

	public Expression getTable() {
		return table;
	}

	public void setTable(Expression table) {
		this.table = table;
	}

	public Expression getHeader() {
		return header;
	}

	public void setHeader(Expression header) {
		this.header = header;
	}

	public Expression getRow() {
		return row;
	}

	public void setRow(Expression row) {
		this.row = row;
	}

	public Expression getCol() {
		return col;
	}

	public void setCol(Expression col) {
		this.col = col;
	}

	public Expression getAd() {
		return ad;
	}

	public void setAd(Expression ad) {
		this.ad = ad;
	}

	public Expression getFirstRecorderHeader() {
		return firstRecorderHeader;
	}

	public void setFirstRecorderHeader(Expression firstRecorderHeader) {
		this.firstRecorderHeader = firstRecorderHeader;
	}

	public Expression getPopup() {
		return popup;
	}

	public void setPopup(Expression popup) {
		this.popup = popup;
	}

	public Expression getNextPage() {
		return nextPage;
	}

	public void setNextPage(Expression nextPage) {
		this.nextPage = nextPage;
	}

   
    
}
