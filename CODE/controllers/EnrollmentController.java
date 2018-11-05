package controllers;
import java.util.ArrayList;
import entities.*;

public class EnrollmentController {
	private ArrayList<Student> StudentEnroll = new ArrayList<Student>();
	private ArrayList<Course> CourseEnroll = new ArrayList<Course>();
	
	public boolean EnrollCourse(Student stud , Course course , int index) {
		StudentEnroll.add(stud);
		CourseEnroll.add(course);
		stud.addCourse(course , index);
		return course.addStudenttoCourse(stud, index);
	}
	public boolean DropCourse(Student stud , Course course) {
		for (int i = 0 ; i < StudentEnroll.size();i++) {
			if ((stud == StudentEnroll.get(i))&&(course == CourseEnroll.get(i))) {
				StudentEnroll.remove(i);
				CourseEnroll.remove(i);
				stud.removeCourse(course);
				return course.removeStudentfromCourse(stud);
			}
		}
		return false;
	}
	public boolean changeIndex(Student stud , Course course , int index) {
		for (int i = 0 ; i < StudentEnroll.size();i++) {
			if ((stud == StudentEnroll.get(i))&&(course == CourseEnroll.get(i))) {
				ArrayList<StudentCourse> RegisteredCourses;
				RegisteredCourses = stud.getRegisteredCourses();
				for (int j = 0 ; j < RegisteredCourses.size();j++) {
					if (RegisteredCourses.get(j).getCourse() == course) {
						RegisteredCourses.get(j).setIndex(index);
						course.removeStudentfromCourse(stud);
						course.addStudenttoCourse(stud, index);
						return true;
					}
				}
			}
		}
		return false;
	}
}