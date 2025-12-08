package com.student.is.ClassStructure;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PersonalAbsenceOperations {

    public StringProperty studentNumber ;
    public StringProperty studentName;
    public StringProperty studentSurname;
    public IntegerProperty teorikAbsence;
    public IntegerProperty praciteAbsence;
    public StringProperty absenceStatus;
    public Student studentReferans;   // öğrenci için bir referans
    public Lecture lectureReferans;

    public PersonalAbsenceOperations(String studentNumber, String studentName, String studentSurname, int teorikAbsence, int praciteAbsence, String absenceStatus, Student studentReferans, Lecture lectureReferans) {
        this.studentNumber = new SimpleStringProperty(studentNumber);
        this.studentName = new SimpleStringProperty(studentName);
        this.studentSurname = new SimpleStringProperty(studentSurname);
        this.teorikAbsence = new SimpleIntegerProperty(teorikAbsence);
        this.praciteAbsence = new SimpleIntegerProperty(praciteAbsence);
        this.absenceStatus = new SimpleStringProperty(absenceStatus);
        this.studentReferans = studentReferans;
        this.lectureReferans = lectureReferans;
    }

    public String getStudentNumber() {
        return studentNumber.get();
    }

    public Student getStudentReferans() {
        return studentReferans;
    }


    public Lecture getLectureReferans() {
        return lectureReferans;
    }


    public StringProperty studentNumberProperty() {
        return studentNumber;
    }
    public String getStudentName() {
        return studentName.get();
    }
    public StringProperty studentNameProperty() {
        return studentName;
    }
    public String getStudentSurname() {
        return studentSurname.get();
    }
    public StringProperty studentSurnameProperty() {
        return studentSurname;
    }
    public int getTeorikAbsence() {
        return teorikAbsence.get();
    }
    public IntegerProperty teorikAbsenceProperty() {
        return teorikAbsence;
    }
    public int getPraciteAbsence() {
        return praciteAbsence.get();
    }
    public IntegerProperty praciteAbsenceProperty() {
        return praciteAbsence;
    }
    public String getAbsenceStatus() {
        return absenceStatus.get();
    }
    public StringProperty absenceStatusProperty() {
        return absenceStatus;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber.set(studentNumber);
    }

    public void setStudentName(String studentName) {
        this.studentName.set(studentName);
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname.set(studentSurname);
    }

    public void setTeorikAbsence(int teorikAbsence) {
        this.teorikAbsence.set(teorikAbsence);
    }

    public void setPraciteAbsence(int praciteAbsence) {
        this.praciteAbsence.set(praciteAbsence);
    }

    public void setAbsenceStatus(String absenceStatus) {
        this.absenceStatus.set(absenceStatus);
    }
}
