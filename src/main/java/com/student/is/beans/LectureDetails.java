package com.student.is.beans;

import com.student.is.Authentication.Authentication;

public class LectureDetails {
    public String lectureName;
    public String lectureCode;
    public String lectureCredit;
    public int lectureAKTS;
    public String lectureNoteABCDF;

    public LectureDetails(String lectureName, String lectureCode, String lectureCredit, int lectureAKTS, String lectureNoteABCDF) {
        this.lectureName = lectureName;
        this.lectureCode = lectureCode;
        this.lectureCredit = lectureCredit;
        this.lectureAKTS = lectureAKTS;
        this.lectureNoteABCDF = lectureNoteABCDF;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public String getLectureCode() {
        return lectureCode;
    }

    public void setLectureCode(String lectureCode) {
        this.lectureCode = lectureCode;
    }

    public String getLectureCredit() {
        return lectureCredit;
    }

    public void setLectureCredit(String lectureCredit) {
        this.lectureCredit = lectureCredit;
    }

    public int getLectureAKTS() {
        return lectureAKTS;
    }

    public void setLectureAKTS(int lectureAKTS) {
        this.lectureAKTS = lectureAKTS;
    }

    public String getLectureNoteABCDF() {
        double average = ((Integer.parseInt(Authentication.currentStudentUser.stuNotes.get(lectureCode).split(",")[0]) + Integer.parseInt(Authentication.currentStudentUser.stuNotes.get(lectureCode).split(",")[1]) )/2);
        if (average >= 90.0) {
            this.lectureNoteABCDF = "AA";
        } else if (average >= 85.0) {
            this.lectureNoteABCDF = "BA";
        } else if (average >= 80.0) {
            this.lectureNoteABCDF = "BB";
        } else if (average >= 75.0) {
            this.lectureNoteABCDF = "CB";
        } else if (average >= 70.0) {
            this.lectureNoteABCDF = "CC";
        } else if (average >= 60.0) {
            this.lectureNoteABCDF = "DC";
        } else if (average >= 50.0) {
            this.lectureNoteABCDF = "DD";
        } else {
            this.lectureNoteABCDF = "FF";
        }

        return lectureNoteABCDF;
    }

    public void setLectureNoteABCDF(String lectureNoteABCDF) {
        this.lectureNoteABCDF = lectureNoteABCDF;
    }

}
