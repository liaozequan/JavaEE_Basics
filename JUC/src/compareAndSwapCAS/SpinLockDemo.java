package compareAndSwapCAS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 使用CAS实现自旋锁
 * 要求：A线程先调用myLock方法持有锁5秒，B随后发现有线程持有锁，直到A释放锁B才能持有锁
 */
public class SpinLockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();
    public void lock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "::come in");
        //当一个进程调用lock()，发现atomicReference为null
        //说明没有线程持有锁，则自己获取锁，即将atomicReference设为自己的线程
        while (!atomicReference.compareAndSet(null, thread)) {
            //取到锁这返回true，锁被其他线程占用，没取到锁则返回false，则一直循环等待
        }
    }
    public void unLock(){
        Thread thread = Thread.currentThread();
        //当自己持有锁时atomicReference为自己的线程
        //使用将atomicReference设为null，表示释放锁
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "::over,unlock");
    }
    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(()->{
            //t1线程先获取锁
            spinLockDemo.lock();
            //5秒后
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //t1释放锁
            spinLockDemo.unLock();
        }, "t1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            spinLockDemo.lock();
            spinLockDemo.unLock();
        }, "t2").start();

    }
}
