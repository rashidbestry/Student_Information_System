package com.student.is.beans;

import com.student.is.Authentication.Authentication;
import com.student.is.ClassStructure.Lecture;

import java.time.LocalDate;

public class StudentDetails {
    public String nameSurname;
	public String sumGpa;
	public String studentNumber;
	public String faculty;
	public String department;
	public String program;
	public String enrollDate;
	public String osym;
	public String degree;
	public String isActive;
	public String pdfDate;
    public String sumAKTS;

    public StudentDetails() {
        this.nameSurname = Authentication.currentStudentUser.firstName + " " + Authentication.currentStudentUser.lastName;
        this.sumGpa = String.valueOf(Authentication.currentStudentUser.gpa);
        this.studentNumber = Authentication.currentStudentUser.stuId;
        this.faculty = "Mühendislik Fakültesi";
        this.department = "Bilgisayar Mühendisliği Bölümü";
        this.program = Authentication.currentStudentUser.faculty;
        this.enrollDate = "26.08."+String.valueOf(2025-Authentication.currentStudentUser.classYear);
        this.osym = "YKS";
        this.degree = "Lisans";
        this.isActive = "Aktif";
        LocalDate currentDate = LocalDate.now();
        this.pdfDate = currentDate.toString();
        int sum = 0;
        for (Lecture lec : Authentication.currentStudentUser.lectures)
            sum += lec.lectureAKTS;

        this.sumAKTS = String.valueOf(sum);

    }

    public String getNameSurname() {
        return nameSurname;
    }

    public String getSumAKTS() {
        return sumAKTS;
    }

    public void setSumAKTS(String sumAKTS) {
        this.sumAKTS = sumAKTS;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getSumGpa() {
        return this.sumGpa;
    }

    public void setSumGpa(String sumGpa) {
        this.sumGpa = sumGpa;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(String enrollDate) {
        this.enrollDate = enrollDate;
    }

    public String getOsym() {
        return osym;
    }

    public void setOsym(String osym) {
        this.osym = osym;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getPdfDate() {
        return pdfDate;
    }

    public void setPdfDate(String pdfDate) {
        this.pdfDate = pdfDate;
    }
}
