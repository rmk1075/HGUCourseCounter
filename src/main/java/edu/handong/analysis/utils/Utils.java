package edu.handong.analysis.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class Utils {
	
	public static ArrayList<String> getLines(String file, boolean removeHeader) {
		
		ArrayList<String> csv = new ArrayList<String>();
		
		try {
			Reader in = new FileReader(file);
			Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
			for (CSVRecord record : records) {
			    String line = null;
			    
			    line = record.get(0);
			    for(int i = 1; i < record.size(); i++) {
			    	line += "," + record.get(i);
			    }
			    
			    csv.add(line);
			}			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(removeHeader == true) {
			csv.remove(0);
		}
		
		/*
		try
		{
			// Skip the header line by reading and ignoring it
			Scanner inputStream = new Scanner(new File(file)); 
			String line;
			if(removeHeader == true) {
				line = inputStream.nextLine();
			}
			
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
			System.out.println("The file path does not exits. Please check your CLI argument!");
			System.exit(0);
		}
		*/
		
		return csv;
	}

	public static void writeAFile(ArrayList<String> lines, String targetFileName) {
		
		File result = new File(targetFileName);
		if(!result.exists() && result.getParentFile() != null) result.getParentFile().mkdirs();
		
		try 
		{
			BufferedWriter outputStream = new BufferedWriter(new FileWriter(targetFileName)); 
			
			for(String line : lines) {
				outputStream.write(line + "\n");
			}
			
			outputStream.flush();
			outputStream.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
