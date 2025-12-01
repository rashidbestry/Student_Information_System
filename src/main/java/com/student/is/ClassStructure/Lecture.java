package com.student.is.ClassStructure;
import com.student.is.DataManagement.Database;

import java.util.ArrayList;

public class Lecture {
    public String lectureCode;
    public String lectureName;
    public boolean lectureMandatory;
    public int lectureCredit;
    public int lectureAKTS;
    public int lectureClass;
    public String lectureLang;
    public String lectureType;
    public int lectureTheory;
    public int lectureApplication;
    public String lectureTeacher;
    public String lectureSeason;
    public ArrayList<Student> lectureStudentList = new ArrayList<>();

    public void createStudentListForLecture(){
        for (Student student : Database.studentList){
            if (student.stuAbsence.containsKey(this.lectureCode)){
                lectureStudentList.add(student);
            }
        }
    }


//  GETTERS /// ////////////////////////////////////////////////////////


    public String getLectureCode() {
        return lectureCode;
    }

    public String getLectureName() {
        return lectureName;
    }

    public boolean isLectureMandatory() {
        return lectureMandatory;
    }

    public int getLectureCredit() {
        return lectureCredit;
    }

    public int getLectureAKTS() {
        return lectureAKTS;
    }

    public int getLectureClass() {
        return lectureClass;
    }

    public String getLectureLang() {
        return lectureLang;
    }

    public String getLectureType() {
        return lectureType;
    }

    public int getLectureTheory() {
        return lectureTheory;
    }

    public int getLectureApplication() {
        return lectureApplication;
    }

    public String getLectureTeacher() {
        return lectureTeacher;
    }

    public String getLectureSeason() {
        return lectureSeason;
    }

    public ArrayList<Student> getLectureStudentList() {
        return lectureStudentList;
    }


//  SETTERS /// //////////////////////////////////////////////////


    public void setLectureCode(String lectureCode) {
        this.lectureCode = lectureCode;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public void setLectureMandatory(boolean lectureMandatory) {
        this.lectureMandatory = lectureMandatory;
    }

    public void setLectureCredit(int lectureCredit) {
        this.lectureCredit = lectureCredit;
    }

    public void setLectureAKTS(int lectureAKTS) {
        this.lectureAKTS = lectureAKTS;
    }

    public void setLectureClass(int lectureClass) {
        this.lectureClass = lectureClass;
    }

    public void setLectureLang(String lectureLang) {
        this.lectureLang = lectureLang;
    }

    public void setLectureType(String lectureType) {
        this.lectureType = lectureType;
    }

    public void setLectureTheory(int lectureTheory) {
        this.lectureTheory = lectureTheory;
    }

    public void setLectureApplication(int lectureApplication) {
        this.lectureApplication = lectureApplication;
    }

    public void setLectureTeacher(String lectureTeacher) {
        this.lectureTeacher = lectureTeacher;
    }

    public void setLectureSeason(String lectureSeason) {
        this.lectureSeason = lectureSeason;
    }

    public void setLectureStudentList(ArrayList<Student> lectureStudentList) {
        this.lectureStudentList = lectureStudentList;
    }
}
