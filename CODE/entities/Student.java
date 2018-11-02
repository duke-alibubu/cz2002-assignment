package entities;
import java.util.ArrayList;

public class Student {
	private String StudentName;
	private String StudentID;
	private String Faculty;
	private int Year;
	public ArrayList<StudentCourse> RegisteredCourses;
	
	public Student(String studentName, String studentID, String faculty, int year){
		this.StudentName = studentName;
		this.StudentID = studentID;
		this.Faculty = faculty;
		this.Year = year;
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
	
	public String getFaculty() {
		return new String(this.Faculty);
	}
	
	public int getYear() {
		return this.year;
	}
	
	private int find(Course aCourse)
	{
		int index = -1;
		for (int i=0; i<RegistedCourses.size(); i++)
		{
			if (aCourse == RegistedCourses.get(i).getCourse())
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

	public ArrayList<StudentCourse> getRegisteredCourses()
	{
		return RegisteredCourses;
	}	
}
