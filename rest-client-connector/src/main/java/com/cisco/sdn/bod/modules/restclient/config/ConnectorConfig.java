package com.cisco.sdn.bod.modules.restclient.config;

import org.mule.api.annotations.components.Configuration;
import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.param.Default;

@Configuration(friendlyName = "Configuration")
public class ConnectorConfig {

	/**
     * Host
     */
    @Configurable
    @Default("7.16.0.6")
    private String host;
    
    /**
     * Port
     */
    @Configurable
    @Default("7777")
    private String port;
    
    /**
     * Rest API Path
     */
    @Configurable
    @Default("/wae/network/modeled/entities/node/get-all-nodes")
    private String path;
    
    /**
     * Http method
     */
    @Configurable
    @Default("GET")
    private String httpMethod;
    
    /**
     * Get Host
     * @return
     */
	public String getHost() {
		return host;
	}

	/**
	 * Set Host
	 * @param host
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * Get Port
	 * @return
	 */
	public String getPort() {
		return port;
	}

	/**
	 * Set Port
	 * @param port
	 */
	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * Get Path
	 * @return
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Set Path
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

}