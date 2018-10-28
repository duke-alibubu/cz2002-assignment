import java.util.*;

public class Student {
	private String studentName;
	private String studentID;
	private String faculty;
	private int year;
	private ArrayList<Course> registeredCourse;
	private ArrayList<Grade> studentGrade;

	public Student(String studentName, String studentID, String faculty, int year){
		this.studentName = studentName;
		this.studentID = studentID;
		this.faculty = faculty;
		this.year = year;
	}
	
	public void setStudentName(String StudentName) {
		this.studentName = StudentName;
	}
	
	public String getStudentName() {
		return this.studentName;
	}
	
	public void setStudentID(String StudentID) {
		this.studentID = StudentID;
	}
	
	public String getStudentID() {
		return this.studentID;
	}
	
	public void setFaculty(String Faculty) {
		this.faculty = Faculty;
	}
	
	public String getFaculty() {
		return this.faculty;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public int getYear() {
		return this.year;
	}
	
	public void addCourse(Course course1) {
		this.registeredCourse.add(course1);
	}
	
	public ArrayList getRegisteredCourse() {
		return (this.registeredCourse);
	}
	
	public void addStudentGrade() { // parameters still not sure
		
	}
	
	public void getStudentGrade() {
		// for each registered course, print out the corresponding grade to it
		// maybe match the index with the course (like in arraylist registered course index 0 -> index 0 of grade)
	}
}
