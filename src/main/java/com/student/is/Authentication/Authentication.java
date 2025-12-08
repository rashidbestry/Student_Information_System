package com.student.is.Authentication;

import com.student.is.ClassStructure.Admin;
import com.student.is.ClassStructure.Personal;
import com.student.is.ClassStructure.Student;
import com.student.is.DataManagement.Database;
import net.sf.jasperreports.engine.JRException;
import java.io.*;
import java.security.NoSuchAlgorithmException;

public class Authentication {
    public static Student currentStudentUser;
    public static Personal currentPersonalUser;
    public static Admin currentAdminUser;

    public static boolean checkStudentAuth(String login , String password) throws JRException {
        if (!login.contains("@")) {
            return false;
        } else if (login.split("@")[1].equals("inonu.edu.tr") || login.split("@")[1].equals("ogr.inonu.edu.tr")){
            try  {
                BufferedReader br = new BufferedReader(new FileReader("src/main/resources/com/student/is/database/auth.bin"));
                String line;
                while ((line = br.readLine()) != null){
                    if (line.equals("personnelend"))
                        break;
                }
                while ((line = br.readLine()) != null){
                    String loginTemp = line.split(" ")[0];
                    if (loginTemp.equals(login)) {
                        if (line.split(" ")[1].equals(Encryption.encryptString(password))){
                            currentStudentUser = Database.createStudentUser(login);
                            br.close();
                            return true;
                        }
                        return false;
                    }
                }
                br.close();
            }
            catch (IOException e){
                System.out.println("Error reading file!" + e);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            return false;
        }
        return false;
    }
    public static boolean checkPersonalAuth(String login , String password) {
        if (!login.contains("@")) {
            return false;
        }
        else if (login.split("@")[1].equals("inonu.edu.tr") || login.split("@")[1].equals("ogr.inonu.edu.tr")){
            try  {
                BufferedReader br = new BufferedReader(new FileReader("src/main/resources/com/student/is/database/auth.bin"));
                String line;
                while ((line = br.readLine()) != null){
                    if (line.equals("personnelend"))
                        break;
                    String loginTemp = line.split(" ")[0];
                    if (loginTemp.equals(login)) {
                        if (line.split(" ")[1].equals(Encryption.encryptString(password))){
                            currentPersonalUser = Database.createPersonalUser(login);
                            br.close();
                            return true;
                        }
                        br.close();
                        return false;
                    }
                }
                br.close();
            }
            catch (IOException e){
                System.out.println("Error reading file!" + e);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            return false;
        }
        return false;

    }
    public static boolean changePassword(String login,String password) {
        boolean status=false;
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/com/student/is/database/auth.bin"));
            BufferedWriter wr = new BufferedWriter(new FileWriter("src/main/resources/com/student/is/database/auth_2.bin"));
            String line;
            while ((line = br.readLine()) != null) {
                String loginTemp = line.split(" ")[0];
                if (loginTemp.equals(login)) {
                    line = loginTemp + " " + Encryption.encryptString(password);
                    wr.write(line + "\n");
                    status = true;
                    continue;
                }
                wr.write(line + "\n");
            }
            br.close();
            wr.close();
            File f1 = new File("src/main/resources/com/student/is/database/auth.bin");
            f1.delete();
            File f2 = new File("src/main/resources/com/student/is/database/auth_2.bin");
            f2.renameTo(new File("src/main/resources/com/student/is/database/auth.bin"));

        } catch (IOException e) {
            System.out.println("Error reading file!" + e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }return status;
    }
    public static boolean forgotPassword(String login){
        if (!login.contains("@")) {
            return false;
        }
        else if (login.split("@")[1].equals("inonu.edu.tr") || login.split("@")[1].equals("ogr.inonu.edu.tr")){

            try  {
                BufferedReader br = new BufferedReader(new FileReader("src/main/resources/com/student/is/database/auth.bin"));
                String line;
                while ((line = br.readLine()) != null){
                    if (line.equals("personnelend"))
                        break;
                }
                while ((line = br.readLine()) != null){
                    String loginTemp = line.split(" ")[0];
                    if (loginTemp.equals(login)) {
                        br.close();
                        return Mail.forgotPasswordMail(login);
                    }
                }
                br.close();
            }
            catch (IOException e){
            }
            return false;
        }
        return false;
    }
}

