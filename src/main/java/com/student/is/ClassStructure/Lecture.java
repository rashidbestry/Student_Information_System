package com.student.is.ClassStructure;

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

    public Lecture(){
    }


}
