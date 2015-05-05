
package org.mule.modules.weatherapi.adapters;

import javax.annotation.Generated;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.devkit.ProcessAdapter;
import org.mule.api.devkit.ProcessTemplate;
import org.mule.api.devkit.ProcessTemplate;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.routing.filter.Filter;
import org.mule.modules.weatherapi.WeatherAPIConnector;
import org.mule.security.oauth.callback.ProcessCallback;


/**
 * A <code>WeatherAPIConnectorProcessAdapter</code> is a wrapper around {@link WeatherAPIConnector } that enables custom processing strategies.
 * 
 */
@Generated(value = "Mule DevKit Version 3.5.1", date = "2015-05-06T01:14:42+05:30", comments = "Build UNNAMED.1967.45d0eb0")
public abstract class WeatherAPIConnectorProcessAdapter
    extends WeatherAPIConnectorLifecycleAdapter
    implements ProcessAdapter<WeatherAPIConnectorCapabilitiesAdapter>
{


    public<P >ProcessTemplate<P, WeatherAPIConnectorCapabilitiesAdapter> getProcessTemplate() {
        final WeatherAPIConnectorCapabilitiesAdapter object = this;
        return new ProcessTemplate<P,WeatherAPIConnectorCapabilitiesAdapter>() {


            @Override
            public P execute(ProcessCallback<P, WeatherAPIConnectorCapabilitiesAdapter> processCallback, MessageProcessor messageProcessor, MuleEvent event)
                throws Exception
            {
                return processCallback.process(object);
            }

            @Override
            public P execute(ProcessCallback<P, WeatherAPIConnectorCapabilitiesAdapter> processCallback, Filter filter, MuleMessage message)
                throws Exception
            {
                return processCallback.process(object);
            }

        }
        ;
    }

}
