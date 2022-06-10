package StreamAPITest;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**Stream终止操作
 *匹配与查找
 */
public class StreamTest4 {

    @Test
    public void test1(){
        List<Integer> integers = Arrays.asList(11, 1, -5, 99, 5, -1, 0, 11);
        //allMatch(predicate p)--检查是否都匹配所有元素，下面代码：所有元素是否都大于10
        boolean allMatch = integers.stream().allMatch(num -> num > 10);
        System.out.println("流中元素是否都大于10？" + allMatch);

        //anyMatch(predicate p)--检查是否至少一个匹配的元素，下面代码：是否有一个元素大于10
        boolean anyMatch = integers.stream().anyMatch(num -> num > 10);
        System.out.println("流中元素是否有一个元素大于10？" + anyMatch);

        //noneMatch(predicate p)--检查是否没有匹配的元素，下面代码：是否没有元素10
        boolean noneMatch = integers.stream().noneMatch(num -> num == 10);
        System.out.println("流中元素是否没有元素10？" + noneMatch);

        //findFirst()--返回流中第一个元素
        Optional<Integer> first = integers.stream().findFirst();
        System.out.println("流中第一个元素：" + first);

        /**findAny()返回任意一个元素
         * count()返回流中元素个数
         * max(comparator c)按照给定比较方法返回最大值
         * min(comparator c)
         *
         */
    }
}
