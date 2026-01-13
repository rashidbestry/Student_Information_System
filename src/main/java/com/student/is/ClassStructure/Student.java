package com.student.is.ClassStructure;
import com.student.is.DataManagement.Database;
import java.util.ArrayList;
import java.util.HashMap;

public class Student {
    public HashMap<String,String> stuAbsence = new HashMap<>();
    public HashMap<String,String> stuNotes = new HashMap<>();
    public ArrayList<Lecture> lectures = new ArrayList<>();
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
    public String email;
    public String address;

    public Student(String stuId,String firstName,String lastName,String bornDate,int classYear,String faculty,String phoneNo,String email) {
        this.stuId = stuId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bornDate = bornDate;
        this.classYear = classYear;
        this.faculty = faculty;
        this.phoneNo = phoneNo;
        this.email = email;
        this.gpa = 0.0;
        this.section = "Mühendislik Fakültesi";
        this.address="Adres Belirlenmedi";
        this.notes=new Notes();
        this.lectures = new ArrayList<>();
        this.stuNotes=new HashMap<>();
        this.stuAbsence=new HashMap<>();

        for(Lecture lecture : Database.lectureList) {
            if (this.classYear == lecture.getLectureClass()) {
                    lectures.add(lecture);
            }
        }
        for(Lecture lecture : lectures) {
            String key =lecture.getLectureCode();
            String value = "0,0";
            stuAbsence.put(key,value);
        }
        for(Lecture lecture : lectures) {
            String key =lecture.getLectureCode();
            String value = "00,00";
            stuNotes.put(key,value);
        }
    }
    public Student(){

    }

    public void createLectures(){
        for (Lecture object : Database.lectureList){
            if (stuNotes.containsKey(object.lectureCode)){
                lectures.add(object);
            }
        }
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
        if (fallvizeCount != 0 && fallfinalCount != 0 && springvizeCount != 0 && springfinalCount != 0) {
            notes.fall1 = (int) fallvize / fallvizeCount;
            notes.fall2 = (int) fallfinal / fallfinalCount;
            notes.spring1 = (int) springvize / springvizeCount;
            notes.spring1 = (int) springfinal / springfinalCount;
            double gpa_4 = (double)(((notes.fall1+notes.fall2+notes.spring1+notes.spring1)/4)*4.0)/100;
            this.gpa = gpa_4;
        }
    }

//  GETTERS /////////////////////////////////////////////////////////

    public HashMap<String, String> getStuAbsence() {
        return stuAbsence;
    }

    public HashMap<String, String> getStuNotes() {
        return stuNotes;
    }

    public ArrayList<Lecture> getLectures() {
        return lectures;
    }
    public String getLecturesToString() {
        String str="/";
        for (Lecture lec : lectures){
            str = str+lec.lectureCode+"("+stuAbsence.get(lec.lectureCode)+")("+stuNotes.get(lec.lectureCode).replace(",",")(")+")"+"/";
        }

        return str.substring(1,str.length()-1);
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

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public int getClassYear() {
        return classYear;
    }

    public String getFaculty() {
        return faculty;
    }

    public Notes getNotes() {
        return notes;
    }
    public String getStringNotes(){
        return "("+String.valueOf(notes.fall1) +","+ String.valueOf(notes.fall2)+")("+String.valueOf(notes.fall1)+","+String.valueOf(notes.fall2)+")";
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public double getGpa() {
        return gpa;
    }

    public String getSection() {
        return "Mühendislik Fakültesi";
    }


//    SETTERS /// ///////////////////////////////////////////////////


    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }

    public void setClassYear(int classYear) {
        this.classYear = classYear;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public void setSection(String section) {
        this.section = section;
    }

}


