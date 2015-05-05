
package org.mule.modules.weatherapi.adapters;

import javax.annotation.Generated;
import org.mule.modules.weatherapi.WeatherAPIConnector;
import org.mule.modules.weatherapi.connection.Connection;


/**
 * A <code>WeatherAPIConnectorConnectionIdentifierAdapter</code> is a wrapper around {@link WeatherAPIConnector } that implements {@link org.mule.devkit.dynamic.api.helper.Connection} interface.
 * 
 */
@Generated(value = "Mule DevKit Version 3.5.1", date = "2015-05-06T01:14:42+05:30", comments = "Build UNNAMED.1967.45d0eb0")
public abstract class WeatherAPIConnectorConnectionIdentifierAdapter
    extends WeatherAPIConnectorProcessAdapter
    implements Connection
{


    public String getConnectionIdentifier() {
        return super.connectionId();
    }

}
