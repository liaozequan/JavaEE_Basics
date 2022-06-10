package wrapper;

import org.junit.Test;

/**
 * 包装类的使用：
 *  1.java提供8种基本数据类型，包装类使基本数据类型有了类的特征（能调用方法等）
 *  2.基本数据类型、包装类、String的相互转换
 */
public class WrapperTest {

    /**
     * 基本数据类型--->包装类
     */
    @Test
    public void test1(){
        int num1 = 10;
        //可以用int型，string型来实例化integer
        Integer in1 = new Integer(num1);
        System.out.println(in1);
        Integer in2 = new Integer("123");
        System.out.println(in2);

        //不能混合 数字和字母 来实例化integer，会抛出异常
//        Integer in3 = new Integer("123abc");
//        System.out.println(in3);
        Float f1 = new Float(1);
        System.out.println(f1);

        Boolean b1 = new Boolean("true");
        Boolean b2 = new Boolean(true);
        //构造器中不是"true"的都算false
        Boolean b3 = new Boolean("true123");
        System.out.println(b3); //输出false

    }

    /***
     * 包装类--->基本数据类型
     */
    @Test
    public void test2(){
        Integer i1 = new Integer(12);
        int i2 = i1.intValue();
        int i3 = i1.byteValue();
        System.out.println(i3);
    }

    /**JDK5.0新特性
     * 自动装箱与自动拆箱
     */
    @Test
    public void test3(){
        int num1 = 213;
        //自动装箱，不需要new Integer()
        Integer i1 = num1;

        //自动拆箱，直接将Integer对象赋值给int基本数据类型
        int num2 = i1;
        System.out.println(num2);
    }

    /**
     * 基本数据类型、包装类--->String
     */
    @Test
    public void test4(){
        /**
         *1.直接拼接空串，所有基本数据类型和包装类都会转string
         */
        boolean b1 = false;
        Boolean b2 = true;
        String s1 = "" + b1;
        String s2 = "" + b2;
        System.out.println(s1);
        System.out.println(s2 instanceof String);
        /**
         * 2.String.valueOf()
         */
        Float f1 = 123.3f;
        String s3 = String.valueOf(f1);
        System.out.println(s3 instanceof String);
    }

    /**
     *  String--->基本数据类型、包装类
     *  对应基本数据类型的包装类的方法: parseInt()\parseFloat().....
     */
    @Test
    public void test5(){
        String s1 = "1234";
        int num1 = Integer.parseInt(s1);

        float f1 = Float.parseFloat(s1);

        Float f2 = Float.parseFloat(s1);

        boolean b1 = Boolean.parseBoolean(s1);

        Order o1 = new Order();
        System.out.println(o1.id);

    }
}

class Order{
    int id = 3;
    {
        id = 4;
    }
}
