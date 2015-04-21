
package org.mule.modules.mymulelogger.adapters;

import javax.annotation.Generated;
import org.mule.api.MuleContext;
import org.mule.api.context.MuleContextAware;
import org.mule.modules.mymulelogger.MyMuleLoggerConnector;


/**
 * A <code>MyMuleLoggerConnectorInjectionAdapter</code> is a wrapper around {@link MyMuleLoggerConnector } that allows the injection of several Mule facilities when a MuleContext is set.
 * 
 */
@Generated(value = "Mule DevKit Version 3.5.1", date = "2015-04-21T10:54:14+05:30", comments = "Build UNNAMED.1967.45d0eb0")
public class MyMuleLoggerConnectorInjectionAdapter
    extends MyMuleLoggerConnectorProcessAdapter
    implements MuleContextAware
{


    @Override
    public void setMuleContext(MuleContext context) {
        super.setMuleContext(context);
        super.setRegistry(context.getRegistry());
    }

}
