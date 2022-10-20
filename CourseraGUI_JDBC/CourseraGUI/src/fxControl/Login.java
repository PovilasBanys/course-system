package fxControl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Student;
import model.User;
import utils.DbOperations;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Login implements Initializable {

    @FXML
    public Button logInBtn;
    @FXML
    public Button signUpBtn;
    @FXML
    public TextField loginField;
    @FXML
    public CheckBox empChk;
    @FXML
    public CheckBox stdChk;
    @FXML
    public PasswordField pswField;
    @FXML
    public ComboBox coursesBox;
    @FXML
    public Button signUpEmployee;
    @FXML
    public Button signUpStudent;
    @FXML
    public Button pvz;

    private User currentUser = null;
    private int courseIsId = 0;
    private Connection connection;
    private PreparedStatement statement;


    private Student student = new Student();

    public void setStudId(int id) {
        this.student.setId(id);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*List<String> options = new ArrayList<>();
        connection = DbOperations.connectToDb();
        if (connection == null) {
            alertMessage("Unable to connect");
            Platform.exit();
        } else {
            try {
                statement = connection.prepareStatement("SELECT * FROM course_is");
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    options.add(rs.getString(2) + "(" + rs.getInt(1) + ")");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            coursesBox.getItems().addAll(options);
        }
        //DbOperations.disconnectFromDb(connection, statement);*/

        try {
            connection = DbOperations.connectToDb();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Error!");
            alert.setContentText("Failed connect to database");
            alert.showAndWait();
        }
    }

    private void alertMessage(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText(alertMessage);
        alert.showAndWait();
    }

    public void checkIfOccupied(ActionEvent actionEvent) {
    }


    public void signUpEmployee(ActionEvent actionEvent) throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/signUpEmployee.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) loginField.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }
    public void signUpStudent(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/signUpStudent.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) loginField.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }


    public void validateAndLogin(ActionEvent event) throws IOException {

        if(loginField != null && pswField!= null) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/MainWindow1.fxml"));
            Parent root = loader.load();
            MainWindow1 mainWindow = loader.getController();
            Stage stage = null;

            try {
                if(stdChk.isSelected()) {

                    String query = "SELECT id, login, password FROM student WHERE login = \"" + loginField.getText() + "\" AND password = \"" + pswField.getText() + "\"";
                    Statement st;

                    st = connection.createStatement();
                    ResultSet result = st.executeQuery(query);

                    if(result.next()) {
                        mainWindow.setStudId(Integer.parseInt(result.getString(1)));
                        stage = (Stage)loginField.getScene().getWindow();
                    }else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error!");
                        alert.setHeaderText("ERROR");
                        alert.setContentText("Wrong ussername or password!");
                        alert.showAndWait();
                        return;
                    }

                }
                if(empChk.isSelected()) {

                    String query = "SELECT id, login, password FROM employee WHERE login = \"" + loginField.getText() + "\" AND password = \"" + pswField.getText() + "\"";
                    Statement st;

                    st = connection.createStatement();
                    ResultSet result = st.executeQuery(query);

                    if(result.next()) {
                        mainWindow.setAdminId(Integer.parseInt(result.getString(1)));
                        stage = (Stage)loginField.getScene().getWindow();
                    }else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error!");
                        alert.setHeaderText(null);
                        alert.setContentText("Wrong ussername or password!");
                        alert.showAndWait();
                        return;
                    }

                }
            } catch (SQLException e) {

                e.printStackTrace();
                return;
            }


            mainWindow.setConnection(connection);
            //mainWindow.initialize(null, null);
            Scene scene = new Scene(root);
            //scene.getStylesheets().add(getClass().getResource("/view/app.css").toExternalForm());
            stage.setScene(scene);
            stage.show();

        }

    }

}
