package com.student.is.Authentication;

import java.io.*;

public class Authentication {
    public static String currentUser;
    public static boolean authenticated;

    public boolean checkStudentAuth(String login , String password) {
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
                    if (line.split(" ")[1].equals(password)){
                        currentUser = loginTemp;
                        authenticated = true;
                        return true;
                    }
                    return false;
                }
            }
            br.close();
        }
        catch (IOException e){
            System.out.println("Error reading file!" + e);
        }
        return false;
    }
    public boolean checkPersonnelAuth(String login , String password) {
        try  {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/com/student/is/database/auth.bin"));
            String line;
            while ((line = br.readLine()) != null){
                if (line.equals("personnelend"))
                    break;
                String loginTemp = line.split(" ")[0];
                if (loginTemp.equals(login)) {
                    if (line.split(" ")[1].equals(password)){
                        currentUser = loginTemp;
                        authenticated = true;
                        return true;
                    }
                    return false;
                }
            }
            br.close();
        }
        catch (IOException e){
            System.out.println("Error reading file!" + e);
        }
        return false;
    }
}

