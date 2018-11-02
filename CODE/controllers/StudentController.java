import java.util.*;

public class StudentController {
	private ArrayList<Student> StudentList;
	Scanner scan = new Scanner(System.in);
	
	public void AddStudent(String Name, String ID, String faculty, int year) {
		this.StudentList.add(new Student(Name, ID, faculty, year));
	}
	
	public boolean RemoveStudent(String ID) {
		Iterator iter = StudentList.iterator();
		int loop=0;
		while (iter.hasNext()) {
			Student check = StudentList.get(loop);
			if (check.studentID == ID) {
				this.StudentList.remove(loop);
				return true;
			}
			loop ++;
		}
		return false;
	}
	
	public boolean EditParticulars(String ID) {
		Iterator iter = StudentList.iterator();
		int loop=0;
		while (iter.hasNext()) {
			Student edit = StudentList.get(loop);
			if (edit.studentID == ID) {
				while (true) {
					System.out.println("Please choose the particulars that you want to edit: ");
					System.out.println("Input 1 to edit name, Input 2 to edit faculty, Input 3 to edit , Input -1 to terminate edit function: ");
					int select = scan.nextInt();
					if (select == -1) {
						break;
					}
					switch (select) {
					case 1:
						System.out.println("Please enter the name: ");
						String name = scan.next();
						edit.editName(name);
						this.StudentList.remove(loop);
						this.StudentList.add(loop, edit);
						break;
					case 2:
						System.out.println("Please enter the faculty: ");
						String faculty = scan.next();
						edit.editFaculty(faculty);
						this.StudentList.remove(loop);
						this.StudentList.add(loop, edit);
						break;
					case 3:	
						System.out.println("Please enter the faculty: ");
						int year = scan.nextInt();
						edit.editYear(year);
						this.StudentList.remove(loop);
						this.StudentList.add(loop, edit);
						break;
					default:
						System.out.println("Please enter correct values: ");
					}
				}
				return true;
			}
			loop ++;
		}
		return false;
	}
	
	public boolean EditGrade(String studentID, String CourseID) {
		Iterator iter = StudentList.iterator();
		int loop=0;
		while (iter.hasNext()) {
			Student check = StudentList.get(loop);
			if (check.studentID == studentID) {
				boolean grade = check.editCourseGrade(CourseID);
				return grade;
			}
			loop ++;
		}
		return false;
	}
	
}
