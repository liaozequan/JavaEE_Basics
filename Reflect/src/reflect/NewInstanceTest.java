package reflect;

import org.junit.Test;

import java.util.Random;

/**
 * 通过反射创造运行时类对象
 */
public class NewInstanceTest {
    @Test
    public void test() throws InstantiationException, IllegalAccessException {
        Class<Person> clazz = Person.class;
        /**newInstance(),调用此方法，实例化对应的运行时类的对象
         * 实际上还是调用Person的空参构造器
         * 使用此方法要求：
         *  1.运行时类有空参构造器
         *  2.构造器权限，一般为public
         *
         * javabean中要求提供一个public的空参构造器
         * 1.便于反射，创建运行时类
         * 2.便于子类继承该运行时类，默认调用super()时，保证父类有此空参构造器
         */
        Person p1 = clazz.newInstance();
        System.out.println(p1);

    }

    /**反射的动态性
     * 运行时才决定创建哪个对象
     */
    @Test
    public void test2() throws Exception {
        int num = new Random().nextInt(2);//0,1
        String classPath = "";
        if(num == 0){
            classPath = "reflect.Person";
        }else{
            classPath = "java.util.Date";
        }
        Object o = getInstance(classPath);
        System.out.println(o);
    }

    /**
     * 创建一个指定类的对象
     * @param classPath 类的路径（全类名）
     */
    public Object getInstance(String classPath) throws Exception {
        Class clazz = Class.forName(classPath);
        return clazz.newInstance();
    }
}
