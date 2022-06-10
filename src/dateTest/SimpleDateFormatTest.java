package dateTest;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * SimpleDateFormat:对日期的格式化和解析
 * 1.格式化：日期--->字符串
 * 2.解析:字符串--->日期
 */
public class SimpleDateFormatTest {
    @Test
    public void test1() throws ParseException {
        //实例化SimpleDateFormat对象：使用默认构造器
        SimpleDateFormat sdf = new SimpleDateFormat();
        //格式化：日期--->字符串
        Date date1 = new Date();
        System.out.println(date1);//Thu Apr 21 21:19:10 CST 2022
        String sdate1 = sdf.format(date1);
        System.out.println(sdate1);//22-4-21 下午9:19

        //解析：字符串--->日期
        String sdate2 = "22-4-21 下午9:24";//必须是这种格式（不好用）
        Date date2 = sdf.parse(sdate2);
        System.out.println(date2);
    }
    @Test
    public void test2() throws ParseException {
        //实例化SimpleDateFormat对象：使用自定义格式的构造器
        //月用M，分用m，不会混淆,hh是12小时制，HH是24小时制
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sdate1 = "2022-04-21 21:30:33";
        //解析
        Date date1 = sdf.parse(sdate1);
        System.out.println(date1);
        //格式化
        String sdate2 = sdf.format(date1);
        System.out.println(sdate2);
    }
}
