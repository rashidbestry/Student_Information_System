package com.student.is.ClassStructure;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PersonalGradeOperations {

    public StringProperty  studentNumber ;
    public  StringProperty studentName;
    public  StringProperty studentSurname;
    public DoubleProperty vizeNote;
    public DoubleProperty finalNote;
    public DoubleProperty  averageNote;
    public StringProperty letterNote;
    public StringProperty status;


    public PersonalGradeOperations(String studentNumber, String studentName, String studentSurname, double vizeNote, double finalNote, double averageNote, String letterNote, String status) {
        this.studentNumber = new SimpleStringProperty(studentNumber);
        this.studentName = new SimpleStringProperty(studentName);
        this.studentSurname = new SimpleStringProperty(studentSurname);
        this.vizeNote = new SimpleDoubleProperty(vizeNote);
        this.finalNote = new SimpleDoubleProperty(finalNote);
        this.averageNote = new SimpleDoubleProperty(averageNote);
        this.letterNote = new SimpleStringProperty(letterNote);
        this.status = new SimpleStringProperty(status);
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

    public double getVizeNote() {
        return vizeNote.get();
    }

    public DoubleProperty vizeNoteProperty() {
        return vizeNote;
    }

    public double getFinalNote() {
        return finalNote.get();
    }

    public DoubleProperty finalNoteProperty() {
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
}
