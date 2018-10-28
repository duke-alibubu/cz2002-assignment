import java.util.*;

public class Student {
	private String studentName;
	private String studentID;
	private String faculty;
	private int year;
	private ArrayList<StudentCourse> registeredCourse;
	
	public Student(String studentName, String studentID, String faculty, int year){
		this.studentName = studentName;
		this.studentID = studentID;
		this.faculty = faculty;
		this.year = year;
	}
	
	public String getName() {
		return this.studentName;
	}
	
	public String getID() {
		return this.studentID;
	}
	
	public String getFaculty() {
		return this.faculty;
	}
	
	public int getYear() {
		return this.year;
	}
	
	public boolean addCourse(Course aCourse, int index) {
		Iterator iter = registeredCourse.iterator();
		while (iter.hasNext()) {
			if 	(aCourse == iter.next()) {  //if found, break and return false
				return false;
			}
		}
		this.registeredCourse.add(new StudentCourse(aCourse,index)); // if not found, add it into the list of registered courses
		return true;
	}

	public ArrayList getRegisteredCourse() {
		return (this.registeredCourse);
	}
	
	public boolean changeCourseIndex(Course aCourse, int index) {
		int indexOfStudentCourse = 0;
		Iterator iter = registeredCourse.iterator();
		while (iter.hasNext()) {
			if (aCourse == iter.next()) {
				registeredCourse.remove(indexOfStudentCourse);
				registeredCourse.add(indexOfStudentCourse, new StudentCourse(aCourse,index));
				return true;
			}
			indexOfStudentCourse ++;
		}
		return false;
	}
	
	public boolean removeCourse(Course aCourse) {
		int indexOfStudentCourse = 0;
		Iterator iter = registeredCourse.iterator();
		while (iter.hasNext()) {
			if (aCourse == iter.next()) {
				registeredCourse.remove(indexOfStudentCourse);
				return true;
			}
			indexOfStudentCourse ++;
		}
		return false;
	}
}
