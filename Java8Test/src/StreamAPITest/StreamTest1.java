package StreamAPITest;

import org.junit.Test;

import java.util.stream.Stream;

/**
 * Stream中间操作
 *  筛选与切片
 */
public class StreamTest1 {
    @Test
    public void test1(){
        Stream<Integer> integerStream = Stream.of(1, 29, 3, 4, 99, 11);
        //过滤：filter(predicate p) 接收lambda，从流中排除某些元素
        //只取大于20的元素，
        integerStream.filter(num -> num > 20).forEach(System.out :: println);


        //截断流limit(n)--只取前n个元素
        //因为integerStream已经在上面执行了终止操作，因此无法再使用，需要重新实例化stream
        Stream.of(1, 29, 3, 4, 99, 11).limit(3).forEach(System.out :: println);

        //skip(n)--跳过n个元素，返回一个去除前n个元素的流，如果流中元素不足n个,则返回空
        Stream.of(1, 2, 3, 4, 5, 6).skip(6).forEach(System.out :: println);

        //distinct()--筛选，通过流生成元素的hashcode()和equal()，利用他们来去重,重复的只保留第一个
        Stream.of(1, 99, 99, 4, 99, 11).distinct().forEach(System.out :: println);
    }
}
