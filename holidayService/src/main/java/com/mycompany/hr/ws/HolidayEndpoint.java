package com.mycompany.hr.ws;

import javax.xml.datatype.XMLGregorianCalendar;

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
		// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		XMLGregorianCalendar startDate = holidayRequest.getHoliday().getStartDate();
		// Date endDate = holidayRequest.getHoliday().getStartDate();
		// String name = nameExpression.valueOf(holidayRequest);

		String name = holidayRequest.getEmployee().getFirstName();
		System.out.println("Name:" + name);

		humanResourceService.bookHoliday(null, null, name);
	}

}