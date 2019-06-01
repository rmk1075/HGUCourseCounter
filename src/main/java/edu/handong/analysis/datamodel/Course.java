package edu.handong.analysis.datamodel;

public class Course {
	private String studentId;
	private String yearMonthGraduated;
	private String firstMajor;
	private String secondMajor;
	private String courseCode;
	private String courseName;
	private String courseCredit;
	private int yearTaken;
	private int semesterCourseTaken;
		
	public Course(String line) {
		this.studentId = line.split(",")[0].trim();
		this.yearMonthGraduated = line.split(",")[1].trim();
		this.firstMajor = line.split(",")[2].trim();
		this.secondMajor = line.split(",")[3].trim();
		this.courseCode = line.split(",")[4].trim();
		this.courseName = line.split(",")[5].trim();
		this.courseCredit = line.split(",")[6].trim();
		this.yearTaken = Integer.parseInt(line.split(",")[7].trim());
		this.semesterCourseTaken = Integer.parseInt(line.split(",")[8].trim());	
	}

	/**getter
	 * @return takenSemester => ex) 2012-1 
	 */
	public String TakenSemester() {
		String takenSemester = yearTaken + "-" + semesterCourseTaken;
		
		return takenSemester;
	}

	/**getter
	 * @return yearTaken
	 */
	public int yearTaken() {
		return this.yearTaken;
	}
	
	/**getter
	 * @return courseCode
	 */
	public String courseCode() {
		return this.courseCode;
	}
	
	/**getter
	 * @return courseName
	 */
	public String courseName() {
		return this.courseName;
	}
}
