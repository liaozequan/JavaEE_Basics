package threadSafety;

import java.util.concurrent.locks.ReentrantLock;

/**解决线程安全问题5
 * 使用lock解决 实现runnable接口出现的线程安全问题
 */
public class ThreadSafeLock {
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
    //1.实例化lock
    private ReentrantLock lock = new ReentrantLock(true);
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