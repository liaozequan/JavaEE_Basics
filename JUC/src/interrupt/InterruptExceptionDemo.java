package interrupt;

import java.util.concurrent.TimeUnit;

/**
 * t1先sleep，t2在中断t1，抛出一次InterruptException，循环输出
 * t1自己设置自己的中断标识符为true，自己在sleep，循环抛出InterruptException和输出
 * 原因：
 * 1.如果一个进程处于阻塞状态（如调用了sleep、wait、join），此时其他线程对该阻塞线程调用了interrupt()
 * 则该阻塞线程会立即停止阻塞状态，并抛出interruptException异常，并将中断表示符设为false
 * 2.如果一个线程的中断标识符为true，这个线程再阻塞（如调用sleep、wait、join），
 * 则该阻塞线程会立即停止阻塞状态，并抛出interruptException异常，并将中断表示符设为false
 */
public class InterruptExceptionDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            while (true){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println(Thread.currentThread().getName() + "::中断标识位为ture");
                    break;
                }
                //Thread.currentThread().interrupt();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //解决方案
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "::正常执行");
            }
        }, "t1");
        t1.start();
        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            t1.interrupt();
        }, "t2").start();
    }
}
