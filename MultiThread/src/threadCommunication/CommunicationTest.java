package threadCommunication;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程通信例子：使用两个线程打印1-100，线程1线程2交替打印
 * wait()使得调用该方法的线程进入阻塞状态
 * notify()唤醒被wait的一个线程，如果多个线程被wait，则唤醒优先级高的那个
 * notifyAll()唤醒所有被wait的线程
 * 此方法实现线程通信，只能应用于同步代码块和同步方法，不能应用在lock中
 * wait()notify()notifyAll()这三个方法的调用者必须是同步代码块/同步方法的同步监视器
 */
public class CommunicationTest {
    public static void main(String[] args) {
        NumberPrint numberPrint = new NumberPrint();
        Thread t1 = new Thread(numberPrint);
        Thread t2 = new Thread(numberPrint);
        t1.setName("线程1");
        t2.setName("线程2");
        t1.start();
        t2.start();
    }

}

class NumberPrint implements Runnable{
    private int num = 1;
    @Override
    public void run() {
        while(num <= 100){
            synchronized(this){
                this.notify();   //唤醒被阻塞的线程
                if (num <= 100){
                    System.out.println(Thread.currentThread().getName() + ":" + num);
                    num++;
                    try {
                        //使得调用wait()方法的线程进入阻塞状态，并释放锁
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
