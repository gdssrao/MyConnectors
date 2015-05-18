
package org.mule.modules.weatheroath.adapters;

import javax.annotation.Generated;
import org.mule.api.MetadataAware;
import org.mule.modules.weatheroath.WeatherOathConnector;


/**
 * A <code>WeatherOathConnectorMetadataAdapater</code> is a wrapper around {@link WeatherOathConnector } that adds support for querying metadata about the extension.
 * 
 */
@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.6.1", date = "2015-05-18T07:20:04+05:30", comments = "Build UNNAMED.2405.44720b7")
public class WeatherOathConnectorMetadataAdapater
    extends WeatherOathConnectorCapabilitiesAdapter
    implements MetadataAware
{

    private final static String MODULE_NAME = "WeatherOath";
    private final static String MODULE_VERSION = "1.0.0-SNAPSHOT";
    private final static String DEVKIT_VERSION = "3.6.1";
    private final static String DEVKIT_BUILD = "UNNAMED.2405.44720b7";
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
