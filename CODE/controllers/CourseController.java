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
		if (Index == -1) return null;
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
		    c = null;
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
		if (index!=1) {
			t.setIndex(index);
			}
		t.setLabSupervisorName(LabSupName);
		t.setTutTimeSlot(TutTime);
		t.setLabTimeSlot(LabTime);
	}
	public boolean removeTutorial(Lecture l , Tutorial t) {
		if (t.getIndex()==-1) {return false;}
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
	public void allStudentupdateGradeComponent(Course c , String compname) {
		ArrayList<Lecture> CourseLecture = c.getCourseLecture();
		for (Lecture lec : CourseLecture) {
			if (!checkNoTutCourse(c)) {
				ArrayList<Tutorial> tutorial = lec.getTutorial();
				for (Tutorial tut : tutorial) {
					if(tut.getIndex()!=-1) {
						ArrayList<Student> StudentList = tut.getStudentList();
						for (Student stud : StudentList) {
							stud.addGradeComponentforCourse(c, compname);
						}
					}
				}
			}
			else {
				ArrayList<Tutorial> tutorial = lec.getTutorial();
				ArrayList<Student> StudentList = tutorial.get(0).getStudentList();
				for (Student stud : StudentList) {
					stud.addGradeComponentforCourse(c, compname);
				}
			}
		}
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
			if ((tutorial.size()==1)||(tut.getIndex()!= -1)) {
			System.out.println(tut.detailStudentList());
			}
		}
	}
	public void printCourseStudentList(Course c) {
		ArrayList<Lecture> CourseLecture = c.getCourseLecture();
		for (Lecture lec : CourseLecture) {
			printLectureStudentList(lec);
		}
	}
	public void printTutorialDetails(Tutorial tut) {
		if (tut.getIndex()!=-1)
			System.out.println(tut.detailTutorial());
	}
	//print only lecture details , not details of tutorials in lecture
	public void printLectureDetails(Lecture lec) {
		System.out.println(lec.detailLecture());
	}
	public void printTutorialInLecture(Lecture lec) {
		ArrayList<Tutorial> tutorial = lec.getTutorial();
		for (Tutorial tut : tutorial) {
			if (tut.getIndex()!=-1) {
				printTutorialDetails(tut);
				System.out.println();
				}
		}
	}
	public Tutorial checkTutorialinLecture(Lecture lec , int index) {
		if (index == -1) return null;
		ArrayList<Tutorial> tutorial = lec.getTutorial();
		for (Tutorial tut : tutorial) {
			if (tut.getIndex()==index) return tut;
		}
		return null;
	}
	public void printTutorialVacancy(Tutorial tut) {
		if (tut.getIndex()!=-1)
			System.out.println("Tutorial "+Integer.toString(tut.getIndex())+" : Vacancy = " +Integer.toString(tut.getVacancy()));
	}
	public void printLectureVacancy(Lecture lec) {
		ArrayList<Tutorial> tutorial = lec.getTutorial();
		if (tutorial.size()==1) {
			System.out.println("Lecture Vacancy = " + Integer.toString(tutorial.get(0).getVacancy()));
		}
		else {for (Tutorial tut : tutorial) {
				if (tut.getIndex() != -1)
					printTutorialVacancy(tut);
			}
		}
	}
	public void printCourseVacancy(Course c) {
		ArrayList<Lecture> CourseLecture = c.getCourseLecture();
		for (Lecture lec : CourseLecture) {
			printLectureVacancy(lec);
		}
	}
	public void printAssessmentWeightage(Course c) {
		float perFactor = c.percentageFactor();
		Assessment assess = c.getCourseAssessment();
		for (Component comp : assess.getDistribution()) {
			System.out.println(comp.getName() + " weightage " + Float.toString(comp.getValue()*perFactor));
		}
	}
	public Component checkComponent(Course c , String CompName) {
		Assessment assess = c.getCourseAssessment();
		for (Component comp : assess.getDistribution()) {
			if (comp.getName().equals(CompName)) {
				return comp; 
			}
		}
		return null;
	}
	public boolean checkNoTutCourse(Course c) {
		ArrayList<Lecture> CourseLecture = c.getCourseLecture();
		for (Lecture lec : CourseLecture) {
			ArrayList<Tutorial> tutorial = lec.getTutorial();
			if (tutorial.size()>1) {
				return false;
			}
		}
		return true;
	}
	public void printCourseLectureDetails(Course c) {
		int count = 1;
		ArrayList<Lecture> CourseLecture = c.getCourseLecture();
		for (Lecture lec : CourseLecture) {
			System.out.println("Lecture " + count + ':');
			printLectureDetails(lec);
			count++;
		}
	}
	public int NoOfLec(Course c) {
		ArrayList<Lecture> CourseLecture = c.getCourseLecture();
		return CourseLecture.size();
	}
	public void setInitialVacancy(Lecture lec , int vacancy) {
		ArrayList<Tutorial> tutorial = lec.getTutorial();
		if (tutorial.size()==1) {
			tutorial.get(0).setVacancy(vacancy);
		}
	}
	public void calculateCourseTotalGrade(Course c) {
		ArrayList<Lecture> CourseLecture = c.getCourseLecture();
		for (Lecture lec : CourseLecture) {
			if (!checkNoTutCourse(c)) {
				ArrayList<Tutorial> tutorial = lec.getTutorial();
				for (Tutorial tut : tutorial) {
					if(tut.getIndex()!=-1) {
						ArrayList<Student> StudentList = tut.getStudentList();
						for (Student stud : StudentList) {
							stud.calculateCourseTotal(c);
						}
					}
				}
			}
			else {
				ArrayList<Tutorial> tutorial = lec.getTutorial();
				ArrayList<Student> StudentList = tutorial.get(0).getStudentList();
				for (Student stud : StudentList) {
					stud.calculateCourseTotal(c);
				}
			}
		}
	}
	public void printPercentageComponent(Course c , String compname ) {
		if (checkComponent(c,compname) == null) {return;}
		float no1 = 0 , no2 = 0 , no3 = 0 , no4 = 0 ;
		ArrayList<Lecture> CourseLecture = c.getCourseLecture();
		for (Lecture lec : CourseLecture) {
			if (!checkNoTutCourse(c)) {
				ArrayList<Tutorial> tutorial = lec.getTutorial();
				for (Tutorial tut : tutorial) {
					if(tut.getIndex()!=-1) {
						ArrayList<Student> StudentList = tut.getStudentList();
						for (Student stud : StudentList) {
							float temp = stud.getComponentGrade(c, compname);
							if ((temp >=0)&&(temp<25)) no1++;
							else if ((temp >=25)&&(temp<50)) no2++;
							else if ((temp >=50)&&(temp<75))no3++;
							else no4++;
						}
					}
				}
			}
			else {
				ArrayList<Tutorial> tutorial = lec.getTutorial();
				ArrayList<Student> StudentList = tutorial.get(0).getStudentList();
				for (Student stud : StudentList) {
					float temp = stud.getComponentGrade(c, compname);
					if ((temp >=0)&&(temp<25)) no1++;
					else if ((temp >=25)&&(temp<50)) no2++;
					else if ((temp >=50)&&(temp<75))no3++;
					else no4++;
				}
			}
		}
		float total = no1 + no2 + no3 + no4 ;
		float no1per = no1/total * 100;
		float no2per = no2/total * 100;
		float no3per = no3/total * 100;
		float no4per = no4/total * 100;
		System.out.println("Component " + compname + " percentage : " + Float.toString(no1per) + "% in 0-25 , "
				+ Float.toString(no2per) + "% in 25-50 , "
				+ Float.toString(no3per) + "% in 50-75 , "
				+ Float.toString(no4per) + "% in 75-100  ");
	}
	public void printPercentageTotal(Course c ) {
		float no1 = 0 , no2 = 0 , no3 = 0 , no4 = 0 ;
		ArrayList<Lecture> CourseLecture = c.getCourseLecture();
		for (Lecture lec : CourseLecture) {
			if (!checkNoTutCourse(c)) {
				ArrayList<Tutorial> tutorial = lec.getTutorial();
				for (Tutorial tut : tutorial) {
					if(tut.getIndex()!=-1) {
						ArrayList<Student> StudentList = tut.getStudentList();
						for (Student stud : StudentList) {
							float temp = stud.getFinalGrade(c);
							if ((temp >=0)&&(temp<25)) no1++;
							else if ((temp >=25)&&(temp<50)) no2++;
							else if ((temp >=50)&&(temp<75))no3++;
							else no4++;
						}
					}
				}
			}
			else {
				ArrayList<Tutorial> tutorial = lec.getTutorial();
				ArrayList<Student> StudentList = tutorial.get(0).getStudentList();
				for (Student stud : StudentList) {
					float temp = stud.getFinalGrade(c);
					if ((temp >=0)&&(temp<25)) no1++;
					else if ((temp >=25)&&(temp<50)) no2++;
					else if ((temp >=50)&&(temp<75))no3++;
					else no4++;
				}
			}
		}
		float total = no1 + no2 + no3 + no4 ;
		float no1per = no1/total * 100;
		float no2per = no2/total * 100;
		float no3per = no3/total * 100;
		float no4per = no4/total * 100;
		System.out.println("Final grade percentage : " + Float.toString(no1per) + "% in 0-25 , "
				+ Float.toString(no2per) + "% in 25-50 , "
				+ Float.toString(no3per) + "% in 50-75 , "
				+ Float.toString(no4per) + "% in 75-100  ");
	}
	public void printCourseStatistic(Course c) {
		Assessment assess = c.getCourseAssessment();
		ArrayList<Component> CourseDistribution = assess.getDistribution();
		for (Component comp : CourseDistribution) {
			printPercentageComponent(c,comp.getName());
		}
		printPercentageTotal(c);
	}

}
