package com.student.is.DataManagement;
import com.student.is.ClassStructure.Lecture;
import com.student.is.ClassStructure.Personal;
import com.student.is.ClassStructure.Student;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Database {
    public static  ArrayList<Personal> personalList = new ArrayList<>();
    public static ArrayList<Lecture> lectureList = new ArrayList<>();
    public static ArrayList<Student> studentList = new ArrayList<>();
    public static ArrayList<Student> findedStudentList = new ArrayList<>();


    public static void main(String[] args){
        createStudentList();
        createLectureList();
        createPersonalList();
        byte end=0;
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
                if (line.split("[*]")[0].equals("##stuId"))
                        break;
            }

            while ((line = br.readLine()) != null){
                if (line.equals("")){
                    break;
                }
                String[] temp = line.split("[*]");
                Student stu = new Student();
                stu.stuId = temp[0];
                stu.firstName = temp[1];
                stu.lastName = temp[2];
                stu.bornDate = temp[3];
                stu.classYear = Integer.parseInt(temp[4]);
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
                lec.lectureMandatory = Boolean.parseBoolean(temp[2]);
                lec.lectureCredit = Integer.parseInt(temp[3]);
                lec.lectureAKTS = Integer.parseInt(temp[4]);
                lec.lectureClass = Integer.parseInt(temp[5]);
                lec.lectureLang = temp[6];
                lec.lectureType = temp[7];
                lec.lectureTheory = Integer.parseInt(temp[8]);
                lec.lectureApplication = Integer.parseInt(temp[9]);
                lec.lectureTeacher = Long.parseLong(temp[10]);
                lectureList.add(lec);
            }
            br.close();
        }
        catch (IOException e){
            System.out.println("Error reading file!" + e);
        }

    }
    public static void createPersonalList(){
        try  {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/com/student/is/database/data.bin"));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.split("[*]")[0].equals("&&perId"))
                    break;
            }

            while ((line = br.readLine()) != null){
                if (line.equals("")){
                    break;
                }
                String[] temp = line.split("[*]");
                Personal per = new Personal();
                per.perId = Long.parseLong(temp[0]);
                per.name = temp[1];
                per.surname = temp[2];
                per.title = temp[3];
                per.email = temp[4];
                per.web = temp[5];
                per.officehours = temp[6];
                personalList.add(per);
            }
            br.close();
        }
        catch (IOException e){
            System.out.println("Error reading file!" + e);
        }

    }






}
