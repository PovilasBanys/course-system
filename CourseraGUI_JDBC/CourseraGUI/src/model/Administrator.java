package model;

import java.io.Serializable;
import java.util.ArrayList;

public final class Administrator extends User implements Serializable {

	
	private ArrayList<Course> createdCourses = new ArrayList<>();
	private String phoneNumber;

	public Administrator(String login, String pass, String name, String last_name, String email) {
		super(login, pass, name, last_name, email);
	}

	public Administrator() {
		
	}

	@Override
	public String toString() {
		return super.toString() + "\n\tCreated courses: " + createdCourses.size() + "\n\tPhone number: " + phoneNumber;
	}

	public void showCreatedCourses() {
		System.out.println("\nYou created these " + createdCourses.size() + " courses: ");

		for (int i = 0; i < createdCourses.size(); i++) {
			System.out.println((i + 1) + ") " + createdCourses.get(i).getName());
		}

	}

	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	

	public ArrayList<Course> getCreatedCourses() {
		return createdCourses;
	}

	public void setCreatedCourses(ArrayList<Course> createdCourses) {
		this.createdCourses = createdCourses;
	}
	
	

}
