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
			System.out.println("1. Add Student");
			System.out.println("2. Add Course");
			System.out.println("3. Register Course");
			System.out.println("4. Check Available Slots");
			System.out.println("5. Print Student List of a Course");
			System.out.println("6. Enter course assessment components weightage");
			System.out.println("7. Enter coursework mark C inclusive of its components");
			System.out.println("8. Enter exam mark");
			System.out.println("9. Print course statistics");
			System.out.println("10. Print student transcript");
			choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				NewStudent();
				break;				
			case 2:
				
				break;
				
			case 3:
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
		boolean result = crs.AddCourse(courseID, courseName, coordinatorName);
		if (result) {
			System.out.println("Add lecture ");
			System.out.println("Add one assessment");
		}
		else {
			System.out.println("Course with the same courseID exists");
		}
	}
}