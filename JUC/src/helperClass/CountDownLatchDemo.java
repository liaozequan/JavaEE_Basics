package helperClass;

import java.util.concurrent.CountDownLatch;

/**
 * JUC辅助类-CountDownLatch
 * 功能：new CountDownLatch(int num);提供一个总数为num的计数器
 * countDownLatch.countDown();使计数器总值 -1
 * countDownLatch.await();当计数器总值 ！=0 时，调用该方法当前线程阻塞，知道计数器总值=0，当前线程被唤醒
 * 例子：6个同学陆续离开教室后，班长才锁门
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        /**
         * 不使用CountDownLatch，会出现问题，可能班长先锁门，同学才离开
         */
//        for (int i = 1; i < 6; i++) {
//            new Thread(()->{
//                System.out.println(Thread.currentThread().getName()+"号同学离开教室");
//            }, String.valueOf(i)).start();
//        }
//        System.out.println(Thread.currentThread().getName()+"班长锁门");
        /**
         * 使用CountDownLatch
         */
        //创建CountDownLatch,设置初始值:6个同学
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"号同学离开教室");
                //计数器 -1
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        //await()：当计数器 ！=0 时就一直阻塞，知道=0才唤醒
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"班长锁门");

    }
}
