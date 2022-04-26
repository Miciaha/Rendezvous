package com.miciaha.rendezvous.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormattedValues {

    public static String currentDateTime(){
        Date date = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return formatter.format(date);
    }
}
