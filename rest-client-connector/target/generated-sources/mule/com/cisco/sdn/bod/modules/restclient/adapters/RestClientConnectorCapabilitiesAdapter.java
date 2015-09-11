
package com.cisco.sdn.bod.modules.restclient.adapters;

import com.cisco.sdn.bod.modules.restclient.RestClientConnector;
import javax.annotation.Generated;
import org.mule.api.devkit.capability.Capabilities;
import org.mule.api.devkit.capability.ModuleCapability;


/**
 * A <code>RestClientConnectorCapabilitiesAdapter</code> is a wrapper around {@link RestClientConnector } that implements {@link org.mule.api.Capabilities} interface.
 * 
 */
@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.7.0", date = "2015-09-11T12:35:09+05:30", comments = "Build mule-devkit-3.7.0.2589.361fd9f")
public class RestClientConnectorCapabilitiesAdapter
    extends RestClientConnector
    implements Capabilities
{


    /**
     * Returns true if this module implements such capability
     * 
     */
    public boolean isCapableOf(ModuleCapability capability) {
        if (capability == ModuleCapability.LIFECYCLE_CAPABLE) {
            return true;
        }
        return false;
    }

}
