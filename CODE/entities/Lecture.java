package entities;
import java.util.ArrayList;
import java.io.Serializable;

public class Lecture implements Serializable{
	private static final long serialVersionUID = 1L;
	private String ProfessorName;
	private ArrayList<TimeSlot> LectureTime ;
	private ArrayList<Tutorial> tutorial;
	public Lecture(String ProfessorName, ArrayList<TimeSlot> LectureTime) {
		this.ProfessorName = new String(ProfessorName);
		this.LectureTime = new ArrayList<TimeSlot>();
		for (TimeSlot t : LectureTime) {
			this.LectureTime.add(new TimeSlot(t));
		}
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
	public ArrayList<TimeSlot> getLectureTime(){
		return LectureTime;
	}
	public void setLectureTime( String day , long start , long finish , int index) {
		
		this.LectureTime.get(index).setWeekDay(day);
		this.LectureTime.get(index).setStart(start);
		this.LectureTime.get(index).setFinish(finish);
	}
	public void setLectureTime(TimeSlot t, int index) {
		LectureTime.remove(index);
		LectureTime.add(index, t);
		
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
		String result = new String("Professor Name : " + ProfessorName + '\n' + "Lecture Time Slot : ");
		for (TimeSlot t : LectureTime) {
			String detailTS = t.detailTimeSlot();
			result = result + detailTS + "   ";
		}
		return  result;
	}
	
}
