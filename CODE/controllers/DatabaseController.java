package controllers;
import java.util.*;
import java.io.*;

public class DatabaseController {
	
	public void addRecord(ArrayList<Object> List, String FileNames) {
		
		try{
	         FileOutputStream fos= new FileOutputStream(FileNames);
	         ObjectOutputStream oos= new ObjectOutputStream(fos);
	         oos.writeObject(List);
	         oos.close();
	         fos.close();
	       }catch(IOException ioe){
	            ioe.printStackTrace();
	        }
	}
	
	public ArrayList<Object> readRecord(String FileNames){
		
		ArrayList<Object> arraylist = new ArrayList<Object>();
		
		try
        {
            FileInputStream fis = new FileInputStream(FileNames);
            ObjectInputStream ois = new ObjectInputStream(fis);
            arraylist = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
            return arraylist;
         }catch(IOException ioe){
             ioe.printStackTrace();
             return null;
          }catch(ClassNotFoundException c){
             System.out.println("Class not found");
             c.printStackTrace();
             return null;
          }
	}
}