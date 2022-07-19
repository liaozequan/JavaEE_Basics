package threadCommunication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程定制化通信
 * 要求：A线程输出5次-->B线程输出10次-->C线程输出15次
 * 实现：设置flag=1，A线程判断flag是否=1，是则输出5次后，设置flag=2，唤醒B线程
 *      B判断flag是否=2，是则输出10次后，设置flag=3，唤醒C线程
 *      B判断flag是否=3，是则输出15次后，设置flag=1，唤醒A线程
 */
//创建资源类
class ShareResource{
    private int flag=1;  //A 1  B 2  C 3
    private Lock lock = new ReentrantLock();
    //创建3个condition,类似3把锁分别给ABC线程
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    //A线程打印5次
    public void print5(int loop) throws InterruptedException {
        //上锁
        lock.lock();
        try {
            //判断是否阻塞
            while(flag != 1) {
                c1.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "::第"+ loop + "轮，第" + i + "次打印");
            }
            //改flag
            flag = 2;
            //唤醒c2（即B线程）
            c2.signal();
        }finally {
            //释放锁
            lock.unlock();
        }
    }

    //B线程打印10次
    public void print10(int loop) throws InterruptedException {
        //上锁
        lock.lock();
        try {
            //判断是否阻塞
            while(flag != 2) {
                c2.await();
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "::第"+ loop + "轮，第" + i + "次打印");
            }
            //改flag
            flag = 3;
            //唤醒c3（即C线程）
            c3.signal();
        }finally {
            //释放锁
            lock.unlock();
        }
    }

    //C线程打印15次
    public void print15(int loop) throws InterruptedException {
        //上锁
        lock.lock();
        try {
            //判断是否阻塞
            while(flag != 3) {
                c3.await();
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "::第"+ loop + "轮，第" + i + "次打印");
            }
            //改flag
            flag = 1;
            //唤醒c1（即A线程）
            c1.signal();
        }finally {
            //释放锁
            lock.unlock();
        }
    }
}

public class ThreadCustomizdCommu {
    public static void main(String[] args) {
        ShareResource sr = new ShareResource();
        new Thread(()->{
            for (int i = 1; i < 4; i++) {
                try {
                    sr.print5(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(()->{
            for (int i = 1; i < 4; i++) {
                try {
                    sr.print10(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(()->{
            for (int i = 1; i < 4; i++) {
                try {
                    sr.print15(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

    }
}
