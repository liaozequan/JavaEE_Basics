package helperClass;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * JUC辅助类-CyclicBarrier（循环栅栏）
 * 功能：new CyclicBarrier(int num, runnable)，当num个线程执行了cyclicBarrier.await()方法后
 * runnable中的方法才可能执行。也就是达到了数量条件后，才能执行
 * 例子：收集7颗龙珠才可以召唤神龙
 */
public class CyclicBarrierDemo {
    private static final int NUM = 7;

    public static void main(String[] args) {
        //创建CyclicBarrier
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUM, ()->{
            System.out.println("已集齐7颗龙珠，召唤神龙");
        });
        //集齐7颗龙珠的过程
        for (int i = 1; i <= 7; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"星龙珠已经收集");
                //等待
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
