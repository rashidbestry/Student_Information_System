package com.student.is.ClassStructure;

import com.student.is.DataManagement.Database;

public class Notes {
    int fall1;
    int fall2;
    int spring1;
    int spring2;

    public Notes(String notes){
        this.fall1 = Integer.parseInt(notes.substring(1,3));
        this.fall2 = Integer.parseInt(notes.substring(4,6));
        this.spring1 = Integer.parseInt(notes.substring(8,10));
        this.spring2 = Integer.parseInt(notes.substring(11,13));
    }

}

