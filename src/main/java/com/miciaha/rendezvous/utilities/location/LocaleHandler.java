package com.miciaha.rendezvous.utilities.location;

import com.miciaha.rendezvous.SchedulingApplication;
import javafx.beans.property.StringProperty;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

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
}
