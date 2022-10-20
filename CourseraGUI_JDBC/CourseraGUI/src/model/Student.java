package model;

import java.io.Serializable;
import java.util.ArrayList;

public final class Student extends User implements Serializable {

	
	
	private CreditCard creditCard;
	private ArrayList<Course> enrolledCourses = new ArrayList<>();

	public Student(String login, String pass, String name, String last_name, String email) {
		super(login, pass, name, last_name, email);
	}

	public Student() {
		
	}

	@Override
	public String toString() {
		return "\n\tName: " + super.getName() + "\n\tLast name: " + super.getLast_name() + "\n\tEmail: "
				+ super.getEmail() + "\n\tEnrolled to courses: " + enrolledCourses.size();
	}

	public String studentInfo() {
		return super.toString() + "\n\tEnrolled to courses: " + enrolledCourses.size();
	}

	public void showEnrolledCourses() {
		System.out.println("\nYou enrolled to these " + enrolledCourses.size() + " courses: ");

		for (int i = 0; i < enrolledCourses.size(); i++) {
			System.out.println((i + 1) + ") " + enrolledCourses.get(i).getName());
		}

	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	

	public ArrayList<Course> getEnrolledCourses() {
		return enrolledCourses;
	}

	public void setEnrolledCourses(ArrayList<Course> enrolledCourses) {
		this.enrolledCourses = enrolledCourses;
	}

}
