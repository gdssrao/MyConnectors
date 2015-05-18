/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under the terms of the CPAL v1.0 license,
 * a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.weatheroath;

import org.mule.modules.tests.ConnectorTestCase;

import org.junit.Test;

public class WeatherOathConnectorTest extends ConnectorTestCase {
    
    @Override
    protected String getConfigResources() {
        return "weather-oath-config.xml";
    }

    //@Test
    public void testFlow() throws Exception {
        runFlowAndExpect("testFlow", "Hi Foo. How are you?");
    }
}
