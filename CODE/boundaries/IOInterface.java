package boundaries;
import controllers.*;
import entities.*;

import java.util.Scanner;
import java.util.ArrayList;

public class IOInterface {
	static CourseController crs;
	static StudentController std;
	static EnrollmentController enr;
	static Scanner sc;
	public static void main(String args[]) {
		std = new StudentController();
		crs = new CourseController();
		enr = new EnrollmentController();
		sc = new Scanner(System.in);
		int choice;
		do {
			System.out.println("Please choose on one of the following function(-1 to terminate): ");
			System.out.println("1. Add Student"); //done
			System.out.println("2. Add Course"); //done
			System.out.println("3. Add lecture/tutorial/lab slot"); //done
			System.out.println("4. Add course assessment"); // done
			System.out.println("5. Register Course"); // done
			System.out.println("6. Check Available Slots");// done
			System.out.println("7. Print Student List of a Course");// done
			System.out.println("8. Enter course assessment components weightage");//done
			System.out.println("9. Enter coursework mark C inclusive of its components");//done
			System.out.println("10. Enter exam mark");//done
			System.out.println("11. Print course statistics");
			System.out.println("12. Print student transcript");
			System.out.println("13. Edit Student Particulars");
			System.out.println("14. Edit Course");
			System.out.println("15. Remove Student");
			System.out.println("16. Remove Course");

			choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
			case 1:
				NewStudent();
				break;
				
			case 2:
				NewCourse();
				break;
				
			case 3:
				NewSection();
				break;
				
			case 4:
				while (true) {
					System.out.println("Please enter the courseID that you want to insert the new section into: ");
					String ID = sc.nextLine();
					Course c = crs.checkCourse(ID);
					if (c == null) {
						System.out.println("Invalid courseID");
					}
					else {
						NewAssessment(c);
						break;
					}
				}
				break;
				
			case 5:
				RegisterCourse();
				break;
				
			case 6:
				CheckVacancy(); // actually checkVacancy function can be used in registerCourse, if needed then input courseID will be at here
				break;
				
			case 7:
				PrintStudent();
				break;
				
			case 8:
				EditAssessment();
				break;
				
			case 9:
				EnterComponentMark();
				break;
				
			case 10:
				EnterExamMark();
				break;
				
			case 11:	
				printCourseStatistics();
				break;
				
			case 12:
				printStudentTranscript();
				break;
				
			case 13:
				EditStudentParticulars();
				break;
				
			case 14:
				EditCourse();
				break;
				
			case 15:
				RemoveStudent();
				break;
				
			case 16:
				RemoveCourse();
				break;
			
			default:
				break;
			}
			
		} while (choice != -1);
	}
	
	private static void NewStudent() {
		System.out.println("Please enter the name of the new student:");
		String studentName = sc.nextLine();	
		System.out.println("Please enter the id of the new student:");
		String studentID = sc.nextLine();
		System.out.println("Please enter the faculty of the new student:");
		String faculty = sc.nextLine();
		System.out.println("Please enter the year of the new student:");
		int year = sc.nextInt();
		sc.nextLine();
		boolean result = std.addStudent(studentName, studentID, faculty, year);
		if (result == true) {
			System.out.println("Successfully added the new student");
		}
		else {
			System.out.println("Student with the same studentID exists");
		}
	}
	
	private static void NewCourse() {
		System.out.println("Please enter the ID for the new course: ");
		String courseID = sc.nextLine();
		System.out.println("Please enter the name for the new course: ");
		String courseName = sc.nextLine();
		System.out.println("Please enter the coordinator name for the new course: ");
		String coordinatorName = sc.nextLine();
		Course c = crs.createCourse(courseID, courseName, coordinatorName);
		boolean result = crs.addCourse(c);
		if (result) {
			NewLecture(c);
			NewAssessment(c);
		}
		else {
			System.out.println("Course with the same courseID exists");
		}
	}
	
	private static void NewLecture(Course c) {
		System.out.println("Add lecture day: "); 
		String weekDay = sc.nextLine();
		System.out.println("Add lecture start time: ");
		float startTime = sc.nextFloat();
		sc.nextLine();
		System.out.println("Add lecture end time: ");
		float endTime = sc.nextFloat();
		sc.nextLine();
		TimeSlot ts = crs.createTimeSlot(weekDay, startTime, endTime);
		System.out.println("Enter the name of the professor teaching this lecture section: ");
		String prof = sc.nextLine();
		Lecture lect = crs.createLecture(prof, ts);
		crs.addLectureToCourse(c, lect);
		if (crs.checkNoTutCourse(c)) {
			System.out.println("This course is currently a no - tutorial course . Please enter an initial vacancy for this lecture : ");
			int vacancy = sc.nextInt();
			sc.nextLine();
			crs.setInitialVacancy(lect, vacancy);
		}
		// successfully added
	}
	
	private static void NewTutorial(Course c) {
		ArrayList<Lecture> CourseLecture = c.getCourseLecture();
		crs.printCourseLectureDetails(c);
		System.out.println("Enter the lecture where you want to add a new tuturial in : ");
		int lecno = sc.nextInt(); 
		sc.nextLine();
		//Handle errors where user does not input a valid lecture number 
		while ((lecno < 1)||(lecno > CourseLecture.size())){
			System.out.println("Lecture not valid ! Enter the lecture where you want to add a new tuturial in(Between 1 and "+ CourseLecture.size() + " ) : ");
			lecno = sc.nextInt(); 
			sc.nextLine();
		}
		System.out.println("Add tutorial day: "); 
		String weekDay = sc.nextLine();
		System.out.println("Add tutorial start time: ");
		float startTime = sc.nextFloat();
		sc.nextLine();
		System.out.println("Add tutorial end time: ");
		float endTime = sc.nextFloat();
		sc.nextLine();
		TimeSlot ts = crs.createTimeSlot(weekDay, startTime, endTime);
		System.out.println("Enter the name of the tutor for this tutorial section: ");
		String tutor = sc.nextLine();
		int index;
		do {
		System.out.println("Enter the index of this tutorial section: ");
		index = sc.nextInt();
		sc.nextLine();
		} while (index ==-1);
		System.out.println("Enter the vacancy for this tutorial section: ");
		int vacancy = sc.nextInt();
		sc.nextLine();
		Tutorial newTut = crs.createTutorial(tutor, vacancy, null, null, ts, index);
		//no Lab is created yet
		crs.addTutorialToLecture(CourseLecture.get(lecno-1), newTut);
		 
	}
	
	private static void NewLab(Course c) {
		ArrayList<Lecture> CourseLecture = c.getCourseLecture();
		crs.printCourseLectureDetails(c);
		System.out.println("Enter the lecture where you want to add a new lab in(Between 1 and "+ CourseLecture.size() + " ) : ");
		int lecno = sc.nextInt(); 
		sc.nextLine();
		//Handle errors where user does not input a valid lecture number 
		while ((lecno < 1)||(lecno > CourseLecture.size())){
			System.out.println("Lecture not valid ! Enter the lecture where you want to add a new lab in : ");
			lecno = sc.nextInt(); 
			sc.nextLine();
		}
		System.out.println("Tutorials in this lecture : ");
		crs.printTutorialInLecture(CourseLecture.get(lecno-1));

		//Handle errors where user does not input a valid tutorial index
		do {
			System.out.println("Enter the tutorial index where you want to add a new lab in : ");
			int checkindex = sc.nextInt();
			sc.nextLine();
			Tutorial tut = crs.checkTutorialinLecture(CourseLecture.get(lecno-1), checkindex);
			if (tut != null) {
				System.out.println("Enter the Lab Supervisor Name : ");
				String LabSupervisorName = sc.nextLine();
				System.out.println("Add lab day: "); 
				String weekDay = sc.nextLine();
				System.out.println("Add lab start time: ");
				float startTime = sc.nextFloat();
				sc.nextLine();
				System.out.println("Add lab end time: ");
				float endTime = sc.nextFloat();
				sc.nextLine();
				TimeSlot ts = crs.createTimeSlot(weekDay, startTime, endTime);
				tut.setLabSupervisorName(LabSupervisorName);
				tut.setLabTimeSlot(ts);
				break;
			}
			else System.out.println("Index not available ! ");
		}while (true);
	}
	
	private static void NewSection() {
		Course c;
		while (true) {
			System.out.println("Please enter the courseID that you want to insert the new section into: ");
			String ID = sc.nextLine();
			c = crs.checkCourse(ID);
			if (c == null) {
				System.out.println("Invalid courseID");
			}
			else {
				break;
			}
		}
		int choice;
		do {
			System.out.println("Please choose the section you want to insert");
			System.out.println("1 for lecture section, 2 for tutorial section, 3 for lab section, -1 to terminate");
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				NewLecture(c);
				break;
			case 2:
				NewTutorial(c); // how to insert under certain lecture?
				break;
			case 3:
				NewLab(c); // how to insert under certain tutorial or lecture
				break;
			default:
				break;
			}
		} while (choice != -1);
	}
	
	private static void NewAssessment(Course c) {
		int loop = 0;
		System.out.println("Add a component in the course:");
		while (loop == 0) {
			System.out.println("Please enter the name of the component: ");
			String componentName = sc.nextLine();
			System.out.println("Please enter the weightage of the component: ");
			float weightage = sc.nextFloat();
			sc.nextLine();
			boolean result = crs.addAssessmentComponent(c, componentName, weightage);
			if(result) {
				System.out.println("Successfully added!");
				crs.allStudentupdateGradeComponent(c, componentName);
			}
			else {
				System.out.println("Error! Assessment component already existed ! ");
			}
			System.out.println("Do you want to add another component? (0 to add another, -1 to terminate) ");
			loop = sc.nextInt();
			sc.nextLine();
		}
		
	}
	
	private static void RegisterCourse() {
		Student stu;
		while(true) {
			System.out.println("Please enter the Student ID to register course: ");
			String studentID = sc.nextLine();
			stu = std.checkStudent(studentID);
			if (stu == null) {
				System.out.println("Invalid studentID");
			}
			else {
				break;
			}
		}
		Course c;
		while (true) {
			System.out.println("Please enter the courseID that you want to add the student into: ");
			String ID = sc.nextLine();
			c = crs.checkCourse(ID);
			if (c == null) {
				System.out.println("Invalid courseID!");
			}
			else {
				break;
			}
		}
		if(!crs.checkNoTutCourse(c)) {
		// print out the lecture and tutorial details for the user to choose the index
			ArrayList<Lecture> CourseLecture = c.getCourseLecture();
			for (Lecture lec : CourseLecture ) {
				crs.printLectureDetails(lec);
				crs.printTutorialInLecture(lec);
		} 
			int index;
			while(true) {
				System.out.println("Please enter the tutorial index that you want to add the student into : ");
				index = sc.nextInt();
				sc.nextLine();
				if (!enr.EnrollCourse(stu, c, index)) {    //vacancy also get updated in the EnrollCourse func
					System.out.println("Index not available or the tutorial is already full ! ");
				}
				else break;
				}
			}
		else {
			crs.printCourseLectureDetails(c);
			int nooflec = crs.NoOfLec(c);
			System.out.println("Enter the lecture where you want to register the student into (Between 1 and "+ nooflec + " ) : ");
			int lecno = sc.nextInt(); 
			sc.nextLine();
			//Handle errors where user does not input a valid lecture number 
			while ((lecno < 1)||(lecno > nooflec)){
				System.out.println("Lecture not valid ! Enter the lecture where you want to add a new lab in : ");
				lecno = sc.nextInt(); 
				sc.nextLine();
			}
			ArrayList<Lecture> CourseLecture = c.getCourseLecture();
			Lecture lec = CourseLecture.get(lecno-1);
			enr.EnrollCourse(stu,c,lec);
		}
	}
	
	private static void CheckVacancy() {
		Course c;
		while (true) {
			System.out.println("Please enter the courseID that you want to check vacancy : ");
			String ID = sc.nextLine();
			c = crs.checkCourse(ID);
			if (c == null) {
				System.out.println("Invalid courseID");
			}
			else {
				break;
			}
		}
		// check all vacancies
		crs.printCourseVacancy(c);
	}
	
	private static void PrintStudent() {
		Course c;
		while (true) {
			System.out.println("Please enter the courseID that you want to print the student list : ");
			String ID = sc.nextLine();
			c = crs.checkCourse(ID);
			if (c == null) {
				System.out.println("Invalid courseID");
			}
			else {
				break;
			}
		}
		if(!crs.checkNoTutCourse(c)) {
			System.out.println("Student List by Tutorial Session :");
			crs.printCourseStudentList(c);
			}
		else {
			System.out.println("Student List :");
			crs.printCourseStudentList(c);
			}

	}
	private static void EditAssessment() {
		Course c;
		while (true) {
			System.out.println("Please enter the courseID that you want to edit the assessment : ");
			String ID = sc.nextLine();
			c = crs.checkCourse(ID);
			if (c == null) {
				System.out.println("Invalid courseID");
			}
			else {
				break;
			}
		}
		crs.printAssessmentWeightage(c);
		while (true) {
			System.out.println("Enter the component name that you want to edit weightage : ");
			String compname = sc.nextLine();
			Component comp = crs.checkComponent(c, compname);
			if (comp == null) {
				System.out.println("Invalid component");
			}
			else {	
			System.out.println("Enter the new weightage that you want to for this component : ");
			float weight = sc.nextFloat();
			sc.nextLine();
			crs.editAssessmentComponent(c, compname, weight);
			break;
			}
		}	
	}
	private static void EnterComponentMark() {
		Student stu;
		while(true) {
			System.out.println("Please enter the Student ID thay you want to enter coursework mark : ");
			String studentID = sc.nextLine();
			stu = std.checkStudent(studentID);
			if (stu == null) {
				System.out.println("Invalid studentID");
			}
			else {
				break;
			}
		}
		Course c;
		while (true) {
			System.out.println("Please enter the courseID that you want to enter mark  : ");
			String ID = sc.nextLine();
			c = crs.checkCourse(ID);
			if (c == null) {
				System.out.println("Invalid courseID");
			}
			else {
				break;
			}
		}
		if (std.checkRegisteredCourseforStudent(stu, c)) {
			crs.printAssessmentWeightage(c);
			while (true) {
				System.out.println("Enter the component name that you want to enter mark : ");
				String compname = sc.nextLine();
				Component comp = crs.checkComponent(c, compname);
				if (comp == null) {
					System.out.println("Invalid component");
				}
				else {
					float mark = 0;
					do {
					System.out.println("Enter the mark that you want to for this component (Maximum 100) : ");
					mark = sc.nextFloat();
					sc.nextLine();
					} while ((mark>100)||(mark<0));
					std.EditGrade(stu, c, compname , mark);
					break;
				}
			}
		}
		else System.out.println("Student " + stu.getName() + " is not registered for the course " + c.getCourseID() );
	}
	
	private static void EnterExamMark() {
		Student stu;
		while(true) {
			System.out.println("Please enter the Student ID thay you want to enter exam mark : ");
			String studentID = sc.nextLine();
			stu = std.checkStudent(studentID);
			if (stu == null) {
				System.out.println("Invalid studentID");
			}
			else {
				break;
			}
		}
		Course c;
		while (true) {
			System.out.println("Please enter the courseID that you want to enter exam mark  : ");
			String ID = sc.nextLine();
			c = crs.checkCourse(ID);
			if (c == null) {
				System.out.println("Invalid courseID");
			}
			else {
				break;
			}
		}
		if (std.checkRegisteredCourseforStudent(stu, c)) {
			Component comp = crs.checkComponent(c, "exam");
			if (comp == null) {
				System.out.println("This course does not have an exam component! ");
			}
			else {
				float mark = 0;
				do {
				System.out.println("Enter the mark that you want to for the exam (Maximum 100) : ");
				mark = sc.nextFloat();
				sc.nextLine();
				} while ((mark>100)||(mark<0));
				std.EditGrade(stu, c, "exam" , mark);
			}
		}
		else System.out.println("Student " + stu.getName() + " is not registered for the course " + c.getCourseID() );
	}
	private static void printCourseStatistics() {
		Course c;
		while (true) {
			System.out.println("Please enter the courseID : ");
			String ID = sc.nextLine();
			c = crs.checkCourse(ID);
			if (c == null) {
				System.out.println("Invalid courseID");
			}
			else {
				break;
			}
		}
		crs.calculateCourseTotalGrade(c);
		crs.printCourseStatistic(c);
	}
	private static void printStudentTranscript() {
		Student stu;
		while(true) {
			System.out.println("Please enter the Student ID thay you want to print transcript : ");
			String studentID = sc.nextLine();
			stu = std.checkStudent(studentID);
			if (stu == null) {
				System.out.println("Invalid studentID");
			}
			else {
				break;
			}
		}
		std.printStudentDetails(stu);
		std.printStudentTranscript(stu);
	}
	private static void EditStudentParticulars() {
		Student stu;
		while(true) {
			System.out.println("Please enter the Student ID that you want to edit particulars : ");
			String studentID = sc.nextLine();
			stu = std.checkStudent(studentID);
			if (stu == null) {
				System.out.println("Invalid studentID");
			}
			else {
				break;
			}
		}
		System.out.println("Please enter the name of the student:");
		String studentName = sc.nextLine();	
		System.out.println("Please enter the id of the student:");
		String studentID = sc.nextLine();
		System.out.println("Please enter the faculty of the new student:");
		String faculty = sc.nextLine();
		System.out.println("Please enter the year of the new student:");
		int year = sc.nextInt();
		sc.nextLine();
		std.EditParticulars(stu, studentName,studentID, faculty, year);
	}
	private static void EditCourse() {
		Course c;
		while (true) {
			System.out.println("Please enter the courseID : ");
			String ID = sc.nextLine();
			c = crs.checkCourse(ID);
			if (c == null) {
				System.out.println("Invalid courseID");
			}
			else {
				break;
			}
		}
		System.out.println("Please enter the ID for the course: ");
		String cid = sc.nextLine();
		System.out.println("Please enter the name for the course: ");
		String cname = sc.nextLine();
		System.out.println("Please enter the coordinator name for the course: ");
		String coorname = sc.nextLine();
		crs.editCourse(c, cname, cid, coorname);
	}
	private static void RemoveStudent() {
		Student stu;
		while(true) {
			System.out.println("Please enter the Student ID that you want to remove : ");
			String studentID = sc.nextLine();
			stu = std.checkStudent(studentID);
			if (stu == null) {
				System.out.println("Invalid studentID");
			}
			else {
				break;
			}
		}
		enr.DropAllCourse(stu);
		std.removeStudent(stu);
	}
	private static void RemoveCourse() {
		Course c;
		while (true) {
			System.out.println("Please enter the courseID : ");
			String ID = sc.nextLine();
			c = crs.checkCourse(ID);
			if (c == null) {
				System.out.println("Invalid courseID");
			}
			else {
				break;
			}
		}
		enr.AllStudentDropCourse(c);
		crs.removeCourse(c);
	}
}
