package entities;
import java.util.ArrayList;

public class Tutorial {
	private String TutorName;
	private ArrayList<Student> StudentList;
	private int Vacancy; 
	private String LabSupervisorName ;
	private TimeSlot LabTimeSlot ;
	private TimeSlot TutTimeSlot ;
	private int Index;
	
	public Tutorial(String TutorName, int Vacancy, String LabSupervisorName, TimeSlot LabTime, TimeSlot TutTime, int Index)
	{
		if (TutorName!= null) {
		this.TutorName = new String(TutorName);}
		else this.TutorName = null;
		this.Vacancy = Vacancy;
		if (LabSupervisorName != null) {
		this.LabSupervisorName = new String(LabSupervisorName);}
		else this.LabSupervisorName = null;
		if (LabTime == null) this.LabTimeSlot = null;
		else	this.LabTimeSlot = new TimeSlot(LabTime);
		if (TutTime != null) {
		this.TutTimeSlot = new TimeSlot(TutTime);}
		else this.TutTimeSlot = null;
		this.Index = Index;
		this.StudentList = new ArrayList<Student>();
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
			if (Vacancy == 0) return false;
			Vacancy--;
			return true;
		}
		return false;
	}
	public boolean removeStudent( Student stud ) {
		if (StudentList.contains(stud)) {
			StudentList.remove(stud);
			Vacancy++;
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
		if (LabTimeSlot != null) {
		String detailLab = LabTimeSlot.detailTimeSlot();
		return "Index : " + Integer.toString(Index) + '\n' + "Tutor Name : " + TutorName + '\n' + "Tutorial Time Slot : " + detailTS  + '\n' + 
				"Vacancy : " + Integer.toString(Vacancy) + '\n' + "Lab Supervisor Name : " + LabSupervisorName + '\n' + "Lab Time Slot : "
				+ detailLab;
		}
		else return "Index : " + Integer.toString(Index) + '\n' + "Tutor Name : " + TutorName + '\n' + "Tutorial Time Slot : " + detailTS  + '\n' + 
				"Vacancy : " + Integer.toString(Vacancy) ;
	}
	public String detailStudentList() {
		String detailSL = new String();
		for (Student stud : StudentList) {
			detailSL += stud.detailStudent();
			detailSL += '\n';
		}
		return detailSL;
	}
}