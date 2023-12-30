package API;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class BirthDay {
    public static void main(String[] args) throws ParseException {
        //JDK7
        String birthday = "2000年1月2日";
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy年MM月dd日");
        Date data = simpleDateFormat.parse(birthday);
        long birthdaytime = data.getTime();
        long todaytime = System.currentTimeMillis();
        long time =todaytime - birthdaytime;
        System.out.println(time/1000/60/60/24);

        //JDK8
        LocalDate localDate = LocalDate.of(2000,1,1);
        LocalDate nowdate = LocalDate.now();
        long days = ChronoUnit.DAYS.between(localDate,nowdate);
        System.out.println(days);
    }
}
