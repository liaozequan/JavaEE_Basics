package reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 在使用反射之前，在类的外部，无法调用类的私有属性\构造器\方法
 * 下面的代码，利用反射，可以直接调用类中的私有
 * 1.那么new和反射都可以实例化对象，为什么要用反射呢？
 *  反射特性：动态性。如web中登入和注册，当用户选择登入，java就利用反射造登入对象，用户选择注册，java就利用反射造注册对象，体现了动态性
 * 2.反射机制和封装性是否矛盾？
 *
 * Class类的理解：
 *  1.类加载过程：
 *      源程序通过javac.exe 命令后，生成一个或多个字节码文件（.class结尾）,接着使用java.exe命令对字节码文件进行解释运行。
 *      解释运行相当于将字节码文件加载到内存中（称为类的加载）。加载到内存中的类（称为运行时类），此运行时类，就作为Class类的一个实例
 *      因此Class也就是类的类，一个运行时类就是Class的对象。
 *
 */
public class ReflectTest {
    //使用反射实例化Person对象
    @Test
    public void test() throws Exception {
        Class clazz = Person.class;
        //构造器
        Constructor cons = clazz.getConstructor(String.class, int.class);
        //1.通过上面声明的构造器来实例化对象
        Object o = cons.newInstance("tom", 12);
        System.out.println(o);
        Person person = (Person) o;
        //2.通过反射，调用对象的属性和方法
        //获取Person类中的age属性
        Field age = clazz.getDeclaredField("age");
        //将person对象的age属性改为10
        age.set(person, 10);
        System.out.println(person);

        //调用公共方法
        //获取Person类中的公共show方法,如果show被多次重载，则可以在括号中指定形参列表
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(person);

        //Person类的私有构造器
        Constructor privateCons = clazz.getDeclaredConstructor(String.class);
        privateCons.setAccessible(true);
        //利用私有构造器实例化
        Person p = (Person) privateCons.newInstance("jack");
        System.out.println(p);
        //私有属性
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(p, "jane");
        System.out.println(p);
        //私有方法
        Method privateM = clazz.getDeclaredMethod("privateM", String.class);
        privateM.setAccessible(true);
        privateM.invoke(p, "aaa");


    }
}
