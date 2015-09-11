package com.cisco.sdn.bod.modules.restclient;

import static com.cisco.sdn.bod.modules.restclient.util.ConnectorConstants.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.cisco.sdn.bod.modules.restclient.config.ConnectorConfig;
import com.cisco.sdn.bod.modules.restclient.exception.ConnectorException;
import com.cisco.sdn.bod.modules.restclient.util.ConnectorConstants;
import com.cisco.sdn.bod.modules.restclient.util.Utils;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class ImplRestClient {

	private static Logger LOG = Logger.getLogger(ImplRestClient.class);

	private Client client;

	public ImplRestClient(ConnectorConfig config) {
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
				Boolean.TRUE);
		this.client = Client.create(clientConfig);
	}

	public JSONObject invoke_api(ConnectorConfig config, Object payload)
			throws ConnectorException {
		return processRequest(config.getHttpMethod(), config, payload);
	}

	public JSONObject get(ConnectorConfig config) throws ConnectorException {
		return processRequest(HttpMethod.GET, config, null);
	}

	public JSONObject post(ConnectorConfig config, Object payload)
			throws ConnectorException {
		return processRequest(HttpMethod.POST, config, payload);
	}

	public JSONObject put(ConnectorConfig config, Object payload)
			throws ConnectorException {
		return processRequest(HttpMethod.PUT, config, payload);
	}

	public JSONObject patch(ConnectorConfig config, Object payload)
			throws ConnectorException {
		return processRequest(PATCH, config, payload);
	}

	public JSONObject delete(ConnectorConfig config, Object payload)
			throws ConnectorException {
		return processRequest(HttpMethod.DELETE, config, payload);
	}

	private WebResource getWebResource(ConnectorConfig config) {
		return client.resource(Utils.getAPIUrl(config.getHost(),
				config.getPort(), config.getPath()));
	}

	/**
	 * Method to parse the request and invoke execute method.
	 * 
	 * @param httpMethod
	 * @param config
	 * @param payload
	 * @return
	 * @throws ConnectorException
	 */
	public JSONObject processRequest(String httpMethod, ConnectorConfig config,
			Object payload) throws ConnectorException {
		JSONObject object = null;
		JSONObject response = null;
		String exceptionmsg = null;
		try {
			if (payload != null) {
				if (payload instanceof byte[]) {
					byte[] bytes = (byte[]) payload;
					object = new JSONObject(new String(bytes));
					LOG.debug("Converted to json object from bytes " + object);
				} else if (payload instanceof String) {
					object = new JSONObject((String) payload);
					LOG.debug("Converted to json object from string" + object);
				} else if (payload instanceof InputStream) {
					String convertedString = IOUtils
							.toString((InputStream) payload);
					LOG.debug("Converted to string using io utils"
							+ convertedString);
					object = new JSONObject(convertedString);
					LOG.debug("Converted to json object from string"
							+ convertedString);
				} else {
					LOG.error("Unsupported input json format");
					throw new ConnectorException(
							String.format("ERROR - Unsupported input json format "));
				}
			}
			if (HttpMethod.GET.equalsIgnoreCase(httpMethod)) {
				object = null;
			}
			response = execute(getWebResource(config), httpMethod,
					JSONObject.class, object, config);

		} catch (JSONException jsonException) {
			exceptionmsg = String.format("ERROR - JsonException %s ",
					jsonException.getMessage());
			LOG.error(exceptionmsg);
			throw new ConnectorException(exceptionmsg);
		} catch (IOException ioe) {
			exceptionmsg = String
					.format("ERROR - Problem while converting inputstream to json object %s ",
							ioe.getMessage());
			LOG.error(exceptionmsg);
			throw new ConnectorException(exceptionmsg);
		}
		LOG.info("Response from rest-client connector " + response);
		return response;
	}

	/**
	 * Executes the request by using jersey rest API
	 *
	 */
	private <T> T execute(WebResource webResource, String method,
			Class<T> returnClass, Object requestEntity, ConnectorConfig config)
			throws ConnectorException {
		ClientResponse clientResponse = null;
		String message = null;
		try {
			clientResponse = webResource.accept(MediaType.APPLICATION_JSON)
					.method(method, ClientResponse.class, requestEntity);
		} catch (Exception ce) {
			message = String.format(
					"ERROR - Connection refused by host %s - on port: %s",
					config.getHost(), config.getPort());
			LOG.error(message);
			throw new ConnectorException(message);
		}

		if (Arrays.binarySearch(ConnectorConstants.SUCCESS_STATUS,
				clientResponse.getStatus()) != -1) {
			return clientResponse.getEntity(returnClass);
		} else {
			message = String.format("ERROR - statusCode: %d - message: %s",
					clientResponse.getStatus(),
					clientResponse.getEntity(String.class));
			LOG.error(message);
			throw new ConnectorException(message);
		}
	}

	/**
	 * This is to test method
	 * 
	 * @param args
	 * @throws ConnectorException
	 */
	public static void main(String args[]) throws ConnectorException {
		RestClientConnector connector = new RestClientConnector();
		ConnectorConfig config = new ConnectorConfig();

		config.setHost("7.16.0.6");
		config.setPort("7777");
		config.setHttpMethod("POST");
		config.setPath("/wae/demand/calendared/query-what-if/node-to-node");
		connector.setConfig(config);
		connector.init();

		String payload = "{ \"requests\" : [ { \"timeInterval\" : { \"fromTime\" : 1440718452000, \"tillTime\" : 1440721800000 },\"endpoints\" : { \"source\" : { \"name\" : \"PE1.dev.chf.apg\" }, \"destination\" : { \"name\" : \"PE2.dev.chf.apg\" } }, \"bandwidth\" : 1}], \"admissionPlan\" : \"COMPOUND\" }";
		System.out.println(connector.invoke_api(payload));
	}
}
