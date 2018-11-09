package boundaries;
import controllers.*;
import entities.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

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
		int choice = 0;
		std.load();
		crs.load();
		enr.load();
		do {
			System.out.println("----------------------------------------------------------------------");
			System.out.println("Please choose on one of the following function(-1 to terminate): ");
			System.out.println("1. Add Student"); 
			System.out.println("2. Edit Student Particulars");
			System.out.println("3. Remove Student");
			System.out.println("4. Create a Course");
			System.out.println("5. Edit Course");
			System.out.println("6. Remove Course");
			System.out.println("7. Add lecture/tutorial/lab slot (only for constructing courses)"); 
			System.out.println("8. Add course assessment"); 
			System.out.println("9. Finish constructing a course");
			System.out.println("10. Register Course (only for constructed courses)"); 
			System.out.println("11. Check Available Slots (only for constructed courses)");
			System.out.println("12. Print Student List of a Course (only for constructed courses)");
			System.out.println("13. Enter course assessment components weightage (only for constructed courses)");
			System.out.println("14. Enter coursework mark C inclusive of its components (only for constructed courses)");
			System.out.println("15. Enter exam mark (only for constructed courses)" );
			System.out.println("16. Print course statistics(only for constructed courses)");
			System.out.println("17. Print student transcript");
			System.out.println("-----------------------------------------------------------------------");
			boolean flag = true;
			try {
				choice = sc.nextInt();
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				flag = false;
				System.out.println("Wrong type of input.");
				sc.next();
			}
			if (flag) {
				switch(choice) {
				case 1:
					NewStudent();
					break;
					
				case 2:
					EditStudentParticulars();
					break;
					
				case 3:
					RemoveStudent();
					break;
					
				case 4:
					NewCourse();
					break;
					
				case 5:
					EditCourse();
					break;
					
				case 6:
					RemoveCourse();
					break;
					
				case 7:
					NewSection();
					break;
					
				case 8:
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
					
				case 9:
					RegisterCourse();
					break;
					
				case 10:
					CheckVacancy(); // actually checkVacancy function can be used in registerCourse, if needed then input courseID will be at here
					break;
					
				case 11:
					PrintStudent();
					break;
					
				case 12:
					EditAssessment();
					break;
					
				case 13:
					EnterComponentMark();
					break;
					
				case 14:
					EnterExamMark();
					break;
					
				case 15:
					printCourseStatistics();
					
					break;
					
				case 16:
					printStudentTranscript();
					break;
				
				default:
					break;
				}
			}
		std.save();
		crs.save();
		enr.save();
		} while (choice != -1);
	}
	
	private static void NewStudent() {
		System.out.println("Please enter the name of the new student:");
		String studentName = sc.nextLine();	
		System.out.println("Please enter the id of the new student:");
		String studentID = sc.nextLine();
		System.out.println("Please enter the faculty of the new student:");
		String faculty = sc.nextLine();
		int year = 0;
		while (true) {
			boolean flag = true;
			try {
				System.out.println("Please enter the year of the new student:");
				year = sc.nextInt();
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				flag = false;
				System.out.println("Wrong type of input.");
				sc.next();
			}
			if (flag) {
				break;
			}
		}
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
		boolean result = crs.addConstructingCourse(c);
		if (result) {
			NewLecture(c);
			NewAssessment(c);
			System.out.println("New course added !");
			System.out.println("This course has just been created and not finished constructing yet . Note that once you have done constructing a course , you cannot add new lecture , tutorial or lab to it .");
			System.out.println("You can register students to a course only after that course has been constructed .");
		}
		else {
			System.out.println("Course with the same courseID exists");
		}
	}
	
	private static void NewLecture(Course c) {
		ArrayList<TimeSlot> lectime = new ArrayList<TimeSlot>();
		do {
		System.out.println("Add lecture day: "); 
		String weekDay = sc.nextLine();
		float startTime = 0;
		while (true) {
			boolean flag = true;
			try {
				System.out.println("Add lecture start time: ");
				startTime = sc.nextFloat();
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				flag = false;
				System.out.println("Wrong type of input.");
				sc.next();
			}
			if (flag) {
				break;
			}
		}
		float endTime = 0;
		while (true) {
			boolean flag = true;
			try {
				System.out.println("Add lecture end time: ");
				endTime = sc.nextFloat();
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				flag = false;
				System.out.println("Wrong type of input.");
				sc.next();
			}
			if (flag) {
				break;
			}
		}
		TimeSlot ts = crs.createTimeSlot(weekDay, startTime, endTime);
		lectime.add(ts);
		System.out.println("Enter 1 if you want to add another time slot for this lecture , or enter anything else if you don't ");
		String test = sc.nextLine();
		if (!test.equals("1")) 
			break;
		} while (true) ;
		System.out.println("Enter the name of the professor teaching this lecture section: ");
		String prof = sc.nextLine();
		Lecture lect = crs.createLecture(prof, lectime);
		crs.addLectureToCourse(c, lect);
		if (crs.checkNoTutCourse(c)) {
			int vacancy = 0;
			while (true) {
				boolean flag = true;
				try {
					System.out.println("This course is currently a no - tutorial course . Please enter an initial vacancy for this lecture : ");
					vacancy = sc.nextInt();
					sc.nextLine();
				}
				catch (InputMismatchException e) {
					flag = false;
					System.out.println("Wrong type of input.");
					sc.next();
				}
				if (flag) {
					break;
				}
			}
			crs.setInitialVacancy(lect, vacancy);
		}
		System.out.println("New lecture added!");
		// successfully added
	}
	
	private static void NewTutorial(Course c) {
		ArrayList<Lecture> CourseLecture = c.getCourseLecture();
		crs.printCourseLectureDetails(c);
		int lecno = 0;
		while (true) {
			boolean flag = true;
			try {
				System.out.println("Enter the lecture where you want to add a new tutorial in : ");
				lecno = sc.nextInt();
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				flag = false;
				System.out.println("Wrong type of input.");
				sc.next();
			}
			if (flag) {
				break;
			}
		}
		//Handle errors where user does not input a valid lecture number 
		while ((lecno < 1)||(lecno > CourseLecture.size())){
			while (true) {
				boolean flag = true;
				try {
					System.out.println("Enter the lecture where you want to add a new tutorial in : ");
					lecno = sc.nextInt();
					sc.nextLine();
				}
				catch (InputMismatchException e) {
					flag = false;
					System.out.println("Wrong type of input.");
					sc.next();
				}
				if (flag) {
					break;
				}
			}
		}
		System.out.println("Add tutorial day: "); 
		String weekDay = sc.nextLine();
		float startTime = 0;
		while (true) {
			boolean flag = true;
			try {
				System.out.println("Add tutorial start time: ");
				startTime = sc.nextFloat();
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				flag = false;
				System.out.println("Wrong type of input.");
				sc.next();
			}
			if (flag) {
				break;
			}
		}
		float endTime = 0;
		while (true) {
			boolean flag = true;
			try {
				System.out.println("Add tutorial end time: ");
				endTime = sc.nextFloat();
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				flag = false;
				System.out.println("Wrong type of input.");
				sc.next();
			}
			if (flag) {
				break;
			}
		}
		TimeSlot ts = crs.createTimeSlot(weekDay, startTime, endTime);
		System.out.println("Enter the name of the tutor for this tutorial section: ");
		String tutor = sc.nextLine();
		int index = 0;
		do {
			while (true) {
				boolean flag = true;
				try {
					System.out.println("Enter the index of this tutorial section: ");
					index = sc.nextInt();
					sc.nextLine();
				}
				catch (InputMismatchException e) {
					flag = false;
					System.out.println("Wrong type of input.");
					sc.next();
				}
				if (flag) {
					break;
				}
			}
			Tutorial tut = crs.checkTutorialinCourse(c, index);
			if (tut != null) {
				System.out.println("Tutorial with the same index already exist!");
			}
			else break;
		} while (true);
		int vacancy = 0;
		while (true) {
			boolean flag = true;
			try {
				System.out.println("Enter the vacancy for this tutorial section: ");
				vacancy = sc.nextInt();
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				flag = false;
				System.out.println("Wrong type of input.");
				sc.next();
			}
			if (flag) {
				break;
			}
		}
		Tutorial newTut = crs.createTutorial(tutor, vacancy, null, null, ts, index);
		//no Lab is created yet
		crs.addTutorialToLecture(CourseLecture.get(lecno-1), newTut);
		System.out.println("New tutorial added !");
		 
	}
	
	private static void NewLab(Course c) {
		ArrayList<Lecture> CourseLecture = c.getCourseLecture();
		crs.printCourseLectureDetails(c);
		int lecno = 0;
		while (true) {
			boolean flag = true;
			try {
				System.out.println("Enter the lecture where you want to add a new lab in(Between 1 and "+ CourseLecture.size() + " ) : ");
				lecno = sc.nextInt();
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				flag = false;
				System.out.println("Wrong type of input.");
				sc.next();
			}
			if (flag) {
				break;
			}
		}
		//Handle errors where user does not input a valid lecture number 
		while ((lecno < 1)||(lecno > CourseLecture.size())){
			while (true) {
				boolean flag = true;
				try {
					System.out.println("Lecture not valid ! Enter the lecture where you want to add a new lab in : ");
					lecno = sc.nextInt();
					sc.nextLine();
				}
				catch (InputMismatchException e) {
					flag = false;
					System.out.println("Wrong type of input.");
					sc.next();
				}
				if (flag) {
					break;
				}
			}
		}
		System.out.println("Tutorials in this lecture : ");
		crs.printTutorialInLecture(CourseLecture.get(lecno-1));

		//Handle errors where user does not input a valid tutorial index
		do {
			int checkindex = 0;
			while (true) {
				boolean flag = true;
				try {
					System.out.println("Enter the tutorial index where you want to add a new lab in : ");
					checkindex = sc.nextInt();
					sc.nextLine();
				}
				catch (InputMismatchException e) {
					flag = false;
					System.out.println("Wrong type of input.");
					sc.next();
				}
				if (flag) {
					break;
				}
			}
			Tutorial tut = crs.checkTutorialinLecture(CourseLecture.get(lecno-1), checkindex);
			if (tut != null) {
				System.out.println("Enter the Lab Supervisor Name : ");
				String LabSupervisorName = sc.nextLine();
				System.out.println("Add lab day: "); 
				String weekDay = sc.nextLine();
				float startTime = 0;
				while (true) {
					boolean flag = true;
					try {
						System.out.println("Add lab start time: ");
						startTime = sc.nextFloat();
						sc.nextLine();
					}
					catch (InputMismatchException e) {
						flag = false;
						System.out.println("Wrong type of input.");
						sc.next();
					}
					if (flag) {
						break;
					}
				}
				float endTime = 0;
				while (true) {
					boolean flag = true;
					try {
						System.out.println("Add lab end time: ");
						endTime = sc.nextFloat();
						sc.nextLine();
					}
					catch (InputMismatchException e) {
						flag = false;
						System.out.println("Wrong type of input.");
						sc.next();
					}
					if (flag) {
						break;
					}
				}
				TimeSlot ts = crs.createTimeSlot(weekDay, startTime, endTime);
				tut.setLabSupervisorName(LabSupervisorName);
				tut.setLabTimeSlot(ts);
				System.out.println("New lab added !");
				break;
			}
			else System.out.println("Index not available ! ");
		}while (true);
	}
	
	private static void NewSection() {
		Course c;
		while (true) {
			System.out.println("Please enter the courseID that you want to insert the new section into: (-1 to terminate)");
			String ID = sc.nextLine();
			c = crs.checkConstructingCourse(ID);
			if (ID.equals("-1")) {
				return;
			}
			if (c == null) {
				System.out.println("This course is not available or have finished constructing !");
			}
			else {
				break;
			}
		}
		int choice = 0;
		do {
			while (true) {
				boolean flag = true;
				try {
					System.out.println("Please choose the section you want to insert");
					System.out.println("1 for lecture section, 2 for tutorial section, 3 for lab section, -1 to terminate");
					choice = sc.nextInt();
					sc.nextLine();
				}
				catch (InputMismatchException e) {
					flag = false;
					System.out.println("Wrong type of input.");
					sc.next();
				}
				if (flag) {
					break;
				}
			}
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
			float weightage = 0;
			while (true) {
				boolean flag = true;
				try {
					System.out.println("Please enter the weightage of the component: ");
					weightage = sc.nextFloat();
					sc.nextLine();
				}
				catch (InputMismatchException e) {
					flag = false;
					System.out.println("Wrong type of input.");
					sc.next();
				}
				if (flag) {
					break;
				}
			}
			boolean result = crs.addAssessmentComponent(c, componentName, weightage);
			if(result) {
				System.out.println("Successfully added!");
				crs.allStudentupdateGradeComponent(c, componentName);
			}
			else {
				System.out.println("Error! Assessment component already existed ! ");
			}
			while (true) {
				boolean flag = true;
				try {
					System.out.println("Do you want to add another component? (0 to add another, -1 to terminate) ");
					loop = sc.nextInt();
					sc.nextLine();
				}
				catch (InputMismatchException e) {
					flag = false;
					System.out.println("Wrong type of input.");
					sc.next();
				}
				if (flag) {
					break;
				}
			}
		}
		
	}
	
	private static void RegisterCourse() {
		Student stu;
		while(true) {
			System.out.println("Please enter the Student ID to register course: (-1 to terminate)");
			String studentID = sc.nextLine();		
			if (studentID.equals("-1")) {
				return;
			}
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
			System.out.println("Please enter the courseID that you want to add the student into: (-1 to terminate) ");
			String ID = sc.nextLine();
			c = crs.checkConstructedCourse(ID);
			if ( ID.equals("-1")) {
				return;
			}
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
			int index = 0;
			while(true) {
				while (true) {
					boolean flag = true;
					try {
						System.out.println("Please enter the tutorial index that you want to add the student into (-1 to terminate) : ");
						index = sc.nextInt();
						sc.nextLine();
					}
					catch (InputMismatchException e) {
						flag = false;
						System.out.println("Wrong type of input.");
						sc.next();
					}
					if (flag) {
						break;
					}
				}
				if (index == -1) {
					break;
				}
				if (!enr.EnrollCourse(stu, c, index)) {    //vacancy also get updated in the EnrollCourse func
					System.out.println("Index not available or the tutorial is already full ! ");
				}
				else {
					System.out.println("Registration done !");
					break;
				}
				}
			}
		else {
			crs.printCourseLectureDetails(c);
			int nooflec = crs.NoOfLec(c);
			int lecno = 0;
			while (true) {
				boolean flag = true;
				try {
					System.out.println("Enter the lecture where you want to register the student into (Between 1 and "+ nooflec + " ) : ");
					lecno = sc.nextInt(); 
					sc.nextLine();
				}
				catch (InputMismatchException e) {
					flag = false;
					System.out.println("Wrong type of input.");
					sc.next();
				}
				if (flag) {
					break;
				}
			}
			//Handle errors where user does not input a valid lecture number 
			while ((lecno < 1)||(lecno > nooflec)){
				while (true) {
					boolean flag = true;
					try {
						System.out.println("Lecture not valid ! Enter the lecture where you want to add a new lab in : ");
						lecno = sc.nextInt(); 
						sc.nextLine();
					}
					catch (InputMismatchException e) {
						flag = false;
						System.out.println("Wrong type of input.");
						sc.next();
					}
					if (flag) {
						break;
					}
				}
			}
			ArrayList<Lecture> CourseLecture = c.getCourseLecture();
			Lecture lec = CourseLecture.get(lecno-1);
			enr.EnrollCourse(stu,c,lec);
			System.out.println("Registration done !");
		}
	}
	
	private static void CheckVacancy() {
		Course c;
		while (true) {
			System.out.println("Please enter the courseID that you want to check vacancy :(-1 to terminate) ");
			String ID = sc.nextLine();
			c = crs.checkCourse(ID);
			if (ID.equals("-1")) {
				return;
			}
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
			System.out.println("Please enter the courseID that you want to print the student list :(-1 to terminate) ");
			String ID = sc.nextLine();
			c = crs.checkConstructedCourse(ID);
			if (ID.equals("-1")) {
				return;
			}
			if (c == null) {
				System.out.println("Invalid courseID! ");
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
			System.out.println("Please enter the courseID that you want to edit the assessment : (-1 to terminate)");
			String ID = sc.nextLine();
			c = crs.checkCourse(ID);
			 if (ID.equals("-1")) {
					return;
				}
			if (c == null) {
				System.out.println("Invalid courseID");
			}
			else {
				break;
			}
		}
		crs.printAssessmentWeightage(c);
		while (true) {
			System.out.println("Enter the component name that you want to edit weightage (-1 to terminate): ");
			String compname = sc.nextLine();
			Component comp = crs.checkComponent(c, compname);
			if (compname.equals("-1")) {
				return;
			}
			if (comp == null) {
				System.out.println("Invalid component");
			}
			else {	
				float weight = 0;
				while (true) {
					boolean flag = true;
					try {
						System.out.println("Enter the new weightage that you want to for this component : ");
						weight = sc.nextFloat();
						sc.nextLine();
					}
					catch (InputMismatchException e) {
						flag = false;
						System.out.println("Wrong type of input.");
						sc.next();
					}
					if (flag) {
						break;
					}
				}
			crs.editAssessmentComponent(c, compname, weight);
			System.out.println("Assessment edited !");
			break;
			}
		}	
	}
	private static void EnterComponentMark() {
		Student stu;
		while(true) {
			System.out.println("Please enter the Student ID that you want to enter coursework mark (-1 to terminate): ");
			String studentID = sc.nextLine();
			stu = std.checkStudent(studentID);
			if (studentID.equals("-1")) {
				return;
			}
			if (stu == null) {
				System.out.println("Invalid studentID");
			}
			else {
				break;
			}
		}
		Course c;
		while (true) {
			System.out.println("Please enter the courseID that you want to enter mark (-1 to terminate) : ");
			String ID = sc.nextLine();
			c = crs.checkConstructedCourse(ID);
			if (ID.equals("-1")) {
				return;
			}
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
				System.out.println("Enter the component name that you want to enter mark (-1 to terminate) : ");
				String compname = sc.nextLine();
				Component comp = crs.checkComponent(c, compname);
				if (compname.equals("-1")) {
					return;
				}
				if (comp == null) {
					System.out.println("Invalid component");
				}
				else {
					float mark = 0;
					do {
						while (true) {
							boolean flag = true;
							try {
								System.out.println("Enter the mark that you want to for this component (Maximum 100) : ");
								mark = sc.nextFloat();
								sc.nextLine();
							}
							catch (InputMismatchException e) {
								flag = false;
								System.out.println("Wrong type of input.");
								sc.next();
							}
							if (flag) {
								break;
							}
						}
					} while ((mark>100)||(mark<0));
					std.EditGrade(stu, c, compname , mark);
					System.out.println("Mark entered !");
					break;
				}
			}
		}
		else System.out.println("Student " + stu.getName() + " is not registered for the course " + c.getCourseID() );
	}
	
	private static void EnterExamMark() {
		Student stu;
		while(true) {
			System.out.println("Please enter the Student ID thay you want to enter exam mark (-1 to terminate): ");
			String studentID = sc.nextLine();
			stu = std.checkStudent(studentID);
			if (studentID.equals("-1")) {
				return;
			}
			if (stu == null) {
				System.out.println("Invalid studentID");
			}
			else {
				break;
			}
		}
		Course c;
		while (true) {
			System.out.println("Please enter the courseID that you want to enter exam mark (-1 to terminate) : ");
			String ID = sc.nextLine();
			c = crs.checkConstructedCourse(ID);
			if (ID.equals("-1")) {
				return;
			}
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
					while (true) {
						boolean flag = true;
						try {
							System.out.println("Enter the mark that you want to for the exam (Maximum 100) : ");
							mark = sc.nextFloat();
							sc.nextLine();
						}
						catch (InputMismatchException e) {
							flag = false;
							System.out.println("Wrong type of input.");
							sc.next();
						}
						if (flag) {
							break;
						}
					}
				} while ((mark>100)||(mark<0));
				std.EditGrade(stu, c, "exam" , mark);
				System.out.println("Exam mark entered !");
			}
		}
		else System.out.println("Student " + stu.getName() + " is not registered for the course " + c.getCourseID() );
	}
	private static void printCourseStatistics() {
		Course c;
		while (true) {
			System.out.println("Please enter the courseID :(-1 to terminate) ");
			String ID = sc.nextLine();
			c = crs.checkConstructedCourse(ID);
			if (ID.equals("-1")) {
				return;
			}
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
			System.out.println("Please enter the Student ID thay you want to print transcript :(-1 to terminate) ");
			String studentID = sc.nextLine();
			stu = std.checkStudent(studentID);
			if (studentID.equals("-1")) {
				return;
			}
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
			System.out.println("Please enter the Student ID that you want to edit particulars : (-1 to terminate)");
			String studentID = sc.nextLine();
			stu = std.checkStudent(studentID);
			if (studentID.equals("-1")) {
				return;
			}
			if (stu == null) {
				System.out.println("Invalid studentID");
				System.out.println("");
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
		int year = 0;
		while (true) {
			boolean flag = true;
			try {
				System.out.println("Please enter the year of the new student:");
				year = sc.nextInt();
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				flag = false;
				System.out.println("Wrong type of input.");
				sc.next();
			}
			if (flag) {
				break;
			}
		}
		std.EditParticulars(stu, studentName,studentID, faculty, year);
		System.out.println("Student particulars edited !");
	}
	private static void EditCourse() {
		Course c;
		while (true) {
			System.out.println("Please enter the courseID :(-1 to terminate) ");
			String ID = sc.nextLine();
			c = crs.checkCourse(ID);
			if (ID.equals("-1")) {
				return;
			}
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
		System.out.println("Course edited !");
	}
	private static void RemoveStudent() {
		Student stu;
		while(true) {
			System.out.println("Please enter the Student ID that you want to remove (-1 to terminate) : ");
			String studentID = sc.nextLine();
			stu = std.checkStudent(studentID);
			if (studentID.equals("-1")) {
				return;
			}
			if (stu == null) {
				System.out.println("Invalid studentID");
			}
			else {
				break;
			}
		}
		enr.DropAllCourse(stu);
		std.removeStudent(stu);
		System.out.println("Student removed !");
	}
	private static void RemoveCourse() {
		Course c;
		while (true) {
			System.out.println("Please enter the courseID (-1 to terminate): ");
			String ID = sc.nextLine();
			c = crs.checkCourse(ID);
			if (ID.equals("-1")) {
				return;
			}

			if (c == null) {
				System.out.println("Invalid courseID");
			}
			else {
				break;
			}
		}
		enr.AllStudentDropCourse(c);
		crs.removeCourse(c);
		System.out.println("Course removed !");
	}
	private static void finishConstruct() {
		Course c;
		while (true) {
			System.out.println("Please enter the courseID that you want to insert the new section into: (-1 to terminate)");
			String ID = sc.nextLine();
			c = crs.checkConstructingCourse(ID);
			if (ID.equals("-1")) {
				return;
			}
			if (c == null) {
				System.out.println("This course is not available or have finished constructing !");
			}
			else {
				break;
			}
		}
		crs.constructingFinish(c);
	}
}
