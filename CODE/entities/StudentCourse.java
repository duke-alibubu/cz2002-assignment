package entities;

public class StudentCourse {
	private Course aCourse;
	private Grade aGrade;
	private int anIndex;
	
	public StudentCourse(Course aCourse, int anIndex) {
		this.aCourse = aCourse;
		this.anIndex = anIndex;
	}
	
	public String getCourse() {
		return this.aCourse;
	}
	public boolean editGrade(String ComponentName, float Value){
		return aGrade.editComponent(ComponentName, Value);
	}
}

