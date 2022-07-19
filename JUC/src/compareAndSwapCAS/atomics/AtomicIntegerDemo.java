package compareAndSwapCAS.atomics;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**AtomicInteger使用
 * 配合CountDownLatch实现检测线程完成任务
 *
 */
class MyNumber{
    AtomicInteger atomicInteger = new AtomicInteger();
    public void incr(){
        //getAndIncrement()返回当前值，并+1
        atomicInteger.getAndIncrement();
    }
}
public class AtomicIntegerDemo {
    //50个线程
    public static final int SIZE = 50;

    public static void main(String[] args) throws InterruptedException {
        MyNumber myNumber = new MyNumber();
        //辅助类，设置50为50个线程
        CountDownLatch countDownLatch = new CountDownLatch(50);
        //50个线程对atomicInteger分别+1 1000次
        for (int i = 1; i <= SIZE; i++) {
            new Thread(()->{
                try {
                    for (int j = 0; j < 1000; j++) {
                        myNumber.incr();
                    }
                } finally {
                    //每个线程执行完，countDownLatch-1
                    countDownLatch.countDown();
                }
            }, String.valueOf(i)).start();
        }
        //等待50个线程全部计算完
        //不希望人工手写代码等待任务完成，因为无法计算任务什么时候完成
//        TimeUnit.SECONDS.sleep(2);
        //使用辅助类countDownLatch,一个线程完成任务countDownLatch-1
        // 等待countDownLatch值为0
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "::" + myNumber.atomicInteger.get());
    }
}
