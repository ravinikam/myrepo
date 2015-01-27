package test.holidayService;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.junit.Before;
import org.junit.Test;

import com.mycompany.hr.model.Department;
import com.mycompany.hr.model.Employee;
import com.myschema.EmployeeType;
import com.myschema.HolidayRequest;
import com.myschema.HolidayType;
import com.myschema.HumanResource;
import com.myschema.HumanResourceService;

//import org.junit.Test;

public class HolidayServiceTest {

	private static final String NAMESPACE_URI = "http://mycompany.com/hr/schemas";
	public static final String WEB_SERVICE_URL = "http://localhost:8080/holidayService/holiday.wsdl";

	QName serviceName = new QName(NAMESPACE_URI, "HumanResourceService");
	private HumanResource humanResourcePort;
	private EntityManager em;

	@Before
	public void initPort() {
		try {
			HumanResourceService humanResourceService = new HumanResourceService(new URL(WEB_SERVICE_URL), serviceName);
			humanResourcePort = humanResourceService.getHumanResourceSoap11();

			EntityManagerFactory emf = Persistence.createEntityManagerFactory("holidayService");
			em = emf.createEntityManager();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testHolidayService() throws Exception {

		em.getTransaction().begin();
		Employee empl = new Employee();
		empl.setEmployeeName("Aaryan");
		empl.setSalary(120000);
		empl.setEmployeeNbr("RN244437");
		Department dept = new Department();
		dept.setName("IT-BFS");
		dept.getEmployees().add(empl);
		empl.setDept(dept);
		//em.persist(empl);
		em.persist(dept);
		em.flush();
		em.getTransaction().commit();
		
		HolidayRequest holidayRequest = new HolidayRequest();
		EmployeeType emp = new EmployeeType();
		emp.setFirstName("r");
		emp.setLastName("n");
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
