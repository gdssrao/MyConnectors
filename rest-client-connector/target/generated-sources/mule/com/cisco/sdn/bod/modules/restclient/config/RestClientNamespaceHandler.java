
package com.cisco.sdn.bod.modules.restclient.config;

import javax.annotation.Generated;
import org.mule.config.MuleManifest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;


/**
 * Registers bean definitions parsers for handling elements in <code>http://www.mulesoft.org/schema/mule/rest-client</code>.
 * 
 */
@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.7.0", date = "2015-09-11T12:35:09+05:30", comments = "Build mule-devkit-3.7.0.2589.361fd9f")
public class RestClientNamespaceHandler
    extends NamespaceHandlerSupport
{

    private static Logger logger = LoggerFactory.getLogger(RestClientNamespaceHandler.class);

    private void handleException(String beanName, String beanScope, NoClassDefFoundError noClassDefFoundError) {
        String muleVersion = "";
        try {
            muleVersion = MuleManifest.getProductVersion();
        } catch (Exception _x) {
            logger.error("Problem while reading mule version");
        }
        logger.error(((((("Cannot launch the mule app, the  "+ beanScope)+" [")+ beanName)+"] within the connector [rest-client] is not supported in mule ")+ muleVersion));
        throw new FatalBeanException(((((("Cannot launch the mule app, the  "+ beanScope)+" [")+ beanName)+"] within the connector [rest-client] is not supported in mule ")+ muleVersion), noClassDefFoundError);
    }

    /**
     * Invoked by the {@link DefaultBeanDefinitionDocumentReader} after construction but before any custom elements are parsed. 
     * @see NamespaceHandlerSupport#registerBeanDefinitionParser(String, BeanDefinitionParser)
     * 
     */
    public void init() {
        try {
            this.registerBeanDefinitionParser("config", new RestClientConnectorConnectorConfigConfigDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("config", "@Config", ex);
        }
        try {
            this.registerBeanDefinitionParser("invoke_api", new Invoke_apiDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("invoke_api", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get", new GetDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("post", new PostDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("post", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("put", new PutDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("put", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("patch", new PatchDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("patch", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("delete", new DeleteDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("delete", "@Processor", ex);
        }
    }

}
