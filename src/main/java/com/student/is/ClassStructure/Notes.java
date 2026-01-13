package com.student.is.ClassStructure;

public class Notes {
    int fall1;
    int fall2;
    int spring1;
    int spring2;

    public Notes(String notes){
        notes = notes.trim();

        // (00,00)(00,00)
        String[] terms = notes
                .replace("(", "")
                .split("\\)");

        String[] fall = terms[0].split(",");
        String[] spring = terms[1].split(",");

        this.fall1 = Integer.parseInt(fall[0]);
        this.fall2 = Integer.parseInt(fall[1]);
        this.spring1 = Integer.parseInt(spring[0]);
        this.spring2 = Integer.parseInt(spring[1]);
    }
    public Notes(){
        this.fall1 = 0;
        this.fall2 = 0;
        this.spring1 = 0;
        this.spring2 = 0;
    }
    @Override
    public String toString(){
        return String.format("(%02d,%02d)(%02d,%02d)", fall1, fall2, spring1, spring2);
    }

}
