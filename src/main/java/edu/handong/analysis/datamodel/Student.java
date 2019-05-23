package edu.handong.analysis.datamodel;

import java.util.ArrayList;
import java.util.HashMap;

public class Student {
	private String studentId = null;
	private ArrayList<Course> coursesTaken = new ArrayList<Course>();
	private HashMap<String,Integer> semestersByYearAndSemester = new HashMap<String,Integer>(); 
		
	public Student(String studentId) {
		this.studentId = studentId;		
	}
	
	public void addCourse(Course newRecord) {
		this.coursesTaken.add(newRecord);
	}
	
	public HashMap<String,Integer> getSemestersByYearAndSemester() {
		
		HashMap<String, Integer> semesterOfStudent = new HashMap<String, Integer>();
		
		int value = 1;
		
		for(Course course : coursesTaken) {
			if(!semesterOfStudent.containsKey(course.TakenSemester())){
				semesterOfStudent.put(course.TakenSemester(), value++);
			}
		}
		
		return semesterOfStudent;
	}
	
	public int getNumCourseInNthSementer(int semester) {
		
		HashMap <String, Integer> yearAndSemester = this.getSemestersByYearAndSemester();
		
		
		return coursesTaken.indexOf(semester);
	}



}
