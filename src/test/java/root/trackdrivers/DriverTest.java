package root.trackdrivers;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import junit.framework.TestCase;

public class DriverTest extends TestCase {

	String driverName = "John Doe";
	double avgSpeed = 0d;
	double tMiles = 0d;
	Driver dr = new Driver(driverName);

	@Test
	public void testDriverAttributes() {
		assertEquals(driverName, dr.getName());
		assertEquals(avgSpeed, dr.getAvgSpeed());
		assertEquals(avgSpeed, dr.getTotalMiles());
	}

	@Test
	public void testDriverAttributesFail() {
		assertNotEquals("John", dr.getName());
		assertNotEquals(10d, dr.getAvgSpeed());
		assertNotEquals(10d, dr.getTotalMiles());
	}

	@Test
	public void testCalculatingAvgSpeedWithAllowedSpeed() {
		Trip tr = new Trip("12:00", "13:00", 45d);
		dr.addTripDetail(tr);
		dr.calculateAvgSpeedAndDistanceTravelled();
		assertEquals(45d, dr.getTotalMiles());
		assertEquals(45d, dr.getAvgSpeed());
	}

	@Test
	public void testCalculatingAvgSpeedWithVeryLowSpeedTrip() {
		Trip tr = new Trip("12:00", "13:00", 4d);
		dr.addTripDetail(tr);
		dr.calculateAvgSpeedAndDistanceTravelled();
		assertEquals(avgSpeed, dr.getAvgSpeed());
		assertEquals(avgSpeed, dr.getTotalMiles());
	}

	@Test
	public void testCalculatingAvgSpeedWithVeryHighSpeedTrip() {
		Trip tr = new Trip("12:00", "13:00", 104d);
		dr.addTripDetail(tr);
		dr.calculateAvgSpeedAndDistanceTravelled();
		assertEquals(avgSpeed, dr.getAvgSpeed());
		assertEquals(avgSpeed, dr.getTotalMiles());
	}
}
