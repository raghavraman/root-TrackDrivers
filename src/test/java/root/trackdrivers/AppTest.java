package root.trackdrivers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	HistoryTracker hisTracker = new HistoryTracker();
	int expectedDrivers = 3; 
	
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		assertTrue(true);
	}

	@org.junit.Test
	public void testTrackingHistoryWithValidFIle() throws IOException {
		List<String> expected = new ArrayList<String>();
		expected.add("Alex: 42 miles @ 34 mph");
		expected.add("Dan: 39 miles @ 47 mph");
		expected.add("Bob: 0 miles");
		assertEquals(expected,hisTracker.trackHistroy(this.getClass().getClassLoader().getResource("input.txt").getFile()));
		assertEquals(expectedDrivers, hisTracker.getDriversList().size());
	}
	
	@org.junit.Test
	public void testTrackingHistoryWithEmptyFile() throws IOException {
		hisTracker.trackHistroy(this.getClass().getClassLoader().getResource("empty.txt").getFile());
		assertEquals(0, hisTracker.getDriversList().size());
	}
	
	@org.junit.Test
	public void testTrackingHistoryWithInvalidLowSpeedTrips() throws IOException {
		List<String> expected = new ArrayList<String>();
		expected.add("Alex: 42 miles @ 34 mph");
		expected.add("Dan: 39 miles @ 47 mph");
		expected.add("Bob: 0 miles");
		assertEquals(expected,hisTracker.trackHistroy(this.getClass().getClassLoader().getResource("inputTest1.txt").getFile()));
		assertEquals(expectedDrivers, hisTracker.getDriversList().size());
	}
	
	@org.junit.Test
	public void testTrackingHistoryWithInvalidHighSpeedTrips() throws IOException {
		List<String> expected = new ArrayList<String>();
		expected.add("Alex: 42 miles @ 34 mph");
		expected.add("Dan: 39 miles @ 47 mph");
		expected.add("Bob: 0 miles");
		assertEquals(expected,hisTracker.trackHistroy(this.getClass().getClassLoader().getResource("inputTest2.txt").getFile()));
		assertEquals(expectedDrivers, hisTracker.getDriversList().size());
	}
	
	@org.junit.Test
	public void testTrackingHistoryWithDriverNotRegistered() throws IOException {
		List<String> expected = new ArrayList<String>();
		assertEquals(expected,hisTracker.trackHistroy(this.getClass().getClassLoader().getResource("inputTest3.txt").getFile()));
		assertEquals(0, hisTracker.getDriversList().size());
	}
	
	@org.junit.Test
	public void testTrackingHistoryWithDriverNoTrips() throws IOException {
		List<String> expected = new ArrayList<String>();
		expected.add("Dan: 0 miles");
		expected.add("Alex: 0 miles");
		expected.add("Bob: 0 miles");
		assertEquals(expected,hisTracker.trackHistroy(this.getClass().getClassLoader().getResource("inputTest4.txt").getFile()));
		assertEquals(expectedDrivers, hisTracker.getDriversList().size());
	}

	@org.junit.Test
	public void testTrackingHistoryWithDriverNotFoundAndAdded() throws IOException {
		List<String> expected = new ArrayList<String>();
		expected.add("Alex: 42 miles @ 34 mph");
		expected.add("Dan: 39 miles @ 47 mph");
		expected.add("Bob: 0 miles");
		assertEquals(expected,hisTracker.trackHistroy(this.getClass().getClassLoader().getResource("inputTest5.txt").getFile()));
		assertEquals(expectedDrivers, hisTracker.getDriversList().size());
	}
	
	@org.junit.Test
	public void testTrackingHistoryWithUnknownCommand() throws IOException {
		List<String> expected = new ArrayList<String>();
		expected.add("Alex: 42 miles @ 34 mph");
		expected.add("Dan: 39 miles @ 47 mph");
		expected.add("Bob: 0 miles");
		assertEquals(expected,hisTracker.trackHistroy(this.getClass().getClassLoader().getResource("inputTest6.txt").getFile()));
		assertEquals(expectedDrivers, hisTracker.getDriversList().size());
	}
}
