package root.trackdrivers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class HistoryTracker {

	public HashMap<String, Driver> getDriversList() {
		return driversList;
	}

	final static Logger LOGGER = Logger.getLogger(HistoryTracker.class);
	HashMap<String, Driver> driversList = new HashMap<String, Driver>();
	
	public String getOutputLine(Driver d) {
		String output = d.avgSpeed > 0 ? MessageFormat.format(AppConstants.WITH_MILES, d.getName(),
				Math.round(d.getTotalMiles()), Math.round(d.getAvgSpeed()))
				: MessageFormat.format(AppConstants.WITHOUT_MILES, d.getName());
		return output;
	}

	public List<String> trackHistroy(String fileName) throws IOException {
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
				LOGGER.log(Level.WARN, "INVALID COMMAND SKIPPING THIS LINE: " + curLine);
			}
		}
		reader.close();
		return formatReport();
	}

	public List<String> formatReport() {
		List<Driver> driverTripDetails = new ArrayList<Driver>(driversList.values());
		List<String> report = new ArrayList<String>();
		for (Driver d : driverTripDetails) {
			d.calculateAvgSpeedAndDistanceTravelled();
		}

		Collections.sort(driverTripDetails, new Comparator<Driver>() {
			public int compare(Driver d1, Driver d2) {
				return (int) (d2.getTotalMiles() - d1.getTotalMiles());
			}
		});

		for (Driver d : driverTripDetails) {
			report.add(getOutputLine(d));
			System.out.println(getOutputLine(d));
		}
		
		return report;
	}

	public void addTripToDriver(String driverName, String strtTime, String endTime, String miles){
		Driver tripDriver = driversList.get(driverName);
		if (tripDriver != null) {
			Trip newTrip = new Trip(strtTime, endTime, Double.parseDouble(miles));
			tripDriver.addTripDetail(newTrip);
		} else {
			LOGGER.log(Level.ERROR, "DRIVER NOT REGISTERED");
		}

	}

	public void registerDriver(String driverName) {
		if (!driversList.containsKey(driverName)) {
			Driver driver = new Driver(driverName);
			driversList.put(driverName, driver);
		}
	}
}
