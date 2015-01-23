package com.mycompany.hr.ws;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import com.mycompany.hr.service.HumanResourceService;
import com.myschema.HolidayRequest;

@Endpoint
public class HolidayEndpoint {

	private static final String NAMESPACE_URI = "http://mycompany.com/hr/schemas";

	private HumanResourceService humanResourceService;

	@Autowired
	public HolidayEndpoint(HumanResourceService humanResourceService) {
		this.humanResourceService = humanResourceService;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "HolidayRequest")
	public void handleHolidayRequest(@RequestPayload HolidayRequest holidayRequest) throws Exception {
		
		Date startDate = holidayRequest.getHoliday().getStartDate().toGregorianCalendar().getTime();
		Date endDate = holidayRequest.getHoliday().getEndDate().toGregorianCalendar().getTime();
		String name = holidayRequest.getEmployee().getFirstName() + " " + holidayRequest.getEmployee().getLastName();

		humanResourceService.bookHoliday(startDate, endDate, name);
	}

}