
package com.cisco.sdn.bod.modules.restclient.adapters;

import com.cisco.sdn.bod.modules.restclient.RestClientConnector;
import javax.annotation.Generated;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.devkit.ProcessAdapter;
import org.mule.api.devkit.ProcessTemplate;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.routing.filter.Filter;
import org.mule.security.oauth.callback.ProcessCallback;


/**
 * A <code>RestClientConnectorProcessAdapter</code> is a wrapper around {@link RestClientConnector } that enables custom processing strategies.
 * 
 */
@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.7.0", date = "2015-09-11T12:35:09+05:30", comments = "Build mule-devkit-3.7.0.2589.361fd9f")
public class RestClientConnectorProcessAdapter
    extends RestClientConnectorLifecycleInjectionAdapter
    implements ProcessAdapter<RestClientConnectorCapabilitiesAdapter>
{


    public<P >ProcessTemplate<P, RestClientConnectorCapabilitiesAdapter> getProcessTemplate() {
        final RestClientConnectorCapabilitiesAdapter object = this;
        return new ProcessTemplate<P,RestClientConnectorCapabilitiesAdapter>() {


            @Override
            public P execute(ProcessCallback<P, RestClientConnectorCapabilitiesAdapter> processCallback, MessageProcessor messageProcessor, MuleEvent event)
                throws Exception
            {
                return processCallback.process(object);
            }

            @Override
            public P execute(ProcessCallback<P, RestClientConnectorCapabilitiesAdapter> processCallback, Filter filter, MuleMessage message)
                throws Exception
            {
                return processCallback.process(object);
            }

        }
        ;
    }

}
