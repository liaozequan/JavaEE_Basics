package threadSafety;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：两个储户分别向同一个账户存3000，一次存1000，分3次存，每次存完款打印余额
 */
public class ThreadTest1 {
    public static void main(String[] args) {
        Account account = new Account();
        Thread t1 = new Thread(account);
        Thread t2 = new Thread(account);
        t1.setName("储户1");
        t2.setName("储户2");
        t1.start();
        t2.start();
    }
}

class Account implements Runnable{
    private int balance = 0;
    private ReentrantLock lock = new ReentrantLock(true);
    @Override
    public void run() {
        for(int i=0; i<3; i++){
            try {
                lock.lock();
                deposit();
            }finally {
                lock.unlock();
            }
        }
    }

    public void deposit(){
        this.balance += 1000;
        System.out.println(Thread.currentThread().getName()+"操作后,当前余额为："+this.balance);
    }
}
