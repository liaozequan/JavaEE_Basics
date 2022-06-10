package dateTest;

import org.junit.Test;

import java.util.Date;

/**
 * jdk8之前的时间和日期API测试
 * System.currentTimeMillis()
 * java.util.Date
 *        /---java.sql.Date
 */
public class DateTimeTest {
    public static void main(String[] args) {
        //1970/1/1 00:00:00 到现在的毫秒时间差
        long time = System.currentTimeMillis();
        System.out.println(time);
    }

    /**
     * java.util.Date
     *      /--java.sql.Date
     * 1.两个构造器的使用
     *      new Date()  当前时间的Date对象
     *      new Date(long time) 具体时间戳的Date对象
     * 2.两个方法的使用
     *      toString()  获取日期时间信息
     *      getTime() 获取时间戳
     * 3.java.util.Date --> java.sql.Date
     */
    @Test
    public void testDate(){
        //构造器1：new Date()
        Date date1 = new Date();    //获取当前时间的Date对象
        System.out.println(date1.toString());//Thu Apr 21 20:29:25 CST 2022
        System.out.println(date1.getTime());//1650544214820,时间戳

        //构造器2：new Date(long time)参数为时间戳
        Date date2 = new Date(1650544214820L);
        System.out.println(date2.toString());   //Thu Apr 21 20:30:14 CST 2022

        java.sql.Date date3 = new java.sql.Date(1650544214820L);
        System.out.println("数据库Date："+date3.toString());//2022-04-21 只有年月日

        //java.util.Date --> java.sql.Date
        Date date4 = new Date();
        java.sql.Date date5 = new java.sql.Date(date4.getTime());
    }
}
