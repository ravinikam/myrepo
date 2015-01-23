package test.holidayService;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.junit.Before;
import org.junit.Test;

import com.myschema.EmployeeType;
import com.myschema.HolidayRequest;
import com.myschema.HolidayType;
import com.myschema.HumanResource;
import com.myschema.HumanResourceService;

//import org.junit.Test;

public class HolidayServiceTest {
	
	private static final String NAMESPACE_URI = "http://mycompany.com/hr/schemas";
	public static final String WEB_SERVICE_URL="http://localhost:8080/holidayService/holiday.wsdl";
	
	QName serviceName = new QName(NAMESPACE_URI, "HumanResourceService");
	private HumanResource humanResourcePort;
	
	@Before
	public void initPort(){
		try {
			HumanResourceService humanResourceService = new HumanResourceService(new URL(WEB_SERVICE_URL), serviceName);
			 humanResourcePort = humanResourceService.getHumanResourceSoap11();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void testHolidayService() throws Exception {
		
		HolidayRequest holidayRequest = new HolidayRequest();
		EmployeeType emp = new EmployeeType();
		emp.setFirstName("rb");
		emp.setLastName("nikam");
		emp.setNumber(new BigInteger("020"));
		holidayRequest.setEmployee(emp);
		HolidayType hDay = new HolidayType();
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		XMLGregorianCalendar xgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		hDay.setEndDate(xgc);
		hDay.setStartDate(xgc);
		holidayRequest.setHoliday(hDay);
		humanResourcePort.holiday(holidayRequest);

	}
}
