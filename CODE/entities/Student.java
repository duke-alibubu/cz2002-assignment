import java.util.*;

public class Student {
	private String studentName;
	public String studentID;
	private String faculty;
	private int year;
	public ArrayList<StudentCourse> registeredCourse;
	
	public Student(String studentName, String studentID, String faculty, int year){
		this.studentName = studentName;
		this.studentID = studentID;
		this.faculty = faculty;
		this.year = year;
	}
	
	public void editName(String studentName) {
		this.studentName = studentName;
	}
	
	public void editFaculty(String faculty) {
		this.faculty = faculty;
	}
	
	public void editYear(int year) {
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
	
	public boolean editCourseGrade(String CourseID, String component, int value) {
		Iterator iter = registeredCourse.iterator();
		int loop = 0;
		while (iter.hasNext()) {
			StudentCourse edit = registeredCourse.get(loop);
			if (CourseID == edit.getCourse().getCourseID()) {
				boolean grade = edit.setGrade(component, value);
				return grade;
			}
		}
		return false;
	}
}
