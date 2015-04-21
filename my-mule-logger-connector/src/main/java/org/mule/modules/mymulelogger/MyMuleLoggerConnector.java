/**
 * (c) 2003-2014 MuleSoft, Inc. The software in this package is published under the terms of the CPAL v1.0 license,
 * a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.mymulelogger;


import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.mule.api.MuleContext;
import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.Module;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.param.Payload;
import org.mule.api.annotations.param.SessionHeaders;
import org.mule.api.registry.Registry;

/********************************************************************
 * File Name :MyMuleLoggerConnector
 * 
 * @Author:gdssrao@gmail.com
 * Date :14-April-2014
 * 
 ******************************************************************/

@Module(name = "my-mule-logger", friendlyName = "MyMuleLogger")
public class MyMuleLoggerConnector
{
   
    /**
	 * Configurable Service Name
	 */
	@Configurable
	protected String serviceName;

	/**
	 * Configurable Logger Name
	 */
	@Configurable
	protected String loggerName;

	public String getLoggerName() {
		return loggerName;
	}

	public void setLoggerName(String loggerName) {
		this.loggerName = loggerName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/**
	 * Injecting Mule Context for future use
	 */
	@Inject
	protected MuleContext muleContext;
	/**
	 * Injecting Registry for future use
	 */
	@Inject
	protected Registry registry;

	public MuleContext getMuleContext() {
		return muleContext;
	}

	public void setMuleContext(MuleContext muleContext) {
		this.muleContext = muleContext;
	}

	public Registry getRegistry() {
		return registry;
	}

	public void setRegistry(Registry registry) {
		this.registry = registry;
	}

	@PostConstruct
	public void initializeMDCConfiguration() {
		//myMuleLogger = new EnterpriseLogger(loggerName, serviceName);
	}


    /**
	 * {@sample.xml ../../../doc/my-mule-logger-connector.xml.sample my-mule-logger:debug}
	 * 
	 * @param payloadObject     is payload refernce injected to pass the message   and string
	 * @param processState      is process State passed for current state of process
	 * @param logMessage        is log message passed to populate log message
	 */
	@Processor
	@Inject
	public void debug(@Payload Object payloadObject,
			@SessionHeaders("messageId?,messageSource?,messageAction?,resourceId?,resourceName?,sourceId?") Map<String,String> headers,
			String processState, String logMessage) {
		//emLogger.debug(payloadObject, headers, processState, logMessage);
	}

    
}