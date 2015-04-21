
package org.mule.modules.mymulelogger.adapters;

import javax.annotation.Generated;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.devkit.ProcessAdapter;
import org.mule.api.devkit.ProcessTemplate;
import org.mule.api.devkit.ProcessTemplate;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.routing.filter.Filter;
import org.mule.modules.mymulelogger.MyMuleLoggerConnector;
import org.mule.security.oauth.callback.ProcessCallback;


/**
 * A <code>MyMuleLoggerConnectorProcessAdapter</code> is a wrapper around {@link MyMuleLoggerConnector } that enables custom processing strategies.
 * 
 */
@Generated(value = "Mule DevKit Version 3.5.1", date = "2015-04-21T10:54:14+05:30", comments = "Build UNNAMED.1967.45d0eb0")
public class MyMuleLoggerConnectorProcessAdapter
    extends MyMuleLoggerConnectorLifecycleAdapter
    implements ProcessAdapter<MyMuleLoggerConnectorCapabilitiesAdapter>
{


    public<P >ProcessTemplate<P, MyMuleLoggerConnectorCapabilitiesAdapter> getProcessTemplate() {
        final MyMuleLoggerConnectorCapabilitiesAdapter object = this;
        return new ProcessTemplate<P,MyMuleLoggerConnectorCapabilitiesAdapter>() {


            @Override
            public P execute(ProcessCallback<P, MyMuleLoggerConnectorCapabilitiesAdapter> processCallback, MessageProcessor messageProcessor, MuleEvent event)
                throws Exception
            {
                return processCallback.process(object);
            }

            @Override
            public P execute(ProcessCallback<P, MyMuleLoggerConnectorCapabilitiesAdapter> processCallback, Filter filter, MuleMessage message)
                throws Exception
            {
                return processCallback.process(object);
            }

        }
        ;
    }

}
