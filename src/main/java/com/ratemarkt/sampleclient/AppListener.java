package com.ratemarkt.sampleclient;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ratemarkt.connectors.ratemarkt.RatemarktConfig;

public class AppListener implements ServletContextListener {

	private final static Logger logger = LoggerFactory.getLogger(AppListener.class);

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		logger.info("Sample client starting...");
		RatemarktConfig config = new RatemarktConfig();
		String baseUrl = System.getProperty("com.ratemarkt.sampleclient.baseUrl");

		if (baseUrl == null) {
			baseUrl = "http://localhost:8080/api/v1";
		}

		String apiKey = System.getProperty("com.ratemarkt.sampleclient.apiKey");

		if (apiKey == null) {
			throw new IllegalArgumentException("Api key is missing");
		}

		config.setApiKey(apiKey);
		config.setBaseUrl(baseUrl);

		RatemarktService.init(config);
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		logger.info("Sample client stopped...");
	}
}
