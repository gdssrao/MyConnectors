/**
 * (c) 2003-2014 MuleSoft, Inc. The software in this package is published under the terms of the CPAL v1.0 license,
 * a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.weatherapi.client;
import javax.ws.rs.core.MediaType;

import org.mule.modules.weatherapi.WeatherAPIConnector;
import org.mule.modules.weatherapi.exception.WeatherAPIRestConnectorException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class WeatherAPIClient {
	 private Client client;
	    private WebResource apiResource;
	    private WeatherAPIConnector connector;
	 
	    public WeatherAPIClient(WeatherAPIConnector connector) {
	        setConnector(connector);
	 
	        ClientConfig clientConfig = new DefaultClientConfig();
	        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	        this.client = Client.create(clientConfig);
	        this.apiResource = this.client.resource(getConnector().getUrl());
	    }
	    /**
	     * Executes the Dropbox request
	     *
	     */
	    private <T> T execute(WebResource webResource, String method, Class<T> returnClass) throws  WeatherAPIRestConnectorException {
	        ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON).method(method, ClientResponse.class);
	        if(clientResponse.getStatus() == 200) {
	            return clientResponse.getEntity(returnClass);
	        }else {
	            throw new WeatherAPIRestConnectorException(
	                    String.format("ERROR - statusCode: %d - message: %s",
	                            clientResponse.getStatus(), clientResponse.getEntity(String.class)));
	        }
	    }
	    public Client getClient() {
	        return client;
	    }
	 
	    public void setClient(Client client) {
	        this.client = client;
	    }
	 
	   /* public WebResource getApiResource() {
	        return addSignHeader(apiResource);
	    }*/
	 
	    public void setApiResource(WebResource apiResource) {
	        this.apiResource = apiResource;
	    }
	 
	    public WeatherAPIConnector getConnector() {
	        return connector;
	    }
	 
	    public void setConnector(WeatherAPIConnector connector) {
	        this.connector = connector;
	    }
	}