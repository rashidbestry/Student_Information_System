package com.student.is.ClassStructure;

import com.student.is.DataManagement.Database;

import java.util.ArrayList;

public class Personal {
    public String perId;
    public String name;
    public String surname;
    public String title;
    public String email;
    public String web;
    public String officehours;
    public ArrayList<Lecture> lectures = new ArrayList<>();

    public Personal(){
    }
    public void createLectures(){
        for (Lecture object : Database.lectureList){
            if (object.lectureTeacher.equals(perId)){
                lectures.add(object);
            }
        }
    }
    public boolean deleteStudent(Student object){
        return Database.deleteObject(object);
    }
    public boolean changeStudentData(Student object){
        return Database.changeObjectData(object);
    }

}
