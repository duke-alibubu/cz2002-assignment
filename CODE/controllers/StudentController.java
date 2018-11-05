package controllers;
import java.util.*;
import entities.*;

public class StudentController {
	private ArrayList<Student> StudentList;

	public StudentController()
	{
		StudentList = new ArrayList<Student>();
	}

	private int find(Student aStudent)
	{
		return StudentList.indexOf(aStudent);
	}
	private int find(String ID)
	{
		int index = -1;
		for (int i=0; i < StudentList.size(); i++)
		{
			if (ID.equals(StudentList.get(i).getID()))
			{
				index = i;
				break;
			}
		}
		return index;
	}
	
	public Student getStudent(String ID)
	{
		return StudentList.get(find(ID));
	}

	public boolean addStudent(String Name, String ID, String Faculty, int Year)
	{
		if (find(ID) != -1)
			return false;
		Student aStudent = new Student(Name, ID, Faculty, Year);
		StudentList.add(aStudent);
		return true;
	}
	
	public boolean removeStudent(Student aStudent)
	{
		int index = find(aStudent);
		if (index == -1)
			return false;
		StudentList.remove(index);
		return true;
	}

	public boolean removeStudent(String ID)
	{
		return removeStudent(getStudent(ID));
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
	
	public boolean EditGrade(String studentID, Course aCourse, String component, float value) {
		Iterator iter = StudentList.iterator();
		int loop=0;
		while (iter.hasNext()) {
			Student check = StudentList.get(loop);
			if (check.getID() == studentID) {
				return check.EditCourseGrade(aCourse, component, value);
			}
			loop ++;
		}
		return false;
	}
	public Student checkStudent(String StudentID) {
		for (Student std : StudentList) {
			if (std.getID().equals(StudentID)){
				return std;
			}
		}
		return null;
	}
}
