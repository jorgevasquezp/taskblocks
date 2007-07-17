package taskblocks;

public class Utils {

	public static final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;

	/** The first saturday since the Epoch (time 0). */
	public static final long FIRST_SATURDAY = 1;
	
	/**
	 * This method adds 'duration' working days to 'startTime'. Saturday is also the regular
	 * end of the work. (This is different from beginning of work - there saturday should be recounted
	 * to monday
	 */
	public static long countFinishTime(long startTime, long duration) {
		long startDayInWeek = getDayInWeek(startTime);
		
		long durationWeeks = (duration) / 5;
		long durationRest = (duration) % 5;
		
		long daysAdd = durationWeeks*2; // every week of work means 2 days of weekend
		
		if(startDayInWeek + durationRest > 5) {
			daysAdd += 2;
		}
		
		if(duration > 0 && startDayInWeek == 0 && duration % 5 == 0) {
			daysAdd-=2;
		}
		return startTime + duration + daysAdd;
	}
	
	/** Returns day in week for given time. Time is the count of days, not milliseconds
	 * 
	 * @param time Time in number of days.
	 * @return
	 */
	public static int getDayInWeek(long time) {
		return (int)((time + FIRST_SATURDAY + 2) % 7);
	}
	
	public static long repairStartTime(long startTime) {
		long startDayInWeek = Utils.getDayInWeek(startTime);
		if(startDayInWeek == 5) {
			return startTime+2;
		} else if(startDayInWeek == 6) {
			return startTime+1;
		}
		return startTime;
	}
	
	/** Counts duration between given times, counting only working days */
	public static long countWorkDuration(long start, long end) {
		long result;
		long weeks = (end-start) / 7;
		result = weeks * 5;
		long rest = (end-start) % 7;
		int startInWeek = getDayInWeek(start);
		for(int i = 1; i <= rest; i++) {
			long stepInWeek = (startInWeek + i) % 7;
			if(stepInWeek != 0 && stepInWeek != 6) {
				result ++;
			}
		}
		return result;
	}

}