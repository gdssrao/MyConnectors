
package org.mule.modules.weatherapi.connectivity;

import javax.annotation.Generated;
import org.apache.commons.pool.KeyedPoolableObjectFactory;
import org.mule.api.context.MuleContextAware;
import org.mule.api.lifecycle.Disposable;
import org.mule.api.lifecycle.Initialisable;
import org.mule.api.lifecycle.Startable;
import org.mule.api.lifecycle.Stoppable;
import org.mule.modules.weatherapi.adapters.WeatherAPIConnectorRestClientAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Generated(value = "Mule DevKit Version 3.5.1", date = "2015-05-06T01:14:42+05:30", comments = "Build UNNAMED.1967.45d0eb0")
public class WeatherAPIConnectorConnectionFactory implements KeyedPoolableObjectFactory
{

    private static Logger logger = LoggerFactory.getLogger(WeatherAPIConnectorConnectionFactory.class);
    private WeatherAPIConnectorConnectionManager connectionManager;

    public WeatherAPIConnectorConnectionFactory(WeatherAPIConnectorConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public Object makeObject(Object key)
        throws Exception
    {
        if (!(key instanceof WeatherAPIConnectorConnectionKey)) {
            if (key == null) {
                logger.warn("Connection key is null");
            } else {
                logger.warn("Cannot cast key of type ".concat(key.getClass().getName().concat(" to ").concat("org.mule.modules.weatherapi.connectivity.WeatherAPIConnectorConnectionKey")));
            }
            throw new RuntimeException("Invalid key type ".concat(key.getClass().getName()));
        }
        WeatherAPIConnectorRestClientAdapter connector = new WeatherAPIConnectorRestClientAdapter();
        connector.setUrl(connectionManager.getUrl());
        connector.setCountry(connectionManager.getCountry());
        connector.setCity(connectionManager.getCity());
        if (connector instanceof MuleContextAware) {
            ((MuleContextAware) connector).setMuleContext(connectionManager.getMuleContext());
        }
        if (connector instanceof Initialisable) {
            ((Initialisable) connector).initialise();
        }
        if (connector instanceof Startable) {
            ((Startable) connector).start();
        }
        if (connector instanceof MuleContextAware) {
            connector.setMuleContext((connectionManager.muleContext));
        }
        if (!connector.isConnected()) {
            connector.connect(((WeatherAPIConnectorConnectionKey) key).getUsername(), ((WeatherAPIConnectorConnectionKey) key).getPassword());
        }
        return connector;
    }

    public void destroyObject(Object key, Object obj)
        throws Exception
    {
        if (!(key instanceof WeatherAPIConnectorConnectionKey)) {
            if (key == null) {
                logger.warn("Connection key is null");
            } else {
                logger.warn("Cannot cast key of type ".concat(key.getClass().getName().concat(" to ").concat("org.mule.modules.weatherapi.connectivity.WeatherAPIConnectorConnectionKey")));
            }
            throw new RuntimeException("Invalid key type ".concat(key.getClass().getName()));
        }
        if (!(obj instanceof WeatherAPIConnectorRestClientAdapter)) {
            if (obj == null) {
                logger.warn("Connector is null");
            } else {
                logger.warn("Cannot cast connector of type ".concat(obj.getClass().getName().concat(" to ").concat("org.mule.modules.weatherapi.adapters.WeatherAPIConnectorRestClientAdapter")));
            }
            throw new RuntimeException("Invalid connector type ".concat(obj.getClass().getName()));
        }
        try {
            ((WeatherAPIConnectorRestClientAdapter) obj).disconnect();
        } catch (Exception e) {
            throw e;
        } finally {
            if (((WeatherAPIConnectorRestClientAdapter) obj) instanceof Stoppable) {
                ((Stoppable) obj).stop();
            }
            if (((WeatherAPIConnectorRestClientAdapter) obj) instanceof Disposable) {
                ((Disposable) obj).dispose();
            }
        }
    }

    public boolean validateObject(Object key, Object obj) {
        if (!(obj instanceof WeatherAPIConnectorRestClientAdapter)) {
            if (obj == null) {
                logger.warn("Connector is null");
            } else {
                logger.warn("Cannot cast connector of type ".concat(obj.getClass().getName().concat(" to ").concat("org.mule.modules.weatherapi.adapters.WeatherAPIConnectorRestClientAdapter")));
            }
            throw new RuntimeException("Invalid connector type ".concat(obj.getClass().getName()));
        }
        try {
            return ((WeatherAPIConnectorRestClientAdapter) obj).isConnected();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    public void activateObject(Object key, Object obj)
        throws Exception
    {
        if (!(key instanceof WeatherAPIConnectorConnectionKey)) {
            throw new RuntimeException("Invalid key type");
        }
        if (!(obj instanceof WeatherAPIConnectorRestClientAdapter)) {
            throw new RuntimeException("Invalid connector type");
        }
        try {
            if (!((WeatherAPIConnectorRestClientAdapter) obj).isConnected()) {
                ((WeatherAPIConnectorRestClientAdapter) obj).connect(((WeatherAPIConnectorConnectionKey) key).getUsername(), ((WeatherAPIConnectorConnectionKey) key).getPassword());
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void passivateObject(Object key, Object obj)
        throws Exception
    {
    }

}
