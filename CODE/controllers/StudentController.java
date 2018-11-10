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
		aStudent = null;
		return true;
	}

	public boolean removeStudent(String ID)
	{
		return removeStudent(getStudent(ID));
	}
	
	public void EditParticulars(Student stud, String Name, String ID , String Faculty, int Year) {
				stud.setName(Name);
				stud.setFaculty(Faculty);
				stud.setYear(Year);
				if (checkID(ID)) {
					stud.setID(ID);
				}
				else {
					System.out.println("ID is unchanged . A student with the ID "+ID+ "had already existed in the Student List");
				}
	}
	
	public boolean EditGrade(Student stud, Course aCourse, String component, float value) {
		return stud.EditCourseGrade(aCourse, component, value);
	}
	public Student checkStudent(String StudentID) {
		for (Student stud : StudentList) {
			if (StudentID.equals(stud.getID())){
				return stud;
			}
		}
		return null;
	}
	public void printStudentDetails(Student stud) {
		System.out.println(stud.detailStudent());
	}
	public boolean checkRegisteredCourseforStudent(Student stud , Course c) {
		return stud.checkRegistered(c);
	}
	
	public boolean checkID(String ID) {
		for (Student stu : StudentList) {
			if (stu.getID().equals(ID)) {
				return false;
			}
		}
		return true;
	}
}
