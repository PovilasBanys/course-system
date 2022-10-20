package webControllers;

import com.google.gson.Gson;
import model.Administrator;
import model.Course;
import model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utils.DbOperations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

@Controller
@RequestMapping(value = "/users")
public class WebUserController {

    //READ
    @RequestMapping(value = "/getAllAdmins", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllAdmins(@RequestParam("courseIs") int courseIs) {
        ArrayList<Administrator> allAdmins = new ArrayList<>();
        Gson parser = new Gson();
        try {
            allAdmins = DbOperations.getAllAdminsFromDb(courseIs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parser.toJson(allAdmins);
    }

    @RequestMapping(value = "/getAllStudents", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllStudents(@RequestParam("courseIs") int courseIs) {
        ArrayList<Student> allStudents = new ArrayList<>();
        Gson parser = new Gson();
        try {
            allStudents = DbOperations.getAllStudentsFromDb(courseIs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parser.toJson(allStudents);
    }

    @RequestMapping(value = "/getUser/{login}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getUser(@PathVariable("login") String name) {
        Gson parser = new Gson();

        try {
            return parser.toJson(DbOperations.getUserByName(name));
        } catch (Exception e) {
            e.printStackTrace();
            return "Error selecting";
        }

    }

    //Authorization
    @RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String loginEmployee(@RequestBody String request) {
        Gson parser = new Gson();
        Properties data = parser.fromJson(request, Properties.class);
        String loginName = data.getProperty("login");
        String password = data.getProperty("psw");
        int courseIs = Integer.parseInt(data.getProperty("courseIs"));
        Administrator administrator = null;
        try {
            administrator = DbOperations.getAdmin(loginName, password, courseIs);
        } catch (Exception e) {
            return "Error";
        }
        if (administrator == null) {
            return "Wrong credentials";
        }
        return Integer.toString(administrator.getId());
    }

    @RequestMapping(value = "/studentLogin", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String loginStudent(@RequestBody String request) {
        Gson parser = new Gson();
        Properties data = parser.fromJson(request, Properties.class);
        String loginName = data.getProperty("login");
        String password = data.getProperty("psw");
        int courseIs = Integer.parseInt(data.getProperty("courseIs"));
        Student student = null;
        try {
            student = DbOperations.getStudent(loginName, password, courseIs);
        } catch (Exception e) {
            return "Error";
        }
        if (student == null) {
            return "Wrong credentials";
        }
        return Integer.toString(student.getId());
    }

    //INSERT
    @RequestMapping(value = "/insertAdministrator", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String insertAdministrator(@RequestBody String request) {
        Gson parser = new Gson();
        Properties data = parser.fromJson(request, Properties.class);
        String login = data.getProperty("login");
        String psw = data.getProperty("psw");
        String email = data.getProperty("email");
        String phoneNum = data.getProperty("email");
        int courseIs = Integer.parseInt(data.getProperty("courseIs"));
        try {
            DbOperations.insertRecordAdmin(login, psw, email, phoneNum, courseIs);
            return parser.toJson(DbOperations.getUserByName(login));
        } catch (Exception e) {
            return "There were errors during insert operation";
        }
    }


    //INSERT
    @RequestMapping(value = "/insertStudent", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String insertStudent(@RequestBody String request) {
        Gson parser = new Gson();
        Properties data = parser.fromJson(request, Properties.class);
        String login = data.getProperty("login");
        String psw = data.getProperty("psw");
        String email = data.getProperty("email");
        int courseIs = Integer.parseInt(data.getProperty("courseIs"));
        //Student relevant properties
        try {
            //DbOperations.insertRecordStudent(login, psw, email, phoneNum, courseIs);
            return parser.toJson(DbOperations.getUserByName(login));
        } catch (Exception e) {
            return "There were errors during insert operation";
        }
    }

    //UPDATE
    @RequestMapping(value = "/updAdmin", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateAdmin(@RequestBody String request) {
        Gson parser = new Gson();
        Properties data = parser.fromJson(request, Properties.class);
        int courseId = Integer.parseInt(data.getProperty("id"));
        String name = data.getProperty("name");
       Double price = Double.parseDouble(data.getProperty("courseIs"));
        try {
            DbOperations.updateDbRecord(courseId, "name", name);
            if (!data.getProperty("start").equals(""))
                DbOperations.updateDbRecord(courseId, "start_date", LocalDate.parse(data.getProperty("start")));
            if (!data.getProperty("end").equals(""))
            DbOperations.updateDbRecord(courseId, "email", price);
            return parser.toJson(DbOperations.getCourseByName(name));
        } catch (Exception e) {
           return "There were errors during insert operation";
        }
    }

    @RequestMapping(value = "/updStudent", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateStudent(@RequestBody String request) {
        Gson parser = new Gson();
        Properties data = parser.fromJson(request, Properties.class);
        int courseId = Integer.parseInt(data.getProperty("id"));
        String name = data.getProperty("name");
        Double price = Double.parseDouble(data.getProperty("courseIs"));
        try {
            DbOperations.updateDbRecord(courseId, "name", name);
            if (!data.getProperty("start").equals(""))
                DbOperations.updateDbRecord(courseId, "start_date", LocalDate.parse(data.getProperty("start")));
            if (!data.getProperty("end").equals(""))
                DbOperations.updateDbRecord(courseId, "email", price);
            return parser.toJson(DbOperations.getCourseByName(name));
        } catch (Exception e) {
            return "There were errors during insert operation";
        }
    }

    @RequestMapping(value = "/delUserName", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String deleteUserName(@RequestParam("name") String name) {
        try {
            DbOperations.deleteDbRecord(name);
            return "Record deleted";
        } catch (Exception e) {
            return "There were errors during insert operation";
        }
    }

    @RequestMapping(value = "/delUserId/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String deleteUserId(@PathVariable("id") int id) {
        try {
            DbOperations.deleteDbRecord(id);
            return "Record deleted";
        } catch (Exception e) {
            return "There were errors during insert operation";
        }
    }
}
