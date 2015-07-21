/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under the terms of the CPAL v1.0 license,
 * a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.restweather;

import java.io.IOException;

import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.ReconnectOn;
import org.mule.api.annotations.rest.HttpMethod;
import org.mule.api.annotations.rest.RestCall;
import org.mule.api.annotations.rest.RestUriParam;


@Connector(name="rest-weather", friendlyName="RestWeather")
public abstract class RestWeatherConnector {

     /**
     * Custom processor
     *
     * {@sample.xml ../../../doc/rest-weather-connector.xml.sample rest-weather:GetForecastOfCity}
     *
     * @param id Name of a friend we want to greet
     * @return The greeting and reply to the selected friend.
     * @throws IOException Comment for Exception
     */
   //?id=524901&APPID=1111111111
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(uri=" http://api.openweathermap.org/data/2.5/forecast/city?id={id}&APPID={APIKEY}", method=HttpMethod.GET)
    public abstract void GetForecastOfCity(@RestUriParam("id") String id, @RestUriParam("apiKey")String apiKey) throws IOException;  
    /**
     * Custom processor
     *
     * {@sample.xml ../../../doc/rest-weather-connector.xml.sample rest-weather:GetForecastOfCity1}
     *
     * @param id Name of a friend we want to greet
     * @return The greeting and reply to the selected friend.
     * @throws IOException Comment for Exception
     */
   //?id=524901&APPID=1111111111
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(uri=" http://api.openweathermap.org/data/2.5/forecast/city?id=524901&APPID=1111111111", method=HttpMethod.GET)
    public abstract void GetForecastOfCity1(@RestUriParam("id") String id) throws IOException;  

  
}