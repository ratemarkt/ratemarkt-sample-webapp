package com.ratemarkt.sampleclient;

import com.ratemarkt.connectors.ratemarkt.RatemarktConfig;
import com.ratemarkt.connectors.ratemarkt.RatemarktConnector;

public class RatemarktService {

	private static RatemarktConfig config;

	private static RatemarktConnector connector;

	public static void init(RatemarktConfig config) {
		assert RatemarktService.config != null : "Ratemarkt Service already initied";
		RatemarktService.config = config;
	}

	public static RatemarktConnector getConnector() {
		if (connector == null) {
			connector = new RatemarktConnector(config);
		}
		return connector;
	}
}
