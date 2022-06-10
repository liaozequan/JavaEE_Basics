package dateTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * DateTimeFormat:格式化或解析日期、时间
 * 类似于simpleDateFormat
 */
public class DateTimeFormatTest {
    public static void main(String[] args) {
        //自定义日期格式
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //格式化
        String date = dtf.format(LocalDateTime.now());
        System.out.println(date);//2022-04-22 20:44:12
        //解析
        TemporalAccessor ta = dtf.parse("2022-04-22 20:44:12");
        System.out.println(ta);
    }
}
