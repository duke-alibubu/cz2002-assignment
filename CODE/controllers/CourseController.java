package controllers;
import java.util.ArrayList;

import entities.*;

public class CourseController {
	private ArrayList<Course> CourseList;
	public CourseController()
	{	
		CourseList = new ArrayList<Course>();
	}
	
	public boolean AddCourse(String courseID, String courseName, String courseCoordinator ) {
		for(Course check : CourseList) {	
			String checkid = check.getCourseID();
			if (courseID.equals(checkid)) {
				return false;
			}
		}
		CourseList.add(new Course(courseID, courseName, courseCoordinator));
		return true;
	}
	
	public boolean EditCourse(Course c , String cname , String cid , String coorname) {
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

	public boolean AddTutorialToLecture(Course c , Lecture l , Tutorial t) {
		if (CourseList.contains(c)) {
			ArrayList<Lecture> CourseLecture;
			CourseLecture = c.getCourseLecture();
			if (CourseLecture.contains(l)) {
				l.addTutorial(t);
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	public void EditTutorial(Tutorial t , String TutorName , String LabSupName , int index , TimeSlot TutTime , TimeSlot LabTime) {
		t.setTutorName(TutorName);
		t.setIndex(index);
		t.setLabSupervisorName(LabSupName);
		t.setTutTimeSlot(TutTime);
		t.setLabTimeSlot(LabTime);
	}
	public boolean RemoveTutorial(Course c , Lecture l , Tutorial t) {
		if (CourseList.contains(c)) {
			ArrayList<Lecture> CourseLecture;
			CourseLecture = c.getCourseLecture();
			if (CourseLecture.contains(l)) {
				ArrayList<Tutorial> tutorial;
				tutorial = l.getTutorial();
				if (tutorial.contains(t)) {
					tutorial.remove(t);
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	public boolean AddLectureToCourse(Course c , Lecture l) {
		if (CourseList.contains(c)) {
		    c.addLecture(l);
		    return true;
		}
		else {
			return false;
		}
	}
	public void EditLecture(Lecture l , String ProfessorName , TimeSlot LectureTime ) {
		l.setProfessorName(ProfessorName);
		l.setLectureTime(LectureTime);
	}
	public boolean RemoveLecture(Course c , Lecture l) {
		if (CourseList.contains(c)) {
			ArrayList<Lecture> CourseLecture;
			CourseLecture = c.getCourseLecture();
			if (CourseLecture.contains(l)) {
				c.removeLecture(l);
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	public boolean EditAssessmentComponent(Course c , String compname , float weight ) {
		Assessment assess;
		assess = c.getCourseAssessment();
		return assess.editComponent(compname,weight);
	}
	public boolean AddAssessmentComponent(Course c , String compname , float weight ) {
		Assessment assess;
		assess = c.getCourseAssessment();
		return assess.addComponent(compname,weight);
	}	
	public boolean RemoveAssessmentComponent(Course c , String compname , float weight ) {
		Assessment assess;
		assess = c.getCourseAssessment();
		return assess.removeComponent(compname);
	}
	
}
