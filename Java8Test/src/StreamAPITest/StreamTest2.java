package StreamAPITest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream中间操作
 *  映射
 */
public class StreamTest2 {
    @Test
    public void mapTest(){
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");

        //map()--映射，接收一个函数作为参数，将流中每个元素经过函数执行并返回到流中
        list.stream().map(str -> str.toUpperCase()).forEach(System.out :: println);

        //flatMap(function f)--接收一个函数作为参数，将流中的每一个元素都转换为一个流，再将所有流连接成一个流
        /**flatMap和map的区别
         * 1.使用map 调用fromStringToStream，会将("aa", "bb", "cc", "dd")中每个字符串都转换为一个流，最终返回是包含多个流的流
         * 2.使用flatMap,将所有流连接为一个流，返回的是一个流
         * 类似于list.add() 和list.addAll() 的区别，见testList()例子
         */

        Stream<Stream<Character>> streamStream = list.stream().map(StreamTest2::fromStringToStream);
        //遍历包含多个stream的stream
        streamStream.forEach(s -> {
            s.forEach(System.out :: println);
        });

        System.out.println("******************");
        Stream<Character> characterStream = list.stream().flatMap(StreamTest2::fromStringToStream);
        characterStream.forEach(System.out :: println);

    }

    //将一个字符串中的所有字符构建成集合，再将集合转换为stream
    public static Stream<Character> fromStringToStream(String str){
        List<Character> list = new ArrayList<>();
        for(Character c : str.toCharArray()){
            list.add(c);
        }
        return  list.stream();
    }

    @Test
    public void testList(){
        List l1 = Arrays.asList(1,2,3);
        List l2 = Arrays.asList(4,5,6);

        l1.add(l2); //结果为[1,2,3,[4,5,6]],l1长度为4

        l1.addAll(l2); //结果为[1,2,3,4,5,6],l1长度为6
    }
}
