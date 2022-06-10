package StreamAPITest;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**Stream终止操作
 * 收集:将流转换为其他数据结构
 */
public class StreamTest6 {
    @Test
    public void test(){
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> collect = integers.stream().filter(e -> e > 3).collect(Collectors.toList());
        collect.forEach(System.out :: println);
    }

}
