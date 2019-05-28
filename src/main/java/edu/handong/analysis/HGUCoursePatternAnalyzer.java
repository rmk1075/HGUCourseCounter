package edu.handong.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import edu.handong.analysis.datamodel.Course;
import edu.handong.analysis.datamodel.Student;
import edu.handong.analysis.utils.NotEnoughArgumentException;
import edu.handong.analysis.utils.Utils;

public class HGUCoursePatternAnalyzer {

	private HashMap<String,Student> students;
	
	/**
	 * This method runs our analysis logic to save the number courses taken by each student per semester in a result file.
	 * Run method must not be changed!!
	 * @param args
	 */
	public void run(String[] args) {
		
		try {
			// when there are not enough arguments from CLI, it throws the NotEnoughArgmentException which must be defined by you.
			if(args.length<2)
				throw new NotEnoughArgumentException();
		} catch (NotEnoughArgumentException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		String dataPath = args[0]; // csv file to be analyzed
		String resultPath = args[1]; // the file path where the results are saved.
		ArrayList<String> lines = Utils.getLines(dataPath, true);
		
		students = loadStudentCourseRecords(lines, args[2], args[3]);
				
		// To sort HashMap entries by key values so that we can save the results by student ids in ascending order.
		Map<String, Student> sortedStudents = new TreeMap<String,Student>(students); 
				
		// Generate result lines to be saved.
		ArrayList<String> linesToBeSaved = countNumberOfCoursesTakenInEachSemester(sortedStudents);
		
		// Write a file (named like the value of resultPath) with linesTobeSaved.
		Utils.writeAFile(linesToBeSaved, resultPath);
	}
	
	/**
	 * This method create HashMap<String,Student> from the data csv file. Key is a student id and the corresponding object is an instance of Student.
	 * The Student instance have all the Course instances taken by the student.
	 * @param lines
	 * @return
	 */
	private HashMap<String,Student> loadStudentCourseRecords(ArrayList<String> lines, String startyear, String endyear) {
				
		// TODO: Implement this method
		HashMap<String, Student> Students = new HashMap<String, Student>();
		
		for(String line : lines) {
			String ID = line.split(",")[0].trim();
			
			Course newCourse = new Course(line);
			if(newCourse.yearTaken() < Integer.parseInt(startyear)) continue;
			if(newCourse.yearTaken() > Integer.parseInt(endyear)) continue;
						
			if(Students.containsKey(ID)) {
				Students.get(ID).addCourse(newCourse);
			} else {				
				Student newStudent = new Student(ID);
				newStudent.addCourse(newCourse);
				
				Students.put(ID, newStudent);
			}
		}
		
		return Students; // do not forget to return a proper variable.
	}

	/**
	 * This method generate the number of courses taken by a student in each semester. The result file look like this:
	 * StudentID, TotalNumberOfSemestersRegistered, Semester, NumCoursesTakenInTheSemester
	 * 0001,14,1,9
     * 0001,14,2,8
	 * ....
	 * 
	 * 0001,14,1,9 => this means, 0001 student registered 14 semeters in total. In the first semeter (1), the student took 9 courses.
	 * 
	 * 
	 * @param sortedStudents
	 * @return
	 */
	private ArrayList<String> countNumberOfCoursesTakenInEachSemester(Map<String, Student> sortedStudents) {
		
		// TODO: Implement this method
		ArrayList<String> result = new ArrayList<String>();
		result.add("StudentID,TotalNumberOfSemester,Semester,NumCoursesTakenInTheSemester");
		
		//each student
		for(String key : sortedStudents.keySet()) {
			Student stu = sortedStudents.get(key);
			Map<String, Integer> sortedSemesters = new TreeMap<String,Integer>(stu.getSemestersByYearAndSemester());
			int TotalNumberOfSemester = sortedSemesters.keySet().size();
			
			//each course of the student
			for(String semester : sortedSemesters.keySet()) {
				String line = key + "," + TotalNumberOfSemester + "," + sortedSemesters.get(semester) + "," + stu.getNumCourseInNthSementer(sortedSemesters.get(semester));
				result.add(line);
			}
		}
		
		return result; // do not forget to return a proper variable.
	}
}
