package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class CourseIS implements Serializable {

	private ArrayList<Administrator> allAdmins = new ArrayList<>();
	private ArrayList<Student> allStudents = new ArrayList<>();
	private ArrayList<User> allUsers = new ArrayList<>();

	private ArrayList<Course> allCourses = new ArrayList<>();

	private String informationSystemName;
	private Date dataCreated;
	private String version;

	public CourseIS(String informationSystemName, Date dataCreated, String version) {
		super();
		this.informationSystemName = informationSystemName;
		this.dataCreated = dataCreated;
		this.version = version;
	}
	
	
	public void printAllCourses() {
		System.out.println("\nPrinting all courses..");
		
		for(Course c : allCourses) {
			c.printAllCourseInfo();
		}
		
		System.out.println("\n");
	}

	public ArrayList<Administrator> getAllAdmins() {
		return allAdmins;
	}

	public void setAllAdmins(ArrayList<Administrator> allAdmins) {
		this.allAdmins = allAdmins;
	}

	public ArrayList<Student> getAllStudents() {
		return allStudents;
	}

	public void setAllStudents(ArrayList<Student> allStudents) {
		this.allStudents = allStudents;
	}

	public ArrayList<Course> getAllCourses() {
		return allCourses;
	}

	public void setAllCourses(ArrayList<Course> allCourses) {
		this.allCourses = allCourses;
	}

	public String getInformationSystemName() {
		return informationSystemName;
	}

	public void setInformationSystemName(String informationSystemName) {
		this.informationSystemName = informationSystemName;
	}

	public ArrayList<User> getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(ArrayList<User> allUsers) {
		this.allUsers = allUsers;
	}

	public Date getDataCreated() {
		return dataCreated;
	}

	public void setDataCreated(Date dataCreated) {
		this.dataCreated = dataCreated;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
