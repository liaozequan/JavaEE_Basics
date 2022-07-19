package interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 中断 3种实现
 * 1.volatile修饰的变量来充当中断标识符，决定一个线程是否要结束
 * 2.AtomicBoolean修饰的变量来充当中断标识符
 * 3.使用线程自己的中断标识符
 *      3.1 t1.isInterrupted() 判断t1线程标识符是否为true
 *      3.2 t1.interrupt() 将t1线程的线程标识符设置为true
 *      3.3 静态方法 t1.interrupted() 先返回t1线程的进程标识符，在将t1线程的进程标识符设为false
 *
 * 如果一个进程处于阻塞状态（如调用了sleep、wait、join），此时其他线程对该阻塞线程调用了interrupt()
 * 则该阻塞线程会立即停止阻塞状态，并抛出interruptException异常
 *
 * 不活动的线程，对他进行中断操作是无效的，即中断标识符一直是false
 */
public class InterruptDemo {
    //volatile用于标识多个线程共用的资源
    static volatile boolean isStop = false;
    //原子布尔型变量，可以用于多线程
    static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) {
        m3_Interrupt();
//        m2_AtomicBoolean();
//        m1_volatile();
    }

    /**
     * 线程自带中断标识符实现
     */
    private static void m3_Interrupt(){
        Thread t1 = new Thread(()->{
            while (true){
                //isInterrupted() 判断线程标识符是否为true
                if(Thread.currentThread().isInterrupted()){
                    System.out.println(Thread.currentThread().getName() + "::isInterrupted()被修改为true,线程停止");
                    break;
                }
                System.out.println("hello");
            }
        }, "t1");
        t1.start();
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        t1.interrupt();  t1自己将自己的中断标准位设为true
        new Thread(()->{
            //t2线程将t1线程的中断标准位设为true
            t1.interrupt();
        }, "t2").start();
    }

    /***
     * 中断实现二：AtomicBoolean
     */
    private static void m2_AtomicBoolean(){
        new Thread(()->{
            while (true){
                if(atomicBoolean.get()){
                    System.out.println(Thread.currentThread().getName() + "::isStop被修改为true,线程停止");
                    break;
                }
                System.out.println("hello");
            }
        }, "t1").start();
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            atomicBoolean.set(true);
        }, "t2").start();
    }

    /**
     * 中断实现一：volatile
     */
    private static void m1_volatile(){
        new Thread(()->{
            while (true){
                if(isStop){
                    System.out.println(Thread.currentThread().getName() + "::isStop被修改为true,线程停止");
                    break;
                }
                System.out.println("hello");
            }
        }, "t1").start();
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            isStop = true;
        }, "t2").start();
    }
}
