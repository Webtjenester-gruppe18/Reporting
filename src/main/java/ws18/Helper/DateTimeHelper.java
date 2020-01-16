package ws18.Helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeHelper {

    public static long convertDateStringToMS(String dateFrom) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMMyyyy");
        long dateFromInMS = 0;

        try {
            Date date = simpleDateFormat.parse(dateFrom);
            dateFromInMS = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateFromInMS;
    }

}
