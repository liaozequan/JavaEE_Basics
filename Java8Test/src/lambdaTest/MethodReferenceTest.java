package lambdaTest;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 方法引用：当传递给lambda体的抽象方法，已经实现了，就可以使用方法引用
 * 1.方法引用本质上就是lambda表达式，也就是一个函数式接口的实例
 * 2.格式： 类（或对象） :: 方法名()
 * 3.具体分为3种情况：
 *  （1）对象 :: 非静态方法
 *  （2）类 :: 静态方法
 *  （3）类 :: 非静态方法
 *
 * 4.要求：需要函数式接口的抽象方法和已经实现的方法的 参数列表、返回值类型 一致才可以使用方法引用（针对于情况(1)(2)）
 */
public class MethodReferenceTest {
    /**
     * （1）对象 :: 非静态方法
     * Consumer<String>    形参列表String，无返回值
     * System.out.println()形参列表String，无返回值
     */
    @Test
    public void test1(){
        //lambda表达式
        Consumer<String> consumer = s -> System.out.println("lambda表达式"+s);
        consumer.accept("111");
        //方法引用
        PrintStream ps = System.out;
        //ps是System.out对象，println是对象的方法,参数类型在声明Consumer<String>时以泛型定义
        Consumer<String> consumer1 = ps :: println;
        consumer1.accept("方法引用" + "222");
    }
    /**
     * （2）类 :: 静态方法
     * Comparator<Integer>.compare 形参列表(Integer o1, Integer o2) 返回值int
     * Integer.compare             形参列表(Integer o1, Integer o2) 返回值int
     */
    @Test
    public void test2(){
        //一般写法
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        System.out.println(comparator.compare(10,9));

        //lambda表达式
        Comparator<Integer> comparator1 = (t1, t2) -> Integer.compare(t1, t2);
        System.out.println(comparator1.compare(12, 13));

        //方法引用
        Comparator<Integer> comparator2 = Integer::compare;
        System.out.println(comparator2.compare(15,14));
    }

    /**
     * （3）类 :: 非静态方法
     * Comparator<String>形参列表(String s1, String s2) 返回值int
     * String中的compareTo方法： s1.compareTo(s2)
     * s1对应于调用方法的对象，s2对应于方法的形参
     */
    @Test
    public void test3(){
        //lambda表达式
        Comparator<String> comparator1 = (s1, s2) -> s1.compareTo(s2);
        System.out.println(comparator1.compare("abc", "bcd"));

        //方法引用
        Comparator<String> comparator2 = String :: compareTo;
        System.out.println(comparator2.compare("abc", "bcd"));
    }
}
