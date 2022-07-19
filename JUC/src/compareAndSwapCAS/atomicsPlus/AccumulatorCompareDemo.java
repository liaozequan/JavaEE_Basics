package compareAndSwapCAS.atomicsPlus;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

class ClickNumber{
    int num = 0;
    //使用synchronized实现多线程并发安全
    public synchronized void clickBySynchronized(){
        num++;
    }
    //使用AtomicLong实现点赞数计算
    AtomicLong atomicLong = new AtomicLong(0);
    public void clickByAtomicLong(){
        atomicLong.getAndIncrement();
    }
    //使用LongAdder实现点赞数计算
    LongAdder longAdder = new LongAdder();
    public void clickByLongAdder(){
        longAdder.increment();
    }
    //使用LongAccumulator实现点赞数计算
    LongAccumulator longAccumulator = new LongAccumulator((x, y)-> x+y, 0);
    public void clickBylongAccumulator(){
        longAccumulator.accumulate(1);
    }

}

/**
 * 50个线程，每个线程点赞100w次
 */
public class AccumulatorCompareDemo {
    public static final int count = 10000;
    public static final int threadNum = 50;

    public static void main(String[] args) throws InterruptedException {
        ClickNumber clickNumber = new ClickNumber();
        long startTime;
        long endTime;
        CountDownLatch countDownLatch = new CountDownLatch(50);
        startTime = System.currentTimeMillis();
        for (int i = 1; i <= threadNum; i++) {
            new Thread(()->{
                try {
                    for (int j = 0; j < 100*count; j++) {
                        clickNumber.clickBylongAccumulator();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        endTime = System.currentTimeMillis();
        System.out.println("cost time:" + (endTime - startTime) + "ms :: " + clickNumber.longAccumulator.get());
        //synchronized：3215ms
        //clickByAtomicLong:1376ms
        //clickByLongAdder():235ms
        //clickBylongAccumulator:221ms
    }
}
