package entities;
import java.util.ArrayList;

public class Tutorial {
	private String TutorName;
	private ArrayList<Student> StudentList;;
	private int Vacancy; 
	private String LabSupervisorName ;
	private TimeSlot LabTimeSlot ;
	private TimeSlot TutTimeSlot ;
	private int Index;
	
	public Tutorial(String TutorName, int Vacancy, String LabSupervisorName, TimeSlot LabTime, TimeSlot TutTime, int Index)
	{
		this.TutorName = new String(TutorName);
		this.Vacancy = Vacancy;
		this.LabSupervisorName = new String(LabSupervisorName);
		this.LabTimeSlot = new TimeSlot(LabTime);
		this.TutTimeSlot = new TimeSlot(TutTime);
		this.Index = Index;
	}
	
	public int getIndex() {
		return Index;
	}
	public void setIndex(int Index) {
		this.Index = Index;
	}
	public String getTutorName() {
		return TutorName;
	}	
	public void setTutorName (String TutorName) {
		this.TutorName = null;
		this.TutorName = new String(TutorName);
	}
	
	public int getVacancy(){
		return Vacancy;
	}
	public void setVacancy(int Vacancy) {
		this.Vacancy = Vacancy;
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
	public ArrayList<Student> getStudentList()
	{
		return StudentList;
	}
	public TimeSlot getTutTimeSlot()
	{
		return TutTimeSlot;
	}
	public void setTutTimeSlot(TimeSlot time)
	{
		this.TutTimeSlot = null;
		this.TutTimeSlot = new TimeSlot(time);
	}	
	public TimeSlot getLabTimeSlot()
	{
		return LabTimeSlot;
	}
	public void setLabTimeSlot(TimeSlot time)
	{
		this.LabTimeSlot = null;
		this.LabTimeSlot = new TimeSlot(time);
	}
	public String detailTutorial() {
		String detailTS = TutTimeSlot.detailTimeSlot();
		return "Index : " + Integer.toString(Index) + '\n' + "Tutor Name : " + TutorName + '\n' + "Tutorial Time Slot : " + detailTS  + '\n' + 
				"Vacancy : " + Integer.toString(Vacancy) ;
	}

}

