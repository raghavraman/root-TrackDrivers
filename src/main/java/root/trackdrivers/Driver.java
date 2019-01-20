package root.trackdrivers;

import java.text.ParseException;
import java.util.ArrayList;

public class Driver {
	String name;
	ArrayList<Trip> tripDetails = new ArrayList<Trip>();
	double totalMiles;
	double avgSpeed;

	public Driver(String driverName) {
		this.name = driverName;
		this.avgSpeed = 0d;
		this.totalMiles = 0d;
	}

	public String getName() {
		return name;
	}

	public void addTripDetail(Trip tripDetail) {
		this.tripDetails.add(tripDetail);
	}

	public double getTotalMiles() {
		return totalMiles;
	}

	public double getAvgSpeed() {
		return avgSpeed;
	}

	public void calculateAvgSpeedAndDistanceTravelled() throws ParseException {
		
		double distanceTravelled = 0d;
		double timetravelled = 0d;

		if (!this.tripDetails.isEmpty()) {
			for (Trip e : this.tripDetails) {
				distanceTravelled += e.distance;
				timetravelled += e.getTimeinMins();
			}
			this.avgSpeed = (distanceTravelled / timetravelled) * AppConstants.VALUE_OF_HOUR_IN_MIN;
			this.totalMiles = distanceTravelled;
		}
	}

}
