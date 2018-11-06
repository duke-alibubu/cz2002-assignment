package entities;

 public class StudentCourse {
	private Course aCourse;
	private Grade aGrade;
	private int anIndex;
	private float TotalMark;
	
	public StudentCourse(Course aCourse, int anIndex)
	{
		this.aCourse = aCourse;
		this.anIndex = anIndex;
		this.aGrade = new Grade(new Assessment());
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
	public int getIndex(){
		return this.anIndex;
	}
	public void setIndex(int index){
		this.anIndex = index;
	}
	public void calculateTotal() {
		Assessment assess = aCourse.getCourseAssessment();
		float totalMark = 0;
		float totalWeight = 0;
		for (Component GradeComp : aGrade.Distribution) {
			// get AssessComp with the same name as GradeComp
			totalWeight += GradeComp.getValue();
			for (Component comp : assess.Distribution) {
				if (comp.getName().equals(GradeComp.getName())){
					Component AssessComp = comp;
					totalMark += AssessComp.getValue() * GradeComp.getValue();
					break;
				}
			}
		}
		TotalMark =  totalMark / totalWeight;
	}
	public float getTotalMark() {
		return TotalMark;
	}
}
