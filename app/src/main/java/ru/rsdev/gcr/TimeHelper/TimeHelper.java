package ru.rsdev.gcr.TimeHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by rsdev on 25.01.2016.
 */
public class TimeHelper {

    public String getCurrentTime(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String formattedDate = sdf.format(calendar.getTime());
        return formattedDate;
    }

}
