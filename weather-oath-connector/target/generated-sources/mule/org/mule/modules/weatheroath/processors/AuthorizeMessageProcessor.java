
package org.mule.modules.weatheroath.processors;

import java.util.regex.Pattern;
import javax.annotation.Generated;
import org.mule.modules.weatheroath.oauth.WeatherOathConnectorOAuthManager;
import org.mule.security.oauth.processor.BaseOAuth2AuthorizeMessageProcessor;

@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.6.1", date = "2015-05-18T07:20:04+05:30", comments = "Build UNNAMED.2405.44720b7")
public class AuthorizeMessageProcessor
    extends BaseOAuth2AuthorizeMessageProcessor<WeatherOathConnectorOAuthManager>
{

    private final static Pattern AUTH_CODE_PATTERN = Pattern.compile("code=([^&]+)");

    @Override
    protected String getAuthCodeRegex() {
        return AUTH_CODE_PATTERN.pattern();
    }

    @Override
    protected Class<WeatherOathConnectorOAuthManager> getOAuthManagerClass() {
        return WeatherOathConnectorOAuthManager.class;
    }

}
