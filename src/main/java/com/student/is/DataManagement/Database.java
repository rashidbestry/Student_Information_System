package com.student.is.DataManagement;
import com.student.is.Authentication.Authentication;
import com.student.is.Authentication.Encryption;
import com.student.is.ClassStructure.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;


public class Database {

    public static ArrayList<Personal> personalList = new ArrayList<>();
    public static ArrayList<Lecture> lectureList = new ArrayList<>();
    public static ArrayList<Student> studentList = new ArrayList<>();
    public static ArrayList<Student> findedStudentList = new ArrayList<>();
    public static ArrayList<Personal> findedPersonalList = new ArrayList<>();
    public static ArrayList<Lecture> findedLectureList = new ArrayList<>();
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        createStudentList();
        createLectureList();
        createPersonalList();
        int b=0;
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

            while (!(line = br.readLine()).equals("##stuend")){
                String[] temp = line.split("[*]");
                Student stu = new Student();
                stu.stuId = temp[0];
                stu.firstName = temp[1];
                stu.lastName = temp[2];
                stu.bornDate = temp[3];
                stu.classYear = Integer.parseInt(temp[4]);
                stu.faculty = temp[5];
                stu.notes = new Notes(temp[6]);
                stu.LectureAbsence(temp[7]);
                stu.LectureNotes(temp[7]);
                stu.phoneNo = temp[8];
                stu.email = temp[9];
                stu.address = temp[10];
                stu.calculateGpa();
                stu.createLectures();
                Database.studentList.add(stu);
            }
            br.close();
        }
        catch (IOException e){
            System.out.println("Error reading file!" + e);
        }
    }
    public static Student createStudentUser(String login){
        for (int i = 0; i < studentList.size();i++){
            if (studentList.get(i).stuId.equals(login.split("@")[0]))
                return studentList.get(i);
        } return null;
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
                if (line.equals("&&perend")){
                    break;
                }
                String[] temp = line.split("[*]");
                Personal per = new Personal();
                per.perId = temp[0];
                per.name = temp[1];
                per.surname = temp[2];
                per.title = temp[3];
                per.email = temp[4];
                per.web = temp[5];
                per.officehours = temp[6];
                per.createLectures();
                personalList.add(per);
            }
            br.close();
        }
        catch (IOException e){
            System.out.println("Error reading file!" + e);
        }

    }
    public static Personal createPersonalUser(String login){
        for (int i = 0; i < personalList.size();i++){
            if (personalList.get(i).email.equals(login))
                return personalList.get(i);
        } return null;
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
                if (line.equals("%%lectureend")){
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
                lec.lectureTeacher = temp[10];
                lec.lectureSeason = temp[11];
                lectureList.add(lec);
            }
            br.close();
        }
        catch (IOException e){
            System.out.println("Error reading file!" + e);
        }

    }
    public static boolean deleteObject(Object object){
        if (object instanceof Student){
            try  {
                BufferedReader br = new BufferedReader(new FileReader("src/main/resources/com/student/is/database/temp.bin"));
                BufferedWriter wr = new BufferedWriter(new FileWriter("src/main/resources/com/student/is/database/temp_temp.bin"));
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.split("[*]")[0].equals(((Student) object).stuId))
                        continue;
                    wr.write(line+"\n");
                }
                br.close();
                wr.close();
                File f1 = new File("src/main/resources/com/student/is/database/temp.bin");
                f1.delete();
                File f2 = new File("src/main/resources/com/student/is/database/temp_temp.bin");
                f2.renameTo(new File("src/main/resources/com/student/is/database/temp.bin"));
                Database.studentList.remove(object);
            }
            catch (IOException e){
                System.out.println("Error reading file!" + e);
            } return true;

        }
        else if (object instanceof Lecture) {
            try {
                BufferedReader br = new BufferedReader(new FileReader("src/main/resources/com/student/is/database/temp.bin"));
                BufferedWriter wr = new BufferedWriter(new FileWriter("src/main/resources/com/student/is/database/temp_temp.bin"));
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.split("[*]")[0].equals(((Lecture) object).lectureCode))
                        continue;
                    wr.write(line + "\n");
                }
                br.close();
                wr.close();
                File f1 = new File("src/main/resources/com/student/is/database/temp.bin");
                f1.delete();
                File f2 = new File("src/main/resources/com/student/is/database/temp_temp.bin");
                f2.renameTo(new File("src/main/resources/com/student/is/database/temp.bin"));
                Database.lectureList.remove(object);

            } catch (IOException e) {
                System.out.println("Error reading file!" + e);
            }return true;

        }
        else if (object instanceof Personal){
            try {
                BufferedReader br = new BufferedReader(new FileReader("src/main/resources/com/student/is/database/temp.bin"));
                BufferedWriter wr = new BufferedWriter(new FileWriter("src/main/resources/com/student/is/database/temp_temp.bin"));
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.split("[*]")[0].equals(((Personal) object).perId))
                        continue;
                    wr.write(line + "\n");
                }
                br.close();
                wr.close();
                File f1 = new File("src/main/resources/com/student/is/database/temp.bin");
                f1.delete();
                File f2 = new File("src/main/resources/com/student/is/database/temp_temp.bin");
                f2.renameTo(new File("src/main/resources/com/student/is/database/temp.bin"));
                Database.personalList.remove(object);

            } catch (IOException e) {
                System.out.println("Error reading file!" + e);
            } return true;

        }
        return false;
    }
    public static boolean changeObjectData(Object object) {
        if (object instanceof Student) {
            try {
                BufferedReader br = new BufferedReader(new FileReader("src/main/resources/com/student/is/database/temp.bin"));
                BufferedWriter wr = new BufferedWriter(new FileWriter("src/main/resources/com/student/is/database/temp_temp.bin"));
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.split("[*]")[0].equals(((Student) object).stuId)) {
                        wr.write(((Student) object).stuId + "*" + ((Student) object).firstName + "*" + ((Student) object).lastName + "*" + ((Student) object).bornDate + "*" + ((Student) object).classYear + "*" + ((Student) object).faculty + "*" + ((Student) object).getStringNotes() + "*" + ((Student) object).getLecturesToString() + "*" + ((Student) object).phoneNo + "*" + ((Student) object).email + "*" + ((Student) object).address + "\n");
                        continue;
                    }
                    wr.write(line + "\n");
                }
                br.close();
                wr.close();
                File f1 = new File("src/main/resources/com/student/is/database/temp.bin");
                f1.delete();
                File f2 = new File("src/main/resources/com/student/is/database/temp_temp.bin");
                f2.renameTo(new File("src/main/resources/com/student/is/database/temp.bin"));
            } catch (IOException e) {
                System.out.println("Error reading file!" + e);
            } finally {
                return true;
            }
        } else if (object instanceof Lecture) {
            try {
                BufferedReader br = new BufferedReader(new FileReader("src/main/resources/com/student/is/database/temp.bin"));
                BufferedWriter wr = new BufferedWriter(new FileWriter("src/main/resources/com/student/is/database/temp_temp.bin"));
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.split("[*]")[0].equals(((Lecture) object).lectureCode))
                        wr.write(((Lecture) object).lectureCode+"*"+((Lecture) object).lectureName+"*"+Boolean.toString(((Lecture) object).lectureMandatory)+"*"+((Lecture) object).lectureCredit+"*"+((Lecture) object).lectureAKTS+"*"+((Lecture) object).lectureClass+"*"+((Lecture) object).lectureLang+"*"+((Lecture) object).lectureType+"*"+((Lecture) object).lectureTheory+"*"+((Lecture) object).lectureApplication+"*"+((Lecture) object).lectureTeacher+"*"+((Lecture) object).lectureSeason+"\n");
                    wr.write(line + "\n");
                }
                br.close();
                wr.close();
                File f1 = new File("src/main/resources/com/student/is/database/temp.bin");
                f1.delete();
                File f2 = new File("src/main/resources/com/student/is/database/temp_temp.bin");
                f2.renameTo(new File("src/main/resources/com/student/is/database/temp.bin"));

            } catch (IOException e) {
                System.out.println("Error reading file!" + e);
            } finally {
                return true;
            }
        } else if (object instanceof Personal) {
            try {
                BufferedReader br = new BufferedReader(new FileReader("src/main/resources/com/student/is/database/temp.bin"));
                BufferedWriter wr = new BufferedWriter(new FileWriter("src/main/resources/com/student/is/database/temp_temp.bin"));
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.split("[*]")[0].equals(((Personal) object).perId))
                        wr.write(((Personal) object).perId + "*" + ((Personal) object).name + "*" + ((Personal) object).surname + "*" + ((Personal) object).title + "*" + ((Personal) object).email + "*" + ((Personal) object).web + "*" + ((Personal) object).officehours + "\n");

                    wr.write(line + "\n");

                }
                br.close();
                wr.close();
                File f1 = new File("src/main/resources/com/student/is/database/temp.bin");
                f1.delete();
                File f2 = new File("src/main/resources/com/student/is/database/temp_temp.bin");
                f2.renameTo(new File("src/main/resources/com/student/is/database/temp.bin"));

            } catch (IOException e) {
                System.out.println("Error reading file!" + e);
            } finally {
                return true;
            }
        }
        return false;
    }
    public static boolean createAuth(Object object) {

        String DEFAULT_PASSWORD = "827ccb0eea8a706c4c34a16891f84e7b";

        File file = new File("src/main/resources/com/student/is/database/auth.bin");
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Dosya okuma hatası: " + e.getMessage());
            return false;
        }

        String newLine;

        if (object instanceof Personal) {
            Personal p = (Personal) object;
            newLine = p.getEmail() + " " +DEFAULT_PASSWORD;

            // personnelend ÜSTÜNE ekle
            int index = lines.indexOf("personnelend");
            if (index == -1) return false;

            lines.add(index, newLine);

        } else if (object instanceof Student) {
            Student s = (Student) object;
            newLine = s.getStuId() + "@ogr.inonu.edu.tr " + DEFAULT_PASSWORD;

            // personnelend ALTINA ekle
            int index = lines.indexOf("personnelend");
            if (index == -1) return false;

            lines.add(newLine);

        } else {
            return false;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (String l : lines) {
                bw.write(l);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Dosya yazma hatası: " + e.getMessage());
            return false;
        }

        return true;
    }
    public static boolean createObject(Object object){
        if (object instanceof Student) {
            try {
                createAuth((Student) object);
                ////------------------LECTUREINFO----------------------////
                StringBuilder sb =new StringBuilder();

                for(Lecture lecture:((Student) object).lectures){
                    String code = lecture.getLectureCode();
                    String absence = ((Student) object).getStuAbsence().get(code);
                    String notes = ((Student) object).getStuNotes().get(code);
                    String[] note =notes.split(",");

                    sb.append(code).append("(").append(absence).append(")")
                            .append("(").append(note[0]).append(")")
                            .append("(").append(note[1]).append(")")
                            .append("/");
                }
                sb.deleteCharAt(sb.length()-1); // sondaki '/' sil
                String lectureInfo = sb.toString();
                ////-------------------------------------------------------------------/////

                BufferedReader br = new BufferedReader(new FileReader("src/main/resources/com/student/is/database/temp.bin"));
                BufferedWriter wr = new BufferedWriter(new FileWriter("src/main/resources/com/student/is/database/temp_temp.bin"));
                String line;
                while ((line = br.readLine()) != null) {
                    if ((line.equals("##stuend"))) {
                        wr.write(((Student) object).stuId + "*" + ((Student) object).firstName + "*" + ((Student) object).lastName + "*" + ((Student) object).bornDate + "*" + String.valueOf(((Student) object).classYear) + "*" + ((Student) object).faculty + "*" + ((Student) object).notes.toString() + "*" +  lectureInfo + "*" + ((Student) object).phoneNo + "*" + ((Student) object).email + "*" +((Student) object).address + " "+ "\n");
                        wr.write("##stuend\n");
                        break;
                    }
                    wr.write(line + "\n");
                }
                br.close();
                wr.close();
                File f1 = new File("src/main/resources/com/student/is/database/temp.bin");
                f1.delete();
                File f2 = new File("src/main/resources/com/student/is/database/temp_temp.bin");
                f2.renameTo(new File("src/main/resources/com/student/is/database/temp.bin"));

            } catch (IOException e) {
                System.out.println("Error reading file!" + e);
            }  return true;

        } else if (object instanceof Lecture) {
            try {
                BufferedReader br = new BufferedReader(new FileReader("src/main/resources/com/student/is/database/temp.bin"));
                BufferedWriter wr = new BufferedWriter(new FileWriter("src/main/resources/com/student/is/database/temp_temp.bin"));
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.split("[*]")[0].equals("%%lectureend")) {
                        wr.write(((Lecture) object).lectureCode + "*" + ((Lecture) object).lectureName + "*" + Boolean.toString(((Lecture) object).lectureMandatory) + "*" + ((Lecture) object).lectureCredit + "*" + ((Lecture) object).lectureAKTS + "*" + ((Lecture) object).lectureClass + "*" + ((Lecture) object).lectureLang + "*" + ((Lecture) object).lectureType + "*" + ((Lecture) object).lectureTheory + "*" + ((Lecture) object).lectureApplication + "*" + ((Lecture) object).lectureTeacher + "*" + ((Lecture) object).lectureSeason + "\n");
                        wr.write("%%lectureend\n\n");
                        continue;
                    }
                    wr.write(line + "\n");
                }
                br.close();
                wr.close();
                File f1 = new File("src/main/resources/com/student/is/database/temp.bin");
                f1.delete();
                File f2 = new File("src/main/resources/com/student/is/database/temp_temp.bin");
                f2.renameTo(new File("src/main/resources/com/student/is/database/temp.bin"));

            } catch (IOException e) {
                System.out.println("Error reading file!" + e);
            } return true;

        } else if (object instanceof Personal) {
            try {
                BufferedReader br = new BufferedReader(new FileReader("src/main/resources/com/student/is/database/temp.bin"));
                BufferedWriter wr = new BufferedWriter(new FileWriter("src/main/resources/com/student/is/database/temp_temp.bin"));
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.split("[*]")[0].equals("&&perend")) {
                        wr.write((((Personal) object).perId + "*" + ((Personal) object).name + "*" + ((Personal) object).surname + "*" + ((Personal) object).title + "*" + ((Personal) object).email + "*" + ((Personal) object).web + "*" + ((Personal) object).officehours + "\n"));
                        wr.write("&&perend\n\n");
                        continue;
                    }
                    wr.write(line + "\n");
                }
                br.close();
                wr.close();
                File f1 = new File("src/main/resources/com/student/is/database/temp.bin");
                f1.delete();
                File f2 = new File("src/main/resources/com/student/is/database/temp_temp.bin");
                f2.renameTo(new File("src/main/resources/com/student/is/database/temp.bin"));

            } catch (IOException e) {
                System.out.println("Error reading file!" + e);
            } return true;

        }
        return false;
    }
    public static void searchInStudentData(ArrayList<Student> student,String input){
        findedStudentList.clear();
        for (Student stu : student){
            if (input.toLowerCase().contains(stu.firstName.toLowerCase())){
                findedStudentList.add(stu);
                continue;
            }
            if (input.toLowerCase().contains(stu.lastName.toLowerCase())){
                findedStudentList.add(stu);
                continue;
            }
            if (input.contains(stu.stuId)){
                findedStudentList.add(stu);
                continue;
            }
            if (input.toLowerCase().contains(stu.faculty.toLowerCase())) {
                findedStudentList.add(stu);
                continue;
            }
            if (input.contains(stu.bornDate.split("[.]")[2])){
                findedStudentList.add(stu);
                continue;
            }
            if (input.contains(stu.phoneNo)){
                findedStudentList.add(stu);
                continue;
            }
            if (input.contains(stu.email)){
                findedStudentList.add(stu);
                continue;
            }
            if (input.toLowerCase().contains(stu.address.toLowerCase())){
                findedStudentList.add(stu);
                continue;
            }



        }



    }
    public static void searchInPersonalData(ArrayList<Personal> personal,String input){
        findedPersonalList.clear();
        for (Personal per : personal){
            if (input.toLowerCase().contains(per.name.toLowerCase())){
                findedPersonalList.add(per);
                continue;
            }
            if (input.toLowerCase().contains(per.surname.toLowerCase())){
                findedPersonalList.add(per);
                continue;
            }
            if (input.toLowerCase().contains(per.web.toLowerCase())){
                findedPersonalList.add(per);
                continue;
            }
            if (input.toLowerCase().contains(per.perId.toLowerCase())){
                findedPersonalList.add(per);
                continue;
            }
            if (input.toLowerCase().contains(per.title.toLowerCase())){
                findedPersonalList.add(per);
                continue;
            }
        }



    }
    public static void searchInLectureData(ArrayList<Lecture> lecture,String input){

        findedLectureList.clear();
        for (Lecture lec : lecture){
            if (input.toLowerCase().contains(lec.lectureName.toLowerCase())){
                findedLectureList.add(lec);
                continue;
            }
            if (input.toLowerCase().contains(lec.lectureCode.toLowerCase())){
                findedLectureList.add(lec);
                continue;
            }
            if (input.toLowerCase().contains(lec.lectureSeason.toLowerCase())){
                findedLectureList.add(lec);
                continue;
            }
            if (input.toLowerCase().contains(lec.lectureTeacher.toLowerCase())){
                findedLectureList.add(lec);
                continue;
            }
            if (input.toLowerCase().contains(lec.lectureType.toLowerCase())){
                findedLectureList.add(lec);
                continue;
            }
        }

    }
    public static String getNewStudentId(){
        String result = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/com/student/is/database/temp.bin"));
            String line = "";
            while ((line = br.readLine()) != null) {
                if (line.split("[*]")[0].equals("##stuId"))
                    break;
            }
            String tempLine = "";
            while ((line = br.readLine()) != null) {
                if (line.equals("##stuend")) {
                    line = tempLine;
                    break;
                }
                tempLine = br.readLine();
                if (tempLine.equals("##stuend")) {
                    break;
                }
            }

            br.close();
            result = String.valueOf(Long.parseLong(line.split("[*]")[0])+1);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "0"+result;
    }
    public static String getNewPersonalId(){
        long randomNumber = RandomGenerator.getDefault().nextLong(24234364355L,84234364355L);
        return String.valueOf(randomNumber);
    }

}






