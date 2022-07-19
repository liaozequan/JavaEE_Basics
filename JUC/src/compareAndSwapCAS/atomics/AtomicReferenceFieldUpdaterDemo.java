package compareAndSwapCAS.atomics;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * 多个线程实例化一个类
 */
class MyVar{
    public volatile Boolean isInit = false;
    AtomicReferenceFieldUpdater<MyVar, Boolean> referenceFieldUpdater =
            AtomicReferenceFieldUpdater.newUpdater(MyVar.class, Boolean.class, "isInit");

    public void init(MyVar myVar) throws InterruptedException {
        if (referenceFieldUpdater.compareAndSet(myVar, Boolean.FALSE, Boolean.TRUE)) {
            System.out.println(Thread.currentThread().getName() + "::start init, need 2s");
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName() + "::finish init");
        }else{
            System.out.println(Thread.currentThread().getName() + "::已有线程正在实例化");
        }
    }
}
public class AtomicReferenceFieldUpdaterDemo {
    public static void main(String[] args) {
        MyVar myVar = new MyVar();
        for (int i = 1; i <= 5; i++) {
            new Thread(()->{
                try {
                    myVar.init(myVar);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
