package com.mycompany.hr.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class StubHumanResourceService implements HumanResourceService {
	public void bookHoliday(Date startDate, Date endDate, String name) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		System.out.println("Booking holiday for [" + dateFormat.format(startDate) + "-" + dateFormat.format(endDate)
				+ "] for [" + name + "] ");
	}
}