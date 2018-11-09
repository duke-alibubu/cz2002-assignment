package entities;
import java.io.Serializable;

public class TimeSlot implements Serializable{
	private String WeekDay;
	private float StartTime;
	private float FinishTime;
	public TimeSlot(String weekDay , float startTime , float finishTime) {
		this.WeekDay = new String(weekDay);
		this.StartTime = startTime ;
		this.FinishTime = finishTime;
	}
	public TimeSlot(TimeSlot t) {
		this.WeekDay = new String(t.WeekDay);
		this.StartTime = t.StartTime;
		this.FinishTime = t.FinishTime;
	}
	public float getStart() {
		return StartTime;
	}
	public void setStart(float startTime) {
		this.StartTime = startTime;
	}
	public float getFinish() {
		return FinishTime;
	}
	public void setFinish(float finishTime) {
		this.FinishTime = finishTime;
	}
	public String getweekDay() {
		return this.WeekDay;
	}
	public void setWeekDay(String weekDay) {
		this.WeekDay = new String(weekDay);
	}
	public String detailTimeSlot() {
		int shour , sminute , fhour , fminute;
		shour = (int)StartTime;
		sminute = (int)((StartTime - (float)shour)*60) ;
		fhour = (int)FinishTime;
		fminute = (int)((FinishTime - (float)fhour)*60) ;
		return (WeekDay + " From " + Integer.toString(shour) + "h"+ Integer.toString(sminute) +"m to " + Integer.toString(fhour) + "h"+ Integer.toString(fminute)+"m" );
	}
}
