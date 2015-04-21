
package org.mule.modules.mymulelogger.processors;

import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.devkit.ProcessAdapter;
import org.mule.api.devkit.ProcessTemplate;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.processor.MessageProcessor;
import org.mule.devkit.processor.DevkitBasedMessageProcessor;
import org.mule.modules.mymulelogger.MyMuleLoggerConnector;
import org.mule.modules.mymulelogger.adapters.MyMuleLoggerConnectorInjectionAdapter;
import org.mule.security.oauth.callback.ProcessCallback;


/**
 * DebugMessageProcessor invokes the {@link org.mule.modules.mymulelogger.MyMuleLoggerConnector#debug(java.lang.Object, java.util.Map, java.lang.String, java.lang.String)} method in {@link MyMuleLoggerConnector }. For each argument there is a field in this processor to match it.  Before invoking the actual method the processor will evaluate and transform where possible to the expected argument type.
 * 
 */
@Generated(value = "Mule DevKit Version 3.5.1", date = "2015-04-21T10:54:14+05:30", comments = "Build UNNAMED.1967.45d0eb0")
public class DebugMessageProcessor
    extends DevkitBasedMessageProcessor
    implements MessageProcessor
{

    protected Object payloadObject;
    protected Object _payloadObjectType;
    protected Object headers;
    protected Map<String, String> _headersType;
    protected Object processState;
    protected String _processStateType;
    protected Object logMessage;
    protected String _logMessageType;

    public DebugMessageProcessor(String operationName) {
        super(operationName);
    }

    /**
     * Obtains the expression manager from the Mule context and initialises the connector. If a target object  has not been set already it will search the Mule registry for a default one.
     * 
     * @throws InitialisationException
     */
    public void initialise()
        throws InitialisationException
    {
    }

    @Override
    public void start()
        throws MuleException
    {
        super.start();
    }

    @Override
    public void stop()
        throws MuleException
    {
        super.stop();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    /**
     * Sets headers
     * 
     * @param value Value to set
     */
    public void setHeaders(Object value) {
        this.headers = value;
    }

    /**
     * Sets payloadObject
     * 
     * @param value Value to set
     */
    public void setPayloadObject(Object value) {
        this.payloadObject = value;
    }

    /**
     * Sets processState
     * 
     * @param value Value to set
     */
    public void setProcessState(Object value) {
        this.processState = value;
    }

    /**
     * Sets logMessage
     * 
     * @param value Value to set
     */
    public void setLogMessage(Object value) {
        this.logMessage = value;
    }

    /**
     * Invokes the MessageProcessor.
     * 
     * @param event MuleEvent to be processed
     * @throws Exception
     */
    public MuleEvent doProcess(final MuleEvent event)
        throws Exception
    {
        Object moduleObject = null;
        try {
            moduleObject = findOrCreate(MyMuleLoggerConnectorInjectionAdapter.class, false, event);
            final Object _transformedPayloadObject = ((Object) evaluateAndTransform(getMuleContext(), event, DebugMessageProcessor.class.getDeclaredField("_payloadObjectType").getGenericType(), null, "#[payload]"));
            final Map<String, String> _transformedHeaders = ((Map<String, String> ) evaluateAndTransform(getMuleContext(), event, DebugMessageProcessor.class.getDeclaredField("_headersType").getGenericType(), null, "#[headers:SESSION:messageId?,messageSource?,messageAction?,resourceId?,resourceName?,sourceId?]"));
            final String _transformedProcessState = ((String) evaluateAndTransform(getMuleContext(), event, DebugMessageProcessor.class.getDeclaredField("_processStateType").getGenericType(), null, processState));
            final String _transformedLogMessage = ((String) evaluateAndTransform(getMuleContext(), event, DebugMessageProcessor.class.getDeclaredField("_logMessageType").getGenericType(), null, logMessage));
            final ProcessTemplate<Object, Object> processTemplate = ((ProcessAdapter<Object> ) moduleObject).getProcessTemplate();
            processTemplate.execute(new ProcessCallback<Object,Object>() {


                public List<Class<? extends Exception>> getManagedExceptions() {
                    return null;
                }

                public boolean isProtected() {
                    return false;
                }

                public Object process(Object object)
                    throws Exception
                {
                    ((MyMuleLoggerConnector) object).debug(_transformedPayloadObject, _transformedHeaders, _transformedProcessState, _transformedLogMessage);
                    return null;
                }

            }
            , this, event);
            return event;
        } catch (Exception e) {
            throw e;
        }
    }

}
