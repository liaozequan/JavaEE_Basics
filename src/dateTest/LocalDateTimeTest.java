package dateTest;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 新日期API：LocalDate、LocalTime、LocalDateTime
 * LocalDateTime使用频率更高
 * 1.new() 2.of() 3.getxxx() 4.withxxx() 5.plusxxx() 6.minusxxx()
 */
public class LocalDateTimeTest {
    @Test
    public void test1(){
        //now()获取当前的日期、时间
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDate:"+localDate);//2022-04-22
        System.out.println("localTime:"+localTime);//10:11:30.513
        System.out.println("localDateTime:"+localDateTime);//2022-04-22T10:11:30.513

        //of()指定日期、时间,没有偏移量，new Date(2022,12,10...)有偏移量
        LocalDateTime localDateTime1 = LocalDateTime.of(2022,4,21,12,24,35);
        System.out.println(localDateTime1);

        //getxxx()
        System.out.println(localDateTime1.getDayOfMonth());
        System.out.println(localDateTime1.getMinute());

        //withxxx():修改对象日期时间付给另一个对象(或自己)
        LocalDateTime localDateTime2 = localDateTime1.withDayOfMonth(7);
        localDateTime2 = localDateTime2.withHour(5);
        System.out.println(localDateTime2);//2022-04-07T05:24:35

        //plusxxx()：给对象日期时间增加时间
        localDateTime2 = localDateTime2.plusHours(5);
        //minusxxx()给对象日期时间减少时间
        localDateTime2 = localDateTime2.minusHours(5);
        
    }
}
