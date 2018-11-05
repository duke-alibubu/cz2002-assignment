package controllers;
import java.util.ArrayList;

import entities.*;

public class CourseController {
	private ArrayList<Course> CourseList;
	public CourseController()
	{	
		CourseList = new ArrayList<Course>();
	}
	
	public TimeSlot createTimeSlot(String WeekDay, long StartTime, long FinishTime)
	{
		return new TimeSlot(WeekDay, StartTime, FinishTime);
	}
	public Lecture createLecture(String ProfessorName, TimeSlot Time)
	{
		return new Lecture(ProfessorName, Time);
	}
	public Tutorial createTutorial(String TutorName, int Vacancy, String LabSupervisorName, TimeSlot LabTime, TimeSlot TutTime, int Index)
	{
		return new Tutorial(TutorName, Vacancy, LabSupervisorName, LabTime, TutTime, Index);
	}
	public Course createCourse(String ID, String Name, String CoordinatorName)
	{
		return new Course(ID, Name, CoordinatorName);
	}

	public boolean addCourse(Course aCourse) {
		for(Course check : CourseList) {	
			String checkid = check.getCourseID();
			if (aCourse.getCourseID().equals(checkid)) {
				return false;
			}
		}
		CourseList.add(aCourse);
		return true;
	}
	
	public boolean editCourse(Course c , String cname , String cid , String coorname) {
		if (CourseList.contains(c)) {
		c.setCourseName(cname);
		c.setCourseCoordinatorName(coorname);
		c.setCourseID(cid);
		return true;
		}
		else {
			return false;
		}
	}
	public boolean removeCourse(Course c) {
		if (CourseList.contains(c)) {
		    CourseList.remove(c);
		    return true;
		}
		else {
			return false;
		}
	}
	public void addLectureToCourse(Course c , Lecture l) {
		c.addLecture(l);
	}
	public void editLecture(Lecture l , String ProfessorName , TimeSlot LectureTime ) {
		l.setProfessorName(ProfessorName);
		l.setLectureTime(LectureTime);
	}
	public boolean removeLecture(Course c , Lecture l)
	{
		ArrayList<Lecture> CourseLecture = c.getCourseLecture();
		if (CourseLecture.contains(l))
		{
			c.removeLecture(l);
			return true;
		}
		else
		{
			return false;
		}
	}
	public void addTutorialToLecture(Lecture l , Tutorial t) {
		l.addTutorial(t);
	}
	public void editTutorial(Tutorial t , String TutorName , String LabSupName , int index , TimeSlot TutTime , TimeSlot LabTime) {
		t.setTutorName(TutorName);
		t.setIndex(index);
		t.setLabSupervisorName(LabSupName);
		t.setTutTimeSlot(TutTime);
		t.setLabTimeSlot(LabTime);
	}
	public boolean removeTutorial(Lecture l , Tutorial t) {
		ArrayList<Tutorial> tutorial = l.getTutorial();
		if (tutorial.contains(t))
		{
			tutorial.remove(t);
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean editAssessmentComponent(Course c , String compname , float weight ) {
		Assessment assess;
		assess = c.getCourseAssessment();
		return assess.editComponent(compname,weight);
	}
	public boolean addAssessmentComponent(Course c , String compname , float weight ) {
		Assessment assess;
		assess = c.getCourseAssessment();
		return assess.addComponent(compname,weight);
	}	
	public boolean removeAssessmentComponent(Course c , String compname , float weight ) {
		Assessment assess;
		assess = c.getCourseAssessment();
		return assess.removeComponent(compname);
	}
	
}
