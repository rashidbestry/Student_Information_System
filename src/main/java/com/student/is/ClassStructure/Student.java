package com.student.is.ClassStructure;

import com.student.is.DataManagement.Database;

import java.util.HashMap;

public class Student {
    public HashMap<String,String> stuAbsence = new HashMap<>();
    public HashMap<String,String> stuNotes = new HashMap<>();
    public String stuId;
    public String firstName;
    public String lastName;
    public String bornDate;
    public int classYear;
    public String faculty;
    public Notes notes;
    public String phoneNo;
    public double gpa;
    public String section;

    public Student(){
    }

    public String getStuId() {
        return stuId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBornDate() {
        return bornDate;
    }

    public int getClassYear() {
        return classYear;
    }

    public String getFaculty() {
        return faculty;
    }

    public double getGpa() {
        return gpa;
    }

    public String getSection() {
        return section;
    }
    public void LectureAbsence(String string){
        String[] lectures = string.split("/");
        for (String lecture : lectures){
            String absence = lecture.substring(lecture.indexOf("(")+1,lecture.indexOf("(")+4);
            String lec = lecture.substring(0,lecture.indexOf("("));
            stuAbsence.put(lec,absence);
        }
    }
    public void LectureNotes(String string){
        String[] lectures = string.split("/");
        for (String lecture : lectures){
            String absence = lecture.substring(lecture.indexOf("(")+6,lecture.indexOf("(")+12).replace(")(",",");
            String lec = lecture.substring(0,lecture.indexOf("("));
            stuNotes.put(lec,absence);
        }
    }

    public void calculateGpa(){
        int fallvize = 0;
        int fallvizeCount = 0;
        int fallfinal = 0;
        int fallfinalCount = 0;
        int springvize = 0;
        int springvizeCount = 0;
        int springfinal = 0;
        int springfinalCount = 0;
        for (String fall1 : stuNotes.keySet()) {
            for (Lecture lec :  Database.lectureList) {
                if (lec.lectureCode.equals(fall1) && lec.lectureSeason.equals("Güz")) {
                    int sumfall1 = Integer.parseInt(stuNotes.get(fall1).split(",")[0]);
                    fallvize += sumfall1;
                    fallvizeCount++;
                }
            }
        }
        for (String fall2 : stuNotes.keySet()) {
            for (Lecture lec :  Database.lectureList) {
                if (lec.lectureCode.equals(fall2) && lec.lectureSeason.equals("Bahar")) {
                    int sumfall2 = Integer.parseInt(stuNotes.get(fall2).split(",")[0]);
                    fallfinal += sumfall2;
                    fallfinalCount++;
                }
            }
        }
        for (String spring1 : stuNotes.keySet()) {
            for (Lecture lec :  Database.lectureList) {
                if (lec.lectureCode.equals(spring1) && lec.lectureSeason.equals("Güz")) {
                    int sumspring1 = Integer.parseInt(stuNotes.get(spring1).split(",")[0]);
                    springvize += sumspring1;
                    springvizeCount++;
                }
            }
        }
        for (String spring2 : stuNotes.keySet()) {
            for (Lecture lec :  Database.lectureList) {
                if (lec.lectureCode.equals(spring2) && lec.lectureSeason.equals("Bahar")) {
                    int sumspring2 = Integer.parseInt(stuNotes.get(spring2).split(",")[0]);
                    springfinal += sumspring2;
                    springfinalCount++;
                }
            }
        }

        notes.fall1 = (int) fallvize / fallvizeCount;
        notes.fall2 = (int) fallfinal / fallfinalCount;
        notes.spring1 = (int) springvize / springvizeCount;
        notes.spring1 = (int) springfinal / springfinalCount;

        int end = 0;
    }


}


