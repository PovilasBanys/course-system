package utils;

import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class EditMyCourses {

	private static ArrayList<String> choices = new ArrayList<>();
	private static ArrayList<String> choices2 = new ArrayList<>();
	private static ArrayList<String> choices3 = new ArrayList<>();
	
	
	
	public static void editMyCourse(TreeItem<String> item, TreeView<String> myCoursesTreeView, Connection con) {
			
		choices.clear();
		choices2.clear();
		choices3.clear();
		
		choices.add("Change course name");
		choices.add("Add folder");
		
		choices2.add("Change folder name");
		choices2.add("Delete folder");
		choices2.add("Add file");
		
		choices3.add("Change file name");
		choices3.add("Change file recourse");
		choices3.add("Delete file");
		
		
		try {
					
				if(item.equals(myCoursesTreeView.getRoot())) {
				// Root
						
				ChoiceDialog<String> dialogChoose = new ChoiceDialog<>("Change course name",choices);
				dialogChoose.setTitle("Choose action");
				dialogChoose.setHeaderText(null);
				dialogChoose.setContentText("Choose action: ");

				Optional<String> resultChoose = dialogChoose.showAndWait();
				if (resultChoose.isPresent()){
						    
					if(resultChoose.get().equals("Change course name")) {
						// Change name
								
						TextInputDialog dialog = new TextInputDialog();
						dialog.setTitle("Change course name!");
						dialog.setHeaderText("Current name " + item.getValue());
						dialog.setContentText("Enter new course name: ");

						Optional<String> result = dialog.showAndWait();
						if(result.get().equals("")) {
							return;
						}
						if (result.isPresent()){
	
							try {
								String query = "UPDATE course SET NAME = ? WHERE NAME = ?";
								PreparedStatement st = con.prepareStatement(query);
								st.setString(1, result.get());
								st.setString(2, item.getValue());
								int res = st.executeUpdate();
								if(res > 0) {
									Alert alert = new Alert(AlertType.INFORMATION);
									alert.setTitle("Success!");
									alert.setHeaderText(null);
									alert.setContentText("Course name changed to " + result.get());
									alert.showAndWait();
								}
										
							}catch(Exception e) {
								Alert alert = new Alert(AlertType.ERROR);
								alert.setTitle("Error!");
								alert.setHeaderText(null);
								alert.setContentText("Course name " + result.get() +" already exists!");
								alert.showAndWait();
							}
		   
						}}else if(resultChoose.get().equals("Add folder")) {
							// Create folder
							TextInputDialog dialog = new TextInputDialog();
							dialog.setTitle("Create folder");
							dialog.setHeaderText(null);
							dialog.setContentText("Enter folder name: ");

							Optional<String> result = dialog.showAndWait();
							if(result.get().equals("")) {
								return;
							}
							
							String query = "INSERT INTO fold VALUES(0,?,?)";
							PreparedStatement st = con.prepareStatement(query);
							st.setString(1, result.get());
							st.setString(2, LocalDate.now().toString());
							int res = st.executeUpdate();
							
							query = "SELECT ID FROM fold ORDER BY ID DESC LIMIT 1";
							st = con.prepareStatement(query);
							ResultSet rs = st.executeQuery();
							int folderID = 0;
							if(rs.next()) {
								folderID = rs.getInt(1);
							}
							
							query = "SELECT ID FROM course WHERE NAME = ?";
							st = con.prepareStatement(query);
							st.setString(1, item.getValue());
							rs = st.executeQuery();
							int courseID = 0;
							if(rs.next()) {
								courseID = rs.getInt(1);
							}
							
							query = "INSERT INTO folder VALUES(?,0,0,?)";
							st = con.prepareStatement(query);

							st.setInt(1, folderID);
						    st.setInt(2, courseID);
							res = st.executeUpdate();
							
							if(res > 0) {
								Alert alert = new Alert(AlertType.INFORMATION);
								alert.setTitle("Success!");
								alert.setHeaderText(null);
								alert.setContentText("Folder " + result.get() +" created!");
								alert.showAndWait();
							}

						}
							
				}}else if(item.getParent().equals(myCoursesTreeView.getRoot())){
					// Folder
						
					ChoiceDialog<String> dialogChoose = new ChoiceDialog<>("Change folder name",choices2);
					dialogChoose.setTitle("Choose action");
					dialogChoose.setHeaderText(null);
					dialogChoose.setContentText("Choose action: ");

					Optional<String> resultChoose = dialogChoose.showAndWait();
					if (resultChoose.isPresent()){
						
						if(resultChoose.get().equals("Change folder name")) {
							// Change folder name
							TextInputDialog dialog = new TextInputDialog();
							dialog.setTitle("Change folder name!");
							dialog.setHeaderText("Current name " + item.getValue());
							dialog.setContentText("Enter new folder name: ");

							Optional<String> result = dialog.showAndWait();
							if(result.get().equals("")) {
								return;
							}
							if (result.isPresent()){

								try {
									String query = "UPDATE fold SET NAME = ? WHERE NAME = ?";
									PreparedStatement st = con.prepareStatement(query);
									st.setString(1, result.get());
									st.setString(2, item.getValue());
									int res = st.executeUpdate();
									if(res > 0) {
										Alert alert = new Alert(AlertType.INFORMATION);
										alert.setTitle("Success!");
										alert.setHeaderText(null);
										alert.setContentText("Folder name changed to " + result.get());
										alert.showAndWait();
									}
										
								}catch(Exception e) {
									Alert alert = new Alert(AlertType.ERROR);
									alert.setTitle("Error!");
									alert.setHeaderText(null);
									alert.setContentText("Failed to update name!");
									alert.showAndWait();
								}
		   
							}}else if(resultChoose.get().equals("Add file")){
								// Add file
								
								
								
								TextInputDialog dialog = new TextInputDialog();
								dialog.setTitle("Create file");
								dialog.setHeaderText(null);
								dialog.setContentText("Enter file name: ");

								Optional<String> result = dialog.showAndWait();
								if(result.get().equals("")) {
									return;
								}
								
								TextInputDialog dialog2 = new TextInputDialog();
								dialog2.setTitle("Create file");
								dialog2.setHeaderText(null);
								dialog2.setContentText("Enter file recourse: ");

								Optional<String> result2 = dialog2.showAndWait();
								if(result2.get().equals("")) {
									return;
								}

								
								String query = "INSERT INTO fil VALUES(0,?,?)";
								PreparedStatement st = con.prepareStatement(query);
								st.setString(1, result.get());
								//st.setString(2, LocalDate.now().toString());
								st.setString(2, result2.get());
								int res = st.executeUpdate();
								
								query = "SELECT ID FROM fil ORDER BY ID DESC LIMIT 1";
								st = con.prepareStatement(query);
								ResultSet rs = st.executeQuery();
								int fileID = 0;
								if(rs.next()) {
									fileID = rs.getInt(1);
								}
								
								query = "SELECT ID FROM fold WHERE NAME = ?";
								st = con.prepareStatement(query);
								st.setString(1, item.getValue());
								rs = st.executeQuery();
								int folderID = 0;
								if(rs.next()) {
									folderID = rs.getInt(1);
								}
								
								query = "INSERT INTO folder_files VALUES(0,0,0?,?)";
								st = con.prepareStatement(query);
								st.setInt(1, fileID);
							    st.setInt(2, folderID);
							 	res = st.executeUpdate();
								
								if(res > 0) {
									Alert alert = new Alert(AlertType.INFORMATION);
									alert.setTitle("Success!");
									alert.setHeaderText(null);
									alert.setContentText("File " + result.get() +" created!");
									alert.showAndWait();
								}else {
									Alert alert = new Alert(AlertType.ERROR);
									alert.setTitle("Error!");
									alert.setHeaderText(null);
									alert.setContentText("Failed to create file!");
									alert.showAndWait();
								}

								
							}else if(resultChoose.get().equals("Delete folder")) {
								
								String query = "SELECT ID FROM fold WHERE NAME = ?";
								PreparedStatement st = con.prepareStatement(query);
								st.setString(1, item.getValue());
								ResultSet rs = st.executeQuery();
								int folderID = 0;
								if(rs.next()) {
									folderID = rs.getInt(1);
								}
								
								query = "SELECT FILE_ID FROM folder_files WHERE FOLDER_ID = ?";
								st = con.prepareStatement(query);
								st.setInt(1, folderID);
								rs = st.executeQuery();
								while(rs.next()) {
									query = "DELETE FROM fil WHERE ID = ?";
									PreparedStatement st2 = con.prepareStatement(query);
									st2.setInt(1, rs.getInt(1));
									int res = st2.executeUpdate();
								}
								
								query = "DELETE FROM folder_files WHERE FOLDER_ID = ?";
								st = con.prepareStatement(query);
								st.setInt(1, folderID);
								int res = st.executeUpdate();
								
								query = "DELETE FROM folder WHERE FOLDER_ID = ?";
								st = con.prepareStatement(query);
								st.setInt(1, folderID);
								res = st.executeUpdate();
								
								if(res > 0) {
									Alert alert = new Alert(AlertType.INFORMATION);
									alert.setTitle("Success!");
									alert.setHeaderText(null);
									alert.setContentText("Folder " + item.getValue() + " deleted!");
									alert.showAndWait();
								}
								
								
							}

							
					}}else {
						// File
						
						ChoiceDialog<String> dialogChoose = new ChoiceDialog<>("Change file name",choices3);
						dialogChoose.setTitle("Choose action");
						dialogChoose.setHeaderText(null);
						dialogChoose.setContentText("Choose action: ");

						Optional<String> resultChoose = dialogChoose.showAndWait();
						if (resultChoose.isPresent()){
							
							if(resultChoose.get().equals("Change file name")) {
								// Change file name
								TextInputDialog dialog = new TextInputDialog();
								dialog.setTitle("Change file name!");
								dialog.setHeaderText("Current name " + item.getValue());
								dialog.setContentText("Enter new file name: ");

								Optional<String> result = dialog.showAndWait();
								if (result.isPresent()){
									if(result.get().equals("")) {
										return;
									}
									String query = "UPDATE fil SET NAME = ? WHERE NAME = ?";
									PreparedStatement st = con.prepareStatement(query);
									st.setString(1, result.get());
									st.setString(2, item.getValue());
									int res = st.executeUpdate();
									if(res > 0) {
										Alert alert = new Alert(AlertType.INFORMATION);
										alert.setTitle("Success!");
										alert.setHeaderText(null);
										alert.setContentText("File name changed to " + result.get());
										alert.showAndWait();
									}
								}}else if(resultChoose.get().equals("Change file recourse")) {
									// Change file recourse
									TextInputDialog dialog = new TextInputDialog();
									dialog.setTitle("Change file source!");
									dialog.setHeaderText(null);
									dialog.setContentText("Enter new file source: ");

									Optional<String> result = dialog.showAndWait();
									
									if (result.isPresent()){	
										if(result.get().equals("")) {
											return;
										}
										try {
											String query = "UPDATE fil SET LINK = ? WHERE NAME = ?";
											PreparedStatement st = con.prepareStatement(query);
											st.setString(1, result.get());
											st.setString(2, item.getValue());
											int res = st.executeUpdate();
											if(res > 0) {
												Alert alert = new Alert(AlertType.INFORMATION);
												alert.setTitle("Success!");
												alert.setHeaderText(null);
												alert.setContentText("File source changed to " + result.get());
												alert.showAndWait();
											}
											
										}catch(Exception e) {
											Alert alert = new Alert(AlertType.ERROR);
											alert.setTitle("Error!");
											alert.setHeaderText(null);
											alert.setContentText("Failed to update source!");
											alert.showAndWait();
										}
			   
									}
									
								}else if(resultChoose.get().equals("Delete file")) {
									
									
									String query = "SELECT ID FROM fil WHERE NAME = ?";
									PreparedStatement st = con.prepareStatement(query);
									st.setString(1, item.getValue());
									ResultSet rs = st.executeQuery();
									int fileID = 0;
									if(rs.next()) {
										fileID = rs.getInt(1);
									}
									
									
									query = "DELETE FROM fil WHERE ID = ?";
									st = con.prepareStatement(query);
									st.setInt(1, fileID);
									int res = st.executeUpdate();
									
									
									query = "DELETE FROM folder_files WHERE FILE_ID = ?";
									st = con.prepareStatement(query);
									st.setInt(1, fileID);
									res = st.executeUpdate();
									
									if(res > 0) {
										Alert alert = new Alert(AlertType.INFORMATION);
										alert.setTitle("Success!");
										alert.setHeaderText(null);
										alert.setContentText("File " + item.getValue() + " deleted!");
										alert.showAndWait();
									}
									
									
								}
							
						}

					}
		
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		
	}

}