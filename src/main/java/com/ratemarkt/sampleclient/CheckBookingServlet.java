package com.ratemarkt.sampleclient;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ratemarkt.connectors.ratemarkt.RatemarktConnector;
import com.ratemarkt.models.CheckBookingQuery;
import com.ratemarkt.models.CheckBookingResult;
import com.ratemarkt.models.ImmutableCheckBookingQuery;

public class CheckBookingServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bookingRef = req.getParameter("booking_ref");

		CheckBookingQuery query = ImmutableCheckBookingQuery.builder().bookingRef(bookingRef).build();

		RatemarktConnector connector = RatemarktService.getConnector();

		CheckBookingResult result = connector.checkBooking(null, query);

		Map<String, Object> context = new Hashtable<String, Object>();
		context.put("result", result);

		TemplateUtil.renderTemplate("templates/checkbooking.html", context, resp);
	}
}
