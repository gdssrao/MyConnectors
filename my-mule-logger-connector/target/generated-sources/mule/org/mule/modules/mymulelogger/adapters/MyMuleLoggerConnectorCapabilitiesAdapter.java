
package org.mule.modules.mymulelogger.adapters;

import javax.annotation.Generated;
import org.mule.api.devkit.capability.Capabilities;
import org.mule.api.devkit.capability.ModuleCapability;
import org.mule.modules.mymulelogger.MyMuleLoggerConnector;


/**
 * A <code>MyMuleLoggerConnectorCapabilitiesAdapter</code> is a wrapper around {@link MyMuleLoggerConnector } that implements {@link org.mule.api.Capabilities} interface.
 * 
 */
@Generated(value = "Mule DevKit Version 3.5.1", date = "2015-04-21T10:54:14+05:30", comments = "Build UNNAMED.1967.45d0eb0")
public class MyMuleLoggerConnectorCapabilitiesAdapter
    extends MyMuleLoggerConnector
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
