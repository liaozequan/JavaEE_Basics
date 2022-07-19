package lockSupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**线程阻塞（通信）三种实现
 * 1.LockSupport
 * 2.synchronized：wait()\notify()
 * 3.lock:await()\signal()
 */
public class LockSupportDemo {
    public static void main(String[] args) {
        lockSupport();
    }

    /**LockSupport.park()/unPark()实现线程阻塞和唤醒（即线程通信）
     * park()查询当前线程是否有permit？
     *  如果没有则阻塞
     *  如果有则消耗掉这个permit，并继续执行
     * unPark(thread) 给线程一个permit
     *
     * 一个线程的permit是不能叠加的，最多只有一个
     */
    private static void lockSupport(){
        Thread t1 = new Thread(()->{
            System.out.println(Thread.currentThread().getName() + ":: come in");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + ":: 被唤醒");
        }, "t1");
        t1.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + ":: 唤醒其他阻塞线程");
        }, "t2").start();
    }

    /**使用lock和condition实现线程阻塞和唤醒（即线程通信）
     * 容易出现的问题：
     * 1.await()signal()方法必须在lock.lock()后(即线程必须持有锁)，不然会报异常
     * 2.必须先await()再signal()，不然会导致await的线程一直阻塞，无法唤醒
     */
    private static void lockCondition(){
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(()->{
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + ":: come in");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":: 被唤醒");
            }finally {
                lock.unlock();
            }
        }, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + ":: 唤醒其他阻塞线程");
            }finally {
                lock.unlock();
            }
        }, "t2").start();
    }

    /**使用Object类的wait()Notify()实现线程阻塞和唤醒（即线程通信）
     * 容易出现的问题：
     * 1.wait()Notify()方法必须在同步代码块或同步方法中使用(即线程必须持有锁)，不然会报异常
     * 2.必须先wait再notify，不然会导致wait的线程一直阻塞，无法唤醒
     */
    private static void waitNotify(){
        Object objectLock = new Object();
        new Thread(()->{
            synchronized (objectLock){
                System.out.println(Thread.currentThread().getName() + ":: come in");
                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":: 被唤醒");
            }
        }, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            synchronized (objectLock){
                //释放锁，通知唤醒其他线程
                objectLock.notify();
                System.out.println(Thread.currentThread().getName() + ":: 唤醒其他阻塞线程");
            }
        }, "t2").start();
    }
}
