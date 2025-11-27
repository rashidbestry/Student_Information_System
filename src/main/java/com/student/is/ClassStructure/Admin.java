package com.student.is.ClassStructure;
import com.student.is.Authentication.Authentication;
import com.student.is.DataManagement.Database;

public class Admin extends Personal {
    public boolean deleteLecture(Lecture object){
        return Database.deleteObject(object);
    }
    public boolean changeLectureData(Lecture object){
        return Database.changeObjectData(object);
    }
    public boolean deletePersonal(Personal object){
        return Database.deleteObject(object);
    }
    public boolean changePersonalData(Personal object) {
        return Database.changeObjectData(object);
    }
    public boolean changeUserPassword(String login,String password){

        return Authentication.changePassword(login, password);
    }
}
