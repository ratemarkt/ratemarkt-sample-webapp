package com.ratemarkt.sampleclient;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ratemarkt.connectors.ratemarkt.RatemarktConnector;
import com.ratemarkt.models.CheckHotelsQuery;
import com.ratemarkt.models.CheckHotelsResult;
import com.ratemarkt.models.ImmutableCheckHotelsQuery;
import com.ratemarkt.models.Pax;

public class CheckHotelsServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TemplateUtil.renderTemplate("templates/checkhotels.html", null, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RatemarktConnector connector = RatemarktService.getConnector();

		String destinationCode = req.getParameter("destination_code");
		LocalDate checkin = LocalDate.parse(req.getParameter("checkin"));
		LocalDate checkout = LocalDate.parse(req.getParameter("checkout"));
		Currency currency = Currency.getInstance(req.getParameter("currency"));
		String nationality = req.getParameter("nationality");
		Pax pax = Pax.fromPaxString(req.getParameter("pax"));

		CheckHotelsQuery query = ImmutableCheckHotelsQuery.builder().destinationCode(destinationCode).checkin(checkin)
				.checkout(checkout).addPaxes(pax).nationality(nationality).currency(currency).build();

		CheckHotelsResult result = connector.checkHotels(null, query);

		Map<String, Object> context = new Hashtable<String, Object>();
		context.put("result", result);

		TemplateUtil.renderTemplate("templates/checkhotels.html", context, resp);
	}
}
