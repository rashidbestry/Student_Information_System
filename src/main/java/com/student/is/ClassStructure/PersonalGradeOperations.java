package com.student.is.ClassStructure;

import javafx.beans.property.*;

public class PersonalGradeOperations {

    public StringProperty  studentNumber ;
    public  StringProperty studentName;
    public  StringProperty studentSurname;
    public IntegerProperty vizeNote;
    public IntegerProperty finalNote;
    public DoubleProperty  averageNote;
    public StringProperty letterNote;
    public StringProperty status;
    public Student studentReferans;   // öğrenci için bir referans
    public Lecture lectureReferans;  // ders için bir referans

    public PersonalGradeOperations(String studentNumber, String studentName, String studentSurname, int vizeNote,  int finalNote, double averageNote, String letterNote, String status, Student studentReferans, Lecture lectureReferans) {
        this.studentNumber = new SimpleStringProperty(studentNumber);
        this.studentName = new SimpleStringProperty(studentName);
        this.studentSurname = new SimpleStringProperty(studentSurname);
        this.vizeNote = new SimpleIntegerProperty(vizeNote);
        this.finalNote = new SimpleIntegerProperty(finalNote);
        this.averageNote = new SimpleDoubleProperty(averageNote);
        this.letterNote = new SimpleStringProperty(letterNote);
        this.status = new SimpleStringProperty(status);

        this.studentReferans = studentReferans;
        this.lectureReferans = lectureReferans;

    }

    public Student getStudentReferans() {
        return studentReferans;
    }

    public Lecture getLectureReferans() {
        return lectureReferans;
    }

    public String getStudentNumber() {
        return studentNumber.get();
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

    public int getVizeNote() {
        return vizeNote.get();
    }

    public IntegerProperty vizeNoteProperty() {
        return vizeNote;
    }

    public int getFinalNote() {
        return finalNote.get();
    }

    public IntegerProperty finalNoteProperty() {
        return finalNote;
    }

    public double getAverageNote() {
        return averageNote.get();
    }

    public DoubleProperty averageNoteProperty() {
        return averageNote;
    }

    public String getLetterNote() {
        return letterNote.get();
    }

    public StringProperty letterNoteProperty() {
        return letterNote;
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
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

    public void setVizeNote(int vizeNote) {
        this.vizeNote.set(vizeNote);
    }

    public void setFinalNote(int finalNote) {
        this.finalNote.set(finalNote);
    }

    public void setAverageNote(double averageNote) {
        this.averageNote.set(averageNote);
    }

    public void setLetterNote(String letterNote) {
        this.letterNote.set(letterNote);
    }

    public void setStatus(String status) {
        this.status.set(status);
    }
}
