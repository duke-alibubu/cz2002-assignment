import java.util.*;

public class StudentCourse {
	private Course aCourse;
	private Grade aGrade;
	private int anIndex;
	
	public StudentCourse(Course aCourse, int anIndex) {
		this.aCourse = aCourse;
		this.anIndex = anIndex;
	}
	
	public String getCourseID() {
		return this.aCourse.getCourseID();
	}
	public void addGrade(){
		// haven't implement
	}
}

