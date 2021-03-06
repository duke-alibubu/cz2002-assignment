package controllers;
import java.util.ArrayList;
import entities.*;

public class EnrollmentController {
	//private ArrayList<Student> StudentEnroll = new ArrayList<Student>();
	//private ArrayList<Course> CourseEnroll = new ArrayList<Course>();
	private ArrayList<Enrollment> Enrollments = new ArrayList<Enrollment>();
	private DatabaseController dbc = new DatabaseController();
	public void save(){
		dbc.addEnrollmentRecord(Enrollments, "Database/EnrollmentList.ser");}
	
	public void load() {
		Enrollments = dbc.readEnrollmentRecord("Database/EnrollmentList.ser");}
	
	public boolean EnrollCourse(Student stud , Course course , int index) {

		Enrollment anEnrollment = new Enrollment(stud, course, index);
		Enrollments.add(anEnrollment);
		stud.addCourse(anEnrollment);
		return course.addStudenttoCourse(stud, index);
	}
	public boolean EnrollCourse(Student stud , Course course , Lecture lec) {
		
		Enrollment anEnrollment = new Enrollment(stud, course, -1);
		Enrollments.add(anEnrollment);

		stud.addCourse(anEnrollment);
		return course.addStudenttoNoTutCourse(stud, lec);
	}
	public boolean DropCourse(Student stud , Course course) {
		//for (int i = 0 ; i < StudentEnroll.size();i++) {
		for (int i = 0 ; i < Enrollments.size();i++) {
			if ((stud.getID().equals(Enrollments.get(i).getStudent().getID())&&(course.getCourseID().equals(Enrollments.get(i).getCourse().getCourseID())))) {
				Enrollments.remove(i);
				stud.removeCourse(course);
				return course.removeStudentfromCourse(stud);
			}
		}
		return false;
	}
	public void DropAllCourse(Student stud) {
		ArrayList<Enrollment> RegisteredCourses = stud.getRegisteredCourses();
		ArrayList<Enrollment> spare = new ArrayList<Enrollment>();
		for (Enrollment studcour : RegisteredCourses) {
			spare.add(studcour);
		}
		for (Enrollment studcour : spare) {
			DropCourse(stud,studcour.getCourse());
		}
		spare = null;
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
				spare = null;
			}
		}
	}
	public boolean changeIndex(Student stud , Course course , int index) {
		if (index == -1) {
			return false;
		}
		for (int i = 0 ; i < Enrollments.size();i++) {
			if ((stud.getID().equals(Enrollments.get(i).getStudent().getID())&&(course.getCourseID().equals(Enrollments.get(i).getCourse().getCourseID())))) {
				ArrayList<Enrollment> RegisteredCourses;
				RegisteredCourses = stud.getRegisteredCourses();
				for (int j = 0 ; j < RegisteredCourses.size();j++) {
					if (RegisteredCourses.get(j).getCourse().getCourseID().equals(course.getCourseID())) {
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
		 ArrayList<Enrollment> RegisteredCourses = stud.getRegisteredCourses();
		 for (Enrollment stucour : RegisteredCourses) {
			 Course c = stucour.getCourse();
			 printStudentCourseTranscript(stud , c);
		 }
	}
}
