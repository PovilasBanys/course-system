package utils;

import model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DbOperations {

    private static Connection connection;
    private static PreparedStatement statement;

    public static Connection connectToDb() {

        String DB_URL = "jdbc:mysql://localhost/coursera";
        String USER = "root";
        String PASS = "";
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void disconnectFromDb(Connection connection, Statement statement) {
        try {
            if (connection != null && statement != null) {
                connection.close();
                statement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateDbRecord(int id, String colName, Double newValue) throws SQLException {
        if (newValue != 0) {
            connection = DbOperations.connectToDb();
            String sql = "UPDATE course SET `" + colName + "`  = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setDouble(1, newValue);
            statement.setInt(2, id);
            statement.executeUpdate();
            DbOperations.disconnectFromDb(connection, statement);
        }
    }

    public static void updateDbRecord(int id, String colName, String newValue) throws SQLException {
        if (!newValue.equals("")) {
            connection = DbOperations.connectToDb();
            String sql = "UPDATE `course` SET `" + colName + "`  = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, newValue);
            statement.setInt(2, id);
            statement.executeUpdate();
            DbOperations.disconnectFromDb(connection, statement);
        }
    }

    public static void updateDbRecord(int id, String colName, LocalDate newValue) throws SQLException {
        connection = DbOperations.connectToDb();
        String sql = "UPDATE `course` SET `" + colName + "`  = ? WHERE id = ?";
        statement = connection.prepareStatement(sql);
        statement.setDate(1, Date.valueOf(newValue));
        statement.setInt(2, id);
        statement.executeUpdate();
        DbOperations.disconnectFromDb(connection, statement);
    }

    public static void deleteDbRecord(int id) throws SQLException {
        connection = DbOperations.connectToDb();
        String sql = "DELETE FROM `course` WHERE id = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.execute();
        DbOperations.disconnectFromDb(connection, statement);
    }

    public static void deleteDbRecord(String name) throws SQLException {
        connection = DbOperations.connectToDb();
        String sql = "DELETE FROM `course` WHERE name = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        statement.execute();
        DbOperations.disconnectFromDb(connection, statement);
    }

    public static void insertRecordCourse(String name, LocalDate startDate, LocalDate endDate, int adminId, double price) throws SQLException {
        connection = DbOperations.connectToDb();
        String sql = "INSERT INTO `course`(`id`, `name`, `start_date`, `end_date`, `admin_id`, `course_price`) VALUES(?,?,?,?,?,?)";
        statement = connection.prepareStatement(sql);

        statement.setString(2, name);
        statement.setDate(3, Date.valueOf(startDate));
        statement.setDate(4, Date.valueOf(endDate));
        statement.setInt(5, adminId);
        statement.setDouble(6, price);

        statement.execute();
        DbOperations.disconnectFromDb(connection, statement);


       /* PreparedStatement s1 = con.prepareStatement(query);
        s1.setString(1, "0");
        s1.setString(2, myCourseName.getText());
        s1.setString(3, startDate.toString());
        s1.setString(4, endDate.toString());
        s1.setInt(5, admin.getId());
        s1.setString(6, String.valueOf(price));*/
    }









    public static ArrayList<Course> getAllCoursesFromDb(int courseIsId) throws SQLException {
        ArrayList<Course> allCourses = new ArrayList<>();
        connection = DbOperations.connectToDb();

       /* String query = "SELECT name FROM course";
        try {
            Statement st = con.createStatement();
            ResultSet result = st.executeQuery(query);

            while(result.next()) {
               allCourses.add(new Course(result.getString(1)));
            }

        }catch(Exception e) {

        }*/

       String sql = "SELECT * FROM course AS c WHERE c.admin_id = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, courseIsId);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            allCourses.add(new Course(rs.getString(1), LocalDate.parse(rs.getString(2)), LocalDate.parse(rs.getString(3)), rs.getInt(4), rs.getDouble(5)));
            DbOperations.disconnectFromDb(connection, statement);
        }
            return allCourses;

    }

    public static ArrayList<File> getAllFilesFromDb(int courseIsId) throws SQLException {
        ArrayList<File> allFiles = new ArrayList<>();
        connection = DbOperations.connectToDb();

        String sql = "SELECT * FROM folder_files AS c WHERE c.folder_id = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, courseIsId);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            allFiles.add(new File(rs.getString(1),(rs.getString(2))));
            DbOperations.disconnectFromDb(connection, statement);
        }
        return allFiles;

    }

    public static Object getFileByName(String name) throws SQLException {
        File file = null;
        connection = DbOperations.connectToDb();
        String sql = "SELECT * FROM users AS c WHERE c.login = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            file = new File(rs.getString(1), rs.getString(2));
        }
        DbOperations.disconnectFromDb(connection, statement);
        return file;
    }

    public static void insertFile(String name, LocalDate dateAdded, int folderId) throws SQLException {
        connection = DbOperations.connectToDb();
        String sql = "INSERT INTO `folder_files`(`name`, `date_added`, `folder_id`) VALUES(?,?,?)";
        statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        statement.setDate(2, Date.valueOf(dateAdded));
        statement.setInt(3, folderId);

        statement.execute();
        DbOperations.disconnectFromDb(connection, statement);
    }









    public static Course getCourseByName(String name) throws SQLException {
        Course course = null;
        connection = DbOperations.connectToDb();
        String sql = "SELECT * FROM course AS c WHERE c.name = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            course = new Course(rs.getInt(1), rs.getString(2), LocalDate.parse(rs.getString(3)), LocalDate.parse(rs.getString(4)),rs.getInt(5), rs.getDouble(6));
        }
        DbOperations.disconnectFromDb(connection, statement);
        return course;
    }

    public static ArrayList<Administrator> getAllAdminsFromDb(int courseIs) throws SQLException {
        ArrayList<Administrator> allAdmins = new ArrayList<>();
        connection = DbOperations.connectToDb();
        String sql = "SELECT * FROM `employee` AS c WHERE c.course_is = ? AND c.email is not NULL";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, courseIs);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            allAdmins.add(new Administrator(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
        }
        DbOperations.disconnectFromDb(connection, statement);
        return allAdmins;
    }

    public static ArrayList<Student> getAllStudentsFromDb(int courseIs) throws SQLException {
        ArrayList<Student> allStudents = new ArrayList<>();
        connection = DbOperations.connectToDb();
        String sql = "SELECT * FROM `student` AS c WHERE c.course_is = ? AND c.email is not NULL";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, courseIs);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            allStudents.add(new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
        }
        DbOperations.disconnectFromDb(connection, statement);
        return allStudents;
    }

    public static User getUserByName(String login) throws SQLException {
        User user = null;
        connection = DbOperations.connectToDb();
        String sql = "SELECT * FROM users AS c WHERE c.login = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, login);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            user = new User(rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5), rs.getString(6));
        }
        DbOperations.disconnectFromDb(connection, statement);
        return user;
    }

    public static Administrator getAdmin(String login, String psw, int courseIs) throws SQLException {
        Administrator administrator = null;
        connection = DbOperations.connectToDb();
        String sql = "SELECT * FROM users AS c WHERE c.login = ? AND c.psw = ? AND c.course_is = ? AND c.phone_number is not NULL";
        statement = connection.prepareStatement(sql);
        statement.setString(1, login);
        statement.setString(2, psw);
        statement.setInt(3, courseIs);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            administrator = new Administrator(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
        }
        DbOperations.disconnectFromDb(connection, statement);
        return administrator;
    }

    public static Student getStudent(String login, String psw, int courseIs) throws SQLException {
        Student student = null;
        connection = DbOperations.connectToDb();
        String sql = "SELECT * FROM student AS c WHERE c.login = ? AND c.psw = ? AND c.course_is = ? AND c.email is not NULL";
        statement = connection.prepareStatement(sql);
        statement.setString(1, login);
        statement.setString(2, psw);
        statement.setInt(3, courseIs);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            student = new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
        }
        DbOperations.disconnectFromDb(connection, statement);
        return student;
    }

    public static void insertRecordAdmin(String login, String psw, String email, String phoneNum, int courseIs) throws SQLException {
        connection = DbOperations.connectToDb();
        String sql = "INSERT INTO `users`(`login`, `psw`, `email`, `phone_number`, `course_is`) VALUES(?,?,?,?,?)";
        statement = connection.prepareStatement(sql);
        statement.setString(1, login);
        statement.setString(2, psw);
        statement.setString(3, email);
        statement.setString(4, phoneNum);
        statement.setInt(5, courseIs);
        statement.execute();
        DbOperations.disconnectFromDb(connection, statement);
    }

    public static ArrayList<Folder> getAllFoldersFromDb(int courseIsId) throws SQLException {
        ArrayList<Folder> allFolders = new ArrayList<>();
        connection = DbOperations.connectToDb();
        String sql = "SELECT * FROM folder AS c WHERE c.course_id = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, courseIsId);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            allFolders.add(new Folder(rs.getString(2), (rs.getDate(3)), rs.getString(4)));
        }
        DbOperations.disconnectFromDb(connection, statement);
        return allFolders;
    }

    public static Object getFolderByName(String name) throws SQLException {

        Folder folder = null;
        connection = DbOperations.connectToDb();
        String sql = "SELECT * FROM folder AS c WHERE c.folder_name = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            folder = new Folder(rs.getString(2), rs.getDate(3), rs.getString(4));
        }
        DbOperations.disconnectFromDb(connection, statement);
        return folder;
    }

    public static void insertFolder(String name, LocalDate dateAdded, int courseId) throws SQLException {
        connection = DbOperations.connectToDb();
        String sql = "INSERT INTO `folder_files`(`folder_name`, `date_added`, `course_id`) VALUES(?,?,?)";
        statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        statement.setDate(2, Date.valueOf(dateAdded));
        statement.setInt(3, courseId);

        statement.execute();
        DbOperations.disconnectFromDb(connection, statement);
    }
}
