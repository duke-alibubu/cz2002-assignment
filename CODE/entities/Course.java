package entities;

import java.util.ArrayList;

public class Course {
	private String CourseID;
	private String CourseName;
	private String CourseCoordinatorName;
  	private ArrayList<Lecture> CourseLectures;
	private Assessment CourseAssessment;
	
	public Course(String CourseID, String CourseName, String CourseCoordinator)
	{
		CourseLecture = new ArrayList<Lecture>(); //instantiate the CourseLecture
		CourseAssessment = new Assessment();
		this.CourseID = new String(CourseID); 
		this.CourseName = new String(CourseName); 
		this.CourseCoordinatorName = new String(CourseCoordinator);
	}
    
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
    
	public void addAssessment(Assessment assessment1) {this.CourseAssessment.add(assessment1);}
    	
	public void removeAssessment(Assessment assessment1) {this.CourseAssessment.remove(assessment1);}
	
	public ArrayList<Lecture> getCourseLecture() {return CourseLecture;}

	public Assessment getCourseAssessment() {return CourseAssessment;}
	public void printCourse() {
		System.out.println("Course Name : "+CourseName+ " ID " + CourseID);
		System.out.println("Course Coordinator Name : "+ CourseCoordinatorName);
		int leccount = 1;
		for (Lecture lec : CourseLecture) {
			System.out.println("Lecture " + leccount + ":");
			lec.printLecture();
			leccount++;
			}
	}
	
}
