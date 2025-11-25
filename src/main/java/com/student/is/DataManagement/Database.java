package com.student.is.DataManagement;
import com.student.is.ClassStructure.Lecture;
import com.student.is.ClassStructure.Personel;
import com.student.is.ClassStructure.Student;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;


public class Database {
    public static  ArrayList<Personel> personalList = new ArrayList<>();
    public static ArrayList<Lecture> lectureList = new ArrayList<>();
    public static ArrayList<Student> studentList = new ArrayList<>();
    public static ArrayList<Student> findedStudentList = new ArrayList<>();


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
    public static void saveTempToData() {
        try  {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/com/student/is/database/temp.bin"));
            BufferedWriter wr = new BufferedWriter(new FileWriter("src/main/resources/com/student/is/database/data.bin"));
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
    public static void deleteTemp() {
        try  {
            Path fileToDeletePath = Paths.get("src/main/resources/com/student/is/database/temp.bin");
            Files.delete(fileToDeletePath);
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

    public static void createLectureList(){
        try  {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/com/student/is/database/data.bin"));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.split("[*]")[0].equals("%%lecture code"))
                    break;
            }

            while ((line = br.readLine()) != null){
                if (line.equals("")){
                    break;
                }
                String[] temp = line.split("[*]");
                Lecture lec = new Lecture();
                lec.lectureCode = temp[0];
                lec.lectureName = temp[1];
                lec.lectureMandatory = Boolean.getBoolean(temp[2]);
                lec.lectureTheory = Integer.parseInt(temp[3]);
                lec.lectureApplication = Integer.parseInt(temp[4]);
                lec.lectureCredit = Integer.parseInt(temp[5]);
                lec.lectureAKTS = Integer.parseInt(temp[6]);
                lec.lectureClass = Integer.parseInt(temp[7]);
                lec.lectureLang = temp[8];
                lec.lectureType = temp[9];
                lec.lectureTeacher = Long.parseLong(temp[10]);
                lectureList.add(lec);
            }
            br.close();
        }
        catch (IOException e){
            System.out.println("Error reading file!" + e);
        }

    }






}
