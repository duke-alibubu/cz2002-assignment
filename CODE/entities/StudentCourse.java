package entities;

public class StudentCourse {
	private Course aCourse;
	private Grade aGrade;
	private int anIndex;
	
	public StudentCourse(Course aCourse, int anIndex)
	{
		this.aCourse = aCourse;
		this.anIndex = anIndex;
	}
	
	public Course getCourse()
	{
		return this.aCourse;
	}
	public Grade getGrade()
	{
		return aGrade;
	}
	public boolean setGrade(String ComponentName, float Value)
	{
		return aGrade.editComponent(ComponentName, Value);
	}
}

