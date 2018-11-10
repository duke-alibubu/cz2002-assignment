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
	//method overloading for no-tut courses
	public boolean EnrollCourse(Student stud , Course course , Lecture lec) {
		StudentEnroll.add(stud);
		CourseEnroll.add(course);
		stud.addCourse(course , -1);
		return course.addStudenttoNoTutCourse(stud, lec);
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
	public void DropAllCourse(Student stud) {
		ArrayList<StudentCourse> RegisteredCourses = stud.getRegisteredCourses();
		for (StudentCourse studcour : RegisteredCourses) {
			DropCourse(stud,studcour.getCourse());
		}
	}
	public void AllStudentDropCourse(Course c) {
		ArrayList<Lecture> CourseLecture = c.getCourseLecture();
		for (Lecture lec : CourseLecture) {
			ArrayList<Tutorial> tutorial = lec.getTutorial();
			for (Tutorial tut : tutorial) {
				ArrayList<Student> StudentList= tut.getStudentList();
				ArrayList<Student> spare = new ArrayList<Student>();
				for (Student stud : StudentList) {
					spare.add(stud);
				}
				for (Student stud : spare) {
					DropCourse(stud,c);
				}
			}
		}
	}
	public boolean changeIndex(Student stud , Course course , int index) {
		if (index == -1) {
			return false;
		}
		for (int i = 0 ; i < StudentEnroll.size();i++) {
			if ((stud == StudentEnroll.get(i))&&(course == CourseEnroll.get(i))) {
				ArrayList<StudentCourse> RegisteredCourses;
				RegisteredCourses = stud.getRegisteredCourses();
				for (int j = 0 ; j < RegisteredCourses.size();j++) {
					if (RegisteredCourses.get(j).getCourse() == course) {
						int oldindex = RegisteredCourses.get(j).getIndex();
						RegisteredCourses.get(j).setIndex(index);
						course.removeStudentfromCourse(stud);
						if(!course.addStudenttoCourse(stud, index))
							{
							course.addStudenttoCourse(stud, oldindex);
							return false;
							} else return true;
					}
				}
			}
		}
		return false;
	}
	public void printStudentCourseTranscript(Student stud , Course c ) {
		System.out.println("Course " + c.getCourseName() + ":");
		Assessment assess = c.getCourseAssessment();
		ArrayList<Component> CourseDistribution = assess.getDistribution();
		for (Component comp : CourseDistribution) {
			System.out.println(" Component " + comp.getName() +" : "+ stud.getComponentGrade(c, comp.getName()));
		}
		System.out.println(" Total grade : " + stud.getFinalGrade(c));
	}
	
	public void printStudentTranscript(Student stud) {
		 ArrayList<StudentCourse> RegisteredCourses = stud.getRegisteredCourses();
		 for (StudentCourse stucour : RegisteredCourses) {
			 Course c = stucour.getCourse();
			 printStudentCourseTranscript(stud , c);
		 }
	}
}
