package compareAndSwapCAS.atomicsPlus;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**LongAdder API测试
 * LongAccumulator API测试
 */
public class LongAdderAPI {
    public static void main(String[] args) {
        //初始化longAdder->value = 0
        LongAdder longAdder = new LongAdder();
        //value++
        longAdder.increment();
        longAdder.increment();
        longAdder.increment();
        //返回value
        System.out.println(longAdder.sum());

        //value初始设为0， 函数内表示每次value+一个值
        LongAccumulator longAccumulator = new LongAccumulator((value, plus)-> value + plus, 0);
        //调用accumulate(1)，将value+1
        longAccumulator.accumulate(1);
        //调用accumulate(3)，将value+3
        longAccumulator.accumulate(3);
        //value=4
        System.out.println(longAccumulator.get());
    }
}
