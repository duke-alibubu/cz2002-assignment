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
		this.WeekDay = new String(t.WeekDay);
		this.StartTime = t.StartTime;
		this.FinishTime = t.FinishTime;
	}
	public long getStart() {
		return StartTime;
	}
	public void setStart(long startTime) {
		this.StartTime = startTime;
	}
	public long getFinish() {
		return FinishTime;
	}
	public void setFinish(long finishTime) {
		this.FinishTime = finishTime;
	}
	public String getweekDay() {
		return this.WeekDay;
	}
	public void setWeekDay(String weekDay) {
		this.WeekDay = new String(weekDay);
	}
}
