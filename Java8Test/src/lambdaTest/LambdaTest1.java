package lambdaTest;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**lambda表达式的使用
 * 1.(o1, o2) -> Integer.compare(o1, o2)
 * 2.格式：
 *      ->:lambda操作符
 *      ->的左边:lambda形参列表（就是接口中抽象方法的形参列表）
 *      ->的右边:lambda体（就是重写抽象方法的方法体）
 * 3.具体使用（6种情况的总结）
 *      ->的左边:形参列表可以省略数据类型(抽象方法已经定义过了，如果是泛型，编译期可以通过泛型定义的类型进行类型推断)，
 *              如果只有一个参数，可以省略()
 *      ->的右边:方法体如果只有一条语句，可以省略{}和return
 * 4.lambda本质：作为接口（要求函数式接口）的实例
 */
public class LambdaTest1 {
    /**
     * 语法格式一:无参、无返回值的抽象方法
     */
    @Test
    public void test1(){
        //Runnable接口中只有一个抽象方法run，run方法是无参、无返回值的
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("普通声明的run方法");
            }
        };
        r1.run();

        /**使用lambda表达式
         * 无参，->左边直接用 ()
         * 无返回值,->右边直接写run方法体中的内容
         */
        Runnable r2 = () -> {
            System.out.println("使用lambda表达式声明run方法");
        };
        r2.run();
    }

    /**
     * 语法格式二:一个参、无返回值的抽象方法
     */
    @Test
    public void test2(){
        //Consumer接口中只有一个抽象方法accept，accept方法需要传入一个参数、无返回值的
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        consumer.accept("普通声明的accept方法");

        /**使用lambda表达式
         * 一个参，->左边用 (String s) 表示传入一个形参
         * 无返回值,->右边直接写run方法体中的内容
         */
        Consumer<String> consumer1 = (String s) -> {
            System.out.println(s);
        };
        consumer1.accept("使用lambda表达式声明accept方法");
    }

    /**语法格式二的优化
     * 语法格式三:数据类型可以省略，因为可以由编译期推断得出，称为“类型推断”
     */
    @Test
    public void test3(){
        /**Consumer<String>泛型中已经声明了数据类型为string
         * ->左边括号内的形参可以省略类型
         */
        Consumer<String> consumer1 = (s) -> {
            System.out.println(s);
        };
        consumer1.accept("使用lambda表达式优化声明accept方法");
    }

    /**语法格式三的优化
     * 语法格式四:如果只需要一个形参时，参数的小括号可以省略
     */
    @Test
    public void test4(){
        //->左边直接 s
        Consumer<String> consumer1 = s -> {
            System.out.println(s);
        };
        consumer1.accept("使用lambda表达式优化声明accept方法");
    }

    /**
     * 语法格式五:需要两个及以上形参时，方法内多条执行语句，有返回值
     */
    @Test
    public void test5(){
        //普通声明
        Comparator<Integer> comparator =new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1);
                System.out.println(o2);
                return Integer.compare(o1, o2);
            }
        };
        System.out.println("普通声明Comparator "+ comparator.compare(12,21));

        /**使用lambda表达式
         * 多个参，->左边用 (String s) 表示传入多个形参
         * 有返回值,->右边直接写run方法体中的内容,返回值在调用方法的时候返回
         */
        Comparator<Integer> comparator1 = (o1, o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return Integer.compare(o1, o2);
        };
        System.out.println("使用lambda表达式声明Comparator "+ comparator1.compare(31,21));
    }

    /**语法格式五的优化
     * 语法格式六:如果方法体内只有一条语句，则return、{}都可以省略
     */
    @Test
    public void test6(){
        Comparator<Integer> comparator1 = (o1, o2) -> Integer.compare(o1, o2);
        System.out.println("使用lambda表达式声明Comparator "+ comparator1.compare(31,21));
    }
}
