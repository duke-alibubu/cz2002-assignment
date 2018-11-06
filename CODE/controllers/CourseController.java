package controllers;
import java.util.ArrayList;

import entities.*;

public class CourseController {
	private ArrayList<Course> CourseList;
	public CourseController()
	{	
		CourseList = new ArrayList<Course>();
	}
	
	public TimeSlot createTimeSlot(String WeekDay, float StartTime, float FinishTime)
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
	public Course checkCourse(String CourseID) {
		for (Course c : CourseList) {
			if (c.getCourseID().equals(CourseID)){
				return c;
			}
		}
		return null;
	}
	public void printTutorialStudentList(Tutorial tut) {
		System.out.println(tut.detailStudentList());
	}
	public void printLectureStudentList(Lecture lec ) {
		ArrayList<Tutorial> tutorial = lec.getTutorial();
		for (Tutorial tut : tutorial) {
			System.out.println(tut.detailStudentList());
		}
	}
	public void printCourseStudentList(Course c) {
		ArrayList<Lecture> CourseLecture = c.getCourseLecture();
		for (Lecture lec : CourseLecture) {
			printLectureStudentList(lec);
		}
	}
	public void printTutorialDetails(Tutorial tut) {
		System.out.println(tut.detailTutorial());
	}
	//print only lecture details , not details of tutorials in lecture
	public void printLectureDetails(Lecture lec) {
		System.out.println(lec.detailLecture());
	}
	public void printTutorialInLecture(Lecture lec) {
		ArrayList<Tutorial> tutorial = lec.getTutorial();
		for (Tutorial tut : tutorial) {
			printTutorialDetails(tut);
			System.out.println();
		}
	}
	public Tutorial checkTutorialinLecture(Lecture lec , int index) {
		ArrayList<Tutorial> tutorial = lec.getTutorial();
		for (Tutorial tut : tutorial) {
			if (tut.getIndex()==index) return tut;
		}
		return null;
	}
	public void printTutorialVacancy(Tutorial tut) {
		System.out.println("Tutorial "+Integer.toString(tut.getIndex())+" : Vacancy = " +Integer.toString(tut.getVacancy()));
	}
	public void printLectureVacancy(Lecture lec) {
		ArrayList<Tutorial> tutorial = lec.getTutorial();
		for (Tutorial tut : tutorial) {
			printTutorialVacancy(tut);
		}
	}
	public void printCourseVacancy(Course c) {
		ArrayList<Lecture> CourseLecture = c.getCourseLecture();
		for (Lecture lec : CourseLecture) {
			printLectureVacancy(lec);
		}
	}
}
