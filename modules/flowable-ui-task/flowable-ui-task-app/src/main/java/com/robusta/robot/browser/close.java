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

public class close implements JavaDelegate {

	private static final long serialVersionUID = 1L;

	private Expression browserName;

    @Override
    public void execute(DelegateExecution execution) {
    	String browserName = this.getStringFromField(this.browserName, execution);
    	String usernameValue = null;
    	String passwordValue = null;

    	HttpClientBuilder clientBuilder = HttpClientBuilder.create();

        if (usernameValue != null && passwordValue != null) {
            CredentialsProvider provider = new BasicCredentialsProvider();
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(usernameValue, passwordValue);
            provider.setCredentials(new AuthScope("localhost", -1, "mule-realm"), credentials);
            clientBuilder.setDefaultCredentialsProvider(provider);
        }

        HttpClient client = clientBuilder.build();

        HttpPost request = new HttpPost("http://localhost:8080/daphne-services-web/close");

        try {
        	ObjectMapper mapper = new ObjectMapper();
        	ObjectNode node = mapper.createObjectNode();
       		node.put("action", "CLOSE");
        	node.put("browserId", browserName);
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
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(retVal);
			JsonNode jResult = rootNode.path("result");
			JsonNode jValue = rootNode.path("value");
			String sResult = jResult.asText();
			String sValue = jValue.asText();
			if (!sResult.equals("success")) {
				throw new BpmnError("WA-0050", "Error on closing browser "+sValue);
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

    public Expression getBrowserName() {
        return browserName;
    }

    public void setBrowserName(Expression browserName) {
        this.browserName = browserName;
    }
    
}
