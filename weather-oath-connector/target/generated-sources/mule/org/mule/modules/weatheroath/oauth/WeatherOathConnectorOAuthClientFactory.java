
package org.mule.modules.weatheroath.oauth;

import java.io.Serializable;
import javax.annotation.Generated;
import org.mule.api.store.ObjectStore;
import org.mule.common.security.oauth.OAuthState;
import org.mule.modules.weatheroath.adapters.WeatherOathConnectorOAuth2Adapter;
import org.mule.modules.weatheroath.strategy.ConnectorConnectionStrategy;
import org.mule.security.oauth.BaseOAuthClientFactory;
import org.mule.security.oauth.OAuth2Adapter;
import org.mule.security.oauth.OAuth2Manager;

@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.6.1", date = "2015-05-18T07:20:04+05:30", comments = "Build UNNAMED.2405.44720b7")
public class WeatherOathConnectorOAuthClientFactory
    extends BaseOAuthClientFactory
{

    private WeatherOathConnectorOAuthManager oauthManager;

    public WeatherOathConnectorOAuthClientFactory(OAuth2Manager<OAuth2Adapter> oauthManager, ObjectStore<Serializable> objectStore) {
        super(oauthManager, objectStore);
        this.oauthManager = (WeatherOathConnectorOAuthManager) oauthManager;
    }

    @Override
    protected Class<? extends OAuth2Adapter> getAdapterClass() {
        return WeatherOathConnectorOAuth2Adapter.class;
    }

    @Override
    protected void setCustomAdapterProperties(OAuth2Adapter adapter, OAuthState state) {
        WeatherOathConnectorOAuth2Adapter connector = ((WeatherOathConnectorOAuth2Adapter) adapter);
        connector.setGreeting(oauthManager.getGreeting());
        ((ConnectorConnectionStrategy) connector.getConnectionStrategy()).setReplay(oauthManager.getReplay());
    }

    @Override
    protected void setCustomStateProperties(OAuth2Adapter adapter, OAuthState state) {
        WeatherOathConnectorOAuth2Adapter connector = ((WeatherOathConnectorOAuth2Adapter) adapter);
    }

}
