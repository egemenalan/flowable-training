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

public class open implements JavaDelegate {

	private static final long serialVersionUID = 1L;

    private Expression url;
    private Expression type;
    private Expression resultVariable;
    private Expression pageLoad;
    private Expression download;
    private Expression timeout;
    private Expression profilePath;
    private Expression maximize;

    @Override
    public void execute(DelegateExecution execution) {
    	String urlValue = this.getStringFromField(this.url, execution);
    	String resultVariableValue = this.getStringFromField(this.resultVariable, execution);
    	String type = this.getStringFromField(this.type, execution);
    	String pageLoad = this.getStringFromField(this.pageLoad, execution);
    	String download = this.getStringFromField(this.download, execution);
    	String timeout = this.getStringFromField(this.timeout, execution);
    	String profilePath = this.getStringFromField(this.profilePath, execution);
    	String maximize = this.getStringFromField(this.maximize, execution);
    	String usernameValue = null;
    	String passwordValue = null;
    	String browserId = null;

    	System.out.println("Web Application Open Browser Parameters");
    	System.out.println("Url :"+urlValue);
    	System.out.println("BrowserType :"+type);
    	System.out.println("BrowserName :"+resultVariableValue);
    	System.out.println("PageLoad :"+pageLoad);
    	System.out.println("maximize :"+maximize);
    	System.out.println("Timeout :"+timeout);
    	System.out.println("Download Dir :"+download);
    	System.out.println("Profile Dir :"+profilePath);
    	HttpClientBuilder clientBuilder = HttpClientBuilder.create();

        if (usernameValue != null && passwordValue != null) {
            CredentialsProvider provider = new BasicCredentialsProvider();
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(usernameValue, passwordValue);
            provider.setCredentials(new AuthScope("localhost", -1, "mule-realm"), credentials);
            clientBuilder.setDefaultCredentialsProvider(provider);
        }

        HttpClient client = clientBuilder.build();

        HttpPost request = new HttpPost("http://localhost:8080/daphne-services-web/open");

        try {
        	ObjectMapper mapper = new ObjectMapper();
        	ObjectNode node = mapper.createObjectNode();
        	node.put("action", "OPEN");
        	node.put("type", type);
        	node.put("url", urlValue);
        	node.put("pageload", pageLoad);
        	node.put("maximize", maximize);
        	node.put("directory", download);
        	node.put("timeout", timeout);
        	node.put("profilepath", profilePath);
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
	            if (resultVariableValue != null) {
	                execution.setVariable(resultVariableValue, browserId);
	            }
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

    public Expression getUrl() {
        return url;
    }

    public void setUrl(Expression url) {
        this.url = url;
    }

    public Expression getType() {
        return type;
    }

    public void setType(Expression type) {
        this.type = type;
    }

    public Expression getResultVariable() {
        return resultVariable;
    }

    public void setResultVariable(Expression resultVariable) {
        this.resultVariable = resultVariable;
    }

    public Expression getPageLoad() {
        return pageLoad;
    }
    public void setPageLoad(Expression pageLoad) {
        this.pageLoad = pageLoad;
    }

    public Expression getDownload() {
        return download;
    }
    public void setDownload(Expression download) {
        this.download = download;
    }

    public Expression getTimeout() {
        return timeout;
    }
    public void setTimeout(Expression timeout) {
        this.timeout = timeout;
    }

    public Expression getProfilePath() {
        return profilePath;
    }
    public void setProfilePath(Expression profilePath) {
        this.profilePath = profilePath;
    }

    public Expression getMaximize() {
        return maximize;
    }
    public void setMaximize(Expression maximize) {
        this.maximize = maximize;
    }
}
