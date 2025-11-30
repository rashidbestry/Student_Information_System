package com.student.is.ClassStructure;

public class Student {
    public String stuId;
    public String firstName;
    public String lastName;
    public String bornDate;
    public int classYear;
    public String faculty;
    public String section;
    public int gpa;

    public Student(){
        this.stuId = "02240201199";
        this.firstName = "Ali Osman";
        this.lastName = "Kaylı";
        this.bornDate = "01.01.1999";
        this.classYear = 2;
        this.faculty = "Mühendislik Fakültesi";
        this.section = "Bilgisayar Mühendisliği";
        this.gpa = 10;

    }

    public String getStuId(){
        return stuId;
    }
    public String getName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getBornDate(){
        return this.bornDate;

    }
    public int getClassYear(){
        return classYear;
    }
    public String getFaculty(){
        return faculty;
    }
    public int getGpa(){
        return gpa;
    }
    public String getSection(){
        return section;
    }
}