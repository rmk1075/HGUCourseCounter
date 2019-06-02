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
			System.out.println("The file path does not exits. Please check your CLI argument!");
			System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//remove header line
		if(removeHeader == true) {
			csv.remove(0);
		}
		
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
				outputStream.flush();
			}
			
			outputStream.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
