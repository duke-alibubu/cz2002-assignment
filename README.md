# Git

https://github.com/clarencecastillo/NTUOSS-GitWorkshop

# Todo

- TimeSlot objects

- Change array to ArrayList

- Add databaseController class

- in Component, edit -> setValue(), getName, getValue;

- in Field removeComponent should return boolean

- editGrade in StudentCourse

- in Student, move Edit grade, remove Course, change Index into enrolment controller
# Notes
- when scanning String inputs , use nextLine() instead of next() (better)
- sc.nextLine() after scanning an integer 

# New Modification
- Course , Tutorial , Lecture and TimeSlot now have their own printing function
(e.g public void printCourse())
- CourseController class need to be edited a lot 
- Added a Copy constructor for TimeSlot class
- Lecture and Tutorial classed got new setLabTimeSlot , setTutTimeSlot , setLectureTime which accepts only a TimeSlot object as pass-in parameter
# DUKE , Modification Nov 6th 2018
- Student  : added detailStudent func which returns a string if student detail
- Tutorial : modified detailTutorial func , added detailStudenList func
- StudenController : added checkStudent(String StudentID) func
- IoInterface : Completed RegisterCourse , CheckVacancy() , PrintStudent() function 
# Duke , Modification Evening Nov 6th 2018
- fixed input scanning bugs
- StudentController : added printStudentDetail() function
- CourseController : added checkTutorialinLecture (Lecture lec , int index)
                     printTutorialStudentList(Tutorial tut)
                     printLectureStudentList(Lecture lec )
                     printCourseStudentList(Course c)
                     printTutorialDetails(Tutorial tut)
                     printLectureDetails(Lecture lec)
                     printTutorialInLecture(Lecture lec)
                     printTutorialVacancy(Tutorial tut)
                     printLectureVacancy(Lecture lec)
                     printCourseVacancy(Course c)
 - TimeSlot : changed StartTime & FinishTime data types from long to float 
              also changed the createTimeSlot() func in CourseController due to related issues
         
# DUKE , Modification Nov 7th 2018
- Component : added a copy constructor
- CourseController : added printAssessmentWeightage() and checkComponent(..) func
- StudentCourse : added calculateTotal() func which will calculate the final mark of a student in a course
- Field : added a getDistribution() func
- IOInterface : EditAssessment()
