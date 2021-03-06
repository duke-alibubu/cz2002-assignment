package controllers;
import java.util.*;
import java.io.*;
import entities.*;

public class DatabaseController {
	
	public void addStudentRecord(ArrayList<Student> StudentList, String FileNames) {
		
		//save ArrayList into a file(Serialize)
		try{
	         FileOutputStream fos= new FileOutputStream(FileNames);
	         ObjectOutputStream oos= new ObjectOutputStream(fos);
	         oos.writeObject(StudentList);
	         oos.close();
	         fos.close();
	       }catch(IOException ioe){
	            ioe.printStackTrace();
	        }
	}
	
	public ArrayList<Student> readStudentRecord(String FileNames){
		
		ArrayList<Student> arraylist = new ArrayList<Student>();
		
		//load saved serialized file into ArrayList
		try
        {
            FileInputStream fis = new FileInputStream(FileNames);
            ObjectInputStream ois = new ObjectInputStream(fis);
            arraylist = (ArrayList<Student>) ois.readObject();
            ois.close();
            fis.close();
            return arraylist;
         }catch(IOException ioe){
        	 return arraylist;
          }catch(ClassNotFoundException c){
             System.out.println("Class not found");
             return arraylist;
          }
	}
	

	public void addCourseRecord(ArrayList<Course> CourseList, String FileNames) {
		
		try{
	         FileOutputStream fos= new FileOutputStream(FileNames);
	         ObjectOutputStream oos= new ObjectOutputStream(fos);
	         oos.writeObject(CourseList);
	         oos.close();
	         fos.close();
	       }catch(IOException ioe){
	            ioe.printStackTrace();
	        }
	}
	
	public ArrayList<Course> readCourseRecord(String FileNames){
		
		ArrayList<Course> arraylist = new ArrayList<Course>();
		
		try
        {
            FileInputStream fis = new FileInputStream(FileNames);
            ObjectInputStream ois = new ObjectInputStream(fis);
            arraylist = (ArrayList<Course>) ois.readObject();
            ois.close();
            fis.close();
            return arraylist;
         }catch(IOException ioe){
        	 return arraylist;
          }catch(ClassNotFoundException c){
             System.out.println("Class not found");
             return arraylist;
          }
	}
	
	public void addEnrollmentRecord(ArrayList<Enrollment> EnrollmentList, String FileNames) {
		
		try{
	         FileOutputStream fos= new FileOutputStream(FileNames);
	         ObjectOutputStream oos= new ObjectOutputStream(fos);
	         oos.writeObject(EnrollmentList);
	         oos.close();
	         fos.close();
	       }catch(IOException ioe){
	            ioe.printStackTrace();
	        }
	}
	
	public ArrayList<Enrollment> readEnrollmentRecord(String FileNames){
		
		ArrayList<Enrollment> arraylist = new ArrayList<Enrollment>();
		
		try
        {
            FileInputStream fis = new FileInputStream(FileNames);
            ObjectInputStream ois = new ObjectInputStream(fis);
            arraylist = (ArrayList<Enrollment>) ois.readObject();
            ois.close();
            fis.close();
            return arraylist;
         }catch(IOException ioe){
        	 return arraylist;
          }catch(ClassNotFoundException c){
             System.out.println("Class not found");
             return arraylist;
          }
	}
}
