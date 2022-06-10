package reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 利用反射调用运行时类的结构:属性、方法、构造器
 */
public class CallByReflectTest {
    /*
    操作属性
     */
    @Test
    public void test() throws Exception {
        Class clazz = Person.class;
        //实例化运行时类的对象
        Person p1 = (Person) clazz.newInstance();
        //获取指定属性,getDeclaredField要求属性是任意修饰，
        Field name = clazz.getDeclaredField("name");
        //保证当前属性可访问
        name.setAccessible(true);
        name.set(p1, "jake");
        System.out.println((String) name.get(p1));


        //获取指定属性,getField要求属性是public修饰，
        Field age = clazz.getField("age");
        //设置age属性的值 set(对象, 属性值)
        age.set(p1, 15);
        //获取指定对象的指定属性值 属性.get(对象)-->获取p1对象的age属性值
        System.out.println((int)age.get(p1));
    }

    /**
     * 操作方法
     */
    @Test
    public void test1() throws Exception{
        Class clazz = Person.class;
        //实例化运行时类的对象
        Person p1 = (Person) clazz.newInstance();
        //获取指定方法名getDeclaredMethod(方法名，方法的形参列表)
        Method getReturn = clazz.getDeclaredMethod("getReturn", String.class);
        //保证当前属性可访问
        getReturn.setAccessible(true);
        //invoke(对象, 该方法的实参),invoke方法的返回值，即为调用类方法的返回值
        Object retrunValue = getReturn.invoke(p1, "调用了getReturn方法的返回值");
        System.out.println((String) retrunValue);

        //调用运行时类中静态方法
        Method staticMethod = clazz.getDeclaredMethod("staticM");
        staticMethod.setAccessible(true);
        //调用静态方法时invoke方法中参数为类而不是对象,而类已经在第一行代码定义，参数为null也可以
        staticMethod.invoke(null);
    }

    /**
     * 调用运行时类中指定的构造器
     */
    @Test
    public void test2() throws Exception {
        Class clazz = Person.class;
        //getDeclaredConstructor()参数为指定构造器的参数列表
        Constructor constructor = clazz.getDeclaredConstructor(String.class, int.class);
        constructor.setAccessible(true);
        //利用构造器实例化对象
        Person p1 = (Person) constructor.newInstance("tom", 15);
        System.out.println(p1);
    }
}
