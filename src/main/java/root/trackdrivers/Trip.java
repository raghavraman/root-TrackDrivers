package root.trackdrivers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Trip {

	int timeInMins;
	double distance;
	
	final static Logger LOGGER = Logger.getLogger(HistoryTracker.class);
	
	public double getDistance() {
		return distance;
	}

	public int getTimeInMins() {
		return timeInMins;
	}

	public Trip(String startTime, String endTime, double distance) {
		super();
		this.distance = distance;
		this.timeInMins = calculateTime(startTime, endTime);
	}

	public int calculateTime(String startTime, String endTime) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		Date start, end;
		long totalTime = 0l;
		try {
			start = format.parse(startTime);
			end = format.parse(endTime);
			totalTime = end.getTime() - start.getTime();
			return (int) ((totalTime / AppConstants.VALUE_OF_SEC_IN_MILLISEC) / AppConstants.VALUE_OF_MIN_IN_SEC);
		} catch (ParseException e) {
			LOGGER.log(Level.ERROR, "EXCEPTION in Start/End time format - Please Specify in HH:mm format", e);
			return AppConstants.DEFAULT_TIME_FOR_TRIP;
		}
	}

	public boolean isTripValid() {
		if (this.timeInMins <= AppConstants.DEFAULT_TIME_FOR_TRIP) {
			return false;
		}
		double speed = (this.distance / this.timeInMins) * AppConstants.VALUE_OF_HOUR_IN_MIN;
		return (Math.round(speed) >= AppConstants.ALLOWED_LOWER_MPH
				&& Math.round(speed) <= AppConstants.ALLOWED_UPPER_MPH);
	}

}
