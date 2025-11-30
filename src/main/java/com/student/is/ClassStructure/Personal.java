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

    public String getPerId() {
        return perId;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getTitle() {
        return title;
    }
    public String getEmail() {
        return email;
    }
    public String getWeb() {
        return web;
    }
    public String getOfficehours() {
        return officehours;
    }



    public boolean deleteStudent(Student object){
        return Database.deleteObject(object);
    }
    public boolean changeStudentData(Student object){
        return Database.changeObjectData(object);
    }

}
