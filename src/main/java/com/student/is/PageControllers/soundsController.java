package com.student.is.PageControllers;

import javafx.scene.media.AudioClip;

import java.net.URL;

public class soundsController {
    public void playErrorSound() {
        URL resource = getClass().getResource("/com/student/is/sounds/error.mp3");
        if (resource != null) {
            AudioClip audioClip = new AudioClip(resource.toExternalForm());
            audioClip.play();
        }
        else{
            System.out.println("resource == null");
        }
    }
    public void playSuccessSound() {
        URL resource = getClass().getResource("/com/student/is/sounds/success.mp3");
        if (resource != null) {
            AudioClip audioClip = new AudioClip(resource.toExternalForm());
            audioClip.play();
        }
        else{
            System.out.println("resource == null");
        }
    }
}
