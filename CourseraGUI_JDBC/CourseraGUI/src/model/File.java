package model;

import java.io.Serializable;
import java.util.Date;

public class File implements Serializable {

	private int id;
	private String name;
	private Date dateAdded;
	private String linkToFile;

	public File(String name, String linkToFile) {
		super();
		this.name = name;
		this.linkToFile = linkToFile;
		//this.dateAdded = new Date();
	}

	
	
	public void printFileInfo() {
		System.out.print("\n\t#\t#########################################" + "\n\t#\t#	File: " + name + "\n\t#\t#	Created: "
				+ dateAdded + "\n\t#\t#	Resource Link: " + linkToFile);
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



	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getLinkToFile() {
		return linkToFile;
	}

	public void setLinkToFile(String linkToFile) {
		this.linkToFile = linkToFile;
	}

}
