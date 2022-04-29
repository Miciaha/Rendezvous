package com.miciaha.rendezvous.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type Formatted values.
 */
public class FormattedValues {

    /**
     * Current date time string.
     *
     * @return the string
     */
    public static String currentDateTime() {
        Date date = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return formatter.format(date);
    }
}
