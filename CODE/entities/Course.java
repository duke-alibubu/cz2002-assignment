package entities;

import java.util.*;

public class Course {
	private String CourseID;
	private String CourseName;
	private String CourseCoordinatorName;
  	private ArrayList<Lecture> CourseLecture;
	private Assessment CourseAssessment;
	
	public Course(String CourseID, String CourseName, String CourseCoordinator) {
		CourseLecture = new ArrayList<Lecture>(); //instantiate the CourseLecture
		CourseAssessment = new Assessment();
		this.CourseID = new String(CourseID); 
		this.CourseName = new String(CourseName); 
		this.CourseCoordinatorName = new String (CourseCoordinator);}
    
	public Course() {
		CourseLecture = new ArrayList<Lecture>(); //instantiate the CourseLecture
		CourseAssessment = new Assessment();
		}
	
	public String getCourseID() {return CourseID;}
  
	public void setCourseID(String CourseID) {this.CourseID = new String(CourseID);}
  
	public String getCourseName() {return CourseName;}
  
	public void setCourseName(String CourseName) {this.CourseName = new String(CourseName);}
  
	public String getCourseCoordinatoerName() {return CourseCoordinatorName;}
  
	public void setCourseCoordinatorName(String CourseCoordinatorName) {this.CourseCoordinatorName = new String(CourseCoordinatorName);}
	
	public void addLecture(Lecture lecture1) {this.CourseLecture.add(lecture1);}
  
  	public void removeLecture(Lecture lecture1) {this.CourseLecture.remove(lecture1);}
    
	public boolean addAssessment(String assessName , float weight) {return this.CourseAssessment.addComponent(assessName , weight);}
    	
	public boolean removeAssessment(String assessName) {return this.CourseAssessment.removeComponent(assessName);}
	
	public boolean editAssessment(String assessName, float weight) { return this.CourseAssessment.editComponent(assessName,weight); }
	
	public ArrayList<Lecture> getCourseLecture() {return CourseLecture;}

	public Assessment getCourseAssessment() {return CourseAssessment;}
	public boolean addStudenttoCourse(Student stud , int index) {
		for (Lecture lec : CourseLecture) {
			if (lec.addStudenttoLec(stud, index)) {
				return true;
			}
		}
		return false;
	}
	public boolean removeStudentfromCourse(Student stud) {
		for (Lecture lec : CourseLecture) {
			if (lec.removeStudentfromLec(stud)) {
				return true;
			}
		}
		return false;
	}
}
