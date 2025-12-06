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
    public Personal(String perId, String name, String surname, String title, String email, String web, String officehours){
        this.perId = perId;
        this.name = name;
        this.surname = surname;
        this.title = title;
        this.email = email;
        this.web = web;
        this.officehours = officehours;
        this.lectures = new ArrayList<>();
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

//  GETTERS /// ///////////////////////////////////////////////////


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

    public ArrayList<Lecture> getLectures() {
        return lectures;
    }

//  SETTERS /// /////////////////////////////////////////////////


    public void setPerId(String perId) {
        this.perId = perId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public void setOfficehours(String officehours) {
        this.officehours = officehours;
    }

    public void setLectures(ArrayList<Lecture> lectures) {
        this.lectures = lectures;
    }
}
