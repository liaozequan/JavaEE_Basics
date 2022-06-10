package reflect;

import org.junit.Test;

/**
 * Class类的理解：
 *  1.类加载过程：
 *      源程序通过javac.exe 命令后，生成一个或多个字节码文件（.class结尾）,接着使用java.exe命令对字节码文件进行解释运行。
 *      解释运行解相当于将字节码文件加载到内存中（称为类的加载）。加载到内存中的类（称为运行时类），此运行时类，就作为Class类的一个实例
 *      因此Class也就是类的类，一个运行时类就是Class的对象。
 *  2.加载到内存中的运行时类，会缓存一段时间，在此时间内，用不同方式获取到的此运行时类，都是同一个
 */
public class GetClassTest {
    /**
     * 获取Class类实例的4种方法
     */
    @Test
    public void test1() throws ClassNotFoundException {
        //方式一：调用运行时类的属性：.class
        Class clazz1 = Person.class;

        //方式二：通过运行时类的对象
        Person p1 = new Person();
        Class clazz2 = p1.getClass();//getClass()返回p1对象是有哪个类造的

        //方式3：调用Class的静态方法：forName(String classPath) classPath类的完整路径
        Class clazz3 = Class.forName("reflect.Person");

        System.out.println(clazz1 == clazz2);//true
        System.out.println(clazz1 == clazz3);//true,说明都获取内存中同一个运行时类

        //方式4：使用类的加载器：classLoader(了解)
        //得到系统类加载器，所有自定义类的加载器都是系统类加载器，因此通过一个自定义类（Student）得到系统类加载器，
        //用这个加载器可以加载其他自定义类（Person）
//        ClassLoader classLoader = Student.class.getClassLoader();
//        Class clazz4 = classLoader.loadClass("reflect.Person");
//        System.out.println(clazz1 == clazz4);//true
    }
}
