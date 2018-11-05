package entities;
import java.util.ArrayList;

public class Tutorial {
	private String TutorName;
	private ArrayList<Student> StudentList = new ArrayList<Student>() ;
	private int vacancy ; 
	private String LabSupervisorName ;
	private TimeSlot LabTimeSlot ;
	private TimeSlot TutTimeSlot ;
	private int Index;
	
	public Tutorial() {
		TutTimeSlot = new TimeSlot();
	}
	
	public int getIndex() {
		return Index;
	}
	public void setIndex(int index) {
		this.Index = index;
	}
	public String getTutorName() {
		return TutorName;
	}
	
	public void setTutorName (String TutorName) {
		this.TutorName = new String(TutorName);
	}
	
	public int getvacancy() {
		return vacancy;
	}
	public void setvacancy(int vacancy) {
		this.vacancy = vacancy;
	}
	
	public String getLabSupervisorName() {
		return LabSupervisorName;
	}
	public void setLabSupervisorName (String LabSupervisorName) {
		this.LabSupervisorName = new String(LabSupervisorName);
	}
	
	// manipulate the StudentList
	public boolean addStudent( Student stud ) {
		if (!(StudentList.contains(stud))) {
			StudentList.add(stud);
			return true;
		}
		return false;
	}
	public boolean removeStudent( Student stud ) {
		if (StudentList.contains(stud)) {
			StudentList.remove(stud);
			return true;
		}
		return false;
	}
	public ArrayList<Student> getStudentList(){
		return StudentList;
	}
	
	//manipulate the TutTimeSlot
	public TimeSlot getTutTimeSlot(){
		return TutTimeSlot;
	}
	
	public void setTutTimeSlot(String day , long start , long finish) {
		this.TutTimeSlot.setWeekDay(day);
		this.TutTimeSlot.setStart(start);
		this.TutTimeSlot.setFinish(finish);
	}

	
	//manipulate the LabTimeSlot
	public TimeSlot getLabTimeSlot(){
		return LabTimeSlot;
	}
	public void setLabTimeSlot(String day , long start , long finish) {
		this.LabTimeSlot.setWeekDay(day);
		this.LabTimeSlot.setStart(start);
		this.LabTimeSlot.setFinish(finish);
	}
	
	public void printTutorial() {
		System.out.println("Tutorial index : " + Index);
		System.out.println("Tutor Name : " + TutorName);
		System.out.println("Vacancy : " + vacancy);
		System.out.println("Tutorial Time Slot : ");
		TutTimeSlot.printTimeSlot();
		System.out.println("Lab Supervisor Name : " + LabSupervisorName);
		System.out.println("Lab Time Slot : ");
		LabTimeSlot.printTimeSlot();
	}
	public void setTutTimeSlot(TimeSlot t) {
		TutTimeSlot = new TimeSlot(t);
	}
	public void setLabTimeSlot(TimeSlot t) {
		LabTimeSlot = new TimeSlot(t);
	}

	
	
}