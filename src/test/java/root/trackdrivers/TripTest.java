package root.trackdrivers;

import static org.junit.Assert.assertNotEquals;

import java.text.ParseException;

import org.junit.Test;

import junit.framework.TestCase;

public class TripTest extends TestCase {

	String startTime = "12:00";
	String endTime = "13:00";
	int timeInMins = 60;
	double distance = 45d;
	double inValidLowDistance = 4d;
	double inValidHighDistance = 104d;

	@Test
	public void testTripAttributes() {
		Trip tr = new Trip(startTime, endTime, distance);
		assertEquals(timeInMins, tr.getTimeInMins());
		assertEquals(distance, tr.getDistance());
	}

	@Test
	public void testDriverAttributesFail() {
		Trip tr = new Trip(startTime, endTime, distance);
		assertNotEquals(inValidLowDistance, tr.getDistance());
		assertNotEquals(100, tr.getTimeInMins());
	}

	@Test
	public void testValidTrip() throws ParseException {
		Trip tr = new Trip(startTime, endTime, distance);
		assertTrue(tr.isTripValid());
	}

	@Test
	public void testInValidTripWithLowSpeed() throws ParseException {
		Trip tr = new Trip(startTime, endTime, inValidLowDistance);
		assertFalse(tr.isTripValid());
	}

	@Test
	public void testInValidTripWithHighSpeed() throws ParseException {
		Trip tr = new Trip(startTime, endTime, inValidHighDistance);
		assertFalse(tr.isTripValid());
	}

	@Test
	public void testGetTimeInMins() throws ParseException {
		Trip tr = new Trip(startTime, endTime, distance);
		assertEquals(60, tr.getTimeInMins());
	}

	@Test(expected = ParseException.class)
	public void testTripWithInvalidParams() {
		new Trip("12", endTime, distance);
	}

}
