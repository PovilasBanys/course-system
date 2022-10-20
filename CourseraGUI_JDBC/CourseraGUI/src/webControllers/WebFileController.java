package webControllers;

import com.google.gson.Gson;
import model.File;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utils.DbOperations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

@Controller
@RequestMapping(value = "/files")
public class WebFileController {


    //READ  ..../courses/getAllCourses
    @RequestMapping(value = "/getAllFiles", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllFiles(@RequestParam("courseIs") int courseIs) {
        ArrayList<File> allFiles = new ArrayList<>();
        Gson parser = new Gson();
        try {
            allFiles = DbOperations.getAllFilesFromDb(courseIs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parser.toJson(allFiles);
    }

    @RequestMapping(value = "/getFile/{name}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getFile(@PathVariable("name") String name) {
        Gson parser = new Gson();

        try {
            return parser.toJson(DbOperations.getFileByName(name));
        } catch (Exception e) {
            e.printStackTrace();
            return "Error selecting";
        }
    }

    @RequestMapping(value = "/insertFile", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String insertFile(@RequestBody String request) {
        Gson parser = new Gson();
        Properties data = parser.fromJson(request, Properties.class);
        String name = data.getProperty("name");
        LocalDate dateAdded = LocalDate.parse(data.getProperty("start"));
        int folderId = Integer.parseInt(data.getProperty("folderId"));

        try {
            DbOperations.insertFile(name, dateAdded, folderId);
            return parser.toJson(DbOperations.getCourseByName(name));
        } catch (Exception e) {
            return "There were errors during insert operation";
        }
    }

    @RequestMapping(value = "/updateFile", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateFile(@RequestBody String request) {
        Gson parser = new Gson();
        Properties data = parser.fromJson(request, Properties.class);
        int fileId = Integer.parseInt(data.getProperty("id"));
        String name = data.getProperty("name");
        LocalDate dateAdded = LocalDate.parse(data.getProperty("start"));
        int folderId = Integer.parseInt(data.getProperty("folderId"));

        try {
            DbOperations.updateDbRecord(fileId, "name", name);
            if (!data.getProperty("start").equals(""))
                DbOperations.updateDbRecord(fileId, "date_added", LocalDate.parse(data.getProperty("added")));
            DbOperations.updateDbRecord(fileId, "folder_id", (double) folderId);
            return parser.toJson(DbOperations.getFileByName(name));
        } catch (Exception e) {
            return "There were errors during insert operation";
        }
    }

    @RequestMapping(value = "/delFileId/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String deleteFileId(@PathVariable("id") int id) {
        try {
            DbOperations.deleteDbRecord(id);
            return "Record deleted";
        } catch (Exception e) {
            return "There were errors during insert operation";
        }
    }

    @RequestMapping(value = "/delFileName", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String deleteFileName(@RequestParam("name") String name) {
        try {
            DbOperations.deleteDbRecord(name);
            return "Record deleted";
        } catch (Exception e) {
            return "There were errors during insert operation";
        }
    }
}