package edu.handong.analysis.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {
	
	public static ArrayList<String> getLines(String file, boolean removeHeader) {
		ArrayList<String> csv = new ArrayList<String>();
		
		try
		{
			// Skip the header line by reading and ignoring it
			Scanner inputStream = new Scanner(new File(file)); 
			String line = inputStream.nextLine();
			// Total sales
			double total = 0;
			// Read the rest of the file line by line
			while (inputStream.hasNextLine())
			{
				// Contains SKU,Quantity,Price,Description
				line = inputStream.nextLine();

				csv.add(line);
			}
			inputStream.close( );
		}
		catch(FileNotFoundException e) {
			System.out.println("Cannot find file " + file);
		}
		
		if(removeHeader == true) {
			for(int i = 0; i < 9; i++) {
				csv.remove(0);
			}
		}
		
		return csv;
	}

	public static void writeAFile(ArrayList<String> lines, String targetFileName) {
		
	}
}
