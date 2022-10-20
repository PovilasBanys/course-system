package model;

import java.io.Serializable;

public class User implements Serializable {
	
	private int id;
	private String login;
	private String pass;
	private String name;
	private String last_name;
	private String email;

	public User(String login, String pass, String name, String last_name, String email) {
		super();
		this.login = login;
		this.pass = pass;
		this.name = name;
		this.last_name = last_name;
		this.email = email;
	}

	public User() {
		
	}

	@Override
	public String toString() {
		return "\n\t====== User ======\n\tLogin: " + login + "\n\tPass: " + pass + "\n\tName: " + name
				+ "\n\tLast name: " + last_name + "\n\tEmail: " + email;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
