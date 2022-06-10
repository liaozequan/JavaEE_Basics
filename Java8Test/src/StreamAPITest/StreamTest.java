package StreamAPITest;

/**1.Stream关注的是对数据的运算，与CPU打交道
 *   而集合Collection关注的是数据存储的数据结构，与内存打交道
 *
 * 2.Stream自己不会存储元素
 *   Stream不会改变源数据，只会返回一个持有结果的新Stream
 *   Stream操作是延迟的，意味着Stream会等到需要结果的时候才执行
 *
 * 3.Stream执行流程 ①Stream实例化--②一系列中间操作（过滤、映射...）--③终止操作
 *  一旦执行终止操作，才会执行中间操作，并产生结果，之后不能再被使用
 *
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream的四种实例化方式
 */
public class StreamTest {
    /**
     * 实例化方式一：通过集合
     */
    @Test
    public void test1(){
        //实例化集合
        List<String> list = new ArrayList<>();
        //实例化Stream
        //stream()返回一个顺序流
        Stream<String> stringStream = list.stream();

        //stream()返回一个并行流
        Stream<String> parallelStream = list.parallelStream();
    }

    /**
     * 实例化方式二：通过数组
     */
    @Test
    public void test2(){
        //实例化数组
        int[] nums = new int[10];
        //实例化Stream
        //Arrays.stream(int[] arr)返回一个流
        IntStream intStream = Arrays.stream(nums);
    }

    /**
     * 实例化方式三：通过Stream.of()
     */
    @Test
    public void test3(){
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6);
    }

    /**
     * 实例化方式四：创建无限流（少用）
     */
    @Test
    public void test4(){
        //从0开始遍历前10个偶数
        Stream.iterate(0, t -> t+2).limit(10).forEach(System.out :: println);

        //输出10个随机数
        Stream.generate(Math::random).limit(10).forEach(System.out :: println);
    }
}
