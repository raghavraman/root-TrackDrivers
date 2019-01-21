package root.trackdrivers;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;

public class HistoryTrackerTest extends TestCase {
	HistoryTracker hisTracker = new HistoryTracker();
	int expectedDrivers = 3;

	@Test
	public void testRegistringDriver() {
		hisTracker.registerDriver("John");
		hisTracker.registerDriver("Doe");
		hisTracker.registerDriver("Hulk");
		assertEquals(expectedDrivers, hisTracker.getDriversList().size());
	}

	@Test
	public void testRegistringDriverFail() {
		hisTracker.registerDriver("John");
		hisTracker.registerDriver("Doe");
		hisTracker.registerDriver("Hulk");
		assertNotSame(0, hisTracker.getDriversList().size());
	}

	@Test
	public void testAddingValidTripToDriver() {
		hisTracker.registerDriver("John");
		hisTracker.addTripToDriver("John", "12:00", "13:00", "45d");
		Driver dr = hisTracker.getDriversList().get("John");
		dr.calculateAvgSpeedAndDistanceTravelled();
		assertEquals(45d, dr.getTotalMiles());
	}

	@Test
	public void testAddingInValidTripToDriver() {
		hisTracker.registerDriver("John");
		hisTracker.addTripToDriver("John", "12:00", "13:00", "4d");
		Driver dr = hisTracker.getDriversList().get("John");
		dr.calculateAvgSpeedAndDistanceTravelled();
		assertEquals(0d, dr.getTotalMiles());
	}

	@Test
	public void testFormatReport() {
		List<String> expected = new ArrayList<String>();
		expected.add("John: 45 miles @ 45 mph");
		hisTracker.registerDriver("John");
		hisTracker.addTripToDriver("John", "12:00", "13:00", "45d");
		assertEquals(expected, hisTracker.formatReport());
	}

	@Test
	public void testFormatReportWithInvalidTrips() {
		List<String> expected = new ArrayList<String>();
		expected.add("John: 45 miles @ 45 mph");
		hisTracker.registerDriver("John");
		hisTracker.addTripToDriver("John", "12:00", "13:00", "45d");
		hisTracker.addTripToDriver("John", "12:00", "13:00", "4d");
		assertEquals(expected, hisTracker.formatReport());
	}

}
