
package org.mule.modules.weatherapi.connectivity;

import javax.annotation.Generated;
import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.devkit.ProcessInterceptor;
import org.mule.api.devkit.ProcessTemplate;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.routing.filter.Filter;
import org.mule.modules.weatherapi.adapters.WeatherAPIConnectorConnectionIdentifierAdapter;
import org.mule.modules.weatherapi.connection.ConnectionManager;
import org.mule.modules.weatherapi.process.ManagedConnectionProcessInterceptor;
import org.mule.security.oauth.callback.ProcessCallback;
import org.mule.security.oauth.process.ProcessCallbackProcessInterceptor;
import org.mule.security.oauth.process.RetryProcessInterceptor;

@Generated(value = "Mule DevKit Version 3.5.1", date = "2015-05-06T01:14:42+05:30", comments = "Build UNNAMED.1967.45d0eb0")
public class ManagedConnectionProcessTemplate<P >implements ProcessTemplate<P, WeatherAPIConnectorConnectionIdentifierAdapter>
{

    private final ProcessInterceptor<P, WeatherAPIConnectorConnectionIdentifierAdapter> processInterceptor;

    public ManagedConnectionProcessTemplate(ConnectionManager<WeatherAPIConnectorConnectionKey, WeatherAPIConnectorConnectionIdentifierAdapter> connectionManager, MuleContext muleContext) {
        ProcessInterceptor<P, WeatherAPIConnectorConnectionIdentifierAdapter> processCallbackProcessInterceptor = new ProcessCallbackProcessInterceptor<P, WeatherAPIConnectorConnectionIdentifierAdapter>();
        ProcessInterceptor<P, WeatherAPIConnectorConnectionIdentifierAdapter> managedConnectionProcessInterceptor = new ManagedConnectionProcessInterceptor<P>(processCallbackProcessInterceptor, connectionManager, muleContext);
        ProcessInterceptor<P, WeatherAPIConnectorConnectionIdentifierAdapter> retryProcessInterceptor = new RetryProcessInterceptor<P, WeatherAPIConnectorConnectionIdentifierAdapter>(managedConnectionProcessInterceptor, muleContext, connectionManager.getRetryPolicyTemplate());
        processInterceptor = retryProcessInterceptor;
    }

    public P execute(ProcessCallback<P, WeatherAPIConnectorConnectionIdentifierAdapter> processCallback, MessageProcessor messageProcessor, MuleEvent event)
        throws Exception
    {
        return processInterceptor.execute(processCallback, null, messageProcessor, event);
    }

    public P execute(ProcessCallback<P, WeatherAPIConnectorConnectionIdentifierAdapter> processCallback, Filter filter, MuleMessage message)
        throws Exception
    {
        return processInterceptor.execute(processCallback, null, filter, message);
    }

}
