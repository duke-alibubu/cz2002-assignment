package entities;

public class TimeSlot {
	private String WeekDay;
	private long StartTime;
	private long FinishTime;
	public TimeSlot(String weekDay , long startTime , long finishTime) {
		this.WeekDay = new String(weekDay);
		this.StartTime = startTime ;
		this.FinishTime = finishTime;
	}
	public TimeSlot(TimeSlot t) {
		this.WeekDay = t.WeekDay;
		this.StartTime = t.StartTime;
		this.FinishTime = t.FinishTime;
	}
	public TimeSlot() {}
	public void setStart(long startTime) {
		this.StartTime = startTime;
	}
	public long getStart() {
		return StartTime;
	}
	public void setFinish(long finishTime) {
		this.FinishTime = finishTime;
	}
	public long getFinish() {
		return FinishTime;
	}
	public void setWeekDay(String weekDay) {
		this.WeekDay = new String(weekDay);
	}
	public String getweekDay() {
		return this.WeekDay;
	}
	public void printTimeSlot() {
		System.out.println("Day : " + WeekDay);
		int shour , sminute , fhour , fminute;
		shour = (int)StartTime;
		sminute = (int)((StartTime - (long)shour)*60) ;
		fhour = (int)FinishTime;
		fminute = (int)((FinishTime - (long)fhour)*60) ;
		System.out.println("Start Time : " + shour + "h "+sminute +"m");
		System.out.println("Finish Time : " + fhour + "h "+fminute +"m");
	}
}
