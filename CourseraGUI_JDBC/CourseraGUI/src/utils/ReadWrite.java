package utils;

import model.CourseIS;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ReadWrite {

    public static void saveCourseIS(CourseIS courseIS) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("myCourse.txt"));
            out.writeObject(courseIS);
            out.close();
        } catch (Exception e) {
            System.out.println("Error when saving.");
            e.printStackTrace();
        }
    }

    public static CourseIS loadCourseIS() {
        CourseIS courseIS = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("myCourse.txt"));
            courseIS = (CourseIS) in.readObject();
            in.close();
        } catch (Exception e) {
            System.out.println("Error when opening.");
            e.printStackTrace();
        }
        return courseIS;
    }

}
