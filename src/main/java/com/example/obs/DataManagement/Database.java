package com.example.obs.DataManagement;

import com.example.obs.ClassStructure.Lecture;
import com.example.obs.ClassStructure.Personel;
import com.example.obs.ClassStructure.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Database {
    LinkedList<Personel> personalList;
    LinkedList<Lecture> lectureList;
    LinkedList<Student> studentList;
    ArrayList<Student> findedStudentList;

    public static void createTemp() {
        try  {
            BufferedReader br = new BufferedReader(new FileReader("data/data.bin"));
            BufferedWriter wr = new BufferedWriter(new FileWriter("data/temp.bin"));
            String line;
            while ((line = br.readLine()) != null){
                wr.write(line+"\n");
            }
            br.close();
            wr.close();
        }
        catch (IOException e){
            System.out.println("Error reading file!" + e);
        }

    }

    public static void createStudentList(){
        try  {
            BufferedReader br = new BufferedReader(new FileReader("data/data.bin"));
            String line;
            String regex = "[*]";

            while ((line = br.readLine()) != null){
                String[] temp = line.split(regex);
                for (int i = 0; i < temp.length; i++) {
                    if (temp[i]=="Ali"){
                        System.out.println(Arrays.toString(temp));
                    }

                }
            }
            br.close();
        }
        catch (IOException e){
            System.out.println("Error reading file!" + e);
        }
    }





}
