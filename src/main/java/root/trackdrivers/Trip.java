package root.trackdrivers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Trip {
	
	String startTime;
	String endTime;
	Double distance;

	public Trip(String startTime, String endTime, double distance) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.distance = distance;
	}

	public int getTimeinMins() throws ParseException {
		
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		Date start = format.parse(startTime);
		Date end = format.parse(endTime);
		long totalTime = end.getTime() - start.getTime(); 
		return (int) ((totalTime / AppConstants.VALUE_OF_SEC_IN_MILLISEC)  / AppConstants.VALUE_OF_MIN_IN_SEC);
	}

	public boolean isTripValid() throws ParseException {

		double speed = (this.distance / getTimeinMins()) * AppConstants.VALUE_OF_HOUR_IN_MIN;
		return (Math.round(speed) >= AppConstants.ALLOWED_LOWER_MPH && Math.round(speed) <= AppConstants.ALLOWED_LOWER_MPH);
	}

}
