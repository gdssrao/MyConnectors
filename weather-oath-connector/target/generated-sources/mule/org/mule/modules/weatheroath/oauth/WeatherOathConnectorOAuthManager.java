
package org.mule.modules.weatheroath.oauth;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Generated;
import org.apache.commons.pool.KeyedPoolableObjectFactory;
import org.mule.DefaultMuleMessage;
import org.mule.api.MuleMessage;
import org.mule.api.expression.ExpressionManager;
import org.mule.api.store.ObjectStore;
import org.mule.modules.weatheroath.WeatherOathConnector;
import org.mule.modules.weatheroath.adapters.WeatherOathConnectorOAuth2Adapter;
import org.mule.modules.weatheroath.strategy.ConnectorConnectionStrategy;
import org.mule.security.oauth.BaseOAuth2Manager;
import org.mule.security.oauth.OAuth2Adapter;
import org.mule.security.oauth.OAuth2Manager;
import org.mule.security.oauth.OnNoTokenPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A {@code WeatherOathConnectorOAuthManager} is a wrapper around {@link WeatherOathConnector } that adds access token management capabilities to the pojo.
 * 
 */
@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.6.1", date = "2015-05-18T07:20:04+05:30", comments = "Build UNNAMED.2405.44720b7")
public class WeatherOathConnectorOAuthManager
    extends BaseOAuth2Manager<OAuth2Adapter>
{

    private static Logger logger = LoggerFactory.getLogger(WeatherOathConnectorOAuthManager.class);

    @Override
    protected Logger getLogger() {
        return logger;
    }

    /**
     * Sets greeting
     * 
     * @param scope to set
     */
    public void setGreeting(String value) {
        WeatherOathConnectorOAuth2Adapter connector = ((WeatherOathConnectorOAuth2Adapter) this.getDefaultUnauthorizedConnector());
        connector.setGreeting(value);
    }

    /**
     * Retrieves greeting
     * 
     */
    public String getGreeting() {
        WeatherOathConnectorOAuth2Adapter connector = ((WeatherOathConnectorOAuth2Adapter) this.getDefaultUnauthorizedConnector());
        return connector.getGreeting();
    }

    /**
     * Sets replay
     * 
     * @param scope to set
     */
    public void setReplay(String value) {
        WeatherOathConnectorOAuth2Adapter connector = ((WeatherOathConnectorOAuth2Adapter) this.getDefaultUnauthorizedConnector());
        ((ConnectorConnectionStrategy) connector.getConnectionStrategy()).setReplay(value);
    }

    /**
     * Retrieves replay
     * 
     */
    public String getReplay() {
        WeatherOathConnectorOAuth2Adapter connector = ((WeatherOathConnectorOAuth2Adapter) this.getDefaultUnauthorizedConnector());
        return ((ConnectorConnectionStrategy) connector.getConnectionStrategy()).getReplay();
    }

    /**
     * Sets consumerKey
     * 
     * @param key to set
     */
    public void setConsumerKey(String value) {
        super.setConsumerKey(value);
    }

    /**
     * Sets consumerSecret
     * 
     * @param secret to set
     */
    public void setConsumerSecret(String value) {
        super.setConsumerSecret(value);
    }

    @Override
    protected OAuth2Adapter instantiateAdapter() {
        return new WeatherOathConnectorOAuth2Adapter(this);
    }

    @Override
    protected KeyedPoolableObjectFactory createPoolFactory(OAuth2Manager<OAuth2Adapter> oauthManager, ObjectStore<Serializable> objectStore) {
        return new WeatherOathConnectorOAuthClientFactory(oauthManager, objectStore);
    }

    @Override
    protected void setCustomProperties(OAuth2Adapter adapter) {
        WeatherOathConnectorOAuth2Adapter connector = ((WeatherOathConnectorOAuth2Adapter) adapter);
        connector.setGreeting(getGreeting());
        ((ConnectorConnectionStrategy) connector.getConnectionStrategy()).setReplay(getReplay());
        ((ConnectorConnectionStrategy) connector.getConnectionStrategy()).setConsumerKey(getConsumerKey());
        ((ConnectorConnectionStrategy) connector.getConnectionStrategy()).setConsumerSecret(getConsumerSecret());
    }

    protected void fetchCallbackParameters(OAuth2Adapter adapter, String response) {
        WeatherOathConnectorOAuth2Adapter connector = ((WeatherOathConnectorOAuth2Adapter) adapter);
        ExpressionManager expressionManager = (muleContext.getExpressionManager());
        MuleMessage muleMessage = new DefaultMuleMessage(response, (muleContext));
    }

    public void setOnNoToken(OnNoTokenPolicy policy) {
        this.getDefaultUnauthorizedConnector().setOnNoTokenPolicy(policy);
    }

    @Override
    protected Set<Class<? extends Exception>> refreshAccessTokenOn() {
        Set<Class<? extends Exception>> types = new HashSet<Class<? extends Exception>>();
        types.add(Exception.class);
        return types;
    }

}
