package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Course implements Serializable {

	private int id;
	private int adminId;
	private String name;
	private LocalDate startDate;
	private LocalDate endDate;
	private double coursePrice;
	private ArrayList<Folder> folders = new ArrayList<>();
	private ArrayList<Student> enrolledStudents = new ArrayList<>();
	private Administrator courseAdmin;

	public Course(String name, LocalDate startDate, LocalDate endDate, int adminId, double coursePrice) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.adminId = adminId;
		this.coursePrice = coursePrice;
	}

	public Course(int id, String name, LocalDate startDate, LocalDate endDate, int adminId, double coursePrice) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.adminId = adminId;
		this.coursePrice = coursePrice;
	}

	public Course(String name) {
		super();

		this.name = name;
	}


	public void printCourseInfo() {
		System.out.printf("\nCourse name: %s\nIt has %d folders\nStarts: " + startDate + "\nEnds: " + endDate + "\n",
				name, folders.size());
	}
	
	
	public void printAllCourseInfo() {
		System.out.print("\n\n\t====== Course ======"
				+"\n\tName: "+getName()
				+"\n\tCourse admin: "+getCourseAdmin().getName() + " " + getCourseAdmin().getLast_name()
				+"\n\tStart date: "+getStartDate()
				+"\n\tEnd date: "+getEndDate()
				+"\n\tCourse price: "+getCoursePrice()
				+"\n\tFolders: ");
		for(Folder f : getFolders()) {
			f.printFolderInfo();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public double getCoursePrice() {
		return coursePrice;
	}

	public void setCoursePrice(double coursePrice) {
		this.coursePrice = coursePrice;
	}

	public ArrayList<Folder> getFolders() {
		return folders;
	}

	public void setFolders(ArrayList<Folder> folders) {
		this.folders = folders;
	}

	public ArrayList<Student> getEnrolledStudents() {
		return enrolledStudents;
	}

	public void setEnrolledStudents(ArrayList<Student> enrolledStudents) {
		this.enrolledStudents = enrolledStudents;
	}

	public Administrator getCourseAdmin() {
		return courseAdmin;
	}

	public void setCourseAdmin(Administrator courseAdmin) {
		this.courseAdmin = courseAdmin;
	}

}
