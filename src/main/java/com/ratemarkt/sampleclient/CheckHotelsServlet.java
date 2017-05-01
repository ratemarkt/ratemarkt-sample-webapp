package com.ratemarkt.sampleclient;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ratemarkt.connectors.ratemarkt.RatemarktConfig;
import com.ratemarkt.connectors.ratemarkt.RatemarktConnector;
import com.ratemarkt.models.CheckHotelsQuery;
import com.ratemarkt.models.CheckHotelsResult;
import com.ratemarkt.models.ImmutableCheckHotelsQuery;
import com.ratemarkt.models.ImmutablePax;
import com.ratemarkt.models.Pax;

public class CheckHotelsServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TemplateUtil.renderTemplate("templates/checkhotels.html", null, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RatemarktConfig config = new RatemarktConfig();
		config.setApiKey("YOUR_API_KEY");
		config.setBaseUrl("http://localhost:8080/api/v1");

		RatemarktConnector connector = new RatemarktConnector(config);

		Pax pax = ImmutablePax.builder().numberOfAdults(2).build();

		CheckHotelsQuery query = ImmutableCheckHotelsQuery.builder().addHotelCodes("193a7b", "ed6681", "0aef99")
				.checkin(LocalDate.parse("2017-06-12")).checkout(LocalDate.parse("2017-06-15")).addPaxes(pax)
				.nationality("us").currency(Currency.getInstance("USD")).build();

		CheckHotelsResult result = connector.checkHotels(null, query);

		Map<String, Object> context = new Hashtable<String, Object>();
		context.put("result", result);

		TemplateUtil.renderTemplate("templates/checkhotels.html", context, resp);
	}
}
