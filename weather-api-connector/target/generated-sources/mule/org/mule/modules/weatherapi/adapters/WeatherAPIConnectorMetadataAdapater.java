
package org.mule.modules.weatherapi.adapters;

import javax.annotation.Generated;
import org.mule.api.MetadataAware;
import org.mule.modules.weatherapi.WeatherAPIConnector;


/**
 * A <code>WeatherAPIConnectorMetadataAdapater</code> is a wrapper around {@link WeatherAPIConnector } that adds support for querying metadata about the extension.
 * 
 */
@Generated(value = "Mule DevKit Version 3.5.1", date = "2015-05-06T01:14:42+05:30", comments = "Build UNNAMED.1967.45d0eb0")
public abstract class WeatherAPIConnectorMetadataAdapater
    extends WeatherAPIConnectorCapabilitiesAdapter
    implements MetadataAware
{

    private final static String MODULE_NAME = "WeatherAPI";
    private final static String MODULE_VERSION = "1.0.0-SNAPSHOT";
    private final static String DEVKIT_VERSION = "3.5.1";
    private final static String DEVKIT_BUILD = "UNNAMED.1967.45d0eb0";
    private final static String MIN_MULE_VERSION = "3.5.0";

    public String getModuleName() {
        return MODULE_NAME;
    }

    public String getModuleVersion() {
        return MODULE_VERSION;
    }

    public String getDevkitVersion() {
        return DEVKIT_VERSION;
    }

    public String getDevkitBuild() {
        return DEVKIT_BUILD;
    }

    public String getMinMuleVersion() {
        return MIN_MULE_VERSION;
    }

}
