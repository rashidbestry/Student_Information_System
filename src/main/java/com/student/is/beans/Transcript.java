package com.student.is.beans;

import com.student.is.Authentication.Authentication;
import com.student.is.ClassStructure.Lecture;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignImage;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Transcript {
    public static void doTranscript() throws JRException {
        StudentDetails stu = new StudentDetails();
        String reportPath = "src/main/java/com/student/is/beans/Transcript.jrxml";
        JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
        fixImagePaths(jasperDesign);

        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        List<StudentDetails> studentDetailsList = new ArrayList<StudentDetails>();
        studentDetailsList.add(stu);
        JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(studentDetailsList);

        List<LectureDetails> lectureDetailsList = new ArrayList<LectureDetails>();
        for (Lecture lec : Authentication.currentStudentUser.getLectures()){
            LectureDetails lecture = new LectureDetails(lec.lectureName,lec.lectureCode,String.valueOf(lec.lectureCredit),lec.lectureAKTS,lec.getLectureSeason());
            lectureDetailsList.add(lecture);
        }
        JRBeanCollectionDataSource tableDataSource = new JRBeanCollectionDataSource(lectureDetailsList);

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("TABLE_DATA_SOURCE", tableDataSource);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,datasource);

        JasperExportManager.exportReportToPdfFile(jasperPrint,"src/main/resources/com/student/is/pdf/transcript.pdf");

        System.out.println("PDF Created");
    }
    private static void fixImagePaths(JasperDesign design) {
        // Check common bands for the logo image
        fixBand(design.getTitle());
        fixBand(design.getPageHeader());
    }
    private static void fixBand(JRBand band) {
        if (band == null) return;
        for (JRElement element : band.getElements()) {
            if (element instanceof JRDesignImage) {
                JRDesignImage img = (JRDesignImage) element;
                JRExpression exp = img.getExpression();
                if (exp != null) {
                    String text = exp.getText();
                    // If the image expression points to the logo, update it to the absolute file path
                    if (text != null && text.contains("MainApp_logo_backRem.png")) {
                        File imageFile = new File("src/main/resources/com/student/is/images/MainApp_logo_backRem.png");
                        // Set the new valid path (escaped for Java string)
                        String newPath = "\"" + imageFile.getAbsolutePath().replace("\\", "/") + "\"";
                        ((JRDesignExpression) exp).setText(newPath);
                    }
                }
            }
        }
    }
}
