package com.ratemarkt.sampleclient;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ratemarkt.connectors.ratemarkt.RatemarktConnector;
import com.ratemarkt.models.BookRateQuery;
import com.ratemarkt.models.BookRateResult;
import com.ratemarkt.models.CheckRateQuery;
import com.ratemarkt.models.CheckRateResult;
import com.ratemarkt.models.CreditCard;
import com.ratemarkt.models.Holder;
import com.ratemarkt.models.ImmutableBookRateQuery;
import com.ratemarkt.models.ImmutableCheckRateQuery;
import com.ratemarkt.models.ImmutableCreditCard;
import com.ratemarkt.models.ImmutableHolder;
import com.ratemarkt.models.ImmutableOccupancy;
import com.ratemarkt.models.ImmutableOccupant;
import com.ratemarkt.models.ImmutableRoom;
import com.ratemarkt.models.Occupancy;
import com.ratemarkt.models.Occupant;
import com.ratemarkt.models.OccupantType;
import com.ratemarkt.models.Room;

public class BookRateServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String rateKey = req.getParameter("rate_key");

		CheckRateQuery query = ImmutableCheckRateQuery.builder().rateKey(rateKey).build();

		RatemarktConnector connector = RatemarktService.getConnector();
		CheckRateResult result = connector.checkRate(null, query);

		Map<String, Object> context = new Hashtable<String, Object>();
		context.put("result", result);

		TemplateUtil.renderTemplate("templates/bookrate.html", context, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String rateKey = req.getParameter("rate_key");
		String firstName = req.getParameter("firstname");
		String lastName = req.getParameter("lastname");

		Holder holder = ImmutableHolder.builder().firstName(firstName).lastName(lastName).email("foo@bar.org")
				.phone("+413332211").build();

		CreditCard creditCard = ImmutableCreditCard.builder().number("4111111111111111").cvv("000").year(2020).month(12)
				.firstName(firstName).lastName(lastName).build();
		Room room = ImmutableRoom.builder().sequence(1).build();
		Occupant occupant = ImmutableOccupant.builder().firstName(firstName).lastName(lastName).age(30)
				.occupantType(OccupantType.ADULT).build();

		Occupancy occupany = ImmutableOccupancy.builder().room(room).addOccupants(occupant).build();

		BookRateQuery query = ImmutableBookRateQuery.builder().holder(holder).addOccupancy(occupany).rateKey(rateKey)
				.creditCard(creditCard).clientRef(UUID.randomUUID().toString()).build();

		RatemarktConnector connector = RatemarktService.getConnector();

		BookRateResult result = connector.bookRate(null, query);

		String bookingRef = result.getBooking().getBookingRef();

		resp.sendRedirect("/checkbooking?booking_ref=" + bookingRef);

	}
}
