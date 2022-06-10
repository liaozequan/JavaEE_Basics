package StreamAPITest;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Stream中间操作
 *  排序
 */
public class StreamTest3 {
    @Test
    public void test(){
        List<Integer> integers = Arrays.asList(11, 1, -5, 99, 5, -1, 0, 11);
        //自然排序
        integers.stream().sorted().forEach(System.out :: println);

        //定制排序:从大到小
        integers.stream().sorted((e1, e2) -> {
            return -Integer.compare(e1, e2);
        }).forEach(System.out :: println);

    }
}
