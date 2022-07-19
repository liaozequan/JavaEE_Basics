package compareAndSwapCAS;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger num = new AtomicInteger(5);
        //compareAndSet(期望值，更新值) 如果num的值于期望值相等，这将num修改为更新值
        System.out.println(num.compareAndSet(5, 2022) +"--"+ num.get());
        System.out.println(num.compareAndSet(5, 2022) +"--"+ num.get());
    }
}
