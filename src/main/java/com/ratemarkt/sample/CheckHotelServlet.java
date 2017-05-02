package com.ratemarkt.sample;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ratemarkt.connectors.ratemarkt.RatemarktConnector;
import com.ratemarkt.models.CheckHotelQuery;
import com.ratemarkt.models.CheckHotelResult;
import com.ratemarkt.models.ImmutableCheckHotelQuery;
import com.ratemarkt.models.Pax;

public class CheckHotelServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String hotelCode = req.getParameter("hotel_code");
		LocalDate checkin = LocalDate.parse(req.getParameter("checkin"));
		LocalDate checkout = LocalDate.parse(req.getParameter("checkout"));
		Currency currency = Currency.getInstance(req.getParameter("currency"));
		String nationality = req.getParameter("nationality");
		Pax pax = Pax.fromPaxString(req.getParameter("pax"));

		CheckHotelQuery query = ImmutableCheckHotelQuery.builder().hotelCode(hotelCode).checkin(checkin)
				.checkout(checkout).addPaxes(pax).nationality(nationality).currency(currency).build();

		RatemarktConnector connector = RatemarktService.getConnector();
		CheckHotelResult result = connector.checkHotel(null, query);

		Map<String, Object> context = new Hashtable<String, Object>();
		context.put("result", result);
		context.put("checkin", checkin);
		context.put("checkout", checkout);
		context.put("currency", currency);
		context.put("nationality", nationality);
		context.put("pax", pax.toPaxString());

		TemplateUtil.renderTemplate("templates/checkhotel.html", context, resp);
	}

}
