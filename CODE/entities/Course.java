package entities;

import java.util.*;
import java.io.Serializable;

public class Course implements Serializable {
	private static final long serialVersionUID = 1L;
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
    //not used
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
	public boolean addStudenttoNoTutCourse(Student stud , Lecture lec) {
		
		for (Lecture lect : CourseLecture) {
		 if (lect.getProfessorName().equals(lec.getProfessorName()))
			return lec.addStudenttoLec(stud, -1);
		}
		return false;
	}
	public float percentageFactor() {
		float totalWeight = 0;
		for (Component AssessComp : CourseAssessment.Distribution) {
			totalWeight += AssessComp.getValue();
		}
		if (totalWeight != 0) 
			return 100/totalWeight ;
		else return 0;
	}
	public String DetailCourse() {
		return "Course Name : " + CourseName + " , ID " + CourseID + " , Course Coordinator Name " + CourseCoordinatorName ;
	}
	public ArrayList<Student> getStudentList()
	{
			ArrayList<Student> aList = new ArrayList<Student>();
			for (int i=0; i<CourseLecture.size(); i++)
				aList.addAll(CourseLecture.get(i).getStudentList());
			return aList;

	}
}
