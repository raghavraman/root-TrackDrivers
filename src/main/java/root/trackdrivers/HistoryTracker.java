package root.trackdrivers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class HistoryTracker {
	HashMap<String, Driver> driversList = new HashMap<String, Driver>();

	public String getOutputLine(Driver d) {
		String output = d.avgSpeed > 0
				? MessageFormat.format(AppConstants.WITH_MILES, d.getName(), Math.round(d.getTotalMiles()),
						Math.round(d.getAvgSpeed()))
				: MessageFormat.format(AppConstants.WITHOUT_MILES, d.getName());
		return output;
	}

	public void trackHistroy(String fileName) throws IOException, ParseException {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String curLine;
		String[] curLineData;

		while ((curLine = reader.readLine()) != null) {
			curLineData = curLine.split(" ");
			if (AppConstants.DRIVER_COMMAND.equals(curLineData[0])) {
				registerDriver(curLineData[1]);
			} else if (AppConstants.TRIP_COMMAND.equals(curLineData[0])) {
				addTripToDriver(curLineData[1], curLineData[2], curLineData[3], curLineData[4]);
			} else {
				System.out.println("EXCEPTION - INVALID COMMAND SKIPPING THIS LINE: " + curLine);
			}
		}
		reader.close();
		printReport();

	}

	private void printReport() throws ParseException {
		List<Driver> driverTripDetails = new ArrayList<Driver>(driversList.values());
		for (Driver d : driverTripDetails) {
			d.calculateAvgSpeedAndDistanceTravelled();
		}

		Collections.sort(driverTripDetails, new Comparator<Driver>() {
			public int compare(Driver d1, Driver d2) {
				return (int) (d2.getTotalMiles() - d1.getTotalMiles());
			}
		});

		for (Driver d : driverTripDetails) {
			System.out.println(getOutputLine(d));
		}

	}

	private void addTripToDriver(String driverName, String strtTime, String endTime, String miles) throws ParseException {
		Driver tripDriver = driversList.get(driverName);
		if (tripDriver != null) {
			Trip newTrip = new Trip(strtTime, endTime, Double.parseDouble(miles));
			if (newTrip.isTripValid()) {
				tripDriver.addTripDetail(newTrip);
			}
		} else {
			System.out.println("EXCEPTION - DRIVER NOT REGISTERED");
		}

	}

	private void registerDriver(String driverName) {
		if (!driversList.containsKey(driverName)) {
			Driver driver = new Driver(driverName);
			driversList.put(driverName, driver);
		}
	}
}
