package main.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by virus on 16.04.2017.
 */
public class logFile {
    public static void write(String log){
        File file = new File("C:\\Users\\virus\\IdeaProjects\\project_of_\\temp\\log.txt"); //Определяем файл
        try {
            if(!file.exists()){ //проверяем, что если файл не существует то создаем его
                file.createNewFile();
            }
            FileWriter out = new FileWriter(file.getAbsoluteFile(),true);  //PrintWriter обеспечит возможности записи в файл
            try {
                Date dateNow = new Date();
                SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                out.write(formatForDateNow.format(dateNow) + " //// " + log + "\n");

            } finally {
                out.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
