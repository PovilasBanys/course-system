package webControllers;

import com.google.gson.Gson;
import model.Folder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utils.DbOperations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

@Controller
@RequestMapping(value = "/folders")
public class WebFolderController {


    @RequestMapping(value = "/getAllFolders", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllFolders(@RequestParam("courseIs") int courseIs) {
        ArrayList<Folder> allFolders = new ArrayList<>();
        Gson parser = new Gson();
        try {
            allFolders = DbOperations.getAllFoldersFromDb(courseIs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parser.toJson(allFolders);
    }

    @RequestMapping(value = "/getFolder/{name}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getFolder(@PathVariable("name") String name) {
        Gson parser = new Gson();

        try {
            return parser.toJson(DbOperations.getFolderByName(name));
        } catch (Exception e) {
            e.printStackTrace();
            return "Error selecting";
        }

    }

    @RequestMapping(value = "/insertFolder", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String insertFile(@RequestBody String request) {
        Gson parser = new Gson();
        Properties data = parser.fromJson(request, Properties.class);
        String name = data.getProperty("name");
        LocalDate dateAdded = LocalDate.parse(data.getProperty("start"));
        int courseId = Integer.parseInt(data.getProperty("folderId"));

        try {
            DbOperations.insertFolder(name, dateAdded, courseId);
            return parser.toJson(DbOperations.getFolderByName(name));
        } catch (Exception e) {
            return "There were errors during insert operation";
        }
    }

    @RequestMapping(value = "/updateFolder", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateFolder(@RequestBody String request) {
        Gson parser = new Gson();
        Properties data = parser.fromJson(request, Properties.class);
        String name = data.getProperty("name");
        LocalDate dateAdded = LocalDate.parse(data.getProperty("start"));
        int courseId = Integer.parseInt(data.getProperty("courseId"));

        try {
            DbOperations.updateDbRecord(courseId, "folder_name", name);
            if (!data.getProperty("start").equals(""))
                DbOperations.updateDbRecord(courseId, "date_added", LocalDate.parse(data.getProperty("added")));
            DbOperations.updateDbRecord(courseId, "course_id", (double) courseId);
            return parser.toJson(DbOperations.getFolderByName(name));
        } catch (Exception e) {
            return "There were errors during insert operation";
        }
    }

    @RequestMapping(value = "/delFolderId/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String deleteFolderId(@PathVariable("id") int id) {
        try {
            DbOperations.deleteDbRecord(id);
            return "Record deleted";
        } catch (Exception e) {
            return "There were errors during insert operation";
        }
    }

    @RequestMapping(value = "/delFolderName", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String deleteFolderName(@RequestParam("name") String name) {
        try {
            DbOperations.deleteDbRecord(name);
            return "Record deleted";
        } catch (Exception e) {
            return "There were errors during insert operation";
        }
    }


}
