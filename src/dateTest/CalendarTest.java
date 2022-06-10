package dateTest;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Calendar日历类的使用
 */
public class CalendarTest {
   @Test
    public void test1(){
       /**1.实例化
        * 方式一：创建其子类（GregorianCalendar）对象
        * 方式二：调用其静态方法getInstance()
        */
       Calendar calendar1 = Calendar.getInstance();
       System.out.println(calendar1.getClass());//class java.util.GregorianCalendar,实际还是创建子类

       Calendar calendar2 = new GregorianCalendar();
       //常用方法
       //get()
       System.out.println(calendar1.get(Calendar.DAY_OF_MONTH));//这个月的第几天
       //set()
       calendar1.set(Calendar.DAY_OF_MONTH, 9);//把对象中这个月第几天的值改了
       System.out.println(calendar1.get(Calendar.DAY_OF_MONTH));
       //add()
       calendar1.add(Calendar.DAY_OF_MONTH, -8);
       System.out.println(calendar1.get(Calendar.DAY_OF_MONTH));
       //getTime()
       Date date1 = calendar1.getTime();
       System.out.println(date1);
       //setTime()
       calendar1.setTime(date1);//通过date对象来设置

   }
}
