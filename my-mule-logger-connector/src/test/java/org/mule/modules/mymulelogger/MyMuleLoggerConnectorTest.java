/**
 * (c) 2003-2014 MuleSoft, Inc. The software in this package is published under the terms of the CPAL v1.0 license,
 * a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.mymulelogger;

import org.mule.modules.tests.ConnectorTestCase;

import org.junit.Test;

public class MyMuleLoggerConnectorTest extends ConnectorTestCase {
    
    @Override
    protected String getConfigResources() {
        return "my-mule-logger-config.xml";
    }

  /* // @Test
    public void testFlow() throws Exception {
        runFlowAndExpect("testFlow", "Another string");
    }*/
}
