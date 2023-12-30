package API;

import java.time.LocalDate;
import java.util.Calendar;

public class CalendarYear {
    public static void main(String[] args) {
        //JDK7
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.MARCH,1); //月份里的2代表三月,为了防止混淆，建议使用静态变量
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(day);

        //JDK8
        LocalDate localDate = LocalDate.of(2020,3,1);  //这里的3就是指三月
        LocalDate localDate1 = localDate.minusDays(1);   //原来的LocalDate对象是不会发生变化的,必须用新创建的对象接受
        int day1 = localDate1.getDayOfMonth();
        System.out.println(day1);

        System.out.println(localDate.isLeapYear()); //JDK8里的直接判断方法
    }
}
