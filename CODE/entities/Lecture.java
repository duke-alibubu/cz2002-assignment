package entities;
import java.util.ArrayList;

public class Lecture {
	private String ProfessorName;
	private TimeSlot LectureTime ;
	private ArrayList<Tutorial> tutorial;
	public Lecture(String ProfessorName, TimeSlot LectureTime) {
		this.ProfessorName = new String(ProfessorName);
		this.LectureTime = new TimeSlot(LectureTime);
		tutorial = new ArrayList<Tutorial>();
		tutorial.add(new Tutorial(null,0,null,null,null,-1));
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
	public void setLectureTime(TimeSlot t) {
		LectureTime = new TimeSlot(t);
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
	public boolean addStudenttoLec(Student stud , int index) {
		for (Tutorial tut : tutorial) {
			int check = tut.getIndex();
			if (index == check) {
				return tut.addStudent(stud);
			}
		}
		return false;
	}
	public boolean removeStudentfromLec(Student stud) {
		for (Tutorial tut : tutorial) {
			if (tut.removeStudent(stud)) {
				return true;
			}
		}
		return false;
	}
	public String detailLecture() {
		String detailTS = LectureTime.detailTimeSlot();
		return "Professor Name : " + ProfessorName + '\n' + "Lecture Time Slot : " + detailTS ;
	}
}