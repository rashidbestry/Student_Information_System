package com.student.is.DataManagement;
import com.student.is.ClassStructure.Lecture;
import com.student.is.ClassStructure.Personel;
import com.student.is.ClassStructure.Student;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;


public class Database {
    public static  ArrayList<Personel> personalList;
    public static ArrayList<Lecture> lectureList;
    public static ArrayList<Student> studentList = new ArrayList<>();
    public static ArrayList<Student> findedStudentList;


    public static void main(String[] args){
        createStudentList();
        print();
    }
    Database(){
        createStudentList();
    }

    public static void createTemp() {
        try  {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/com/student/is/database/data.bin"));
            BufferedWriter wr = new BufferedWriter(new FileWriter("src/main/resources/com/student/is/database/temp.bin"));
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
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/com/student/is/database/data.bin"));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.split("[*]")[0].equals("##stuNum"))
                        break;
            }

            while ((line = br.readLine()) != null){
                if (line.equals("")){
                    break;
                }
                String[] temp = line.split("[*]");
                Student stu = new Student();
                stu.stuNum = Long.parseLong(temp[0]);
                stu.firstName = temp[1];
                stu.lastName = temp[2];
                stu.age = Integer.parseInt(temp[3]);
                stu.classNo = Integer.parseInt(temp[4]);
                stu.faculty = temp[5];
                stu.gpa = Double.parseDouble(temp[6]);
                Database.studentList.add(stu);
            }
            br.close();
        }
        catch (IOException e){
            System.out.println("Error reading file!" + e);
        }
    }

    public static void print(){
        System.out.println(lectureList.toString());

    }





}
