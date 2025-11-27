package com.student.is.ClassStructure;

import com.student.is.DataManagement.Database;

public class Personal {
    public String perId;
    public String name;
    public String surname;
    public String title;
    public String email;
    public String web;
    public String officehours;

    public Personal(){
    }
    public boolean deleteStudent(Student object){
        return Database.deleteObject(object);
    }
    public boolean changeStudentData(Student object){
        return Database.changeObjectData(object);
    }

}
