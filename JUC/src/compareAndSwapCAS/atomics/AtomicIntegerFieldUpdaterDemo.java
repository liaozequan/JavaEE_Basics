package compareAndSwapCAS.atomics;

import helperClass.CountDownLatchDemo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

class BankAccount{
    String bankName = "BCC";
    //更新的对象属性必须由volatile修饰
    volatile int money = 0;
    //使用静态方法newUpdater()创建一个更新器，设置想要更新的类和属性
    AtomicIntegerFieldUpdater<BankAccount> fieldUpdater =
            AtomicIntegerFieldUpdater.newUpdater(BankAccount.class, "money");
    //对monkey局部加锁，实现线程安全
    public void incr(BankAccount bankAccount){
        //money++
        fieldUpdater.getAndIncrement(bankAccount);
    }
}

/**AtomicIntegerFieldUpdater:以线程安全的方式操作非线程安全对象内的某些字段
 * 需求：
 *  10个线程，每个线程转账1000
 *  不使用synchronized实现线程安全，使用AtomicIntegerFieldUpdater
 */
public class AtomicIntegerFieldUpdaterDemo {
    public static void main(String[] args) throws InterruptedException {
        BankAccount bankAccount = new BankAccount();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                try {
                    for (int j = 0; j < 1000; j++) {
                        bankAccount.incr(bankAccount);
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "::" + bankAccount.money);
    }
}
