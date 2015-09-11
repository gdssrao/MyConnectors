
package com.cisco.sdn.bod.modules.restclient.adapters;

import com.cisco.sdn.bod.modules.restclient.RestClientConnector;
import javax.annotation.Generated;
import org.mule.api.MetadataAware;


/**
 * A <code>RestClientConnectorMetadataAdapter</code> is a wrapper around {@link RestClientConnector } that adds support for querying metadata about the extension.
 * 
 */
@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.7.0", date = "2015-09-11T12:35:09+05:30", comments = "Build mule-devkit-3.7.0.2589.361fd9f")
public class RestClientConnectorMetadataAdapter
    extends RestClientConnectorCapabilitiesAdapter
    implements MetadataAware
{

    private final static String MODULE_NAME = "RestClient";
    private final static String MODULE_VERSION = "1.0.0";
    private final static String DEVKIT_VERSION = "3.7.0";
    private final static String DEVKIT_BUILD = "mule-devkit-3.7.0.2589.361fd9f";
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
