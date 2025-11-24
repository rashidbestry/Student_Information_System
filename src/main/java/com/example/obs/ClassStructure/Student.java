package com.example.obs.ClassStructure;

public class Student {
    static int stuNumCounter=224000000;

    int stuNum;
    String firstName;
    String lastName;
    byte age;
    byte classNo;
    String faculty;
    float gpa;

    public Student(String firstName, String lastName, byte age, byte classNo, String faculty, float gpa) {
        stuNum = stuNumCounter+1;stuNumCounter++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.classNo = classNo;
        this.faculty = faculty;
        this.gpa = gpa;


    }
}