package entities;
import java.util.ArrayList;

public class Student {
	private String StudentName;
	private String StudentID;
	private String Faculty;
	private int Year;
	private ArrayList<StudentCourse> RegisteredCourses ;
	
	public Student(String studentName, String studentID, String faculty, int year){
		this.StudentName = studentName;
		this.StudentID = studentID;
		this.Faculty = faculty;
		this.Year = year;
		this.RegisteredCourses = new ArrayList<StudentCourse>();
	}
	
	public void setName(String studentName) {
		this.StudentName = new String(studentName);
	}
	
	public void setFaculty(String faculty) {
		this.Faculty = new String(faculty);
	}
	
	public void setYear(int year) {
		this.Year = year;
	}
	
	public String getName() {
		return new String(this.StudentName);
	}
	
	public String getID() {
		return this.StudentID;
	}
	public void setID(String ID) {
		this.StudentID = new String(ID);
	}
	
	public String getFaculty() {
		return new String(this.Faculty);
	}
	
	public int getYear() {
		return this.Year;
	}
	
	private int find(Course aCourse)
	{
		int index = -1;
		for (int i=0; i<RegisteredCourses.size(); i++)
		{
			if (aCourse == RegisteredCourses.get(i).getCourse())
			{
				index = i;
				break;
			}
		}
		return index;
	}
	
	public boolean addCourse(Course aCourse, int index)
	{
		if (-1 != find(aCourse))
			return false;
		this.RegisteredCourses.add(new StudentCourse(aCourse, index));
		return true;
	}
	
	public boolean removeCourse(Course aCourse)
	{
		int index = find(aCourse);
		if (-1 == index)
			return false;

		RegisteredCourses.remove(index);
		return true;
	}

	public ArrayList<StudentCourse> getRegisteredCourses()
	{
		return RegisteredCourses;
	}
	
	public boolean EditCourseGrade(Course aCourse, String componentName, float grade) {
		int index = find(aCourse);
		if (-1 == index)
			return false;
	
		return RegisteredCourses.get(index).setGrade(componentName, grade);
	}
	
	public String detailStudent() {
		return StudentName + " ID " + StudentID + " FACULTY " + Faculty + " YEAR " + Integer.toString(Year); 
	}
	public boolean checkRegistered(Course c ) {
		int index = find(c);
		if (-1 == index)
			return false;
		return true;
	}
	
	public void calculateCourseTotal(Course c) {
		RegisteredCourses.get(find(c)).calculateTotal();
	}
	
	public float getComponentGrade(Course c , String compname) {
		ArrayList<Component> GradeDistribution = RegisteredCourses.get(find(c)).getGrade().getDistribution();
		for (Component comp : GradeDistribution) {
			if (compname.equals(comp.getName())){
				return comp.getValue();
			}
		}
		return 50;
	}
	
	public float getFinalGrade(Course c) {
		return RegisteredCourses.get(find(c)).getTotalMark();
	}
}