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

    public PersonalAbsenceOperations(String studentNumber, String studentName, String studentSurname, int teorikAbsence, int praciteAbsence, String absenceStatus) {
        this.studentNumber = new SimpleStringProperty(studentNumber);
        this.studentName = new SimpleStringProperty(studentName);
        this.studentSurname = new SimpleStringProperty(studentSurname);
        this.teorikAbsence = new SimpleIntegerProperty(teorikAbsence);
        this.praciteAbsence = new SimpleIntegerProperty(praciteAbsence);
        this.absenceStatus = new SimpleStringProperty(absenceStatus);
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
}
