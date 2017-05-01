package com.ratemarkt.sampleclient;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ratemarkt.connectors.ratemarkt.RatemarktConnector;
import com.ratemarkt.models.CheckRateQuery;
import com.ratemarkt.models.CheckRateResult;
import com.ratemarkt.models.ImmutableCheckRateQuery;

public class CheckRateServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String rateKey = req.getParameter("rate_key");

		CheckRateQuery query = ImmutableCheckRateQuery.builder().rateKey(rateKey).build();

		RatemarktConnector connector = RatemarktService.getConnector();
		CheckRateResult result = connector.checkRate(null, query);

		Map<String, Object> context = new Hashtable<String, Object>();
		context.put("result", result);

		TemplateUtil.renderTemplate("templates/checkrate.html", context, resp);
	}
}
