package com.student.is.ClassStructure;

public class Student {
    public long stuNum;
    public String firstName;
    public String lastName;
    public int age;
    public int classNo;
    public String faculty;
    public double gpa;

    public Student(){
    }

    public Student(long stuNum,String firstName, String lastName, byte age, byte classNo, String faculty, float gpa) {
        this.stuNum = stuNum;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.classNo = classNo;
        this.faculty = faculty;
        this.gpa = gpa;
    }
}