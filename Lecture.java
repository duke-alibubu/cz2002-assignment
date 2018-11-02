package LectureAndTutorial;
import java.util.ArrayList;

public class Lecture {
	private String ProfessorName;
	private TimeSlot LectureTime ;
	private ArrayList<Tutorial> tutorial;
	public Lecture() {
		tutorial = new ArrayList<Tutorial>();
		LectureTime = new TimeSlot();
	}
	
	public String getProfessorName() {
		return ProfessorName;
	}
	public void setProfessorName(String ProfessorName) {
		this.ProfessorName = new String(ProfessorName);
	}
	
	//manipulate lecture time
	public TimeSlot getLectureTime(){
		return LectureTime;
	}
	public void setLectureTime( String day , long start , long finish) {
		this.LectureTime.setWeekDay(day);
		this.LectureTime.setStart(start);
		this.LectureTime.setFinish(finish);
	}

	
	
	//manipulate tutorial 
	public ArrayList<Tutorial> getTutorial() {
		return this.tutorial;
	}
	public void addTutorial(Tutorial tut) {
		tutorial.add(tut);
	}
	public void removeTutorial(Tutorial tut) {
		tutorial.remove(tut);
	}
	public ArrayList<Tutorial> getTutList(){
		return this.tutorial;
	}
	public void printLecture() {
		System.out.println("Lecturer Name : " + ProfessorName);
		System.out.println("Lecture Time : ");
		LectureTime.printTimeSlot();
		for (Tutorial tut : tutorial) {
			tut.printTutorial();
		}
	}
	
}
