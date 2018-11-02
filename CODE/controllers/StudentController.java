import java.util.*;

public class StudentController {
	private ArrayList<Student> StudentList;

	
	public void AddStudent(String Name, String ID, String faculty, int year) {
		this.StudentList.add(new Student(Name, ID, faculty, year));
	}
	
	public boolean RemoveStudent(String ID) {
		Iterator iter = StudentList.iterator();
		int loop=0;
		while (iter.hasNext()) {
			Student check = StudentList.get(loop);
			if (check.studentID == ID) {
				this.StudentList.remove(loop);
				return true;
			}
			loop ++;
		}
		return false;
	}
	
	public boolean EditParticulars(String ID, String Name, String Faculty, int Year) {
		Iterator iter = StudentList.iterator();
		int loop=0;
		while (iter.hasNext()) {
			Student edit = StudentList.get(loop);
			if (edit.getID() == ID) {
				edit.setName(Name);
				edit.setFaculty(Faculty);
				edit.setYear(Year);
				this.StudentList.remove(loop);
				this.StudentList.add(loop, edit);
				return true;
			}
			loop ++;
		}
		return false;
	}
	
	public boolean EditGrade(String studentID, String CourseID, String component, int value) {
		Iterator iter = StudentList.iterator();
		int loop=0;
		while (iter.hasNext()) {
			Student check = StudentList.get(loop);
			if (check.studentID == studentID) {
				boolean grade = check.editCourseGrade(CourseID, component, value);
				return grade;
			}
			loop ++;
		}
		return false;
	}
	
}
