package com.cisco.sdn.bod.modules.restclient;

import org.codehaus.jettison.json.JSONObject;
import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.lifecycle.Start;
import org.mule.api.annotations.param.Payload;
import org.springframework.beans.factory.annotation.Autowired;

import com.cisco.sdn.bod.modules.restclient.config.ConnectorConfig;
import com.cisco.sdn.bod.modules.restclient.exception.ConnectorException;

@Connector(name = "rest-client", friendlyName = "RestClient")
public class RestClientConnector {

	@Config
	ConnectorConfig config;

	@Autowired
	ImplRestClient restClient;

	@Start
	public void init() {
		restClient = new ImplRestClient(config);
	}

	@Processor
	public JSONObject invoke_api(@Payload Object payload)
			throws ConnectorException {
		return restClient.invoke_api(this.config, payload);
	}

	@Processor
	public JSONObject get() throws ConnectorException {
		return restClient.get(this.config);
	}

	@Processor
	public JSONObject post(@Payload Object payload) throws ConnectorException {
		return restClient.post(this.config, payload);
	}

	@Processor
	public JSONObject put(@Payload Object payload) throws ConnectorException {
		return restClient.put(this.config, payload);
	}

	@Processor
	public JSONObject patch(@Payload Object payload) throws ConnectorException {
		return restClient.patch(this.config, payload);
	}

	@Processor
	public JSONObject delete(@Payload Object payload) throws ConnectorException {
		return restClient.delete(this.config, payload);
	}

	public ConnectorConfig getConfig() {
		return config;
	}

	public void setConfig(ConnectorConfig config) {
		this.config = config;
	}

}