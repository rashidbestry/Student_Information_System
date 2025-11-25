package com.student.is.Authentication;

public class Authentication {
    public static String currentUser;
    public static boolean authenticated;


    public boolean chekAuth(String login , String password) {
        String temp = DataBase_File_ogrenci(login);
        if (temp.equals(password)) {
            return true;
        } else return false;
    }

    public String DataBase_File_ogrenci(String ogr_no ){
        return "1234" ; // şifreyi return edecek
    }
}

