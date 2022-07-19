package sync;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁和非公平锁
 * 1.公平锁：多个线程都可以获得资源，不会出现线程饿死
 * 线程的切换设计上下文的切换，cpu效率低
 * 2.非公平锁：多个线程中，可能只有一个线程一直获得资源，其他线程饿死
 * 较少的切换线程，减少了cpu的开销，效率高
 */
public class FairORnoFairSync {
    public static void main(String[] args) {
        Window5 w = new Window5();
        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);
        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");
        t1.start();
        t2.start();
        t3.start();
    }

}
class Window5 implements Runnable{
    private int ticket = 100;
    //new ReentrantLock(true) true为公平锁| false为非公平锁（默认）
    private ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        while(ticket>0) {
            try {
                //2.调用lock(),加锁
                lock.lock();
                if(ticket>0){
                    System.out.println(Thread.currentThread().getName() + "卖出了第：" + ticket + "号票");
                    ticket--;
                }
            } finally {
                //3.调用unlock()，解锁
                lock.unlock();
            }
        }

    }
}