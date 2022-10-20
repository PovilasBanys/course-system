package fxControl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.DbOperations;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class SignUpStudent implements Initializable {
    @FXML
    public TextField loginField;
    @FXML
    public TextField passwordField;
    @FXML
    public TextField nameField;
    @FXML
    public TextField surnameField;
    @FXML
    public DatePicker dateField;
    @FXML
    public TextField courseField;
    @FXML
    public TextField emailField;
    @FXML
    public TextField accountField;
    @FXML
    public PasswordField confirmField;




    public Connection connection;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void setConnection(Connection con) {
        this.connection = con;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            connection = DbOperations.connectToDb();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("Failed to coonect to database");
            alert.showAndWait();
        }

    }

    public void exitToLogin(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/login.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void signUpStudent(ActionEvent actionEvent) throws IOException {
        if(passwordField.getText().equals("")||loginField.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("Please fill information");
            alert.showAndWait();
            return;
        }

        if(passwordField.getText().equals(confirmField.getText())) {

            try {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO student VALUES(?,?,?,?,?,?,?,?)");
                statement.setInt(1, 0);
                statement.setString(2, loginField.getText());
                statement.setString(3, passwordField.getText());
                statement.setString(4, nameField.getText());
                statement.setString(5, surnameField.getText());
                statement.setString(6, String.valueOf(dateField.getValue()));
                statement.setString(7, courseField.getText());
                statement.setString(8, emailField.getText());
               // statement.setString(9, accountField.getText());
                int result1 = statement.executeUpdate();

                if(result1 > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success!");
                    alert.setHeaderText(null);
                    alert.setContentText("User successfully created!");
                    alert.showAndWait();
                    loginField.setText("");
                    passwordField.setText("");
                    confirmField.setText("");
                    nameField.setText("");
                    surnameField.setText("");
                    dateField.setAccessibleText("");
                    courseField.setText("");
                    emailField.setText("");
                    //accountField.setText("");

                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error!");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed to create user!");
                    alert.showAndWait();
                }


            }catch(Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText(null);
                alert.setContentText("Username is taken!");
                alert.showAndWait();
                e.printStackTrace();
                return;
            }

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("Password do not match!");
            alert.showAndWait();
        }
    }
}
