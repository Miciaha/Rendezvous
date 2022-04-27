package com.miciaha.rendezvous.utilities.location;

import com.miciaha.rendezvous.SchedulingApplication;
import javafx.beans.property.StringProperty;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LocaleHandler {

    public static String getLanguage(){
        return Locale.getDefault().getLanguage();
    }

    public static void setText(StringProperty text, String property){
        try {
            text.setValue(textProperties().getProperty(property));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // FUTURE ENHANCEMENT: Default to english if the system language does not have a property file
    public static Properties textProperties() throws IOException{
        FileInputStream fileInputStream = null;
        Properties prop = null;
        String directory = "src\\main\\java\\com\\miciaha\\rendezvous\\";
        String fileName = directory + "text_" + getLanguage() + ".properties";

        try{
            fileInputStream = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fileInputStream);
        } catch (IOException e){
            e.printStackTrace();
        } finally{
            assert fileInputStream != null;
            fileInputStream.close();
        }
        return prop;
    }

    public static LocalDateTime gmtToLocal(Date date){
        return (date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    }

    public static Date localToGMT(LocalDateTime date){
        return (Date.from(date.toInstant(ZoneOffset.UTC)));
    }
}
