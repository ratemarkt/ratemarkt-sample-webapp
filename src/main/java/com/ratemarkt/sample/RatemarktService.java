package com.ratemarkt.sample;

import com.ratemarkt.connectors.ratemarkt.RatemarktConfig;
import com.ratemarkt.connectors.ratemarkt.RatemarktConnector;

public class RatemarktService {

	private static boolean inited;

	private static RatemarktConfig config;

	private static RatemarktConnector connector;

	public static void init(RatemarktConfig config) {
		if (!inited) {
			RatemarktService.config = config;
			inited = true;
		}
	}

	public static RatemarktConnector getConnector() {
		if (connector == null) {
			connector = new RatemarktConnector(config);
		}
		return connector;
	}
}
