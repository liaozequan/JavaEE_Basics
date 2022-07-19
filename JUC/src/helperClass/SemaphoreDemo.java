package helperClass;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * JUC辅助类-Semaphore（信号灯），类似于《操作系统》同步与互斥
 * 功能：new Semaphore(int num); 资源数：num
 * semaphore.acquire();抢占资源 资源数-1
 * semaphore.release();释放资源 资源数+1
 * 例子：6辆汽车，停到3个停车位
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        //3个锁，即3个车位
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                try {
                    //抢占资源,即抢占车位
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "号车抢到了车位");
                    //设置随机停车时间
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //释放资源，即离开车位
                    System.out.println(Thread.currentThread().getName() + "号车离开了车位");
                    semaphore.release();
                }
            }, String.valueOf(i)).start();

        }
    }
}
