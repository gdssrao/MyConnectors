
package org.mule.modules.weatheroath.adapters;

import javax.annotation.Generated;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.devkit.ProcessAdapter;
import org.mule.api.devkit.ProcessTemplate;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.routing.filter.Filter;
import org.mule.modules.weatheroath.WeatherOathConnector;
import org.mule.security.oauth.callback.ProcessCallback;


/**
 * A <code>WeatherOathConnectorProcessAdapter</code> is a wrapper around {@link WeatherOathConnector } that enables custom processing strategies.
 * 
 */
@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.6.1", date = "2015-05-18T07:20:04+05:30", comments = "Build UNNAMED.2405.44720b7")
public class WeatherOathConnectorProcessAdapter
    extends WeatherOathConnectorLifecycleInjectionAdapter
    implements ProcessAdapter<WeatherOathConnectorCapabilitiesAdapter>
{


    public<P >ProcessTemplate<P, WeatherOathConnectorCapabilitiesAdapter> getProcessTemplate() {
        final WeatherOathConnectorCapabilitiesAdapter object = this;
        return new ProcessTemplate<P,WeatherOathConnectorCapabilitiesAdapter>() {


            @Override
            public P execute(ProcessCallback<P, WeatherOathConnectorCapabilitiesAdapter> processCallback, MessageProcessor messageProcessor, MuleEvent event)
                throws Exception
            {
                return processCallback.process(object);
            }

            @Override
            public P execute(ProcessCallback<P, WeatherOathConnectorCapabilitiesAdapter> processCallback, Filter filter, MuleMessage message)
                throws Exception
            {
                return processCallback.process(object);
            }

        }
        ;
    }

}
