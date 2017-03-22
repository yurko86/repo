package daystoday;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.text.ParseException;

/**
 *
 * @author iurii
 */
public class DaysToDay {

    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) throws ParseException {

        Date today = dateFormat.parse(dateFormat.format(new Date()));

        System.out.println("Difference is: " + calculateDateDiff(today, getDate()) + " day(s)");

    }

    static long calculateDateDiff(Date date1, Date date2) {
        long diff = Math.abs(date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000);

        return diff;

    }

    static Date getDate() {
        Scanner s = new Scanner(System.in);

        Date inputDate = new Date();

        boolean exitWhile = false;
        while (!exitWhile) {
            System.out.print("Please enter date (dd/MM/yyyy): ");

            inputDate = parseDate(s.nextLine());
            if (inputDate != null) {
                exitWhile = true;
            }
        }
        return inputDate;
    }

    static Date parseDate(String strDate) {
        Date DateDate;

        try {  
            if (strDate == null || !strDate.matches("^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$")) {
                throw new ParseException(strDate, 0);
            }

            DateDate = dateFormat.parse(strDate);
        } catch (ParseException e) {
            System.err.println("Wrong date format. Try again");
            DateDate = null;
        }
        return DateDate;
    }

}
