package com.ratemarkt.sample;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ratemarkt.connectors.ratemarkt.RatemarktConnector;
import com.ratemarkt.models.CancelBookingQuery;
import com.ratemarkt.models.CancelBookingResult;
import com.ratemarkt.models.ImmutableCancelBookingQuery;

public class CancelBookingServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String bookingRef = req.getParameter("booking_ref");

		CancelBookingQuery query = ImmutableCancelBookingQuery.builder().bookingRef(bookingRef).build();

		RatemarktConnector connector = RatemarktService.getConnector();

		CancelBookingResult result = connector.cancelBooking(null, query);

		resp.sendRedirect("/checkbooking?bookingRef=" + result.getBooking().getBookingRef());
	}
}
