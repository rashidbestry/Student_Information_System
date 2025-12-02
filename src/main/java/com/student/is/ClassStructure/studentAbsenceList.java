package com.student.is.ClassStructure;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class studentAbsenceList {

       public StringProperty lectureCode;
       public StringProperty lectureName;
       public StringProperty lectureAbsence;
       public IntegerProperty lectureCredit;
       public IntegerProperty lectureEKTS;
       public IntegerProperty totalAbsence;

    public studentAbsenceList(String lectureCode, String lectureName, String lectureAbsence, int lectureCredit, int lectureEKTS, int totalAbsence) {
        this.lectureCode  = new SimpleStringProperty(lectureCode);
        this.lectureName = new SimpleStringProperty(lectureName);
        this.lectureAbsence = new SimpleStringProperty(lectureAbsence);
        this.lectureCredit = new SimpleIntegerProperty(lectureCredit);
        this.lectureEKTS = new SimpleIntegerProperty(lectureEKTS);
        this.totalAbsence = new SimpleIntegerProperty(totalAbsence);
    }

    public String getLectureCode() {
        return lectureCode.get();
    }

    public StringProperty lectureCodeProperty() {
        return lectureCode;
    }

    public String getLectureName() {
        return lectureName.get();
    }

    public StringProperty lectureNameProperty() {
        return lectureName;
    }

    public String getLectureAbsence() {
        return lectureAbsence.get();
    }

    public StringProperty lectureAbsenceProperty() {
        return lectureAbsence;
    }

    public int getLectureCredit() {
        return lectureCredit.get();
    }

    public IntegerProperty lectureCreditProperty() {
        return lectureCredit;
    }

    public int getLectureEKTS() {
        return lectureEKTS.get();
    }

    public IntegerProperty lectureEKTSProperty() {
        return lectureEKTS;
    }

    public int getTotalAbsence() {
        return totalAbsence.get();
    }

    public IntegerProperty totalAbsenceProperty() {
        return totalAbsence;
    }
}
