package edu.handong.analysis.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {
	
	public static ArrayList<String> getLines(String file, boolean removeHeader) {
		ArrayList<String> csv = new ArrayList<String>();
		
		try
		{
			// Skip the header line by reading and ignoring it
			Scanner inputStream = new Scanner(new File(file)); 
			String line;
			
			// Read the rest of the file line by line
			while (inputStream.hasNextLine())
			{
				// Contains ID, YearMonth, Major1, Major2, CourseCode, CourseName, CourseCredit, YearTaken, SemesterTaken
				line = inputStream.nextLine();

				csv.add(line);
			}
			inputStream.close( );
		}
		catch(FileNotFoundException e) {
			System.out.println("Cannot find file " + file);
		}
		
		if(removeHeader == true) {
			csv.remove(0);
		}
		
		return csv;
	}

	public static void writeAFile(ArrayList<String> lines, String targetFileName) {
		ArrayList<String> csv = new ArrayList<String>();
		
		try 
		{
			ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(targetFileName))); 
			
			for(String line : lines) {
				outputStream.writeObject(line);
				outputStream.writeBytes("\n");
			}
			outputStream.close( );
		}
		catch(FileNotFoundException e) {
			System.out.println("Cannot find file " + targetFileName);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
