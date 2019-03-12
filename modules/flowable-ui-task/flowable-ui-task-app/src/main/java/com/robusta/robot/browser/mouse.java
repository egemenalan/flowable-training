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

public class mouse implements JavaDelegate {

	private static final long serialVersionUID = 1L;

    /*private Expression url;
    private Expression type;
    private Expression resultVariable;
    private Expression pageLoad;
    private Expression download;
    private Expression timeout;
    private Expression profilePath;
    private Expression maximize;*/
    
    //  mouse
    private Expression browserId;
    private Expression xOffset;
    private Expression yOffset;
    private Expression field;
    private Expression dbl;
    private Expression hover;
    private Expression button;
    private Expression pageload;
    private Expression visible;

    @Override
    public void execute(DelegateExecution execution) {
    	
    	/*String urlValue = this.getStringFromField(this.url, execution);
    	String resultVariableValue = this.getStringFromField(this.resultVariable, execution);
    	String type = this.getStringFromField(this.type, execution);
    	String pageLoad = this.getStringFromField(this.pageLoad, execution);
    	String download = this.getStringFromField(this.download, execution);
    	String timeout = this.getStringFromField(this.timeout, execution);
    	String profilePath = this.getStringFromField(this.profilePath, execution);
    	String maximize = this.getStringFromField(this.maximize, execution);
    	String usernameValue = null;
    	String passwordValue = null;
    	String browserId = null;*/
    	
    	String usernameValue = null;
    	String passwordValue = null;
    	
    	//mouse
       	String browserId = this.getStringFromField(this.browserId, execution);
    	String xOffset = this.getStringFromField(this.xOffset, execution);
    	String yOffset = this.getStringFromField(this.yOffset, execution);
    	String field = this.getStringFromField(this.field, execution);
    	String dbl = this.getStringFromField(this.dbl, execution);
    	String hover = this.getStringFromField(this.hover, execution);
    	String button = this.getStringFromField(this.button, execution);
    	String pageload = this.getStringFromField(this.pageload, execution);
    	String visible = this.getStringFromField(this.visible, execution);  
    	
 	

    	System.out.println("Web Application Mouse Parameters");
    	System.out.println("BrowserId :"+browserId);
    	System.out.println("XOffset :"+xOffset);
    	System.out.println("YOffset :"+yOffset);
    	System.out.println("Field :"+field);
    	System.out.println("Dbl :"+dbl);
    	System.out.println("hover :"+hover);
    	System.out.println("button :"+button);
    	System.out.println("pageload :"+pageload);
    	System.out.println("visible :"+visible);
    	
    	
    	HttpClientBuilder clientBuilder = HttpClientBuilder.create();

        /* Credentials Provider
         * 
         * if (usernameValue != null && passwordValue != null) {
            CredentialsProvider provider = new BasicCredentialsProvider();
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(usernameValue, passwordValue);
            provider.setCredentials(new AuthScope("localhost", -1, "mule-realm"), credentials);
            clientBuilder.setDefaultCredentialsProvider(provider);
        }*/

        HttpClient client = clientBuilder.build();

        HttpPost request = new HttpPost( ClientConstants.STENCIL_TASK_WEBHOST_URL + "mouse");

        try {
        	ObjectMapper mapper = new ObjectMapper();
        	ObjectNode node = mapper.createObjectNode();
        	//node.put("action", "OPEN");
        	/*node.put("type", type);
        	node.put("url", urlValue);
        	node.put("pageload", pageLoad);
        	node.put("maximize", maximize);
        	node.put("directory", download);
        	node.put("timeout", timeout);
        	node.put("profilepath", profilePath);*/
        	
        	node.put("browser",browserId);
        	node.put("xOffset",xOffset);
        	node.put("yOffset",yOffset);
        	node.put("field",field);
        	node.put("dbl",dbl);
        	node.put("hover",hover);
        	node.put("button",button);
            node.put("pageload",pageload);
            
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
	        	throw new BpmnError("WA-0002", "Error mouse action on "+browserId);
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

    
    // mouse

	public Expression getBrowserId() {
		return browserId;
	}

	public void setBrowserId(Expression browserId) {
		this.browserId = browserId;
	}

	public Expression getxOffset() {
		return xOffset;
	}

	public void setxOffset(Expression xOffset) {
		this.xOffset = xOffset;
	}

	public Expression getyOffset() {
		return yOffset;
	}

	public void setyOffset(Expression yOffset) {
		this.yOffset = yOffset;
	}

	public Expression getField() {
		return field;
	}

	public void setField(Expression field) {
		this.field = field;
	}

	public Expression getDbl() {
		return dbl;
	}

	public void setDbl(Expression dbl) {
		this.dbl = dbl;
	}

	public Expression getHover() {
		return hover;
	}

	public void setHover(Expression hover) {
		this.hover = hover;
	}

	public Expression getButton() {
		return button;
	}

	public void setButton(Expression button) {
		this.button = button;
	}

	public Expression getPageload() {
		return pageload;
	}

	public void setPageload(Expression pageload) {
		this.pageload = pageload;
	}

	public Expression getVisible() {
		return visible;
	}

	public void setVisible(Expression visible) {
		this.visible = visible;
	}
    
    
    //mouse
    
    
}
