/**
 * (c) 2003-2014 MuleSoft, Inc. The software in this package is published under the terms of the CPAL v1.0 license,
 * a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.weatherapi;

import java.io.IOException;

import org.mule.api.ConnectionException;
import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.Connect;
import org.mule.api.annotations.ConnectionIdentifier;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Disconnect;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.ReconnectOn;
import org.mule.api.annotations.ValidateConnection;
import org.mule.api.annotations.display.Password;
import org.mule.api.annotations.param.ConnectionKey;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.rest.HttpMethod;
import org.mule.api.annotations.rest.RestCall;
import org.mule.api.annotations.rest.RestUriParam;
import org.mule.modules.weatherapi.entities.WeatherAPIInfo;
import org.mule.modules.weatherapi.exception.WeatherAPIRestConnectorException;

/**
 * Anypoint Connector
 *
 * @author MuleSoft, Inc.
 */
@Connector(name="weather-api", schemaVersion="1.0", friendlyName="WeatherAPI")
public abstract class WeatherAPIConnector
{
	@Configurable
	@RestUriParam("url")
	@Default("http://localhost:8089")
	private String url;

     public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
     * Configurable
     */
    @Configurable
    @Default("value")
    private String country;

    @Configurable
    @Default("value")
    private String city;
    
    public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		country = country;
	}




    /**
     * Custom processor
     *
     * {@sample.xml ../../../doc/weather-api-connector.xml.sample weather-api:my-processor}
     *
     * @param content Content to be processed
     * @return Some string
     * @throws IOException Comment for Exception
     */
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(uri="https://api.openweathermap.org/data/2.5/weather?q=Hyderabad,India", method=HttpMethod.GET,contentType = "application/json")
    public abstract void myProcessor(@RestUriParam("content") String content) throws IOException;  

   
    /**
     * Custom processor
     *
     * {@sample.xml ../../../doc/weather-api-connector.xml.sample weather-api:weather}
     *
     * @param content Content to be processed
     * @return Some string
     * @throws IOException Comment for Exception
     */
    @Processor
     public Object weather(@RestUriParam("content") String content) throws  IOException {
        //return getClient().getAccountInfo();
    	return "SAMPLE";
    }
    /**
     * Connect
     *
     * @param username A username
     * @param password A password
     * @throws ConnectionException
     */
    @Connect
    public void connect(@ConnectionKey String username, @Password String password)
        throws ConnectionException {
        /*
         * CODE FOR ESTABLISHING A CONNECTION GOES IN HERE
         */
    }

    /**
     * Disconnect
     */
    @Disconnect
    public void disconnect() {
        /*
         * CODE FOR CLOSING A CONNECTION GOES IN HERE
         */
    }

    /**
     * Are we connected
     */
    @ValidateConnection
    public boolean isConnected() {
        //TODO: Change it to reflect that we are connected.
        return false;
    }

    /**
     * Are we connected
     */
    @ConnectionIdentifier
    public String connectionId() {
        return "001";
    }

	public int getApiVersion() {
		// TODO Auto-generated method stub
		return 1;
	}
}