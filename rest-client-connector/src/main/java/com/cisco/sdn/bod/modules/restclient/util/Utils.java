package com.cisco.sdn.bod.modules.restclient.util;

public class Utils {

	public static String getAPIUrl(String host, String port, String uri) {
		StringBuilder url = new StringBuilder();
		url.append(ConnectorConstants.HTTP).append(ConnectorConstants.COLON)
				.append(ConnectorConstants.SLASH)
				.append(ConnectorConstants.SLASH);
		url.append(host).append(ConnectorConstants.COLON).append(port);
		url.append(uri);
		return url.toString();
	}

}
