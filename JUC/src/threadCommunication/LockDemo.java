package threadCommunication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *lock接口实现线程通信
 */
class Sharer{
    private int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void incr() throws InterruptedException {
        //上锁
        lock.lock();
        try {
            while(num != 0){
                //如果不等于0，则阻塞
                condition.await();
            }
            num++;
            System.out.println("当前线程："+Thread.currentThread().getName() + ",num=" + num);
            //唤醒其他线程
            condition.signalAll();
        }finally {
            //释放锁
            lock.unlock();
        }
    }

    public void decr() throws InterruptedException {
        //上锁
        lock.lock();
        try {
            while(num != 1){
                //如果不等于1，则阻塞
                condition.await();
            }
            num--;
            System.out.println("当前线程："+Thread.currentThread().getName() + ",num=" + num);
            //唤醒其他线程
            condition.signalAll();
        }finally {
            //释放锁
            lock.unlock();
        }
    }
}
public class LockDemo {
    public static void main(String[] args) {
        Sharer sharer = new Sharer();
        new Thread(() -> {
            for(int i=0; i<=10; i++){
                try {
                    sharer.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for(int i=0; i<=10; i++){
                try {
                    sharer.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for(int i=0; i<=10; i++){
                try {
                    sharer.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for(int i=0; i<=10; i++){
                try {
                    sharer.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }

}
