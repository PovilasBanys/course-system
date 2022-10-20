package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Folder implements Serializable {

	private int id;
	private String name;
	private Date createdDate;
	private String courseId;
	private ArrayList<File> folderFiles = new ArrayList<>();

	public Folder(String name, Date createdDate, String courseId) {
		super();
		this.name = name;
		this.createdDate = createdDate;
		this.courseId = courseId;

	}

	
	public void printFolderInfo() {
		System.out.print("\n\t#################################################\n" + "\t#	Folder: " + name + "\n\t#	Created: "
				+ createdDate + "\n\t#	Files: ");
		for(File f : folderFiles) {
			f.printFileInfo();
		}
		
		System.out.print(
				"\n\t#\t#########################################\n"
				+ "\t#\n\t#################################################\n");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedDate() {
		return createdDate;
	}
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public ArrayList<File> getFolderFiles() {
		return folderFiles;
	}

	public void setFolderFiles(ArrayList<File> folderFiles) {
		this.folderFiles = folderFiles;
	}

}
