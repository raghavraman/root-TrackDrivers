package root.trackdrivers;

import java.util.ArrayList;

public class Driver {
	String name;
	double totalMiles;
	double avgSpeed;
	private ArrayList<Trip> tripDetails = new ArrayList<Trip>();

	public Driver(String driverName) {
		this.name = driverName;
		this.avgSpeed = 0d;
		this.totalMiles = 0d;
	}

	public String getName() {
		return name;
	}

	public void addTripDetail(Trip tr) {
		if (tr.isTripValid()) {
			this.tripDetails.add(tr);
		}
	}

	public double getTotalMiles() {
		return totalMiles;
	}

	public double getAvgSpeed() {
		return avgSpeed;
	}

	public void calculateAvgSpeedAndDistanceTravelled() {

		double distanceTravelled = 0d;
		double timetravelled = 0d;

		if (!this.tripDetails.isEmpty()) {
			for (Trip e : this.tripDetails) {
				distanceTravelled += e.getDistance();
				timetravelled += e.getTimeInMins();
			}
			this.avgSpeed = (distanceTravelled / timetravelled) * AppConstants.VALUE_OF_HOUR_IN_MIN;
			this.totalMiles = distanceTravelled;
		}
	}

}
