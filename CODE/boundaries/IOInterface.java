<<<<<<< HEAD:CODE/boundary/IOInterface.java
package boundary;
import controllers.*;
import entities.*;
import java.util.Scanner;
=======
package boundaries;
>>>>>>> b43457913ee2d846f2125ead2bab0d883bef0f89:CODE/boundaries/IOInterface.java

public class IOInterface {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int choice;
		do {
			System.out.println("Please choose on one of the following function(-1 to terminate): ");
			System.out.println("1. Add Student");
			System.out.println("2. Add Course");
			System.out.println("3. Register Course");
			System.out.println("4. Check Available Slots");
			System.out.println("5. Print Student List of a Course");
			System.out.println("6. Enter course assessment components weightage");
			System.out.println("7. Enter coursework mark ¨C inclusive of its components");
			System.out.println("8. Enter exam mark");
			System.out.println("9. Print course statistics");
			System.out.println("10. Print student transcript");
			choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				
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
	
	private boolean NewStudent() {
		return false;
	}
	
	private boolean NewCourse() {
		return false;
	}
	
	private boolean RegisterCourse() {
		return false;
	}
	
	private boolean CheckSlot() {
		return false;
	}
	
	private boolean PrintStudentList() {
		return false;
	}
	
	private boolean EnterComponentsWeightage() {
		return false;
	}
	
	private boolean EnterCourseworkMark() {
		return false;
	}
	
	private boolean EnterExamMark() {
		return false;
	}
	
	private boolean PrintCourseStatistics() {
		return false;
	}
	
	private boolean PrintStudentTranscript() {
		return false;
	}
}
