package StreamAPITest;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**Stream终止操作
 * 归约
 */
public class StreamTest5 {
    @Test
    public void test(){
        //reduce(T identity, T BinaryOperator(T,T))--将流中元素反复结合起来，得到一个值，返回T
        //计算1-5总和
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        /**
         * identity： sum初始值为0，每次选择一个流中元素与sum做BinaryOperator操作（Integer::sum即加和再赋给sum）
         */
        Integer sum = integers.stream().reduce(0, Integer::sum);
        System.out.println("1-5总和:" + sum);

        //reduce(BinaryOperator(T,T))--将流中元素反复结合起来，得到一个值，返回Optional
        Optional<Integer> sum1 = integers.stream().reduce((e1, e2) -> e1 + e2);
        System.out.println("1-5总和:" + sum1);

    }
}
