package boundaries;
import controllers.*;
import entities.*;
import java.util.Scanner;

public class IOInterface {
	static CourseController crs;
	static StudentController std;
	static Scanner sc;
	public static void main(String args[]) {
		std = new StudentController();
		crs = new CourseController();
		sc = new Scanner(System.in);
		int choice;
		do {
			System.out.println("Please choose on one of the following function(-1 to terminate): ");
			System.out.println("1. Add Student"); //done
			System.out.println("2. Add Course"); //done
			System.out.println("3. Add lecture/tutorial/lab slot"); //done main, left NewTutorial and NewLab
			System.out.println("4. Add course component");
			System.out.println("5. Register Course");
			System.out.println("6. Check Available Slots");
			System.out.println("7. Print Student List of a Course");
			System.out.println("8. Enter course assessment components weightage");
			System.out.println("9. Enter coursework mark C inclusive of its components");
			System.out.println("10. Enter exam mark");
			System.out.println("11. Print course statistics");
			System.out.println("12. Print student transcript");
			System.out.println("13. Edit Student Particulars");
			System.out.println("14. Edit Course");
			System.out.println("15. Remove Student");
			System.out.println("16. Remove Course");

			choice = sc.nextInt();
			
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
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				break;
			case 10:
				break;
			case 11:	
				break;
			case 12:
				break;
			default:
				break;
			}
			
		} while (choice != -1);
	}
	
	private static void NewStudent() {
		System.out.println("Please enter the name of the new student:");
		String studentName = sc.next();
		System.out.println("Please enter the id of the new student:");
		String studentID = sc.next();
		System.out.println("Please enter the faculty of the new student:");
		String faculty = sc.next();
		System.out.println("Please enter the year of the new student:");
		int year = sc.nextInt();
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
		String courseID = sc.next();
		System.out.println("Please enter the name for the new course: ");
		String courseName = sc.next();
		System.out.println("Please enter the coordinator name for the new course: ");
		String coordinatorName = sc.next();
		Course c = crs.createCourse(courseID, courseName, coordinatorName);
		boolean result = crs.addCourse(c);
		if (result) {
			NewLecture(c);
			// then after adding lecture, user can add tutorial and lab
			int loop = 0;
			System.out.println("Add a component in the course:");
			while (loop == 0) {
				NewAssessment(c);
				System.out.println("Do you want to add another component? (0 to add another, -1 to terminate) ");
				loop = sc.nextInt();
			}
		}
		else {
			System.out.println("Course with the same courseID exists");
		}
	}
	
	private static void NewLecture(Course c) {
		System.out.println("Add lecture day: "); 
		String weekDay = sc.next();
		System.out.println("Add lecture start time: ");
		long startTime = sc.nextLong();
		System.out.println("Add lecture end time: ");
		long endTime = sc.nextLong();
		TimeSlot ts = crs.createTimeSlot(weekDay, startTime, endTime);
		System.out.println("Enter the name of the professor teaching this lecture section: ");
		String prof = sc.next();
		Lecture lect = crs.createLecture(prof, ts);
		crs.addLectureToCourse(c, lect);
		// successfully added
	}
	
	private static void NewTutorial(Course c) {
		
	}
	
	private static void NewLab(Course c) {
		
	}
	
	private static void NewSection() {
		System.out.println("Please enter the courseID that you want to insert the new section into: ");
		String ID = sc.next();
		Course c = crs.checkCourse(ID); //ERROR HERE, need a function to check for the course and return it using the courseID
		int choice;
		do {
			System.out.println("Please choose the section you want to insert");
			System.out.println("1 for lecture section, 2 for tutorial section, 3 for lab section, -1 to terminate");
			choice = sc.nextInt();
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
		System.out.println("Please enter the name of the component: ");
		String componentName = sc.next();
		System.out.println("Please enter the weightage of the component: ");
		float weightage = sc.nextFloat();
		boolean result = crs.addAssessmentComponent(c, componentName, weightage);
		if(result) {
			System.out.println("Successfully added!");
		}
		else {
			System.out.println("Error");
		}
	}
	
}