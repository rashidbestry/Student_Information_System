package com.student.is.ClassStructure;
import javafx.beans.property.*;

public class studentGradeList {

    public  StringProperty lectureCode;
    public  StringProperty lectureName;
    public  StringProperty lectureStatus;
    public StringProperty letterNote;
    public StringProperty status;
    public  DoubleProperty vizeNote;
    public  DoubleProperty finalNote;
    public  DoubleProperty makeUpNote;
    public  DoubleProperty averageNote;

    public  studentGradeList(String lectureCode,String lectureName,String lectureStatus,String letterNote,String status,Double vizeNote,Double finalNote ,Double makeUpNote,Double averageNote){
        this.lectureCode = new SimpleStringProperty(lectureCode);
        this.lectureName = new SimpleStringProperty(lectureName);
        this.lectureStatus = new SimpleStringProperty(lectureStatus);
        this.letterNote = new SimpleStringProperty(letterNote);
        this.status = new SimpleStringProperty(status);
        this.vizeNote = new SimpleDoubleProperty(vizeNote);
        this.finalNote = new SimpleDoubleProperty(finalNote);
        this.makeUpNote = new SimpleDoubleProperty(makeUpNote);
        this.averageNote = new SimpleDoubleProperty(averageNote);
    }

    public StringProperty LectureCodeProperty(){
        return lectureCode;

    }
    public StringProperty LectureNameProperty(){
        return lectureName;
    }
    public StringProperty LectureStatusProperty(){
        return lectureStatus;

    }
    public StringProperty letterNoteProperty(){
        return letterNote;
    }
    public StringProperty statusProperty(){
        return status;
    }
    public DoubleProperty vizeNoteProperty(){
        return vizeNote;
    }
    public DoubleProperty finalNoteProperty(){
        return finalNote;
    }
    public DoubleProperty makeUpNoteProperty(){
        return makeUpNote;
    }
    public DoubleProperty averageNoteProperty(){
        return averageNote;
    }

}
